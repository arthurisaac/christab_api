package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.HistoriqueRepository;
import com.bafagroupe.christab.entities.Historique;
import com.bafagroupe.christab.service.HistoriqueService;
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
public class HistoriqueRessource {

    private  final Logger log = LoggerFactory.getLogger(HistoriqueRessource.class);
    private static final String ENTITY_NAME = "Historique";
    private final HistoriqueRepository historiqueRepository;
    private final HistoriqueService historiqueService;

    /* =================== Création ===============*/
    @PostMapping("/createHistorique")
    public ResponseEntity<Historique> createHistorique(@RequestBody Historique historique) throws URISyntaxException {
        log.debug("REST request to save Historique : {}", historique);
        Historique newHistorique = historiqueRepository.save(historique);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("Historique créé", newHistorique.getNomEntite())).body(newHistorique);
    }

    /* ======================== Modification ====================*/
    @PostMapping("/updateHistorique")
    public ResponseEntity<Historique> updateHistorique(@RequestBody Historique historique) throws URISyntaxException{
        log.debug("REST request to update Historique : {}", historique);
        if (historique.getIdHistorique() == 0) {
            return createHistorique(historique);
        } else {
            Historique result = historiqueRepository.saveAndFlush(historique);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(historique.getIdHistorique())))
                    .body(result);
        }
    }

    /* ======================== Liste de tous les Historiques ====================*/
    @GetMapping("/findHistoriques")
    public ResponseEntity<List<Historique>> getAllHistorique() {
        return ResponseEntity.ok(historiqueRepository.findAll());
    }

    /* ======================== Affichage d'un seul Historique ======================*/
    @PostMapping("/findHistorique")
    public ResponseEntity<Historique> getHistorique(@RequestBody Integer id) {
        log.debug("REST request to get Historique : {}", id);
        Historique historique = historiqueRepository.getOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(historique));
    }

    /* ============================ Supprimer un Historique =======================*/
    @PostMapping("/deleteHistorique")
    public ResponseEntity<Void> deleteHistorique(@RequestBody Integer id) {
        log.debug("REST request to delete Historique : {}", id);
        historiqueRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
