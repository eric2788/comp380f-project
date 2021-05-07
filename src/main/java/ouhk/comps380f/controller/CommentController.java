
package ouhk.comps380f.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ouhk.comps380f.dao.ShopItem;
import ouhk.comps380f.repository.ShopItemRepository;
import ouhk.comps380f.dao.Comment;
import ouhk.comps380f.repository.CommentRepository;

@Controller
@RequestMapping("/comment")
public class CommentController {
    
    private final CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    @GetMapping("add")
    public String add(@RequestParam("itemid") String itemid) {
        CommentRepository.findById(id);
        Optional<ShopItem>  itemopt = ShopItemRepository.findById(itemid);
        if (itemopt.isPresent()) {
            Comment cmt = new Comment();
            ShopItem item = itemopt.get();
            cmt.setItem(Item item);
            cmt.setOwner(Account ac);
            return new RedirectAndView("comment", "comment", cmt);
        } else {
            return "index";
        }
    }
    
    @GetMapping("delete")
    public String delete(@RequestParam("id") String id) {
        Optional<Comment> cmtOpt = commentRepository.findById(id);
        if (cmtOpt.isPresent()) {
            Comment cm = cmtOpt.get();
            Integer itemid = cm.getItem().getId();
            commentRepository.deleteById(id);
            return "redirect:/shopitem?itemid="+itemid;
        } else {
            return "index";
        }
    }
    
    @PostMapping("add")
    public String postCreate(Comment comment) {
        System.out.println("preparing to save: " + comment.toString());
        commentRepository.save(comment);
        return "redirect:/item";
    }
}
