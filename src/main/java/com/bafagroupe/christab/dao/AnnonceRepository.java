package com.bafagroupe.christab.dao;

import com.bafagroupe.christab.entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnnonceRepository  extends JpaRepository<Annonce, Integer> {

    @Query("SELECT IFNULL(MAX(idAnnonce), 0)+1 AS NEXTID FROM Annonce")
    public int findMaxId();

    @Query("SELECT A FROM Annonce A WHERE A.idAnnonce LIKE :x ")
    public Annonce findOneById(@Param("x") int id);

    @Query("SELECT A FROM Annonce A " +
            " LEFT JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur WHERE U.idUtilisateur LIKE :x ")
    public List<Annonce> findAllAnnoncesByIdUtilisateur(@Param("x") int id);

    /************************** ====Liste des annonces de tous les utilisateurs et leur avis sur les conducteurs ==== ****************************/
    @Query("SELECT A, U, I FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire ")
    public List<Object[]> findAllAnnoncesWithUtilisateurs();

    @Query("SELECT DISTINCT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire ")
    public List<Object[]> findAllAnnoncesWithUAIAAvis(); // DISTINCT permet d'éviter la répétition des lignes

    /************************** ====Liste des annonces de tous les utilisateurs et leur avis sur les conducteurs d'un conducteur ou passager ==== ****************************/
    @Query("SELECT DISTINCT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " WHERE U.idUtilisateur LIKE :x AND U.idTypeFonction = 1 AND STR_TO_DATE(A.dateDepart, '%Y-%m-%d') < CURRENT_DATE")
    public List<Object[]> findAllAnnoncesWithUAIAAvisForC(@Param("x") int id);

    @Query("SELECT DISTINCT A, U, I, AV, D FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " INNER JOIN Demande D ON D.idItineraire = A.idItineraire " +
            " WHERE U.idUtilisateur LIKE :x AND U.idTypeFonction = 1 AND STR_TO_DATE(A.dateDepart, '%Y-%m-%d') < CURRENT_DATE")
    public List<Object[]> findAllAnnoncesWithUAIAAvisDForC(@Param("x") int id);

    @Query("SELECT DISTINCT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " WHERE U.idUtilisateur LIKE :x AND U.idTypeFonction = 2 AND STR_TO_DATE(A.dateDepart, '%Y-%m-%d') < CURRENT_DATE")
    public List<Object[]> findAllAnnoncesWithUAIAAvisForP(@Param("x") int id);

    @Query("SELECT DISTINCT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " WHERE U.idUtilisateur LIKE :x AND U.idTypeFonction = 1 AND STR_TO_DATE(A.dateDepart, '%Y-%m-%d') >= CURRENT_DATE")
    public List<Object[]> findAllAnnoncesWithUAIAAvisForCenCours(@Param("x") int id);

    @Query("SELECT DISTINCT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " WHERE U.idUtilisateur LIKE :x AND U.idTypeFonction = 2 AND STR_TO_DATE(A.dateDepart, '%Y-%m-%d') >= CURRENT_DATE")
    public List<Object[]> findAllAnnoncesWithUAIAAvisForPenCours(@Param("x") int id);

/************************** ==== Liste triée des annonces de tous les utilisateurs et leur avis sur les conducteurs ==== ***************************/
    @Query("SELECT DISTINCT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " ORDER BY A.prix  ASC ")
    public List<Object[]> findAllAnnoncesWithUAIAAvisByPrixA();

    @Query("SELECT DISTINCT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " ORDER BY A.prix  DESC ")
    public List<Object[]> findAllAnnoncesWithUAIAAvisByPrixD();

    @Query("SELECT DISTINCT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " ORDER BY A.nbrePersonne  ASC ")
    public List<Object[]> findAllAnnoncesWithUAIAAvisByPlaceA();

    @Query("SELECT DISTINCT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " ORDER BY A.nbrePersonne  DESC ")
    public List<Object[]> findAllAnnoncesWithUAIAAvisByPlaceD();

    @Query("SELECT DISTINCT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " ORDER BY STR_TO_DATE(A.dateDepart, '%Y-%m-%d')  ASC ")
    public List<Object[]> findAllAnnoncesWithUAIAAvisByDepA();

    @Query("SELECT DISTINCT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " ORDER BY STR_TO_DATE(A.dateDepart, '%Y-%m-%d')  DESC ")
    public List<Object[]> findAllAnnoncesWithUAIAAvisByDepD();

/************************** ====Liste des annonces incluant l'itinéraire, les avis en fonction du conducteur ==== ****************************/
    @Query("SELECT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " WHERE U.idUtilisateur LIKE :x AND " +
            " A.idAnnonce LIKE :y and I.idItineraire LIKE :z")
    public List<Object[]> findAllAnnoncesWithAIAAvisByU(@Param("x") int idU, @Param("y") int idA, @Param("z") int idI);

    @Query("SELECT A, U, I, AV FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur " +
            " LEFT JOIN Avis AV ON AV.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire " +
            " WHERE U.idUtilisateur LIKE 1 AND " +
            " A.idAnnonce LIKE 1 and I.idItineraire LIKE 1")
    public List<Object[]> findAllAnnoncesWithAIAAvisByUTest();


    /************************** ====Liste des trajets en fonction du type d'engin et voyage ==== ****************************/
    @Query("SELECT DISTINCT A, U, I, Av FROM Utilisateur U " +
            " LEFT JOIN Avis Av ON Av.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Annonce A ON U.idUtilisateur = A.idUtilisateur" +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire"+
            " INNER JOIN Engin E ON U.idUtilisateur = E.idUtilisateur" +
            " WHERE E.typeEngin LIKE CONCAT('%', 'Auto', '%') AND " +
            " I.typeVoyage LIKE CONCAT('%', 'Urbain', '%')")
    public List<Object[]> findUserTrajetsAutoUrbain();

    @Query("SELECT DISTINCT A, U, I, Av FROM Utilisateur U " +
            " LEFT JOIN Avis Av ON Av.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Annonce A ON U.idUtilisateur = A.idUtilisateur" +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire"+
            " INNER JOIN Engin E ON U.idUtilisateur = E.idUtilisateur" +
            " WHERE E.typeEngin LIKE CONCAT('%', 'Auto', '%') AND " +
            " I.typeVoyage LIKE CONCAT('%', 'Voyage', '%')")
    public List<Object[]> findUserTrajetsAutoVoyage();

    @Query("SELECT DISTINCT A, U, I, Av FROM Utilisateur U " +
            " LEFT JOIN Avis Av ON Av.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Annonce A ON U.idUtilisateur = A.idUtilisateur" +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire"+
            " INNER JOIN Engin E ON U.idUtilisateur = E.idUtilisateur" +
            " WHERE E.typeEngin LIKE CONCAT('%', 'Moto', '%') AND " +
            " I.typeVoyage LIKE CONCAT('%', 'Urbain', '%')")
    public List<Object[]> findUserTrajetsMotoUrbain();

    @Query("SELECT DISTINCT A, U, I, Av FROM Utilisateur U " +
            " LEFT JOIN Avis Av ON Av.idUtilisateur = U.idUtilisateur " +
            " INNER JOIN Annonce A ON U.idUtilisateur = A.idUtilisateur" +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire"+
            " INNER JOIN Engin E ON U.idUtilisateur = E.idUtilisateur" +
            " WHERE E.typeEngin LIKE CONCAT('%', 'Moto', '%') AND " +
            " I.typeVoyage LIKE CONCAT('%', 'Voyage', '%')")
    public List<Object[]> findUserTrajetsMotoVoyage();

    /*********************************==== Recherche des annonces en fonction des filtres =====***********************/
    @Query("SELECT DISTINCT A, U, I, Av FROM Annonce A " +
            " INNER JOIN Utilisateur U ON A.idUtilisateur = U.idUtilisateur" +
            " LEFT JOIN Avis Av ON U.idUtilisateur = Av.idUtilisateur " +
            " INNER JOIN Engin E ON U.idUtilisateur = E.idUtilisateur" +
            " INNER JOIN Itineraire I ON A.idItineraire = I.idItineraire"+
            " INNER JOIN InformationsSupplementaires IFS ON A.idAnnonce = IFS.idAnnonce" +
            " WHERE (:ld IS NULL OR A.depart LIKE CONCAT('%', :ld, '%')) AND " +
            " (:la IS NULL OR A.destination LIKE CONCAT('%', :la, '%')) AND " +
            " (:nb IS NULL OR A.nbrePersonne LIKE CONCAT('%', :nb, '%')) AND " +
            " (:ifs1 = 0 OR IFS.idInformationsSupplementaires LIKE :ifs1 OR " +
            " :ifs2 = 0 OR IFS.idInformationsSupplementaires LIKE :ifs2 OR " +
            " :ifs3 = 0 OR IFS.idInformationsSupplementaires LIKE :ifs3  OR " +
            " :ifs4 = 0 OR IFS.idInformationsSupplementaires LIKE :ifs4  OR " +
            " IFS.idInformationsSupplementaires IS NOT NULL) AND " +
            " (:hd IS NULL OR I.heureDepart LIKE CONCAT('%', :hd, '%')) AND " +
            " (:ha IS NULL OR I.heureArrivee LIKE CONCAT('%', :ha, '%')) AND " +
            " (:d IS NULL OR I.dateDepart LIKE CONCAT('%', :d, '%')) AND " +
            " (:a IS NULL OR I.dateArrivee LIKE CONCAT('%', :a, '%')) AND " +
            " (:te IS NULL OR E.typeEngin LIKE CONCAT('%', :te, '%')) AND " +
            " (:tv IS NULL OR I.typeVoyage LIKE CONCAT('%', :tv, '%')) ")
    public List<Object[]> findAnnoncesByFilters(@Param("ld") String lieuDepart, @Param("la") String lieuArrivee, @Param("nb") String nbrPersonne,
                                                @Param("hd") String heureDepart, @Param("ha") String heureArrivee, @Param("d") String  dateDepart,
                                        @Param("a") String dateArrivee, @Param("te") String typeEngin, @Param("tv") String typeVoyage, @Param("ifs1") int idInfoSup1,
                                        @Param("ifs2") int idInfoSup2, @Param("ifs3") int idInfoSup3, @Param("ifs4") int idInfoSup4);



}
