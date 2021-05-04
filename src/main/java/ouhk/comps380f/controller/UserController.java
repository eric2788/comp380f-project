package ouhk.comps380f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ouhk.comps380f.dao.Account;
import ouhk.comps380f.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {


    private final UserRepository userRepository;

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

    @GetMapping("edit")
    public String update(ModelMap modelMap, @RequestParam("id") String id) {
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("user", userRepository.findById(id));
        return "user";
    }

    @GetMapping("view")
    public String view(ModelMap modelMap, @RequestParam("id") String id) {
        modelMap.addAttribute("mode", "view");
        modelMap.addAttribute("user", userRepository.findById(id));
        return "user";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("id") String id) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }

    @PostMapping("update")
    public String postUser(Account account) {
        System.out.println("preparing to save: " + account.toString());
        userRepository.save(account);
        return "redirect:/user";
    }
}
