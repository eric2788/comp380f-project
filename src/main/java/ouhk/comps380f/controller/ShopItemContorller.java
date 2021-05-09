package ouhk.comps380f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import ouhk.comps380f.dao.Account;
import ouhk.comps380f.dao.ShopItem;
import ouhk.comps380f.repository.ShopItemRepository;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("item")
public class ShopItemContorller {
    
    private final ShopItemRepository shopItemRepository;

    @Autowired
    public ShopItemContorller(ShopItemRepository shopItemRepository) {
        this.shopItemRepository = shopItemRepository;
    }

    @GetMapping("add")
    public String edit(ModelMap modelMap) {
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("item", new ShopItem());
        return "item";
    }

    @GetMapping("")
    public String view(ModelMap modelMap, @RequestParam("id") String id) {
        modelMap.addAttribute("mode", "view");
        modelMap.addAttribute("item", shopItemRepository.findById(id));
        return "item";
    }

    @GetMapping("edit")
    public String edit(ModelMap modelMap, @RequestParam("id") String id) {
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("item", shopItemRepository.findById(id));
        return "item";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("id") String id) {
        shopItemRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("update")
    public String postUser(@ModelAttribute("item") ShopItem shopItem, @RequestParam("files") MultipartFile[] files) throws IOException {
        System.out.println("preparing to save: " + shopItem.toString());
        shopItemRepository.save(shopItem);
        return "redirect:/item?id="+shopItem.getId();
    }

}
