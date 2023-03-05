package com.rokib.cafe.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "bill")
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uuid;
    private String name;
    private String email;
    @Column(name = "contactnumber")
    private String contactNumber;
    @Column(name = "paymentmethod")
    private String paymentMethod;
    private Integer total;

    @Column(name = "productdetails", columnDefinition = "json")
    private String productDetail;
    @Column(name = "createdby")
    private String createdBy;

}