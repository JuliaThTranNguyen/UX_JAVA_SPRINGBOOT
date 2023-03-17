package com.juliath.service.account.account;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Account {

    @Id
    @SequenceGenerator(
            name = "Account_sequence",
            sequenceName = "Account_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Account_sequence"
    )
    private Long id;

    private String name;

    private String email;

    private LocalDate dob;

    @Transient
    private Integer age;

    public Account(Long id, String name, String email,LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
    }

    public Account(String name,String email, LocalDate dob) {
        this.name = name;
        this.dob = dob;
        this.email = email;
    }

    public Account() {

    }

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {

        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }

    public void setId(Long accountID) {
        this.id = accountID;
    }
}
