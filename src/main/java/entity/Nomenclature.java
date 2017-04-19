package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by Кирилл on 12.04.2017.
 */
@Entity
public class Nomenclature {
    private int id;
    private String name;
    private String article;
    private String descritrion;
    private byte[] picture;
    private Double basePrice;
    private Double wholesalePrice;
    private Double retailPrice;

    @Id
    @GenericGenerator(name = "nomenclatureGen", strategy = "increment")
    @GeneratedValue(generator = "nomenclatureGen")
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
    @Column(name = "article")
    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Basic
    @Column(name = "descritrion")
    public String getDescritrion() {
        return descritrion;
    }

    public void setDescritrion(String descritrion) {
        this.descritrion = descritrion;
    }

    @Basic
    @Column(name = "picture")
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "base_price")
    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    @Basic
    @Column(name = "wholesale_price")
    public Double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(Double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    @Basic
    @Column(name = "retail_price")
    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nomenclature that = (Nomenclature) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (article != null ? !article.equals(that.article) : that.article != null) return false;
        if (descritrion != null ? !descritrion.equals(that.descritrion) : that.descritrion != null) return false;
        if (!Arrays.equals(picture, that.picture)) return false;
        if (basePrice != null ? !basePrice.equals(that.basePrice) : that.basePrice != null) return false;
        if (wholesalePrice != null ? !wholesalePrice.equals(that.wholesalePrice) : that.wholesalePrice != null)
            return false;
        if (retailPrice != null ? !retailPrice.equals(that.retailPrice) : that.retailPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (article != null ? article.hashCode() : 0);
        result = 31 * result + (descritrion != null ? descritrion.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(picture);
        result = 31 * result + (basePrice != null ? basePrice.hashCode() : 0);
        result = 31 * result + (wholesalePrice != null ? wholesalePrice.hashCode() : 0);
        result = 31 * result + (retailPrice != null ? retailPrice.hashCode() : 0);
        return result;
    }
}
