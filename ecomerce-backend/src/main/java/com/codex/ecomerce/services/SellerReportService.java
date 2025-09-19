package com.codex.ecomerce.services;

import com.codex.ecomerce.model.Seller;
import com.codex.ecomerce.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);
}
