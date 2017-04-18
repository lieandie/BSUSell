package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Кирилл on 12.04.2017.
 */
public class SellOrderItemPK implements Serializable {
    private int itemsId;
    private int sellOrdersId;

    @Column(name = "items_id")
    @Id
    public int getItemsId() {
        return itemsId;
    }

    public void setItemsId(int itemsId) {
        this.itemsId = itemsId;
    }

    @Column(name = "sell_orders_id")
    @Id
    public int getSellOrdersId() {
        return sellOrdersId;
    }

    public void setSellOrdersId(int sellOrdersId) {
        this.sellOrdersId = sellOrdersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SellOrderItemPK that = (SellOrderItemPK) o;

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
