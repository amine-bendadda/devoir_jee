package com.example.mscommande.Controller;

import com.example.mscommande.Exceptions.CommandeNotFoundException;
import com.example.mscommande.configurations.ApplicationPropertiesConfiguration;
import com.example.mscommande.model.Commande;
import com.example.mscommande.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CommandeController implements HealthIndicator {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    ApplicationPropertiesConfiguration appProperties;


    @GetMapping(value = "/commandes")
    public List<Commande> listeDesCommandes() {
        System.out.println("***** CommandeController listeDesCommandes()");

        int jours = appProperties.getCommandeLast();
        if (jours <= 0) {
            jours = 10;
        }

        LocalDate dateLimite = LocalDate.now().minusDays(jours);

        List<Commande> commandes = commandeRepository.findAll();

        if (commandes.isEmpty()) {
            throw new CommandeNotFoundException("Aucune commande n'est disponible à la vente");
        }


        List<Commande> commandesFiltrees = commandes.stream()
                .filter(commande -> commande.getDate().isAfter(dateLimite))
                .collect(Collectors.toList());

        if (commandesFiltrees.isEmpty()) {
            throw new CommandeNotFoundException("Aucune commande disponible dans la période des " + jours + " derniers jours");
        }

        return commandesFiltrees;
    }


    @Override
    public Health health() {
        long count = commandeRepository.count();
        System.out.println("***** Vérification des commandes pour la santé du microservice *****");
        System.out.println("Nombre de commandes trouvées : " + count);

        if (count > 0) {
            System.out.println("Le microservice-commandes est en bonne santé. Nombre de commandes : " + count);
            return Health.up().withDetail("commandes", count).build();
        } else {
            System.out.println("Le microservice-commandes est en panne. Aucun commande disponible.");
            return Health.down().withDetail("message", "Pas de commandes disponibles").build();
        }
    }
}
