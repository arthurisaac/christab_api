package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.EnginRepository;
import com.bafagroupe.christab.entities.Engin;
import com.bafagroupe.christab.service.EnginService;
import com.bafagroupe.christab.web.rest.util.HeaderUtil;
import com.bafagroupe.christab.web.rest.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api") @AllArgsConstructor
@RestController
public class EnginRessource {

    private  final Logger log = LoggerFactory.getLogger(EnginRessource.class);
    private static final String ENTITY_NAME = "Engin";
    private final EnginService enginService;
    private final EnginRepository enginRepository;

     /*======================= Création =====================*/
    @PostMapping("/createEngin")
    public ResponseEntity<Engin> createEngin(@RequestBody Engin engin) throws URISyntaxException, IOException {
        log.debug("REST request to save User : {}", engin);
        Engin newEngin = enginService.CreateEngin(engin);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("Engin créé", newEngin.getMarque())).body(newEngin);
    }

     /*======================== Modification ====================*/
    @PostMapping("/updateEngin")
    public ResponseEntity<Engin> updateEngin(@RequestBody Engin engin) throws URISyntaxException, IOException {
        log.debug("REST request to update Alerte : {}", engin);
        if (engin.getIdEngin() == 0) {
            return createEngin(engin);
        } else {
            Engin result = enginService.UpdateEngin(engin);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(engin.getIdEngin())))
                    .body(result);
        }
    }

     /*======================== Liste de tous les Engins ====================*/
    @GetMapping("/findEngins")
    public ResponseEntity<List<Engin>> getAllEngins() throws IOException {
        return ResponseEntity.ok(enginService.getAllEngin());
    }

    /*======================== Liste des Engins d'un utilisateur ====================*/
    @PostMapping("/findEnginsByUser")
    public ResponseEntity<List<Engin>> getAllEnginsByUser(@RequestBody int id) throws IOException {

        return ResponseEntity.ok(enginService.getAllEnginByUser(id));
    }

    /*======================== Liste des Engins d'un utilisateur ====================*/
    @PostMapping("/findEnginsByUAndA")
    public ResponseEntity<Engin> getAllEnginsByUAndA(@RequestBody UtilAnn ua) throws IOException {

        return ResponseEntity.ok(enginRepository.findEnginBUtilisateurAndAnnonce(ua.idU, ua.idA));
    }

     /*======================== Affichage d'un seul Engin ======================*/
    @PostMapping("/findEngin")
    public ResponseEntity<Engin> getUser(@RequestBody int id) throws IOException {
        log.debug("REST request to get User : {}", id);
        Engin engin = enginService.getEngin(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(engin));
    }

     /*============================ Supprimer un Engin =======================*/
    @PostMapping("/deleteEngin")
    public ResponseEntity<Void> deleteUser(@RequestBody int id) {
        log.debug("REST request to delete User : {}", id);
        enginService.deleteEngin(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, String.valueOf(id))).build();
    }
}

@Data
class UtilAnn {
    int idU;
    int idA;
}