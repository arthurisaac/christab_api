package com.bafagroupe.christab.web.rest;

import com.bafagroupe.christab.dao.UtilisateurRepository;
import com.bafagroupe.christab.entities.Utilisateur;
import com.bafagroupe.christab.service.UtilisateurService;
import com.bafagroupe.christab.web.rest.util.FonctionUtil;
import com.bafagroupe.christab.web.rest.util.HeaderUtil;
import com.bafagroupe.christab.web.rest.util.ResponseUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UtilisateurRessource {

    private  final Logger log = LoggerFactory.getLogger(UtilisateurRessource.class);
    private static final String ENTITY_NAME = "Utilisateur";
    private final UtilisateurService utilisateurService;
    private final UtilisateurRepository utilisateurRepository;
    private final FonctionUtil fu = new FonctionUtil();

    /* =================== Création ===============*/
    @PostMapping("/createUtilisateur")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) throws URISyntaxException, IOException {
        log.debug("REST request to save Utilisateur : {}", utilisateur);
        Utilisateur newUtilisateur = utilisateurService.CreateUser(utilisateur);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("Utilisateur créé", newUtilisateur.getNom())).body(newUtilisateur);
    }

    @PostMapping("/testNewFonction")
    public String testFonction(@RequestBody String word) {
        return fu.substringIn3StringsFromFrontTest(word);
    }


    /* ======================== Modification du compte ====================*/
    @PostMapping("/updateUtilisateur")
    public ResponseEntity<Utilisateur> updateUtilisateur(@RequestBody Utilisateur utilisateur) throws URISyntaxException, IOException {
        log.debug("REST request to update Utilisateur : {}", utilisateur);
        if (utilisateur.getIdUtilisateur() == 0) {
            return createUtilisateur(utilisateur);
        } else {
            Utilisateur result = utilisateurService.UpdateUtilisateur(utilisateur);
            return ResponseEntity.ok()
                    .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Integer.toString(utilisateur.getIdUtilisateur())))
                    .body(result);
        }
    }

    /* ======================== Liste de tous les Utilisateurs ====================*/
    @GetMapping("/findUtilisateurs")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateur());
    }

    /* ======================== Affichage d'un seul Utilisateur ======================*/
    @PostMapping("/findUtilisateur")
    public ResponseEntity<Utilisateur> getUtilisateur(@RequestBody int id) {
        /*System.out.println("************ Paramètre d'entrée ***********");
        System.out.println(id);*/
        log.debug("REST request to get User : {}", id);
        Utilisateur utilisateur = utilisateurService.getUtilisateur(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(utilisateur));
    }

    /* ======================== Affichage d'un seul Utilisateur par son email ======================*/
    @PostMapping("/findUtilisateurByEmail")
    public ResponseEntity<Utilisateur> getUtilisateurByEmail(@RequestBody String email) {
        log.debug("REST request to get User : {}", email);
        /*System.out.println("************* Email saisi **************");
        System.out.println(email);
        System.out.println("***************************");*/
        Utilisateur utilisateur = utilisateurRepository.findByEmailIgnoreCase(email);
        // Utilisateur utilisateur = utilisateurService.getUtilisateurByEmail(email);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(utilisateur));
    }

    /* ======================== Test de la fonction de récupération de la photo ======================*/
    @PostMapping("/testGetPhoto")
    public String getPhotoTest(@RequestBody String email) throws IOException {
        log.debug("REST request to get User : {}", email);
        // String photo = utilisateurRepository.findPhotoById(email);
        String photo = "2020-07-17T10:44:08.924.jpeg";
        byte[] p = Files.readAllBytes(Paths.get("fichiers/"+photo));

        StringBuilder base64 = new StringBuilder("data:image/png;base64,");
        base64.append(Base64.getEncoder().encodeToString(p));
        // System.out.println(base64.toString());
        return base64.toString();
    }

    /***************** Test de la fonction de récupération des chemins des fichiers **********/
    @GetMapping("/findPath/{file}")
    public ResponseEntity<Resource> getFilePath(@PathVariable String file) throws IOException {
        /*res.getOutputStream().write(Files.readAllBytes(fu.getFilePath(file)));
        res.getOutputStream().flush();*/
        // return fu.getFilePath(file);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file)
                .body(new ByteArrayResource(Files.readAllBytes(fu.loadFileFromPath(file))));
    }

    /* ======================== Test de la fonction de l'enregistrement de la photo ======================*/
    @PostMapping("/testStorePhoto")
    public String storePhotoTest(@RequestBody String photo) throws IOException {
        return fu.StoreImage(photo);
    }


    /* ======================== Test de la fonction substring ======================*/
    @PostMapping("/testSubstrings2")
    public ArrayList<String> substrTest(@RequestBody String file) {
        log.debug("REST request to get User : {}", file);
        ArrayList<String> s = fu.substringIn2StringsFromFront(file);
        return s;
    }

    @PostMapping("/testSubstrings3")
    public ArrayList<String> substrsTest(@RequestBody String file) {
        log.debug("REST request to get User : {}", file);
        ArrayList<String> s = fu.substringIn3StringsFromFront(file);
        return s;
    }

    @PostMapping("/convertASplit")
    public String convertAConcat(@RequestBody String file) throws IOException {
        log.debug("REST request to get User : {}", file);
        String s = fu.convertAndSplitCnibAndEngin(file);
        return s;
    }

    /* ============================ Supprimer un Utilisateur =======================*/
    @PostMapping("/deleteUtilisateur")
    public ResponseEntity<Void> deleteUtilisateur(@RequestBody int id) {
        log.debug("REST request to delete Utilisateur : {}", id);
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, Integer.toString(id))).build();
    }
}
