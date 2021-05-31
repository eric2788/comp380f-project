package ouhk.comps380f.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ouhk.comps380f.dao.ShopItem;
import ouhk.comps380f.repository.PhotoRepository;
import ouhk.comps380f.repository.ShopItemRepository;
import ouhk.comps380f.service.PhotoService;
import ouhk.comps380f.service.ValidateService;

@Controller
@RequestMapping("item")
public class ShopItemController {

    private final PhotoRepository photoRepository;
    private final ShopItemRepository shopItemRepository;
    private final ValidateService validateService;
    private final PhotoService photoService;
    private final Logger logger = LoggerFactory.getLogger(ShopItemController.class);

    @Autowired
    public ShopItemController(ShopItemRepository shopItemRepository, PhotoRepository photoRepository, ValidateService validateService, PhotoService photoService) {
        this.shopItemRepository = shopItemRepository;
        this.photoRepository = photoRepository;
        this.validateService = validateService;
        this.photoService = photoService;
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
        shopItemRepository.save(shopItem);
        return "redirect:/item/"+shopItem.getId();
    }

    @GetMapping("edit/{id}/photo")
    public String editPhoto(@PathVariable("id") int id, ModelMap modelMap){
        modelMap.addAttribute("item", validateService.validateExist(shopItemRepository, id, "item with id "+id+" not exist"));
        return "photo";
    }

    @GetMapping("delete/{id}/photo")
    public String deletePhoto(@PathVariable("id") long id, @RequestParam("itemid") int itemid){
        photoService.deletePhoto(id);
        return "redirect:/item/edit/"+itemid+"/photo";
    }

    @PostMapping("edit/{id}/photo")
    public String postPhoto(@PathVariable("id") int id, @RequestParam MultipartFile[] files){
        photoService.savePhotos(id, files);
        return "redirect:/item/"+id;
    }

}
