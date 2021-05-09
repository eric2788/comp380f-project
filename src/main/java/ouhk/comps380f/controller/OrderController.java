package ouhk.comps380f.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ouhk.comps380f.repository.CartRepository;
import ouhk.comps380f.repository.ShopItemRepository;
import ouhk.comps380f.service.AuthService;
import ouhk.comps380f.service.OrderService;
import ouhk.comps380f.service.ValidateService;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ShopItemRepository shopItemRepository;

    @Autowired
    private ValidateService validateService;

    @Autowired
    private AuthService authService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String orderIndex(ModelMap modelMap, Authentication authentication) {
        modelMap.addAttribute("carts", orderService.listOrders(authentication.getName()));
        return "carts";
    }

    @GetMapping("delete/{id}")
    public String deleteOrder(@PathVariable("id") int id, Authentication authentication) {
        orderService.removeOrder(id, authentication.getName());
        return "redirect:/order";
    }

    @Transactional
    @GetMapping("add/{itemid}")
    public String addOrder(@PathVariable("itemid") int id, Authentication authentication) {
        orderService.createOrder(id, authentication.getName());
        return "redirect:/item/" + id + "?addedCart";
    }

}
