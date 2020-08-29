package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.DemandeRepository;
import com.bafagroupe.christab.entities.Demande;
import com.bafagroupe.christab.service.DemandeService;
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
public class DemandeRessource {

    private  final Logger log = LoggerFactory.getLogger(DemandeRessource.class);
    private static final String ENTITY_NAME = "Demande";
    private final DemandeRepository demandeRepository;
    private final DemandeService demandeService;

    /* =================== Création ===============*/
    @PostMapping("/createDemande")
    public ResponseEntity<Demande> createDemande(@RequestBody Demande demande) throws URISyntaxException {
        log.debug("REST request to save Demande : {}", demande);
        Demande newDemande = demandeService.CreateDemande(demande);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("Demande créée", newDemande.getDestination())).body(newDemande);
    }

    /* ======================== Modification ====================*/
    @PostMapping("/updateDemande")
    public ResponseEntity<Demande> updateDemande(@RequestBody Demande demande) throws URISyntaxException{
        log.debug("REST request to update Demande : {}", demande);
        if (demande.getIdDemande() == 0) {
            return createDemande(demande);
        } else {
            Demande result = demandeService.UpdateDemande(demande);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(demande.getIdDemande())))
                    .body(result);
        }
    }

    /* ======================== Liste de toutes les Demandes ====================*/
    @GetMapping("/findDemandes")
    public ResponseEntity<List<Demande>> getAllDemande() {

        return ResponseEntity.ok(demandeService.getAllDemande());
    }


    /* ======================== Liste des Demandes d'un utilisateur ====================*/
    @PostMapping("/findDemandesByUtilisateur")
    public ResponseEntity<List<Demande>> getAllDemandesByUtilisateur(@RequestBody Integer id) {

        return ResponseEntity.ok(demandeRepository.findAllDemandesByIdUtilisateur(id));
    }

    /* ======================== Liste de toutes les Demandes avec les informations des utilisateurs et itinéraires pour les passagers ====================*/
    @PostMapping("/findDemandesWithUAndIAvFP")
    public ResponseEntity<List<Object[]>> getAllDemandesWUAIAvisForP(@RequestBody int id) {

        return  ResponseEntity.ok(demandeService.getAllDemandesWUAIAvisForP(id));
    }

    /* ======================== Liste de toutes les Demandes avec les informations des utilisateurs et itinéraires pour les conducteurs ====================*/
    @PostMapping("/findDemandesWithUAndIAvFPEC")
    public ResponseEntity<List<Object[]>> getAllDemandesWUAIAvisForPenCours(@RequestBody int id) {

        return  ResponseEntity.ok(demandeService.getAllDemandesWUAIAvisForPenCours(id));
    }

    /* ======================== Affichage d'une seule Demande ======================*/
    @PostMapping("/findDemande")
    public ResponseEntity<Demande> getDemande(@RequestBody Integer id) {
        log.debug("REST request to get Demande : {}", id);
        Demande demande = demandeService.getDemande(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(demande));
    }

    /* ============================ Supprimer une Demande =======================*/
    @PostMapping("/deleteDemande")
    public ResponseEntity<Void> deleteDemande(@RequestBody Integer id) {
        log.debug("REST request to delete Demande : {}", id);
        demandeService.deleteDemande(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
