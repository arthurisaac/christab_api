package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.TypeAvis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeAvisRepository extends JpaRepository<TypeAvis, Integer> {

    @Query("SELECT IFNULL(MAX(idTypeAvis), 0)+1 AS NEXTID FROM TypeAvis ")
    public int findMaxId();

    @Query("SELECT TA FROM TypeAvis TA WHERE TA.idTypeAvis LIKE :x ")
    public TypeAvis findOneById(@Param("x") int id);

    public List<TypeAvis> findAllByIdAvis(int idAvis);

    @Query("FROM TypeAvis TA")
    public List<TypeAvis> findAllTypesAvis();
}
