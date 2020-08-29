package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaiementRepository  extends JpaRepository<Paiement, Integer> {

    @Query("SELECT IFNULL(MAX(idPaiement), 0)+1 AS NEXTID FROM Paiement")
    public int findMaxId();

    @Query("SELECT P FROM Paiement P WHERE P.idPaiement LIKE :x ")
    public Paiement findOneById(@Param("x") int id);

    @Query("SELECT P FROM Paiement P WHERE STR_TO_DATE(P.datePaiement, '%Y-%m-%d') BETWEEN :stDate AND :edDate")
    public List<Paiement> findPaiementsBetweenDates(@Param("stDate") String date1, @Param("edDate") String date2);

    @Query("SELECT P FROM Paiement P ORDER BY P.numeroPaiement ASC")
    public List<Paiement> findPaiementsByNumberAsc();

    @Query("SELECT P FROM Paiement P ORDER BY P.numeroPaiement DESC")
    public List<Paiement> findPaiementsByNumberDesc();

    @Query("SELECT P FROM Paiement P ORDER BY STR_TO_DATE(P.datePaiement, '%Y-%m-%d') ASC")
    public List<Paiement> findPaiementsByDateAsc();

    @Query("SELECT P FROM Paiement P ORDER BY STR_TO_DATE(P.datePaiement, '%Y-%m-%d') DESC")
    public List<Paiement> findPaiementsByDateDesc();

    @Query("SELECT P FROM Paiement P ORDER BY P.montantPaiement ASC")
    public List<Paiement> findPaiementsByMontantAsc();

    @Query("SELECT P FROM Paiement P ORDER BY P.montantPaiement DESC")
    public List<Paiement> findPaiementsByMontantDesc();

    @Query("SELECT DISTINCT P, A FROM Paiement P" +
            " INNER JOIN Utilisateur U ON P.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Annonce A ON A.idUtilisateur = P.idUtilisateur  WHERE P.idPaiement LIKE :x")
    public List<Object[]> findAllPaiementsWithTrajetById(@Param("x") int id);
}
