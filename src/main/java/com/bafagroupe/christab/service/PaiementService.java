package com.bafagroupe.christab.service;

import com.bafagroupe.christab.dao.PaiementRepository;
import com.bafagroupe.christab.entities.Historique;
import com.bafagroupe.christab.entities.Paiement;
import com.bafagroupe.christab.web.rest.util.FonctionUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaiementService {

    private  final Logger log = LoggerFactory.getLogger(PaiementService.class);
    private static final String ENTITY_NAME = "Paiement";
    private final PaiementRepository paiementRepository;
    private final HistoriqueService historiqueService;
    private final FonctionUtil fu = new FonctionUtil();

    /**************************** Création d'un Paiement ***************************************************/
    public Paiement CreatePaiement(Paiement paiement) {

        // paiement.setIdPaiement(paiementRepository.findMaxId());
        paiement.setNumeroPaiement(fu.getRandomNumber());
        Paiement newPaiement = paiementRepository.save(paiement);
        log.debug("Created Information for Paiement: {}", newPaiement);

        Historique histo = new Historique();
        histo.setIdEntite(newPaiement.getIdPaiement());
        histo.setValeur(("Numéro client: "+newPaiement.getNumeroClient() +" Code otp: "+ newPaiement.getCodeOtp() +" Montant: "+ newPaiement.getMontantPaiement()
                +" Date: "+ newPaiement.getDatePaiement()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.createHistorique(histo);

        return newPaiement;
    }

    /**************************** Mise à jour d'un Paiement ***************************************************/
    public Paiement UpdatePaiement(Paiement paiement) {

        Paiement newPaiement = paiementRepository.findOneById(paiement.getIdPaiement());
        if(newPaiement != null) {
            paiementRepository.save(paiement);
        }
        log.debug("Updated Information for Paiement: {}", newPaiement);


        Historique histo = new Historique();
        histo.setIdEntite(newPaiement.getIdPaiement());
        histo.setValeur(("Numéro client: "+newPaiement.getNumeroClient() +" Code otp: "+ newPaiement.getCodeOtp() +" Montant: "+ newPaiement.getMontantPaiement()
                +" Date: "+ newPaiement.getDatePaiement()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.updateHistorique(histo);

        return paiement;
    }

    /***************************Affichage d'un Paiement **************************************************/
    public Paiement getPaiement(int id) {
        return paiementRepository.findOneById(id);
    }

    /****************************Liste de tous les Paiements ***************************************************/
    public List<Paiement> getAllPaiement(){
        return paiementRepository.findAll();
    }

    /****************************Liste de tous les Paiements entre deux dates ***************************************************/
    public List<Paiement> getAllPaiementsBetweenDates(String startDate, String endDate){
        return paiementRepository.findPaiementsBetweenDates(startDate, endDate);}

    /****************************Liste de tous les Paiements par numéro ascendant ***************************************************/
    public List<Paiement> getAllPaiementsByNumAsc(){

        return paiementRepository.findPaiementsByNumberAsc();
    }

    /****************************Liste de tous les Paiements par numéro descendant ***************************************************/
    public List<Paiement> getAllPaiementsByNumDesc(){

        return paiementRepository.findPaiementsByNumberDesc();
    }

    /****************************Liste de tous les Paiements par date ascendante ***************************************************/
    public List<Paiement> getAllPaiementsByDateAsc(){

        return paiementRepository.findPaiementsByDateAsc();
    }

    /****************************Liste de tous les Paiements par date descendante ***************************************************/
    public List<Paiement> getAllPaiementsByDateDesc(){

        return paiementRepository.findPaiementsByDateDesc();
    }

    /****************************Liste de tous les Paiements par montant ascendant ***************************************************/
    public List<Paiement> getAllPaiementsByMontantAsc(){

        return paiementRepository.findPaiementsByNumberAsc();
    }

    /****************************Liste de tous les Paiements par montant descendant ***************************************************/
    public List<Paiement> getAllPaiementsByMontantDesc(){

        return paiementRepository.findPaiementsByNumberDesc();
    }

    /****************************Suppression d'un Paiement ***************************************************/
    public void deletePaiement(int id) {

        Paiement newPaiement = paiementRepository.findOneById(id);

        Historique histo = new Historique();
        histo.setIdEntite(newPaiement.getIdPaiement());
        histo.setValeur(("Numéro client: "+newPaiement.getNumeroClient() +" Code otp: "+ newPaiement.getCodeOtp() +" Montant: "+ newPaiement.getMontantPaiement()
                +" Date: "+ newPaiement.getDatePaiement()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.deleteHistorique(histo);

        log.debug("Deleted Information for Paiement: {}", newPaiement);

        paiementRepository.delete(newPaiement);

    }
}
