package com.example.devoir.dao;

import com.example.devoir.model.Product;

import java.util.List;

public interface ProductDao {

    public void update(Product p);

    public List<Product> findAll();

}
