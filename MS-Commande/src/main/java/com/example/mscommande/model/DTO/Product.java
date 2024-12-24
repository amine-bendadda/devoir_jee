package com.example.mscommande.model.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class Product {
    private Long id;

    private String titre;
    private String description;
    private String image;
    private Double prix;
}
