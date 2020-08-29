package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.TypeFonctionRepository;
import com.bafagroupe.christab.entities.TypeFonction;
import com.bafagroupe.christab.service.TypeFonctionService;
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
public class TypeFonctionRessource {

    private  final Logger log = LoggerFactory.getLogger(TypeFonctionRessource.class);
    private static final String ENTITY_NAME = "TypeFonction";
    private final TypeFonctionService typeFonctionService;
    private final TypeFonctionRepository typeFonctionRepository;

     /* =================== Création =============== */
    @PostMapping("/createTypeFonction")
    public ResponseEntity<TypeFonction> createTypeFonction(@RequestBody TypeFonction typeFonction) throws URISyntaxException {
        log.debug("REST request to save TypeFonction : {}", typeFonction);
        TypeFonction newTypeFonction = typeFonctionService.CreateTypeFonction(typeFonction);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("TypeFonction créé", newTypeFonction.getLibelleTypeFonction())).body(newTypeFonction);
    }

     /* ======================== Modification ==================== */
    @PostMapping("/updateTypeFonction")
    public ResponseEntity<TypeFonction> updateTypeFonction(@RequestBody TypeFonction typeFonction) throws URISyntaxException {
        log.debug("REST request to update TypeFonction : {}", typeFonction);
        if (typeFonction.getIdTypeFonction() == 0) {
            return createTypeFonction(typeFonction);
        } else {
            TypeFonction result = typeFonctionService.UpdateTypeFonction(typeFonction);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(typeFonction.getIdTypeFonction())))
                    .body(result);
        }
    }

     /* ======================== Liste de tous les TypeFonction ==================== */
    @GetMapping("/findTypeFonctions")
    public ResponseEntity<List<TypeFonction>> getAllTypeFonction() {
        return ResponseEntity.ok(typeFonctionService.getAllTypeFonction());
        // return ResponseEntity.ok(typeFonctionService.getAllTypeFonctionWOLast());
    }

    /* ======================== Liste d'un seul TypeFonction ====================== */
    @PostMapping("/findTypeFonction")
    public ResponseEntity<TypeFonction> getTypeFonction(@RequestBody int id) {
        log.debug("REST request to get TypeFonction : {}", id);
        TypeFonction typeFonction = typeFonctionService.getTypeFonction((short)id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(typeFonction));
    }

    /* ============================ Supprimer un TypeFonction ======================= */
    @PostMapping("/deleteTypeFonction")
    public ResponseEntity<Void> deleteTypeFonction(@RequestBody short id) {
        log.debug("REST request to delete TypeFonction : {}", id);
        typeFonctionService.deleteTypeFonction(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, String.valueOf(id))).build();
    }
}
