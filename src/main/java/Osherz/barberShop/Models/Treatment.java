package Osherz.barberShop.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "treatment")
public class Treatment {

    @Id
    @JsonProperty
    @Column(name = "treatment_id")
    private int id;

    @Column(name = "name")
    @JsonProperty
    private String name;

    @Column(name = "description")
    @JsonProperty
    private String description;

    @Column(name = "price")
    @JsonProperty
    private int price;

    public Treatment() {}

    public Treatment(int id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
