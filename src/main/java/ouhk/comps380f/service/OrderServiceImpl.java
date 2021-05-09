package ouhk.comps380f.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ouhk.comps380f.dao.Account;
import ouhk.comps380f.dao.Cart;
import ouhk.comps380f.dao.ShopItem;
import ouhk.comps380f.exception.CustomException;
import ouhk.comps380f.repository.CartRepository;
import ouhk.comps380f.repository.ShopItemRepository;
import ouhk.comps380f.repository.UserRepository;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopItemRepository shopItemRepository;

    @Transactional
    @Override
    public void createOrder(int itemid, String username) {
        ShopItem item = shopItemRepository.findById(itemid).orElseThrow(() -> new CustomException("no item find with id "+itemid));
        if (!item.isAvailable()) throw new CustomException("this item is not available");
        Account account = userRepository.findById(username).orElseThrow(() -> new CustomException("username not found: "+username));
        Cart cart = new Cart();
        cart.setItem(item);
        cart.setAccount(account);
        cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void removeOrder(int orderid, String username) {
        //Cart cart = cartRepository.findById(orderid).orElseThrow(() -> new CustomException("not found order"));
        //if (!cart.getAccount().getUsername().equals(username)) throw new CustomException("this order is not yours");
        cartRepository.deleteById(orderid);
    }

    @Transactional
    @Override
    public List<Cart> listOrders(String username) {
        return cartRepository.findByAccount_Username(username);
    }
}
