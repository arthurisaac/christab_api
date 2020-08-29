package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.Engin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnginRepository  extends JpaRepository<Engin, Integer> {

    @Query("SELECT IFNULL(MAX(idEngin), 0)+1 AS NEXTID FROM Engin")
    public int findMaxId();
    /*@Query("SELECT COALESCE(MAX (idEngin), 0) +1 AS NEXTID FROM Engin ")
    public int findMaxId();*/

    @Query("SELECT E FROM Engin E WHERE E.idEngin LIKE :x ")
    public Engin findOneById(@Param("x") int id);

    @Query("UPDATE Engin E SET E.photoEngin = NULL, E.carteGrise = NULL, E.photoAssurance = NULL ," +
            " E.photoAssurance = NULL WHERE E.idEngin LIKE :x ")
    public Engin setPhotoNull(@Param("x") int id);

    /// @Query("SELECT E FROM Engin E, Utilisateur U WHERE E.idUtilisateur = U.idUtilisateur AND U.idUtilisateur LIKE :x ")
    List<Engin> findAllByIdUtilisateur(@Param("x") int id);

    @Query("SELECT E, U, A FROM Engin E " +
            " INNER JOIN Utilisateur U ON E.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Annonce A ON A.idUtilisateur = U.idUtilisateur " +
            " WHERE U.idUtilisateur LIKE :x AND A.idAnnonce LIKE :y")
    public Engin findEnginBUtilisateurAndAnnonce(@Param("x") int idU, @Param("y") int idA);

    /*@Query("SELECT E FROM Engin E ")
    public List<Engin> findAllEngin();*/
}
