package com.codex.ecomerce.services;

import com.codex.ecomerce.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(String sellerId);
    SellerReport updateSellerReport(SellerReport sellerReport);
}
