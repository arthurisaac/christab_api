package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.ItineraireRepository;
import com.bafagroupe.christab.entities.Itineraire;
import com.bafagroupe.christab.service.ItineraireService;
import com.bafagroupe.christab.web.rest.util.HeaderUtil;
import com.bafagroupe.christab.web.rest.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ItineraireRessource {

    private  final Logger log = LoggerFactory.getLogger(ItineraireRessource.class);
    private static final String ENTITY_NAME = "Itineraire";
    private final ItineraireService itineraireService;
    private final ItineraireRepository itineraireRepository;

    /* =================== Création ===============*/
    @PostMapping("/createItineraire")
    public ResponseEntity<Itineraire> createItineraire(@RequestBody Itineraire itineraire) throws URISyntaxException {
        log.debug("REST request to save Itineraire : {}", itineraire);
        Itineraire newItineraire = itineraireService.CreateItineraire(itineraire);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("Itineraire créé", newItineraire.getTypeLocalite())).body(newItineraire);
    }

    /* ======================== Modification ====================*/
    @PostMapping("/updateItineraire")
    public ResponseEntity<Itineraire> updateItineraire(@RequestBody Itineraire itineraire) throws URISyntaxException{
        log.debug("REST request to update Itineraire : {}", itineraire);
        if (itineraire.getIdItineraire() == 0) {
            return createItineraire(itineraire);
        } else {
            Itineraire result = itineraireService.UpdateItineraire(itineraire);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(itineraire.getIdItineraire())))
                    .body(result);
        }
    }

    /* ======================== Liste de tous les Itineraires ====================*/
    @GetMapping("/findItineraires")
    public ResponseEntity<List<Itineraire>> getAllItineraire() {
        return ResponseEntity.ok(itineraireService.getAllItineraire());
    }

    /* ======================== Affichage d'un seul Itineraire ======================*/
    @PostMapping("/findItineraire")
    public ResponseEntity<Itineraire> getItineraire(@RequestBody Integer id) {
        log.debug("REST request to get Itineraire : {}", id);
        Itineraire itineraire = itineraireService.getItineraire(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(itineraire));
    }

    /* ============================ Supprimer un Itineraire =======================*/
    @PostMapping("/deleteItineraire")
    public ResponseEntity<Void> deleteItineraire(@RequestBody Integer id) {
        log.debug("REST request to delete Itineraire : {}", id);
        itineraireService.deleteItineraire(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
