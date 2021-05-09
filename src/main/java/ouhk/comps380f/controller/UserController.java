package ouhk.comps380f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ouhk.comps380f.dao.Account;
import ouhk.comps380f.repository.UserRepository;
import ouhk.comps380f.service.AuthService;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("users", userRepository.findAll());
        return "userlist";
    }

    @GetMapping("add")
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("mode", "add");
        modelMap.addAttribute("user", new Account());
        return "user";
    }

    @GetMapping("edit/{id}")
    public String update(ModelMap modelMap, @PathVariable("id") String id) {
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("user", userRepository.findById(id));
        return "user";
    }

    @GetMapping("{id}")
    public String view(ModelMap modelMap, @PathVariable("id") String id) {
        modelMap.addAttribute("mode", "view");
        modelMap.addAttribute("user", userRepository.findById(id));
        return "user";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") String id) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }

    @PostMapping({"edit/{id}", "add"})
    public String postUser(Account account) {
        System.out.println("preparing to save: " + account.toString());
        account.setPassword("{noop}".concat(account.getPassword()));
        userRepository.save(account);
        return "redirect:/user/"+account.getUsername();
    }
}
