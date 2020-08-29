package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.TypeAvisRepository;
import com.bafagroupe.christab.entities.TypeAvis;
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
public class TypeAvisRessource {

    private  final Logger log = LoggerFactory.getLogger(TypeAvisRessource.class);
    private static final String ENTITY_NAME = "TypeAvis";
    // private final TypeAvisService typeAvisService;
    private final TypeAvisRepository typeAvisRepository;

    /*=================== Création ===============*/
    @PostMapping("/createTypeAvis")
    public ResponseEntity<TypeAvis> createAlerte(@RequestBody TypeAvis typeAvis) throws URISyntaxException {
        log.debug("REST request to save TypeAvis : {}", typeAvis);
        // typeAvis.setIdTypeAvis(typeAvisRepository.findMaxId());
        TypeAvis newTypeAvis = typeAvisRepository.save(typeAvis);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("TypeAvis créé", newTypeAvis.getLibelleTypeAvis())).body(newTypeAvis);
    }

    /*======================== Modification ====================*/
    @PostMapping("/updateTypeAvis")
    public ResponseEntity<TypeAvis> updateAlerte(@RequestBody TypeAvis typeAvis) throws URISyntaxException{
        log.debug("REST request to update TypeAvis : {}", typeAvis);
        if (typeAvis.getIdTypeAvis() == 0) {
            return createAlerte(typeAvis);
        } else {
            typeAvisRepository.findOneById(typeAvis.getIdTypeAvis());
            TypeAvis result = typeAvisRepository.save(typeAvis);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(typeAvis.getIdTypeAvis())))
                    .body(result);
        }
    }

    /*======================== Liste de tous les TypeAvis ====================*/
    @GetMapping("/findAllTypeAvis")
    public ResponseEntity<List<TypeAvis>> getAllTypeAvis() {
        // return ResponseEntity.ok(typeAvisRepository.findAll());
        return  ResponseEntity.ok(typeAvisRepository.findAllTypesAvis());
    }

    /*======================== Liste d'un seule typeAvis ======================*/
    @PostMapping("/findTypeAvis")
    public ResponseEntity<TypeAvis> getAlerte(@RequestBody Integer id) {
        log.debug("REST request to get TypeAvis : {}", id);
        TypeAvis typeAvis = typeAvisRepository.findOneById(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(typeAvis));
    }

    /* ======================== Liste des types Avis en fonction d'un avis ====================*/
    @PostMapping("/findAllByAvis")
    public ResponseEntity<List<TypeAvis>> getAllAvisByTA(@RequestBody int id) {

        return ResponseEntity.ok(typeAvisRepository.findAllByIdAvis(id));
    }


    /*============================ Supprimer une typeAvis =======================*/
    @PostMapping("/deleteTypeAvis")
    public ResponseEntity<Void> deleteAlerte(@RequestBody Integer id) {
        log.debug("REST request to delete TypeAvis : {}", id);
        TypeAvis newTypeAvis = typeAvisRepository.findOneById(id);
        typeAvisRepository.delete(newTypeAvis);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
