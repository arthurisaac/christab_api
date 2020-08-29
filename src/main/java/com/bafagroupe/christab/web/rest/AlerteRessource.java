package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.AlerteRepository;
import com.bafagroupe.christab.entities.Alerte;
import com.bafagroupe.christab.service.AlerteService;
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
public class AlerteRessource {

    private  final Logger log = LoggerFactory.getLogger(AlerteRessource.class);
    private static final String ENTITY_NAME = "Alerte";
    private final AlerteService alerteService;
    private final AlerteRepository alerteRepository;
    // private EntityManager em;

     /*=================== Création ===============*/
    @PostMapping("/createAlerte")
    public ResponseEntity<Alerte> createAlerte(@RequestBody Alerte alerte) throws URISyntaxException {
        log.debug("REST request to save Alerte : {}", alerte);
        Alerte newAlerte = alerteService.CreateAlerte(alerte);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("Alerte créée", newAlerte.getMotifAlerte())).body(newAlerte);
    }

     /*======================== Modification ====================*/
    @PostMapping("/updateAlerte")
    public ResponseEntity<Alerte> updateAlerte(@RequestBody Alerte alerte) throws URISyntaxException{
        log.debug("REST request to update Alerte : {}", alerte);
        // employe.setCodeEmploye(id);
        if (alerte.getIdAlerte() == 0) {
            return createAlerte(alerte);
        } else {
            Alerte result = alerteService.UpdateAlerte(alerte);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(alerte.getIdAlerte())))
                    .body(result);
        }
    }

     /*======================== Liste de toutes les alertes ====================*/
    @GetMapping("/findAlertes")
    public ResponseEntity<List<Alerte>> getAllAlerte() {

        return ResponseEntity.ok(alerteService.getAllAlerte());
    }

     /*======================== Liste d'une seule alerte ======================*/
    @PostMapping("/findAlerte")
    public ResponseEntity<Alerte> getAlerte(@RequestBody Integer id) {
        log.debug("REST request to get Alerte : {}", id);
        Alerte alerte = alerteService.getAlerte(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(alerte));
    }

     /*============================ Supprimer une alerte =======================*/
    @PostMapping("/deleteAlerte")
    public ResponseEntity<Void> deleteAlerte(@RequestBody Integer id) {
        log.debug("REST request to delete Alerte : {}", id);
        alerteService.deleteAlerte(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
