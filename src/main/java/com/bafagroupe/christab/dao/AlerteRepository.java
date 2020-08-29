package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.Alerte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlerteRepository  extends JpaRepository<Alerte, Integer> {

    @Query("SELECT IFNULL(MAX(idAlerte), 0)+1 AS NEXTID FROM Alerte")
    public int findMaxId();

    @Query("SELECT A FROM Alerte A WHERE A.idAlerte LIKE :x ")
    public Alerte findOneById(@Param("x") int id);
}
