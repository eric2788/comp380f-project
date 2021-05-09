package ouhk.comps380f.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ouhk.comps380f.dao.Account;
import ouhk.comps380f.dao.Comment;
import ouhk.comps380f.dao.ShopItem;
import ouhk.comps380f.exception.CustomException;
import ouhk.comps380f.repository.CommentRepository;
import ouhk.comps380f.repository.ShopItemRepository;
import ouhk.comps380f.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ShopItemRepository shopItemRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void createComment(Comment comment, String username, int itemid) {
        Account account = userRepository.findById(username).orElseThrow(() -> new CustomException("username not found: "+username));
        ShopItem shopItem = shopItemRepository.findById(itemid).orElseThrow(() -> new CustomException("item not found: "+itemid));
        comment.setAccount(account);
        comment.setItem(shopItem);
        commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void deleteComment(int id, String username) {
        //Comment comment = commentRepository.findById(id).orElseThrow(() -> new CustomException("comment not found: "+id));
        //if (!comment.getAccount().isAdmin() && !comment.getAccount().getUsername().equals(username)) throw new CustomException("comment is not yours");
        commentRepository.deleteById(id);
    }
}
