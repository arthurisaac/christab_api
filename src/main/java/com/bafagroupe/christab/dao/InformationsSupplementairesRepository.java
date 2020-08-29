package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.InformationsSupplementaires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InformationsSupplementairesRepository extends JpaRepository<InformationsSupplementaires, Integer> {

    public List<InformationsSupplementaires> findAllByIdUtilisateur(int idUtilisateur);

    @Query("SELECT IFNULL(MAX(idInformationsSupplementaires), 0)+1 AS NEXTID FROM InformationsSupplementaires")
    public int findMaxId();

    @Query("SELECT I FROM InformationsSupplementaires I WHERE I.idInformationsSupplementaires LIKE :x ")
    public InformationsSupplementaires findOneById(@Param("x") int id);

    public List<InformationsSupplementaires> findAllByIdUtilisateurAndIdAnnonce(@Param("x") int idUtilisateur, @Param("x") int idAnnonce);
}
