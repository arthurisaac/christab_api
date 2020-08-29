package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.PaiementRepository;
import com.bafagroupe.christab.entities.Paiement;
import com.bafagroupe.christab.service.PaiementService;
import com.bafagroupe.christab.web.rest.util.HeaderUtil;
import com.bafagroupe.christab.web.rest.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
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
public class PaiementRessource {

    private  final Logger log = LoggerFactory.getLogger(PaiementRessource.class);
    private static final String ENTITY_NAME = "Paiement";
    private final PaiementRepository paiementRepository;
    private final PaiementService paiementService;

    /* =================== Création ===============*/
    @PostMapping("/createPaiement")
    public ResponseEntity<Paiement> createItineraire(@RequestBody Paiement paiement) throws URISyntaxException {
        log.debug("REST request to save Paiement : {}", paiement);
        Paiement newPaiement = paiementService.CreatePaiement(paiement);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("Paiement créé", newPaiement.getMontantPaiement().toString())).body(newPaiement);
    }

    /* ======================== Modification ====================*/
    @PostMapping("/updatePaiement")
    public ResponseEntity<Paiement> updatePaiement(@RequestBody Paiement paiement) throws URISyntaxException{
        log.debug("REST request to update Paiement : {}", paiement);
        if (paiement.getIdPaiement() == 0) {
            return createItineraire(paiement);
        } else {
            Paiement result = paiementService.UpdatePaiement(paiement);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(paiement.getIdPaiement())))
                    .body(result);
        }
    }

    /* ======================== Liste de tous les Paiements ====================*/
    @GetMapping("/findPaiements")
    public ResponseEntity<List<Paiement>> getAllPaiement() {

        return ResponseEntity.ok(paiementService.getAllPaiement());
    }

    /* ======================== Liste de tous les Paiements entre deux dates ====================*/
    @PostMapping("/findPaiementsBetweenDates")
    public ResponseEntity<List<Paiement>> getAllPaiementsBetweenDates(@RequestBody FilterDates fd) {

        return ResponseEntity.ok(paiementService.getAllPaiementsBetweenDates(fd.startDate, fd.endDate));
    }

    /* ======================== Liste de tous les Paiements par numéro ascendant ====================*/
    @GetMapping("/findPaiementsByNAsc")
    public ResponseEntity<List<Paiement>> getAllPaiementsByNAsc() {

        return ResponseEntity.ok(paiementService.getAllPaiementsByNumAsc());
    }

    /* ======================== Liste de tous les Paiements par numéro descendant ====================*/
    @GetMapping("/findPaiementsByNDesc")
    public ResponseEntity<List<Paiement>> getAllPaiementsByNDesc() {

        return ResponseEntity.ok(paiementService.getAllPaiementsByNumDesc());
    }

    /* ======================== Liste de tous les Paiements par date ascendante ====================*/
    @GetMapping("/findPaiementsByDAsc")
    public ResponseEntity<List<Paiement>> getAllPaiementsByDateAsc() {

        return ResponseEntity.ok(paiementService.getAllPaiementsByDateAsc());
    }

    /* ======================== Liste de tous les Paiements par date descendante ====================*/
    @GetMapping("/findPaiementsByDDesc")
    public ResponseEntity<List<Paiement>> getAllPaiementsByDateDesc() {

        return ResponseEntity.ok(paiementService.getAllPaiementsByDateDesc());
    }

    /* ======================== Liste de tous les Paiements par montant ascendant ====================*/
    @GetMapping("/findPaiementsByMAsc")
    public ResponseEntity<List<Paiement>> getAllPaiementsByMAsc() {

        return ResponseEntity.ok(paiementService.getAllPaiementsByNumAsc());
    }

    /* ======================== Liste de tous les Paiements par montant descendant ====================*/
    @GetMapping("/findPaiementsByMDesc")
    public ResponseEntity<List<Paiement>> getAllPaiementsByMDesc() {

        return ResponseEntity.ok(paiementService.getAllPaiementsByNumDesc());
    }

    /* ======================== Affichage d'un seul Paiement en fonction du trajet ====================*/
    @PostMapping("/findPaiementWItin")
    public ResponseEntity<List<Object[]>> getPaiementWithItineraire(@RequestBody Integer id) {

        return ResponseEntity.ok(paiementRepository.findAllPaiementsWithTrajetById(id));
    }

    /* ======================== Affichage d'un seul Paiement ======================*/
    @PostMapping("/findPaiement")
    public ResponseEntity<Paiement> getPaiement(@RequestBody Integer id) {
        log.debug("REST request to get Paiement : {}", id);
        Paiement paiement = paiementService.getPaiement(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(paiement));
    }

    /* ============================ Supprimer un Paiement =======================*/
    @PostMapping("/deletePaiement")
    public ResponseEntity<Void> deletePaiement(@RequestBody Integer id) {
        log.debug("REST request to delete Paiement : {}", id);
        paiementService.deletePaiement(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}


@Data
class FilterDates {
    String startDate;
    String endDate;
}