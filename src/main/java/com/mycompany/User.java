package com.mycompany;

import javax.persistence.*;

@Entity
@Table (name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String userName;

    @Column
    private String age;

    @Column
    private String email;

    @Column
    private String city;

    public User(String userName,String age,String email,String city){
     this.userName=userName;
     this.age=age;
     this.email=email;
     this.city=city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
