package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Кирилл on 12.04.2017.
 */
public class ShipOrderItemPK implements Serializable {
    private int shipOrdersId;
    private int itemsId;

    @Column(name = "ship_orders_id")
    @Id
    public int getShipOrdersId() {
        return shipOrdersId;
    }

    public void setShipOrdersId(int shipOrdersId) {
        this.shipOrdersId = shipOrdersId;
    }

    @Column(name = "items_id")
    @Id
    public int getItemsId() {
        return itemsId;
    }

    public void setItemsId(int itemsId) {
        this.itemsId = itemsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShipOrderItemPK that = (ShipOrderItemPK) o;

        if (shipOrdersId != that.shipOrdersId) return false;
        if (itemsId != that.itemsId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shipOrdersId;
        result = 31 * result + itemsId;
        return result;
    }
}
