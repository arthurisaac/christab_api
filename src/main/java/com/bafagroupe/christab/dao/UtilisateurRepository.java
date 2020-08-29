package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Utilisateur findByEmailIgnoreCase(String email);

    @Query("SELECT U FROM Utilisateur U WHERE U.email LIKE CONCAT('%',:e,'%') ")
    public Utilisateur findUByEmail(@Param("e") String email);

    @Query("SELECT IFNULL(MAX(idUtilisateur), 0)+1 AS NEXTID FROM Utilisateur")
    public int findMaxId();

    @Query("SELECT U FROM Utilisateur U WHERE U.idUtilisateur LIKE :x ")
    public Utilisateur findOneById(@Param("x") int id);

    @Query("UPDATE Utilisateur U SET U.photo = NULL , U.cnib = NULL WHERE U.idUtilisateur LIKE :x ")
    public Utilisateur setCnibAndPhotNull(@Param("x") int id);

    @Query("UPDATE Utilisateur U SET U.cnib = NULL WHERE U.idUtilisateur LIKE :x ")
    public Utilisateur setCnibNull(@Param("x") int id);

    @Query("UPDATE Utilisateur U SET U.photo = NULL WHERE U.idUtilisateur LIKE :x ")
    public Utilisateur setPhotoNull(@Param("x") int id);

    @Query("SELECT U FROM Utilisateur U WHERE U.idUtilisateur LIKE :x ")
    public List<Utilisateur> findOneByIdInList(@Param("x") int id);

    @Query("SELECT U.photo FROM Utilisateur U WHERE U.email LIKE CONCAT('%',:x,'%') ")
    public String findPhotoById(@Param("x") String email);

    @Query("DELETE FROM Utilisateur U WHERE U.idUtilisateur LIKE :y ")
    public Void deleteOneById(@Param("y") int id);


    // Afficher le nombre d'utilisateurs et la valeur des transactions faite dans chaque segment
    @Query("SELECT U, P FROM Utilisateur U " +
            " INNER JOIN Demande D ON U.idUtilisateur = D.idUtilisateur" +
            " INNER JOIN Itineraire I ON D.idItineraire = I.idItineraire"+
            " INNER JOIN Paiement P ON U.idUtilisateur = P.idUtilisateur" +
            " INNER JOIN Engin E ON U.idUtilisateur = E.idUtilisateur" +
            " WHERE E.typeEngin LIKE CONCAT('%', Auto, '%') AND " +
            " I.typeVoyage LIKE CONCAT('%', Urbain, '%')")
    public List<Object> findUserTransactionsAutoUrbain();

    @Query("SELECT U, P FROM Utilisateur U " +
            " INNER JOIN Demande D ON U.idUtilisateur = D.idUtilisateur" +
            " INNER JOIN Itineraire I ON D.idItineraire = I.idItineraire"+
            " INNER JOIN Paiement P ON U.idUtilisateur = P.idUtilisateur" +
            " INNER JOIN Engin E ON U.idUtilisateur = E.idUtilisateur" +
            " WHERE E.typeEngin LIKE CONCAT('%', Auto, '%') AND " +
            " I.typeVoyage LIKE CONCAT('%', Voyage, '%')")
    public List<Object> findUserTransactionsAutoVoyage();

    @Query("SELECT U, P FROM Utilisateur U " +
            " INNER JOIN Demande D ON U.idUtilisateur = D.idUtilisateur" +
            " INNER JOIN Itineraire I ON D.idItineraire = I.idItineraire"+
            " INNER JOIN Paiement P ON U.idUtilisateur = P.idUtilisateur" +
            " INNER JOIN Engin E ON U.idUtilisateur = E.idUtilisateur" +
            " WHERE E.typeEngin LIKE CONCAT('%', Moto, '%') AND " +
            " I.typeVoyage LIKE CONCAT('%', Urbain, '%')")
    public List<Object> findUserTransactionsMotoUrbain();

    @Query("SELECT U, P FROM Utilisateur U " +
            " INNER JOIN Demande D ON U.idUtilisateur = D.idUtilisateur" +
            " INNER JOIN Itineraire I ON D.idItineraire = I.idItineraire"+
            " INNER JOIN Paiement P ON U.idUtilisateur = P.idUtilisateur" +
            " INNER JOIN Engin E ON U.idUtilisateur = E.idUtilisateur" +
            " WHERE E.typeEngin LIKE CONCAT('%', Moto, '%') AND " +
            " I.typeVoyage LIKE CONCAT('%', Voyage, '%')")
    public List<Object> findUserTransactionsMotoVoyage();
}
