package com.mproduits.msproducts.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private String image;
    private Double prix;

    public Product(String produit_b, String description_du_produit_b, String s, double v) {
    this.titre=produit_b;
    this.description=description_du_produit_b;
    this.image=s;
    this.prix=v;


    }
}
