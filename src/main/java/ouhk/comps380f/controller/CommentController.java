
package ouhk.comps380f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ouhk.comps380f.dao.Account;
import ouhk.comps380f.dao.Comment;
import ouhk.comps380f.dao.ShopItem;
import ouhk.comps380f.repository.CommentRepository;
import ouhk.comps380f.repository.ShopItemRepository;
import ouhk.comps380f.service.AuthService;

import java.util.Optional;

@Controller
@RequestMapping("comment")
public class CommentController {

    private final CommentRepository commentRepository;
    private final ShopItemRepository shopItemRepository;
    private final AuthService authService;

    @Autowired
    public CommentController(CommentRepository commentRepository, ShopItemRepository shopItemRepository, AuthService authService) {
        this.commentRepository = commentRepository;
        this.shopItemRepository = shopItemRepository;
        this.authService = authService;
    }

    @GetMapping("add")
    public ModelAndView add(@RequestParam("itemid") String itemid, Authentication authentication) {
        Optional<ShopItem> itemopt = shopItemRepository.findById(itemid);
        Account account = authService.toAccount(authentication);
        if (account == null || !account.getAdmin()) return new ModelAndView("index");
        if (itemopt.isPresent()) {
            Comment cmt = new Comment();
            ShopItem item = itemopt.get();
            cmt.setItem(item);
            cmt.setAccount(account);
            return new ModelAndView("comment", "comment", cmt);
        } else {
            return new ModelAndView("index");
        }
    }

    @GetMapping("delete")
    public String delete(@RequestParam("id") String id) {
        Optional<Comment> cmtOpt = commentRepository.findById(id);
        if (cmtOpt.isPresent()) {
            Comment cm = cmtOpt.get();
            Integer itemid = cm.getItem().getId();
            commentRepository.deleteById(id);
            return "redirect:/item?id=" + itemid;
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
