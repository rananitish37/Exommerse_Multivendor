package com.codex.ecomerce.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Seller seller;

    private Long totalEarnings =0L;
    private Long totalSales=0L;
    private Long totalRefund=0L;
    private Long totalTax=0L;
    private Long totalnetEarnings=0L;
    private Integer totalOrders=0;
    private Integer canceledOrders=0;
    private Integer totalTransaction=0;
}
