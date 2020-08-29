package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.Itineraire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItineraireRepository  extends JpaRepository<Itineraire, Integer> {

    @Query("SELECT IFNULL(MAX(idItineraire), 0)+1 AS NEXTID FROM Itineraire")
    public int findMaxId();

    @Query("SELECT I FROM Itineraire I WHERE I.idItineraire LIKE :x ")
    public Itineraire findOneById(@Param("x") int id);
}
