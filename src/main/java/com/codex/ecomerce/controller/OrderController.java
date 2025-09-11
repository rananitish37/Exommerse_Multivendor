package com.codex.ecomerce.controller;

import com.codex.ecomerce.domain.PaymentMethod;
import com.codex.ecomerce.exceptions.SellerException;
import com.codex.ecomerce.model.*;
import com.codex.ecomerce.response.PaymentLinkResponse;
import com.codex.ecomerce.services.CartService;
import com.codex.ecomerce.services.OrderService;
import com.codex.ecomerce.services.SellerService;
import com.codex.ecomerce.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final SellerService sellerService;
    private final

    @PostMapping()
    public ResponseEntity<PaymentLinkResponse> craeteOrderHandler(
            @RequestBody Address shippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {

        User user = userService.findUserFromToken(jwt);
        Cart cart = cartService.findUserCart(user);
        Set<Order> orders = orderService.createOrder(user, shippingAddress, cart);


//        PaymentOrder paymentOrder = paymentService.createOrder(user, orders);

        PaymentLinkResponse res = new PaymentLinkResponse();

//        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
//
//        }

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> orderUserHistoryHandler(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserFromToken(jwt);
        List<Order> orders = orderService.usersOrderHistory(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId,
                                              @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserFromToken(jwt);
        Order order = orderService.findOrderById(user.getId());
        return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
    }

    @GetMapping("/item/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(
            @PathVariable Long orderItemId, @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserFromToken(jwt);
        OrderItem orderItem = orderService.getOrderItemById(orderItemId);
        return new ResponseEntity<>(orderItem, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderIt}/cancel")
    public ResponseEntity<Order> cancelOrder(
            @PathVariable Long orderId,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user = userService.findUserFromToken(jwt);
        Order order = orderService.cancelOrder(orderId, user);

//        Seller seller = sellerService.getSellerById(order.getSellerId());
//        SellerReport report = se

        return ResponseEntity.ok(order);
    }
}
