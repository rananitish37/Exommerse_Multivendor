package com.codex.ecomerce.controller;

import com.codex.ecomerce.model.Deal;
import com.codex.ecomerce.response.ApiResponse;
import com.codex.ecomerce.services.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deals")
public class DealController {
    private final DealService dealService;

    @PostMapping
    public ResponseEntity<Deal> createDeals(
            @RequestBody Deal deal
    ){
        Deal createdDeal = dealService.createDeal(deal);

        return new ResponseEntity<>(createdDeal, HttpStatus.ACCEPTED);
    }

    @PatchMapping("'{id}")
    public ResponseEntity<Deal> updateDeal(
            @PathVariable Long id,
            @RequestBody Deal deal
    ) throws Exception {
        Deal updatedDeal = dealService.updateDeal(deal,id);
        return ResponseEntity.ok(updatedDeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDeals(
            @PathVariable Long id
    ) throws Exception {
        dealService.deleteDeal(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Deal removed");

        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }
}
