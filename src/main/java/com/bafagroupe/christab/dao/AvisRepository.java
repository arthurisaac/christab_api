package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvisRepository   extends JpaRepository<Avis, Integer> {

    @Query("SELECT IFNULL(MAX(idAvis), 0)+1 AS NEXTID FROM Avis")
    public int findMaxId();

    @Query("SELECT A FROM Avis A WHERE A.idAvis LIKE :x ")
    public Avis findOneById(@Param("x") int id);

    // @Query("SELECT A FROM Avis A WHERE A.idUtilisateur LIKE :x ")
    public List<Avis> findAllByIdUtilisateur(int idUtilisateur);
}
