package ouhk.comps380f.dao;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private Account account;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private ShopItem item;

    private Integer amount;

    public Account getAccount() {
        return account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ShopItem getItem() {
        return item;
    }

    public void setItem(ShopItem shopItem) {
        this.item = shopItem;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
