package com.mihail.suppubot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "name")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "workingHours")
    private String workingHours;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String googleMapsLink;

//    @Column(name = "link")
//    private byte[] image;

}
