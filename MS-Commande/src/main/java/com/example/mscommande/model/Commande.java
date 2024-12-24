package com.example.mscommande.model;

import com.example.mscommande.model.DTO.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commande {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer quantite;
    @Column(name = "date")
    private LocalDate date;
    private Double montant;
    private long productId;

    @Transient  private Product product;

    public Commande(String commande_de_fournitures, int i, LocalDate of, double v) {
        this.description=commande_de_fournitures;
        this.quantite=i;
        this.date=of;
        this.montant=v;
    }

}
