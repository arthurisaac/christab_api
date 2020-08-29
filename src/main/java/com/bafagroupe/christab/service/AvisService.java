package com.bafagroupe.christab.service;

import com.bafagroupe.christab.dao.AvisRepository;
import com.bafagroupe.christab.entities.Avis;
import com.bafagroupe.christab.entities.Historique;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvisService {

    private  final Logger log = LoggerFactory.getLogger(AvisService.class);
    private static final String ENTITY_NAME = "Avis";
    private final AvisRepository avisRepository;
    private final HistoriqueService historiqueService;

    /**************************** Création d'un Avis ***************************************************/
    public Avis CreateAvis(Avis avis) {

        // avis.setIdAvis(avisRepository.findMaxId());
        Avis newAvis = avisRepository.save(avis);
        log.debug("Created Information for Annonce: {}", newAvis);

        Historique histo = new Historique();
        histo.setIdEntite(newAvis.getIdAvis());
        histo.setValeur(("Avis: "+newAvis.getAvis() +" Date: "+ newAvis.getDateAvis()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.createHistorique(histo);

        return newAvis;
    }

    /**************************** Mise à jour d'un Avis ***************************************************/
    public Avis UpdateAvis(Avis avis) {

        Avis newAvis = avisRepository.findOneById(avis.getIdAvis());
        if(newAvis != null) {
            avisRepository.save(avis);
        }

        log.debug("Created Information for Avis: {}", newAvis);


        Historique histo = new Historique();
        histo.setIdEntite(newAvis.getIdAvis());
        histo.setValeur(("Avis: "+newAvis.getAvis() +" Date: "+ newAvis.getDateAvis()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.updateHistorique(histo);

        return avis;
    }

    /****************************Affichage d'un Avis ***************************************************/
    public Avis getAvis(int id) {
        return avisRepository.findOneById(id);
    }

    /****************************Affichage de tous les Avis ***************************************************/
    public List<Avis> getAllAvis(){
        return avisRepository.findAll();
    }

    /****************************Suppression d'un Avis ***************************************************/
    public void deleteAvis(int id) {

        Avis newAvis = avisRepository.findOneById(id);

        Historique histo = new Historique();
        histo.setIdEntite(newAvis.getIdAvis());
        histo.setValeur(("Avis: "+newAvis.getAvis() +" Date: "+ newAvis.getDateAvis()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.deleteHistorique(histo);

        avisRepository.delete(newAvis);

    }
}
