package com.mihail.suppubot.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id", referencedColumnName = "id")
    private Chat chat;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phoneNumber;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user")
    private List<Order> orders;

}