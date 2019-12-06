package com.example.javaweb.music_center.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Proxy(lazy = false)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    private String name;

    @Transient
    private
    List<Product> products;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + "]";
    }
}
