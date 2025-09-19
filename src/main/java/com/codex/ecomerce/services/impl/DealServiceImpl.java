package com.codex.ecomerce.services.impl;

import com.codex.ecomerce.model.Deal;
import com.codex.ecomerce.model.HomeCategory;
import com.codex.ecomerce.repository.DealRepository;
import com.codex.ecomerce.repository.HomeCategoryRepository;
import com.codex.ecomerce.services.DealService;
import com.stripe.model.tax.Registration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final HomeCategoryRepository homeCategoryRepository;

    @Override
    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal createDeal(Deal deal) {
        HomeCategory category = homeCategoryRepository.findById(deal.getCategory().getId()).orElse(null);
        Deal newDeal = dealRepository.save(deal);
        newDeal.setCategory(category);
        newDeal.setDiscount(deal.getDiscount());
        return dealRepository.save(newDeal);
    }

    @Override
    public Deal updateDeal(Deal deal, Long id) throws Exception {
        Deal existingDeal = dealRepository.findById(id).orElseThrow(null);
        HomeCategory category = homeCategoryRepository.findById(deal.getCategory().getId()).orElseThrow(() ->
                new Exception("This deal does not exist")
        );
        if(existingDeal != null) {
            if (deal.getDiscount() != null) {
                existingDeal.setDiscount(deal.getDiscount());
            }
            if (category != null) {
                existingDeal.setCategory(category);
            }

        }
        throw new Exception("This deal does not exist");
    }

    @Override
    public void deleteDeal(Long id) throws Exception {
        Deal deal = dealRepository.findById(id).orElseThrow(() ->
            new Exception("This deal does not exist")
        );
        dealRepository.delete(deal);
    }
}
