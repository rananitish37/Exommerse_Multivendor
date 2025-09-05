package com.codex.ecomerce.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateProductRequest {
    private String title;
    private String description;
    private int mrpPrice;
    private int sellingPrice;
    private String color;
    private List<String> images;
    private String category; //for male female kids
    private String category2; //for upper wear lower wear
    private String category3; //for shirt t-short, pant trouser

    private String sizes;
}
