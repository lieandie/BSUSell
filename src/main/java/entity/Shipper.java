package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Кирилл on 12.04.2017.
 */
@Entity
public class Shipper {
    private int id;
    private String name;
    private String number;
    private String adress;

    @Id
    @GenericGenerator(name = "shipperGen", strategy = "increment")
    @GeneratedValue(generator = "shipperGen")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "adress")
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shipper shipper = (Shipper) o;

        if (id != shipper.id) return false;
        if (name != null ? !name.equals(shipper.name) : shipper.name != null) return false;
        if (number != null ? !number.equals(shipper.number) : shipper.number != null) return false;
        if (adress != null ? !adress.equals(shipper.adress) : shipper.adress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (adress != null ? adress.hashCode() : 0);
        return result;
    }
}
