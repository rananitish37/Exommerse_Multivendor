package com.codex.ecomerce.services;

import com.codex.ecomerce.model.Home;
import com.codex.ecomerce.model.HomeCategory;

import java.util.List;

public interface HomeService {
    public Home createHomePageData(List<HomeCategory> allCategories);
}
