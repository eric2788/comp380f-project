package ouhk.comps380f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ouhk.comps380f.dao.Account;
import ouhk.comps380f.repository.ShopItemRepository;

@Controller
@RequestMapping("/item")
public class ShopItemContorller {
    
    private final ShopItemRepository shopItemRepository;

    @Autowired
    public ShopItemContorller(ShopItemRepository shopItemRepository) {
        this.shopItemRepository = shopItemRepository;
    }
    
    @GetMapping(value = {""})
    public String view(ModelMap modelMap, @RequestParam("id") String id) {
        modelMap.addAttribute("mode", "view");
        modelMap.addAttribute("item", shopItemRepository.findById(id));
        return "item";
    }
}
