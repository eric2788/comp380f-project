package ouhk.comps380f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ouhk.comps380f.dao.Account;
import ouhk.comps380f.exception.CustomException;
import ouhk.comps380f.repository.ShopItemRepository;
import ouhk.comps380f.repository.UserRepository;
import ouhk.comps380f.service.AuthService;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class IndexController {

    private final ShopItemRepository shopItemRepository;
    private final AuthService authService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public IndexController(ShopItemRepository shopItemRepository, AuthService authService) {
        this.shopItemRepository = shopItemRepository;
        this.authService = authService;
    }

    @GetMapping
    public String getIndex(ModelMap modelMap) {
        modelMap.addAttribute("items", shopItemRepository.findAll());
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public ModelAndView register() {
        return new ModelAndView("register", "user", new Account());
    }

    @PostMapping("register")
    public String createUser(Account account) {
        if (!authService.register(account)) {
            throw new CustomException("username already exist");
        }
        return "redirect:/login?registered";
    }

    @GetMapping("profile")
    public String profile(Authentication authentication, ModelMap modelMap){
        Account account = authService.validateLogon(authentication);
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("user", account);
        return "user";
    }

    @PostMapping("profile")
    public ModelAndView updateProfile(Authentication authentication, Account account){
        Account logging = authService.validateLogon(authentication);
        if (!logging.getUsername().equals(account.getUsername())) throw new CustomException("profile username not the same");
        userRepository.save(account);
        return new ModelAndView("user", "user", account);
    }
}
