package ouhk.comps380f.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import ouhk.comps380f.dao.Photo;
import ouhk.comps380f.dao.ShopItem;
import ouhk.comps380f.repository.PhotoRepository;
import ouhk.comps380f.repository.ShopItemRepository;
import ouhk.comps380f.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("item")
public class ShopItemController {

    private final PhotoRepository photoRepository;
    private final ShopItemRepository shopItemRepository;
    private final ValidateService validateService;
    private final Logger logger = LoggerFactory.getLogger(ShopItemController.class);

    @Autowired
    public ShopItemController(ShopItemRepository shopItemRepository, PhotoRepository photoRepository, ValidateService validateService) {
        this.shopItemRepository = shopItemRepository;
        this.photoRepository = photoRepository;
        this.validateService = validateService;
    }

    @GetMapping("add")
    public String edit(ModelMap modelMap) {
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("item", new ShopItem());
        return "item";
    }

    @GetMapping("{id}")
    public String view(ModelMap modelMap, @PathVariable("id") int id) {
        logger.info("photos: "+photoRepository.count());
        modelMap.addAttribute("mode", "view");
        modelMap.addAttribute("item", validateService.validateExist(shopItemRepository, id, "item with id "+id+" not exist."));
        return "item";
    }

    @GetMapping("edit/{id}")
    public String edit(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("mode", "edit");
        modelMap.addAttribute("item", validateService.validateExist(shopItemRepository, id, "item with id "+id+" not exist."));
        return "item";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        shopItemRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping(value = {"add", "edit/{id}"})
    public String postItem(@ModelAttribute("item") ShopItem shopItem) {
        logger.info("preparing to save: " + shopItem.toString());
        logger.info("files count: "+shopItem.getFiles());
        for (MultipartFile file : shopItem.getFiles()) {
            try{
                logger.info("file "+file.getOriginalFilename()+", size: "+file.getSize());
                Photo p = new Photo();
                p.setItem(shopItem);
                p.setContent(file.getBytes());
                shopItem.getPhotos().add(p);
            }catch (IOException e){
                logger.warn(e.getMessage(), e);
            }
        }
        shopItemRepository.save(shopItem);
        return "redirect:/item/"+shopItem.getId();
    }

}
