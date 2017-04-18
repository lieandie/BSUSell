package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Кирилл on 12.04.2017.
 */
@Entity
@Table(name = "sell_order", schema = "mydb", catalog = "")
public class SellOrder {
    private int id;
    private int clientId;
    private Double sum;

    public SellOrder(){

    }

    @Id
    @GenericGenerator(name = "sellOrderGen", strategy = "increment")
    @GeneratedValue(generator = "sellOrderGen")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "sum")
    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Basic
    @Column(name = "clients_id", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SellOrder sellOrder = (SellOrder) o;

        if (id != sellOrder.id) return false;
        if (sum != null ? !sum.equals(sellOrder.sum) : sellOrder.sum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        return result;
    }
}
