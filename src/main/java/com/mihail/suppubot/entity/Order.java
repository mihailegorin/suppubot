package com.mihail.suppubot.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "device")
    private String device;

    @Column(name = "problem")
    private String problem;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "receiptNumber")
    private String receiptNumber;

    @Column(name = "receiptDate")
    private Double receiptDate;

    @Column(name = "completionDate")
    private Double completionDate;

    @Column(name = "takeawayDate")
    private Double takeawayDate;

    @Column(name = "warrantyExpDate")
    private Double warrantyExpDate;

}
