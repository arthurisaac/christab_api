package com.bafagroupe.christab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class TypeAvis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_avis")
    private int idTypeAvis;

    @Column(name = "libelle_type_avis")
    private String libelleTypeAvis;

    @Column(name = "id_avis")
    private int idAvis;

    @Column(name = "note")
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeAvis typeAvis = (TypeAvis) o;
        return idTypeAvis == typeAvis.idTypeAvis &&
                idAvis == typeAvis.idAvis &&
                Objects.equals(libelleTypeAvis, typeAvis.libelleTypeAvis) &&
                Objects.equals(note, typeAvis.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeAvis, libelleTypeAvis, idAvis, note);
    }
}
