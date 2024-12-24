package com.mproduits.msproducts;

import com.mproduits.msproducts.dao.ProductDao;
import com.mproduits.msproducts.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties
@RefreshScope
public class Tp3Application {

    public static void main(String[] args) {
        SpringApplication.run(Tp3Application.class, args);
    }
    @Bean
    CommandLineRunner start(@Autowired ProductDao productRepository) {
        return args -> {
            // Insérer des données
            productRepository.save(new Product( "Produit A", "Description du produit A", "image_a.jpg", 100.50));
            productRepository.save(new Product( "Produit B", "Description du produit B", "image_b.jpg", 150.75));
            productRepository.save(new Product( "Produit C", "Description du produit C", "image_c.jpg", 200.00));
            productRepository.save(new Product( "Produit D", "Description du produit D", "image_d.jpg", 99.99));
            productRepository.save(new Product( "Produit E", "Description du produit E", "image_e.jpg", 250.25));

            // Affichage des données insérées
            productRepository.findAll().forEach(product -> {
                System.out.println(product.getTitre() + " - " + product.getPrix());
            });
        };
    }
}
