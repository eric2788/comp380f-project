package ouhk.comps380f.dao;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Account account;

    @ManyToOne
    private ShopItem shopItem;


    private Integer amount;

    public Account getAccount() {
        return account;
    }

    public Integer getId() {
        return id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ShopItem getShopItem() {
        return shopItem;
    }

    public void setShopItem(ShopItem shopItem) {
        this.shopItem = shopItem;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
