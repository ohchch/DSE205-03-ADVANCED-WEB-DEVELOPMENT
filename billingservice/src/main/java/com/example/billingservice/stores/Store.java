package com.example.billingservice.stores;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import jakarta.persistence.Table;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import java.util.List;
import java.util.Objects;


@Entity
@Table (name="store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键

    private String name; // 商店名称
    private String phoneNumber; // 商店电话号码

    //@ElementCollection
    //@CollectionTable(name = "store_localities", joinColumns = @JoinColumn(name = "store_id")
    @Column(name = "locality")
    private List<String> localities; // 商店服务的地区或区域列表

    // 默认构造函数
    public Store() {}

    // 参数化构造函数
    public Store(String name, String phoneNumber, List<String> localities) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.localities = localities;
    }

    // id 的 getter 和 setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // name 的 getter 和 setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // phoneNumber 的 getter 和 setter 方法
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // localities 的 getter 和 setter 方法
    public List<String> getLocalities() {
        return localities;
    }

    public void setLocalities(List<String> localities) {
        this.localities = localities;
    }

    // equals 方法重写
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id) &&
                Objects.equals(name, store.name) &&
                Objects.equals(phoneNumber, store.phoneNumber) &&
                Objects.equals(localities, store.localities);
    }

    // hashCode 方法重写
    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, localities);
    }

    // toString 方法重写
    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", localities=" + localities +
                '}';
    }
}
