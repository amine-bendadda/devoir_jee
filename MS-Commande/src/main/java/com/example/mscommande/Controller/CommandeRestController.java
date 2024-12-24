package com.example.mscommande.Controller;

import com.example.mscommande.feign.ProductRestClient;
import com.example.mscommande.model.Commande;
import com.example.mscommande.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeRestController {

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ProductRestClient productRestClient;

    @GetMapping("/commandes/{id}")
    public Commande getCommande(@PathVariable Long id){
     Commande commande = commandeRepository.findById(id).get();
     commande.setProduct(productRestClient.findProductById(commande.getProductId()));
     return commande;

    }
}
