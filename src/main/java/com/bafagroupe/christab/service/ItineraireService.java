package com.bafagroupe.christab.service;

import com.bafagroupe.christab.dao.ItineraireRepository;
import com.bafagroupe.christab.entities.Historique;
import com.bafagroupe.christab.entities.Itineraire;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItineraireService {

    private  final Logger log = LoggerFactory.getLogger(ItineraireService.class);
    private static final String ENTITY_NAME = "Itineraire";
    private final ItineraireRepository itineraireRepository;
    private final HistoriqueService historiqueService;

    /**************************** Création d'un Itineraire ***************************************************/
    public Itineraire CreateItineraire(Itineraire itineraire) {

        // itineraire.setIdItineraire(itineraireRepository.findMaxId());
        Itineraire newItineraire = itineraireRepository.save(itineraire);
        log.debug("Created Information for Itineraire: {}", newItineraire);

        Historique histo = new Historique();
        histo.setIdEntite(newItineraire.getIdItineraire());
        histo.setValeur(("Type de voyage: "+newItineraire.getTypeVoyage() +" Date de départ: "+ newItineraire.getDateDepart() +" Date d'arrivée: "+ newItineraire.getDateArrivee()
                +" Heure de départ: "+ newItineraire.getHeureArrivee() +" Heure d'arrivée: "+ newItineraire.getHeureArrivee() +" Destination: "+ newItineraire.getTypeLocalite()
                +" Confirmer départ?: "+ newItineraire.getConfirmerDepart() +" Confrimer arrivée??: "+ newItineraire.getConfirmerArrivee()).toString());
        histo.setLatitude(newItineraire.getLatitude());
        histo.setLongitude(newItineraire.getLongitude());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.createHistorique(histo);

        return newItineraire;
    }

    /**************************** Mise à jour d'un Itineraire ***************************************************/
    public Itineraire UpdateItineraire(Itineraire itineraire) {

        Itineraire newItineraire = itineraireRepository.findOneById(itineraire.getIdItineraire());
        if(newItineraire != null) {
            itineraireRepository.save(itineraire);
        }
        log.debug("Updated Information for Itineraire: {}", newItineraire);


        Historique histo = new Historique();
        histo.setIdEntite(newItineraire.getIdItineraire());
        histo.setValeur(("Type de voyage: "+newItineraire.getTypeVoyage() +" Date de départ: "+ newItineraire.getDateDepart() +" Date d'arrivée: "+ newItineraire.getDateArrivee()
                +" Heure de départ: "+ newItineraire.getHeureArrivee() +" Heure d'arrivée: "+ newItineraire.getHeureArrivee() +" Destination: "+ newItineraire.getTypeLocalite()
                +" Confirmer départ?: "+ newItineraire.getConfirmerDepart() +" Confrimer arrivée??: "+ newItineraire.getConfirmerArrivee()).toString());
        histo.setLatitude(newItineraire.getLatitude());
        histo.setLongitude(newItineraire.getLongitude());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.updateHistorique(histo);

        return itineraire;
    }

    /****************************Affichage d'un Itineraire ***************************************************/
    public Itineraire getItineraire(int id) {
        return itineraireRepository.findOneById(id);
    }

    /****************************Affichage de tous les Itineraires ***************************************************/
    public List<Itineraire> getAllItineraire(){
        return itineraireRepository.findAll();
    }

    /****************************Suppression d'un Itineraire ***************************************************/
    public void deleteItineraire(int id) {

        Itineraire newItineraire = itineraireRepository.findOneById(id);

        Historique histo = new Historique();
        histo.setIdEntite(newItineraire.getIdItineraire());
        histo.setValeur(("Type de voyage: "+newItineraire.getTypeVoyage() +" Date de départ: "+ newItineraire.getDateDepart() +" Date d'arrivée: "+ newItineraire.getDateArrivee()
                +" Heure de départ: "+ newItineraire.getHeureArrivee() +" Heure d'arrivée: "+ newItineraire.getHeureArrivee() +" Localité: "+ newItineraire.getTypeLocalite()
                +" Confirmer départ?: "+ newItineraire.getConfirmerDepart() +" Confrimer arrivée??: "+ newItineraire.getConfirmerArrivee()).toString());
        histo.setLatitude(newItineraire.getLatitude());
        histo.setLongitude(newItineraire.getLongitude());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.deleteHistorique(histo);

        log.debug("Deleted Information for Itineraire: {}", newItineraire);

        itineraireRepository.delete(newItineraire);

    }
}
