package ouhk.comps380f.service;

import org.springframework.transaction.annotation.Transactional;
import ouhk.comps380f.dao.Cart;

import java.util.List;

public interface OrderService {

    void createOrder(int itemid, String username);

    void removeOrder(int orderid, String username);

    List<Cart> listOrders(String username);
}
