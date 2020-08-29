package com.bafagroupe.christab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor @NoArgsConstructor @Data @ToString
public class InformationsSupplementaires {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informations_supplementaires")
    private int idInformationsSupplementaires;

    @Column(name = "libelle_informations_supplementaires")
    private String libelleInformationsSupplementaires;

    @Column(name = "id_utilisateur")
    private int idUtilisateur;

    @Column(name = "id_annonce")
    private int idAnnonce;
}
