package com.bafagroupe.christab.service;

import com.bafagroupe.christab.dao.DemandeRepository;
import com.bafagroupe.christab.entities.Demande;
import com.bafagroupe.christab.entities.Historique;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DemandeService {

    private  final Logger log = LoggerFactory.getLogger(DemandeService.class);
    private static final String ENTITY_NAME = "Demande";
    private final DemandeRepository demandeRepository;
    private final HistoriqueService historiqueService;

    /**************************** Création d'une Demande ***************************************************/
    public Demande CreateDemande(Demande demande) {

        // demande.setIdDemande(demandeRepository.findMaxId());
        demande.setDateDemande(ZonedDateTime.now().toString());
        Demande newDemande = demandeRepository.save(demande);
        log.debug("Created Information for Demande: {}", newDemande);

        Historique histo = new Historique();
        histo.setIdEntite(newDemande.getIdDemande());
        histo.setValeur(("Destination: "+newDemande.getDestination() +" Date: "+ newDemande.getDateDemande()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.createHistorique(histo);

        return newDemande;
    }

    /**************************** Mise à jour d'une Demande ***************************************************/
    public Demande UpdateDemande(Demande demande) {

        Demande newDemande = demandeRepository.findOneById(demande.getIdDemande());
        if(newDemande != null) {
            demandeRepository.save(demande);
        }

        log.debug("Created Information for Demande: {}", newDemande);


        Historique histo = new Historique();
        histo.setIdEntite(newDemande.getIdDemande());
        histo.setValeur(("Destination: "+newDemande.getDestination() +" Date: "+ newDemande.getDateDemande()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.updateHistorique(histo);

        return demande;
    }

    /****************************Affichage d'une Demande ***************************************************/
    public Demande getDemande(int id) {
        return demandeRepository.findOneById(id);
    }

    /* ======================== Liste de toutes les demandes, itinéraires avec les informations des conducteurs et leurs avis pour les passagers ====================*/
    public List<Object[]> getAllDemandesWUAIAvisForP(int id) {
        return demandeRepository.findAllDemandesWithUAIAAvisForP(id);
    }

    /* ======================== Liste de toutes les demandes, itinéraires avec les informations des conducteurs et leurs avis pour les passagers  ====================*/
    public List<Object[]> getAllDemandesWUAIAvisForPenCours(int id) {
        return demandeRepository.findAllDemandesWithUAIAAvisForPenCours(id);
    }

    /****************************Affichage de toutes les Demandes ***************************************************/
    public List<Demande> getAllDemande(){
        return demandeRepository.findAll();
    }

    /****************************Suppression d'une Demande ***************************************************/
    public void deleteDemande(int id) {

        Demande newDemande = demandeRepository.findOneById(id);

        Historique histo = new Historique();
        histo.setIdEntite(newDemande.getIdDemande());
        histo.setValeur(("Destination: "+newDemande.getDestination() +" Date: "+ newDemande.getDateDemande()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.deleteHistorique(histo);

        demandeRepository.delete(newDemande);

    }
}
