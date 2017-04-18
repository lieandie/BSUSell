package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Кирилл on 12.04.2017.
 */
@Entity
@Table(name = "ship_order", schema = "mydb", catalog = "")
public class ShipOrder {
    private int id;
    private Double sum;
    private int shipper_id;
    private int storage_id;


    @Id
    @GenericGenerator(name = "shipOrderGen", strategy = "increment")
    @GeneratedValue(generator = "shipOrderGen")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ship_order_item",
            joinColumns = @JoinColumn(name = "ship_orders_id"),
            inverseJoinColumns = @JoinColumn(name = "items_id")
    )*/


    @Basic
    @Column(name = "sum")
    public Double getSum()   {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Basic
    @Column(name="shippers_id", nullable = false)
    public int getShipper_id() {
        return shipper_id;
    }

    public void setShipper_id(int shipper_id) {
        this.shipper_id = shipper_id;
    }
    @Basic
    @Column(name = "storages_id", nullable = false)
    public int getStorage_id() {
        return storage_id;
    }

    public void setStorage_id(int storage_id) {
        this.storage_id = storage_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShipOrder shipOrder = (ShipOrder) o;

        if (id != shipOrder.id) return false;
        if (sum != null ? !sum.equals(shipOrder.sum) : shipOrder.sum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        return result;
    }
}
