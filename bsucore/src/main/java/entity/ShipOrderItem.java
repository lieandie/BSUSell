package entity;

import javax.persistence.*;

/**
 * Created by Кирилл on 12.04.2017.
 */
@Entity
@Table(name = "ship_order_item", schema = "mydb", catalog = "")
@IdClass(ShipOrderItemPK.class)
public class ShipOrderItem {
    private int shipOrdersId;
    private int itemsId;

    @Id
    @Column(name = "ship_orders_id")
    public int getShipOrdersId() {
        return shipOrdersId;
    }

    public void setShipOrdersId(int shipOrdersId) {
        this.shipOrdersId = shipOrdersId;
    }

    @Id
    @Column(name = "items_id")
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

        ShipOrderItem that = (ShipOrderItem) o;

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
