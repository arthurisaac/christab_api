package com.bafagroupe.christab.service;

import com.bafagroupe.christab.dao.AlerteRepository;
import com.bafagroupe.christab.entities.Alerte;
import com.bafagroupe.christab.entities.Historique;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AlerteService {

    private  final Logger log = LoggerFactory.getLogger(AlerteService.class);
    private static final String ENTITY_NAME = "Alerte";
    private final AlerteRepository alerteRepository;
    private final HistoriqueService historiqueService;

    /**************************** Création d'une Alerte ***************************************************/
    public Alerte CreateAlerte(Alerte alerte) {

        // DecimalFormat df = new DecimalFormat("#.#######");
        // df.format(0.912385);
        // System.out.println("/***************** Latitude *****************/");
        // System.out.println(df.format(alerte.getLatitude()));
        // System.out.println("/**********************************/");
        // System.out.println("/***************** Longitude *****************/");
        // System.out.println(df.format(alerte.getLongitude()));
        // System.out.println("/**********************************/");

        // alerte.setIdAlerte(alerteRepository.findMaxId());
        Alerte newAlerte = alerteRepository.save(alerte);
        log.debug("Created Information for Alerte: {}", newAlerte);

        Historique histo = new Historique();
        histo.setIdEntite(newAlerte.getIdAlerte());
        histo.setValeur(("Motif: "+newAlerte.getMotifAlerte() +" Date: "+ newAlerte.getDateAlerte() +" Nombre: "+ newAlerte.getNbreAlerte()).toString());
        histo.setLatitude(newAlerte.getLatitude());
        histo.setLongitude(newAlerte.getLongitude());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.createHistorique(histo);

        return newAlerte;
    }

    /**************************** Mise à jour d'une Alerte ***************************************************/
    public Alerte UpdateAlerte(Alerte alerte) {

        Alerte newAlerte = alerteRepository.findOneById(alerte.getIdAlerte());
        if(newAlerte != null) {
            alerteRepository.save(alerte);
        }

        log.debug("Created Information for Alerte: {}", newAlerte);


        Historique histo = new Historique();
        histo.setIdEntite(newAlerte.getIdAlerte());
        histo.setValeur(("Motif: "+newAlerte.getMotifAlerte() +" Date: "+ newAlerte.getDateAlerte() +" Nombre: "+ newAlerte.getNbreAlerte()).toString());
        histo.setLatitude(newAlerte.getLatitude());
        histo.setLongitude(newAlerte.getLongitude());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.updateHistorique(histo);

        return alerte;
    }

    /****************************Affichage d'une Alerte ***************************************************/
    public Alerte getAlerte(int id) {
        return alerteRepository.findOneById(id);
    }

    /****************************Affichage de toutes les Alertes ***************************************************/
    public List<Alerte> getAllAlerte(){
        return alerteRepository.findAll();
    }

    /****************************Suppression d'une Alerte ***************************************************/
    public void deleteAlerte(int id) {

        Alerte newAlerte = alerteRepository.findOneById(id);

        Historique histo = new Historique();
        histo.setIdEntite(newAlerte.getIdAlerte());
        histo.setValeur(("Motif: "+newAlerte.getMotifAlerte() +" Date: "+ newAlerte.getDateAlerte() +" Nombre: "+ newAlerte.getNbreAlerte()).toString());
        histo.setLatitude(newAlerte.getLatitude());
        histo.setLongitude(newAlerte.getLongitude());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.deleteHistorique(histo);

        alerteRepository.delete(newAlerte);

    }
}
