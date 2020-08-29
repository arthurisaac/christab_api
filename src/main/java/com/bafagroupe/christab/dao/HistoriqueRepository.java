package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HistoriqueRepository  extends JpaRepository<Historique, Integer> {

    @Query("SELECT IFNULL(MAX(idHistorique), 0)+1 AS NEXTID FROM Historique")
    public int findMaxId();

    @Query("SELECT H FROM Historique H WHERE H.idHistorique LIKE :x ")
    public Historique findOneById(@Param("x") int id);
}
