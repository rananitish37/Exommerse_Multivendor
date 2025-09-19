package com.codex.ecomerce.controller;

import com.codex.ecomerce.domain.OrderStatus;
import com.codex.ecomerce.exceptions.SellerException;
import com.codex.ecomerce.model.Order;
import com.codex.ecomerce.model.Seller;
import com.codex.ecomerce.services.OrderService;
import com.codex.ecomerce.services.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller/orders")
public class SellerOrderController {
    private final SellerService sellerService;
    private final OrderService orderService;

    @GetMapping()
    public ResponseEntity<List<Order>> getAllOrdersHandler(
            @RequestHeader("Authorization") String jwt
    ) throws SellerException {
        Seller seller = sellerService.getSellerProfile(jwt);
        List<Order> orders = orderService.sellersOrder(seller.getId());

        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{orderIt}/status/{orderStatus}")
    public ResponseEntity<Order> updateOrderHandler(
            @RequestHeader("Authorization")String jwt,
            @PathVariable Long orderId,
            @PathVariable OrderStatus orderStatus
            ) throws Exception {
        Order order = orderService.updateOrderStatus(orderId,orderStatus);

        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }
}
