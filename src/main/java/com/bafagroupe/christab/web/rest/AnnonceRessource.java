package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.AnnonceRepository;
import com.bafagroupe.christab.entities.Annonce;
import com.bafagroupe.christab.service.AnnonceService;
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

@RestController
@RequestMapping("/api") @AllArgsConstructor
public class AnnonceRessource {

    private  final Logger log = LoggerFactory.getLogger(AnnonceRessource.class);
    private static final String ENTITY_NAME = "Annonce";
    private final AnnonceService annonceService;
    private final AnnonceRepository annonceRepository;

    /* =================== Création ===============*/
    @PostMapping("/createAnnonce")
    public ResponseEntity<Annonce> createAnnonce(@RequestBody Annonce annonce) throws URISyntaxException {
        log.debug("REST request to save Annonce : {}", annonce);
        Annonce newAnnonce = annonceService.CreateAnnonce(annonce);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("Annonce créée", newAnnonce.getLibelleAnnonce())).body(newAnnonce);
    }

    /* ======================== Modification ====================*/
    @PostMapping("/updateAnnonce")
    public ResponseEntity<Annonce> updateAnnonce(@RequestBody Annonce annonce) throws URISyntaxException{
        log.debug("REST request to update Annonce : {}", annonce);
        if (annonce.getIdAnnonce() == 0) {
            return createAnnonce(annonce);
        } else {
            Annonce result = annonceService.UpdateAnnonce(annonce);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(annonce.getIdAnnonce())))
                    .body(result);
        }
    }

    /* ======================== Liste de toutes les Annonces ====================*/
    @GetMapping("/findAnnonces")
    public ResponseEntity<List<Annonce>> getAllAnnonce() {

        return ResponseEntity.ok(annonceService.getAllAnnonce());
    }

    /* ======================== Liste de toutes les Annonces avec les informations des utilisateurs et itinéraires ====================*/
    @GetMapping("/findAnnoncesWithUAndI")
    public ResponseEntity<List<Object[]>> getAllAnnoncesWUAI() throws IOException {

        // return ResponseEntity.ok(annonceRepository.findAllAnnoncesWithUtilisateurs());
        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAI());
    }

    /* ======================== Liste de toutes les Annonces avec les informations des utilisateurs et itinéraires ====================*/
    @GetMapping("/findAnnoncesWithUAndIAv")
    public ResponseEntity<List<Object[]>> getAllAnnoncesWUAIAvis() {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAIAvis());
    }

    /* ======================== Liste de toutes les Annonces avec les informations des utilisateurs et itinéraires pour les conducteurs ====================*/
    @PostMapping("/findAnnoncesWithUAndIAvFC")
    public ResponseEntity<List<Object[]>> getAllAnnoncesWUAIAvisForC(@RequestBody int id) {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAIAvisForC(id));
    }

    /* ======================== Liste de toutes les Annonces avec les informations des utilisateurs et itinéraires pour les passagers ====================*/
    @PostMapping("/findAnnoncesWithUAndIAvFP")
    public ResponseEntity<List<Object[]>> getAllAnnoncesWUAIAvisForP(@RequestBody int id) {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAIAvisForP(id));
    }

    /* ======================== Liste de toutes les Annonces avec les informations des utilisateurs et itinéraires pour les conducteurs ====================*/
    @PostMapping("/findAnnoncesWithUAndIAvFCEC")
    public ResponseEntity<List<Object[]>> getAllAnnoncesWUAIAvisForCenCours(@RequestBody int id) {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAIAvisForCenCours(id));
    }

    /* ======================== Liste de toutes les Annonces avec les informations des utilisateurs et itinéraires pour les conducteurs ====================*/
    @PostMapping("/findAnnoncesWithUAndIAvFPEC")
    public ResponseEntity<List<Object[]>> getAllAnnoncesWUAIAvisForPenCours(@RequestBody int id) {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAIAvisForCenCours(id));
    }

    /*************** Liste triée des Annonces par prix ascendant **********/
    @GetMapping("/findAnnoncesByPrA")
    public ResponseEntity<List<Object[]>> getAllAnnoncesByPA() throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAIAvisBPrixA());
    }

    /*************** Liste triée des Annonces par prix descendant ********/
    @GetMapping("/findAnnoncesByPrD")
    public ResponseEntity<List<Object[]>> getAllAnnoncesByPD() throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAIAvisBPrixD());
    }

    /*************** Liste triée des Annonces par place ascendante ********/
    @GetMapping("/findAnnoncesByPlA")
    public ResponseEntity<List<Object[]>> getAllAnnoncesByPlA() throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAIAvisBPlaceA());
    }

    /*************** Liste triée des Annonces par place descendante ********/
    @GetMapping("/findAnnoncesByPlD")
    public ResponseEntity<List<Object[]>> getAllAnnoncesByPlD() throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAIAvisBPlaceD());
    }

    /*************** Liste triée des Annonces par date ascendante ********/
    @GetMapping("/findAnnoncesByDepA")
    public ResponseEntity<List<Object[]>> getAllAnnoncesByDepA() throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAIAvisBDepA());
    }

    /*************** Liste triée des Annonces par date descendante ********/
    @GetMapping("/findAnnoncesByDepD")
    public ResponseEntity<List<Object[]>> getAllAnnoncesByDepD() throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWUAIAvisBDepD());
    }

    /* ======================== Liste des trajets auto urbain ====================*/
    @GetMapping("/findAnnoncesAU")
    public ResponseEntity<List<Object[]>> getAllAnnoncesAutoUrbain() throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesAutoUrbain());
    }

    /* ======================== Liste des trajets auto voyage ====================*/
    @GetMapping("/findAnnoncesAV")
    public ResponseEntity<List<Object[]>> getAllAnnoncesAutoVoyage() throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesAutoVoyage());
    }

    /* ======================== Liste des trajets moto urbain ====================*/
    @GetMapping("/findAnnoncesMU")
    public ResponseEntity<List<Object[]>> getAllAnnoncesMotoUrbain() throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesMotoUrbain());
    }

    /* ======================== Liste des trajets moto voyage ====================*/
    @GetMapping("/findAnnoncesMV")
    public ResponseEntity<List<Object[]>> getAllAnnoncesMotoVoyage() throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesMotoVoyage());
    }

    /* ======================== Affichage test de l'annonce d'un utilisateur ====================*/
    @PostMapping("/findAnnoncesWithUAndIAvByUtilisateurT")
    public ResponseEntity<List<Object[]>> getAllAnnoncesWUAIAvisByUtilisateurT() throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesWithAIAAvisByUT());
    }

    /* ======================== Affichage de l'annonce d'un utilisateur ====================*/
    @PostMapping("/findAnnoncesWithUAndIAvByUtilisateur")
    public ResponseEntity<List<Object[]>> getAllAnnoncesWUAIAvisByUtilisateur(@RequestBody UtilAnnItin u) throws IOException {

        /*System.out.println("******** Paramètres d'entrées ***********");
        System.out.println("======= Id utilisateur =======");
        System.out.println(u.idU);
        System.out.println("======= Id annonce =======");
        System.out.println(u.idA);
        System.out.println("======= Id itineraire =======");
        System.out.println(u.idI);
        System.out.println("*******************************");*/

        return ResponseEntity.ok(annonceService.getAllAnnoncesWithAIAAvisByU(u.idU, u.idA, u.idI));
    }

    /* ======================== Liste de toutes les Annonces en fonction des filtres ====================*/
    @PostMapping("/findAnnoncesByFilters") // /{ld}/{la}/{np}/{hd}/{ha}/{dd}/{da}/{te}/{tv}") // /{ld}")
    public ResponseEntity<List<Object[]>> getAllAnnoncesByF(@RequestBody FilterData fd ) throws IOException {

        return  ResponseEntity.ok(annonceService.getAllAnnoncesByFilters(fd.ld, fd.la, fd.np, fd.hd, fd.ha, fd.dd, fd.da, fd.te, fd.tv, fd.idIS1, fd.idIS2, fd.idIS3, fd.idIS4));
    }


    /* ======================== Liste des Annonces d'un utilisateur ====================*/
    @PostMapping("/findAnnoncesByUtilisateur")
    public ResponseEntity<List<Annonce>> getAllAnnoncesByUtilisateur(@RequestBody Integer id) {

        return ResponseEntity.ok(annonceRepository.findAllAnnoncesByIdUtilisateur(id));
    }


    /* ======================== Affichage d'une seule Annonce ======================*/
    @PostMapping("/findAnnonce")
    public ResponseEntity<Annonce> getAnnonce(@RequestBody Integer id) {
        log.debug("REST request to get Annonce : {}", id);
        Annonce annonce = annonceService.getAnnonce(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(annonce));
    }

    /* ============================ Supprimer une Annonce =======================*/
    @PostMapping("/deleteAnnonce")
    public ResponseEntity<Void> deleteAnnonce(@RequestBody Integer id) {
        log.debug("REST request to delete Annonce : {}", id);
        annonceService.deleteAnnonce(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}

@Data
class FilterData {
        String ld;
        String la;
        String np;
        String hd;
        String ha;
        String dd;
        String da;
        String te;
        String tv;
        int idIS1;
        int idIS2;
        int idIS3;
        int idIS4;
        }

@Data
class UtilAnnItin {
    int idU;
    int idA;
    int idI;
}
