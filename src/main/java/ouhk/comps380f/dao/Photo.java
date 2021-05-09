package ouhk.comps380f.dao;

import javax.persistence.*;
import java.util.Base64;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] content;

    @ManyToOne
    private ShopItem item;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ShopItem getItem() {
        return item;
    }

    public void setItem(ShopItem item) {
        this.item = item;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getBase64(){
        return Base64.getEncoder().encodeToString(content);
    }
}
