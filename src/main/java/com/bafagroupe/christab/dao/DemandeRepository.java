package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DemandeRepository  extends JpaRepository<Demande, Integer> {

    @Query("SELECT IFNULL(MAX(idDemande), 0)+1 AS NEXTID FROM Demande")
    public int findMaxId();

    @Query("SELECT D FROM Demande D WHERE D.idDemande LIKE :x ")
    public Demande findOneById(@Param("x") int id);

    @Query("SELECT D FROM Demande D " +
            " LEFT JOIN Utilisateur U ON D.idUtilisateur = U.idUtilisateur WHERE U.idUtilisateur LIKE :x ")
    public List<Demande> findAllDemandesByIdUtilisateur(@Param("x") int id);

    @Query("SELECT DISTINCT A, U, I, AV, D FROM Demande D " +
            " INNER JOIN Utilisateur U ON D.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON D.idItineraire = I.idItineraire " +
            " LEFT JOIN Annonce A ON D.idItineraire = A.idItineraire " +
            " WHERE U.idUtilisateur LIKE :x AND U.idTypeFonction = 2 AND STR_TO_DATE(A.dateDepart, '%Y-%m-%d') < CURRENT_DATE")
    public List<Object[]> findAllDemandesWithUAIAAvisForP(@Param("x") int id);

    @Query("SELECT DISTINCT A, U, I, AV, D FROM Demande D " +
            " INNER JOIN Utilisateur U ON D.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON D.idItineraire = I.idItineraire " +
            " INNER JOIN Annonce A ON D.idItineraire = A.idItineraire " +
            " WHERE U.idUtilisateur LIKE :x AND U.idTypeFonction = 2 AND STR_TO_DATE(A.dateDepart, '%Y-%m-%d') >= CURRENT_DATE")
    public List<Object[]> findAllDemandesWithUAIAAvisForPenCours(@Param("x") int id);

}
