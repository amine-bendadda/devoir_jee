package com.example.mscommande;

import com.example.mscommande.feign.ProductRestClient;
import com.example.mscommande.model.Commande;
import com.example.mscommande.model.DTO.Product;
import com.example.mscommande.repository.CommandeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MsCommandeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCommandeApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CommandeRepository commandeRepository,
                            ProductRestClient productRestClient) {
        return args -> {
            List<Product> products = productRestClient.getAllProduct();
            products.forEach(product -> {
                Commande commande = Commande.builder()
                        .description("rrr")
                        .productId(product.getId())
                        .date(LocalDate.now())
                        .quantite(1)
                        .montant(45.2)
                        .build();
                commandeRepository.save(commande);
            });
        };
    }
}
