package com.codex.ecomerce.services.impl;

import com.codex.ecomerce.model.Seller;
import com.codex.ecomerce.model.SellerReport;
import com.codex.ecomerce.repository.SellerReportRepository;
import com.codex.ecomerce.repository.SellerRepository;
import com.codex.ecomerce.services.SellerReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerReportServiceImpl implements SellerReportService {
    private final SellerReportRepository sellerReportRepository;
    @Override
    public SellerReport getSellerReport(Seller seller) {

        SellerReport sr = sellerReportRepository.findBySellerId(seller.getId());

        if(sr==null){
            SellerReport newReport = new SellerReport();
            newReport.setSeller(seller);
            return sellerReportRepository.save(newReport);
        }
        return sr;
    }

    @Override
    public SellerReport updateSellerReport(SellerReport sellerReport) {

        return sellerReportRepository.save(sellerReport);
    }
}
