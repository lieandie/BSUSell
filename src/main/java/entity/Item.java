package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Кирилл on 12.04.2017.
 */
@Entity
public class Item {
    private int id;
    private int nomenclatureId;
    private int storageId;
    private Integer quanity;

    public Item(){

    }

    public Item(int nomenclatureId, int storageId, int quanity){
        this.nomenclatureId = nomenclatureId;
        this.storageId = storageId;
        this.quanity = quanity;
    }

    @Id
    @GenericGenerator(name = "itemGen", strategy = "increment")
    @GeneratedValue(generator = "itemGen")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Basic
    @Column(name = "quanity")
    public Integer getQuanity() {
        return quanity;
    }

    public void setQuanity(Integer quanity) {
        this.quanity = quanity;
    }

    @Basic
    @Column(name = "nomenclature_id", nullable = false)
    public int getNomenclatureId() {
        return nomenclatureId;
    }

    public void setNomenclatureId(int nomenclatureId) {
        this.nomenclatureId = nomenclatureId;
    }
    @Basic
    @Column(name = "storages_id", nullable = false)
    public int getStorageId() {
        return storageId;
    }

    public void setStorageId(int storageId) {
        this.storageId = storageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != item.id) return false;
        if (quanity != null ? !quanity.equals(item.quanity) : item.quanity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (quanity != null ? quanity.hashCode() : 0);
        return result;
    }
}
