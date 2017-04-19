package entity;

import javax.persistence.*;

/**
 * Created by Кирилл on 12.04.2017.
 */
@Entity
@Table(name = "sell_order_item", schema = "mydb", catalog = "")
@IdClass(SellOrderItemPK.class)
public class SellOrderItem {
    private int itemsId;
    private int sellOrdersId;
    private int quanity;

    @Id
    @Column(name = "items_id")
    public int getItemsId() {
        return itemsId;
    }

    public void setItemsId(int itemsId) {
        this.itemsId = itemsId;
    }

    @Id
    @Column(name = "sell_orders_id")
    public int getSellOrdersId() {
        return sellOrdersId;
    }

    public void setSellOrdersId(int sellOrdersId) {
        this.sellOrdersId = sellOrdersId;
    }

    @Basic
    @Column(name = "quanity")
    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SellOrderItem that = (SellOrderItem) o;

        if (itemsId != that.itemsId) return false;
        if (sellOrdersId != that.sellOrdersId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemsId;
        result = 31 * result + sellOrdersId;
        return result;
    }
}
