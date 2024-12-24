package com.example.mscommande.feign;

import com.example.mscommande.model.DTO.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.web.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name="TP3")
public interface ProductRestClient {
    @GetMapping("/Produits/{id}")
    Product findProductById(@PathVariable("id") Long id);

    @GetMapping("/Produits")
    List<Product> getAllProduct();
}