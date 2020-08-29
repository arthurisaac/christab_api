package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.TypeFonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeFonctionRepository  extends JpaRepository<TypeFonction, Integer> {

    @Query("SELECT IFNULL(MAX(idTypeFonction), 0)+1 AS NEXTID FROM TypeFonction")
    public short findMaxId();

    @Query("SELECT T FROM TypeFonction T WHERE T.idTypeFonction LIKE :x ")
    public TypeFonction findOneById(@Param("x") short id);

    //sélection des lignes sauf la dernière
    @Query("SELECT T FROM TypeFonction T WHERE T.idTypeFonction <> (SELECT MAX(idTypeFonction) FROM TypeFonction)")
    public List<TypeFonction> findAllWithoutLast();
}
