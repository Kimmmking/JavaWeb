package com.example.javaweb.music_center.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
@Proxy(lazy = false)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name="uid")

    private User user;

    @ManyToOne
    @JoinColumn(name="pid")

    private Product product;

    private String content;
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
