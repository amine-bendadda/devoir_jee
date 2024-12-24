package com.mproduits.msproducts.controller;

import com.mproduits.msproducts.configurations.ApplicationPropertiesConfiguration;
import com.mproduits.msproducts.dao.ProductDao;
import com.mproduits.msproducts.exceptions.ProductNotFoundException;
import com.mproduits.msproducts.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Fallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Configuration
public class ProductController implements HealthIndicator {

    @Autowired
    ProductDao productDao;

    @Autowired
    ApplicationPropertiesConfiguration appProperties;



    @GetMapping(value="/Produits")
    public List<Product> listeDesProduits(){
        System.out.println("******** ProductController listeDesProduits()");
        List<Product> products = productDao.findAll();
        System.out.println("*********** ProductController Produit[1]:"+products.get(1));

        if(products.isEmpty())
            throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");
        List<Product> listeLimitee = products.subList(0, appProperties.getLimitDeProduits());
        return listeLimitee;
    }

    @GetMapping(value = "/Produits/{id}")
    public Product getProduitById(@PathVariable("id") Long id) {
        System.out.println("******** ProductController getProduitById() - id: " + id);
        Product product = productDao.findById(id).orElseThrow(() -> new ProductNotFoundException("Produit avec l'ID " + id + " non trouvé"));
        return product;
    }

    @Override
    public Health health(){
        System.out.println("****** Actuator : ProductController health()");
        List<Product> products = productDao.findAll();
        if(products.isEmpty()){
            return  Health.down().build();
        }
        return Health.up().build();
    }
}
