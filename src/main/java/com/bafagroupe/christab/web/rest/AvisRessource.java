package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.AvisRepository;
import com.bafagroupe.christab.entities.Avis;
import com.bafagroupe.christab.service.AvisService;
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
@AllArgsConstructor
@RequestMapping("/api")
public class AvisRessource {

    private  final Logger log = LoggerFactory.getLogger(AvisRessource.class);
    private static final String ENTITY_NAME = "Avis";
    private final AvisRepository avisRepository;
    private final AvisService avisService;

    /* =================== Création ===============*/
    @PostMapping("/createAvis")
    public ResponseEntity<Avis> createAvis(@RequestBody Avis avis) throws URISyntaxException {
        log.debug("REST request to save Annonce : {}", avis);
        Avis newAvis = avisService.CreateAvis(avis);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("Avis créé", newAvis.getAvis())).body(newAvis);
    }

    /* ======================== Modification ====================*/
    @PostMapping("/updateAvis")
    public ResponseEntity<Avis> updateAvis(@RequestBody Avis avis) throws URISyntaxException{
        log.debug("REST request to update Avis : {}", avis);
        if (avis.getIdAvis() == 0) {
            return createAvis(avis);
        } else {
            Avis result = avisService.UpdateAvis(avis);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(avis.getIdAvis())))
                    .body(result);
        }
    }

    /* ======================== Liste de tous les Avis ====================*/
    @GetMapping("/findAllAvis")
    public ResponseEntity<List<Avis>> getAllAvis() {
        return ResponseEntity.ok(avisService.getAllAvis());
    }

    /* ======================== Liste de tous les Avis en fonction d'un utilisateur ====================*/
    @PostMapping("/findAllAvisByU")
    public ResponseEntity<List<Avis>> getAllAvisByU(@RequestBody int id) {

        return ResponseEntity.ok(avisRepository.findAllByIdUtilisateur(id));
    }

    /* ======================== Affichage d'un seul Avis ======================*/
    @PostMapping("/findAvis")
    public ResponseEntity<Avis> getAvis(@RequestBody Integer id) {
        log.debug("REST request to get Avis : {}", id);
        Avis avis = avisService.getAvis(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(avis));
    }

    /* ============================ Supprimer un Avis =======================*/
    @PostMapping("/deleteAvis")
    public ResponseEntity<Void> deleteAvis(@RequestBody Integer id) {
        log.debug("REST request to delete Avis : {}", id);
        avisService.deleteAvis(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

