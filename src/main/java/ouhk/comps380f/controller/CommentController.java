
package ouhk.comps380f.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ouhk.comps380f.dao.Account;
import ouhk.comps380f.dao.Comment;
import ouhk.comps380f.dao.ShopItem;
import ouhk.comps380f.exception.CustomException;
import ouhk.comps380f.repository.CommentRepository;
import ouhk.comps380f.repository.ShopItemRepository;
import ouhk.comps380f.service.AuthService;
import ouhk.comps380f.service.CommentService;

import java.util.Optional;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ShopItemRepository shopItemRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private CommentService commentService;
    private final Logger logger = LoggerFactory.getLogger(CommentController.class);


    @GetMapping("add/{id}")
    public ModelAndView add(@PathVariable("id") int id) {
        return new ModelAndView("comment", "comment", new Comment());
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id, Authentication authentication, @RequestParam("itemid") int itemid) {
        commentService.deleteComment(id, authentication.getName());
        return "redirect:/item/"+itemid;
    }

    @Transactional
    @PostMapping("add/{id}")
    public String postCreate(@PathVariable("id") int id, Comment comment, Authentication authentication) {
        commentService.createComment(comment, authentication.getName(), id);
        return "redirect:/item/"+id;
    }
}
