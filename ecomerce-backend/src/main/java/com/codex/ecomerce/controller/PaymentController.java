package com.codex.ecomerce.controller;

import com.codex.ecomerce.model.*;
import com.codex.ecomerce.response.ApiResponse;
import com.codex.ecomerce.response.PaymentLinkResponse;
import com.codex.ecomerce.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final TransactionService transactionService;


    @GetMapping("/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
            @PathVariable String paymentId,
            @RequestParam String paymentLinkId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserFromToken(jwt);
        PaymentLinkResponse paymentLinkResponse;
        PaymentOrder paymentOrder = paymentService.getPaymentOrderByPaymentId(paymentId);

        boolean paymentSuccess = paymentService.proceedPaymentOrder(
                paymentOrder,
                paymentId,
                paymentLinkId
        );

        if(paymentSuccess){
            for(Order order: paymentOrder.getOrders()) {
                transactionService.createTransaction(order);
                Seller seller = sellerService.getSellerById(order.getSellerId());
                SellerReport sellerReport = sellerReportService.getSellerReport(seller);
                sellerReport.setTotalOrders(sellerReport.getTotalOrders()+1);
                sellerReport.setTotalEarnings(sellerReport.getTotalEarnings()+order.getTotalSellingPrice());
                sellerReport.setTotalSales(sellerReport.getTotalSales()+order.getOrderItems().size());
                sellerReportService.updateSellerReport(sellerReport);
            }
        }

        ApiResponse res = new ApiResponse();
        res.setMessage("Payment Successful");


        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
