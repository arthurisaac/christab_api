package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.InformationsSupplementairesRepository;
import com.bafagroupe.christab.entities.InformationsSupplementaires;
import com.bafagroupe.christab.service.InformationsSupplementairesService;
import com.bafagroupe.christab.web.rest.util.HeaderUtil;
import com.bafagroupe.christab.web.rest.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api") @AllArgsConstructor
public class InformationsSupplementairesRessource {

    private  final Logger log = LoggerFactory.getLogger(AnnonceRessource.class);
    private static final String ENTITY_NAME = "Informations supplémentaires";
    private final InformationsSupplementairesService informationsSupplementairesService;
    private final InformationsSupplementairesRepository informationsSupplementairesRepository;

    /* =================== Création ===============*/
    @PostMapping("/createInformationsSupplementaires")
    public ResponseEntity<InformationsSupplementaires> createInformationsSupplementaires(@RequestBody InformationsSupplementaires informationsSupplementaires) throws URISyntaxException, IOException {
        log.debug("REST request to save InformationsSupplementaires : {}", informationsSupplementaires);
        System.out.println("********** Entrées des données *******");
        System.out.println(informationsSupplementaires.getLibelleInformationsSupplementaires());
        System.out.println("*****************");
        InformationsSupplementaires newInformationsSupplementaires = informationsSupplementairesService.CreateInformationsSupplementaires(informationsSupplementaires);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("InformationsSupplementaires créée", String.valueOf(newInformationsSupplementaires.getIdInformationsSupplementaires()))).body(newInformationsSupplementaires);
    }

    /* ======================== Modification ====================*/
    @PostMapping("/updateInformationsSupplementaires")
    public ResponseEntity<InformationsSupplementaires> updateAnnonce(@RequestBody InformationsSupplementaires informationsSupplementaires) throws URISyntaxException, IOException {
        log.debug("REST request to update InformationsSupplementaires : {}", informationsSupplementaires);
        if (informationsSupplementaires.getIdAnnonce() == 0) {
            return createInformationsSupplementaires(informationsSupplementaires);
        } else {
            InformationsSupplementaires result = informationsSupplementairesService.UpdateInformationsSupplementaires(informationsSupplementaires);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(informationsSupplementaires.getIdAnnonce())))
                    .body(result);
        }
    }

    /* ======================== Liste de toutes les Informations Supplémentaires ====================*/
    @GetMapping("/findInformationsSupplementaires")
    public ResponseEntity<List<InformationsSupplementaires>> getAllInformationsSupplementaires() throws IOException {

        return ResponseEntity.ok(informationsSupplementairesService.getAllInformationsSupplementaires());
    }

    /* ======================== Affichage de l'informations supplémentaires d'un utilisateur ====================*/
    @PostMapping("/findInformationsSupplementairesByU")
    public ResponseEntity<List<InformationsSupplementaires>> getAllInformationsSupplementairesByUtilisateur(@RequestBody InformationsSupplementaires infoSup) throws IOException {

        /*System.out.println("******** Paramètres d'entrées ***********");
        System.out.println("======= Id utilisateur =======");
        System.out.println(u.idU);
        System.out.println("======= Id informationsSupplementaires =======");
        System.out.println(u.idA);
        System.out.println("======= Id itineraire =======");
        System.out.println(u.idI);
        System.out.println("*******************************");*/

        return ResponseEntity.ok(informationsSupplementairesService.getAllInformationsSupplementairesByU(infoSup.getIdUtilisateur()));
    }

    /* ======================== Affichage d'une seule Information Supplémentaire ======================*/
    @PostMapping("/findInformationsSupplementaire")
    public ResponseEntity<InformationsSupplementaires> getInformationsSupplementaires(@RequestBody Integer id) throws IOException {
        log.debug("REST request to get InformationsSupplementaires : {}", id);
        InformationsSupplementaires informationsSupplementaires = informationsSupplementairesService.getInformationsSupplementaires(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(informationsSupplementaires));
    }

    /* ============================ Supprimer une Information Supplémentaire =======================*/
    @PostMapping("/deleteInformationsSupplementaires")
    public ResponseEntity<Void> deleteInformationsSupplementaires(@RequestBody Integer id) {
        log.debug("REST request to delete InformationsSupplementaires : {}", id);
        informationsSupplementairesService.deleteInformationsSupplementaires(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
