package com.bafagroupe.christab.service;

import com.bafagroupe.christab.dao.AnnonceRepository;
import com.bafagroupe.christab.entities.*;
import com.bafagroupe.christab.web.rest.util.FonctionUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AnnonceService {

    private  final Logger log = LoggerFactory.getLogger(AnnonceService.class);
    private static final String ENTITY_NAME = "Annonce";
    private final AnnonceRepository annonceRepository;
    private final HistoriqueService historiqueService;
    private final FonctionUtil fu = new FonctionUtil();

    /**************************** Création d'une Annonce ***************************************************/
    public Annonce CreateAnnonce(Annonce annonce) {

        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //pour enregistrer la date sous ce format

        // annonce.setIdAnnonce(annonceRepository.findMaxId());
        annonce.setDateAnnonce(ZonedDateTime.now().toLocalDate().toString());
        annonce.setPublier(true);
        Annonce newAnnonce = annonceRepository.save(annonce);
        log.debug("Created Information for Annonce: {}", newAnnonce);

        Historique histo = new Historique();
        histo.setIdEntite(newAnnonce.getIdAnnonce());
        histo.setValeur(("Libellé: "+newAnnonce.getLibelleAnnonce() +" Date: "+ newAnnonce.getDateAnnonce() +" Date de départ: "+ newAnnonce.getDateDepart()
                +" Lieu de départ: "+ newAnnonce.getDepart() +" Destination: "+ newAnnonce.getDestination() +" Plage prix: "+ newAnnonce.getPlagePrix()
                +" Place: "+ newAnnonce.getNbrePersonne() +" Publier?: "+ newAnnonce.getPublier()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.createHistorique(histo);

        return newAnnonce;
    }

    /**************************** Mise à jour d'une Annonce ***************************************************/
    public Annonce UpdateAnnonce(Annonce annonce) {

        Annonce newAnnonce = annonceRepository.findOneById(annonce.getIdAnnonce());
        if(newAnnonce != null) {
            annonceRepository.save(annonce);
        }

        log.debug("Created Information for Annonce: {}", newAnnonce);


        Historique histo = new Historique();
        histo.setIdEntite(newAnnonce.getIdAnnonce());
        histo.setValeur(("Libellé: "+newAnnonce.getLibelleAnnonce() +" Date: "+ newAnnonce.getDateAnnonce() +" Date de départ: "+ newAnnonce.getDateDepart()
                +" Lieu de départ: "+ newAnnonce.getDepart() +" Destination: "+ newAnnonce.getDestination() +" Plage prix: "+ newAnnonce.getPlagePrix()
                +" Place: "+ newAnnonce.getNbrePersonne() +" Publier?: "+ newAnnonce.getPublier()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.updateHistorique(histo);

        return annonce;
    }

    /****************************Affichage d'une Annonce ***************************************************/
    public Annonce getAnnonce(int id) {
        return annonceRepository.findOneById(id);
    }

    /**************************** Liste de toutes les Annonces ***************************************************/
    public List<Annonce> getAllAnnonce(){
        return annonceRepository.findAll();
    }

    /* ======================== Liste de toutes les Annonces, itinéraires avec les informations des conducteurs et leurs avis  ====================*/
    public List<Object[]> getAllAnnoncesWUAIAvis() {
        // List<String>photoUtilisateur = new ArrayList<>();
        List<Object[]> annonces = annonceRepository.findAllAnnoncesWithUAIAAvis();
        return annonces; // annonceRepository.findAllAnnoncesWithUtilisateurs();
    }

    /* ======================== Liste de toutes les Annonces, itinéraires avec les informations des conducteurs et leurs avis pour les conducteurs  ====================*/
    public List<Object[]> getAllAnnoncesWUAIAvisForC(int id) {
        return annonceRepository.findAllAnnoncesWithUAIAAvisForC(id);
    }

    /* ======================== Liste de toutes les Annonces, itinéraires avec les informations des conducteurs et leurs avis pour les passagers ====================*/
    public List<Object[]> getAllAnnoncesWUAIAvisForP(int id) {
        return annonceRepository.findAllAnnoncesWithUAIAAvisForP(id);
    }

    /* ======================== Liste de toutes les Annonces, itinéraires avec les informations des conducteurs et leurs avis pour les conducteurs  ====================*/
    public List<Object[]> getAllAnnoncesWUAIAvisForCenCours(int id) {
        return annonceRepository.findAllAnnoncesWithUAIAAvisForCenCours(id);
    }

    /* ======================== Liste de toutes les Annonces, itinéraires avec les informations des conducteurs et leurs avis pour les passagers  ====================*/
    public List<Object[]> getAllAnnoncesWUAIAvisForPenCours(int id) {
        return annonceRepository.findAllAnnoncesWithUAIAAvisForPenCours(id);
    }

    /****************** Liste triée de toutes les Annonces par prix ascendant  **************/
    public List<Object[]> getAllAnnoncesWUAIAvisBPrixA() {

        List<String>photoUtilisateur = new ArrayList<>();
        List<Object[]> annonces = annonceRepository.findAllAnnoncesWithUAIAAvisByPrixA();
        return annonces;
    }

    /****************** Liste triée de toutes les Annonces par prix descendant  **************/
    public List<Object[]> getAllAnnoncesWUAIAvisBPrixD() {

        List<String>photoUtilisateur = new ArrayList<>();
        List<Object[]> annonces = annonceRepository.findAllAnnoncesWithUAIAAvisByPrixD();
        return annonces;
    }

    /****************** Liste triée de toutes les Annonces par place ascendante  **************/
    public List<Object[]> getAllAnnoncesWUAIAvisBPlaceA() {

        List<String>photoUtilisateur = new ArrayList<>();
        List<Object[]> annonces = annonceRepository.findAllAnnoncesWithUAIAAvisByPlaceA();
        return annonces;
    }

    /****************** Liste triée de toutes les Annonces par place descendante  **************/
    public List<Object[]> getAllAnnoncesWUAIAvisBPlaceD() {

        List<String>photoUtilisateur = new ArrayList<>();
        List<Object[]> annonces = annonceRepository.findAllAnnoncesWithUAIAAvisByPlaceD();
        return annonces;
    }

    /****************** Liste triée de toutes les Annonces par date ascendante  **************/
    public List<Object[]> getAllAnnoncesWUAIAvisBDepA() {

        List<String>photoUtilisateur = new ArrayList<>();
        List<Object[]> annonces = annonceRepository.findAllAnnoncesWithUAIAAvisByDepA();
        return annonces;
    }

    /****************** Liste triée de toutes les Annonces par date descendante  **************/
    public List<Object[]> getAllAnnoncesWUAIAvisBDepD() {

        List<String>photoUtilisateur = new ArrayList<>();
        List<Object[]> annonces = annonceRepository.findAllAnnoncesWithUAIAAvisByDepD();
        return annonces;
    }

    /************************** Liste de toutes les Annonces, itinéraires avis d'un conducteur  ******************/
    public List<Object[]> getAllAnnoncesWithAIAAvisByU(int idU, int idA, int idI) {
        List<Object[]> annonces = annonceRepository.findAllAnnoncesWithAIAAvisByU(idU, idA, idI);
        return annonces;
    }

    /************************** Liste de toutes les Annonces, itinéraires avis d'un conducteur  ******************/
    public List<Object[]> getAllAnnoncesWithAIAAvisByUT() {
        List<Object[]> annonces = annonceRepository.findAllAnnoncesWithAIAAvisByUTest();
        return annonces;
    }

    /****************************Affichage de toutes les Annonces en fonction des filtres ***************************************************/
    public List<Object[]> getAllAnnoncesByFilters(String lieuDepart, String lieuArrivee, String nbrPersonne, String heureDepart,
                                                  String heureArrivee, String dateDepart, String dateArrivee, String typeEngin,
                                                  String typeVoyage, int idInformationsSupplementaires1, int idInformationsSupplementaires2,
                                                  int idInformationsSupplementaires3, int idInformationsSupplementaires4) {

        List<Object[]> annonces = annonceRepository.findAnnoncesByFilters(lieuDepart, lieuArrivee, nbrPersonne, heureDepart,
                heureArrivee, dateDepart, dateArrivee, typeEngin, typeVoyage, idInformationsSupplementaires1,
                idInformationsSupplementaires2, idInformationsSupplementaires3, idInformationsSupplementaires4);
        return annonces; // annonceRepository.findAllAnnoncesWithUtilisateurs();
    }

    /********************* ==== Liste de toutes les Annonces avec les informations des utilisateurs et itinéraires === ***********/
    public List<Object[]> getAllAnnoncesWUAI() {

        List<Object[]> annonces = annonceRepository.findAllAnnoncesWithUtilisateurs();
        return annonces; // annonceRepository.findAllAnnoncesWithUtilisateurs();
    }

    /**************************************** Liste des trajets auto urbain ************************************/
    public List<Object[]> getAllAnnoncesAutoUrbain() {
        List<Object[]> annonces = annonceRepository.findUserTrajetsAutoUrbain();
        return annonces;
    }

    /**************************************** Liste des trajets auto voyage ************************************/
    public List<Object[]> getAllAnnoncesAutoVoyage() {
        List<Object[]> annonces = annonceRepository.findUserTrajetsAutoVoyage();
        return annonces;
    }

    /**************************************** Liste des trajets moto urbain ************************************/
    public List<Object[]> getAllAnnoncesMotoUrbain() {
        List<Object[]> annonces = annonceRepository.findUserTrajetsMotoUrbain();
        return annonces;
    }

    /**************************************** Liste des trajets moto voyage ************************************/
    public List<Object[]> getAllAnnoncesMotoVoyage() {
        List<Object[]> annonces = annonceRepository.findUserTrajetsMotoVoyage();
        return annonces;
    }

    /**************************** Suppression d'une Annonce ***************************************************/
    public void deleteAnnonce(int id) {

        Annonce newAnnonce = annonceRepository.findOneById(id);

        Historique histo = new Historique();
        histo.setIdEntite(newAnnonce.getIdAnnonce());
        histo.setValeur(("Libellé: "+newAnnonce.getLibelleAnnonce() +" Date: "+ newAnnonce.getDateAnnonce() +" Date de départ: "+ newAnnonce.getDateDepart()
                +" Lieu de départ: "+ newAnnonce.getDepart() +" Destination: "+ newAnnonce.getDestination() +" Plage prix: "+ newAnnonce.getPlagePrix()
                +" Place: "+ newAnnonce.getNbrePersonne() +" Publier?: "+ newAnnonce.getPublier()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.deleteHistorique(histo);

        annonceRepository.delete(newAnnonce);

    }
}
