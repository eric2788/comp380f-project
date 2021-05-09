package ouhk.comps380f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ouhk.comps380f.repository.ShopItemRepository;

@Controller
@RequestMapping("/")
public class IndexController {

    private final ShopItemRepository shopItemRepository;

    @Autowired
    public IndexController(ShopItemRepository shopItemRepository) {
        this.shopItemRepository = shopItemRepository;
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
}
