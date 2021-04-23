package ouhk.comps380f.dao;

import javax.persistence.*;


@Table
public class Order {

    @ManyToOne
    private User username;
    @ManyToOne
    private Item id;
    private Integer amount;

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public Item getId() {
        return id;
    }

    public void setId(Item id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
