package com.bafagroupe.christab.service;

import com.bafagroupe.christab.dao.UtilisateurRepository;
import com.bafagroupe.christab.entities.Historique;
import com.bafagroupe.christab.entities.Utilisateur;
import com.bafagroupe.christab.web.rest.util.FonctionUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Service
public class UtilisateurService {

    private  final Logger log = LoggerFactory.getLogger(UtilisateurService.class);
    private static final String ENTITY_NAME = "Utilisateur";
    private final UtilisateurRepository utilisateurRepository;
    private final HistoriqueService historiqueService;
    private final FonctionUtil fu = new FonctionUtil();
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**************************** Création d'un Utilisateur ***************************************************/
    public Utilisateur CreateUser(Utilisateur utilisateur) throws IOException {
        Utilisateur newUtilisateur = new Utilisateur();
        if(utilisateurRepository.findByEmailIgnoreCase(utilisateur.getEmail()) != null) {
            log.debug("Email already exists", utilisateur);
            System.out.println("L'email existe déjà");
        } else {
            // utilisateur.setIdUtilisateur(utilisateurRepository.findMaxId());

            /*********** Conversion de la photo en image *********/
            if(utilisateur.getPhoto() != null) // {
                utilisateur.setPhoto(fu.StoreImage(utilisateur.getPhoto()));
            if(utilisateur.getCnib() != null)
                // System.out.println("******* Contenu cnib ************");
                // System.out.println(utilisateur.getCnib());
                utilisateur.setCnib(fu.convertAndSplitCnibAndEngin(utilisateur.getCnib()));
            newUtilisateur = utilisateurRepository.save(utilisateur);
            log.debug("Created Information for Utilisateur: {}", newUtilisateur);

            Historique histo = new Historique();
            histo.setIdEntite(newUtilisateur.getIdUtilisateur());
            histo.setValeur(("Email: "+newUtilisateur.getEmail() +" Nom: "+ newUtilisateur.getNom() +" Prénom: "+ newUtilisateur.getPrenom() +" Cnib: "+
                    newUtilisateur.getCnib() +" Tel: "+ newUtilisateur.getTel() +" Type fonction: "+ newUtilisateur.getIdTypeFonction()).toString());
            histo.setNomEntite(ENTITY_NAME);
            historiqueService.createHistorique(histo);

        }

        return newUtilisateur;
    }

    /**************************** Mise à jour d'un Utilisateur ***************************************************/

    public Utilisateur UpdateUtilisateur(Utilisateur utilisateur) throws IOException {

        /*System.out.println("***************** Nouveau *****************");
        System.out.println(utilisateur.getPhoto());
        System.out.println("**********************************");*/
        Utilisateur newUtilisateur = utilisateurRepository.findOneById(utilisateur.getIdUtilisateur());
        if(newUtilisateur != null) {
            /*System.out.println("***************** Ancien *****************");
            System.out.println(newUtilisateur.getPhoto());
            System.out.println("**********************************");*/
            /*********** Conversion de la photo en image *********/
            try {
                if(utilisateur.getPhoto() != null && (!utilisateur.getPhoto().equals(newUtilisateur.getPhoto())))
                    utilisateur.setPhoto(fu.StoreImage(utilisateur.getPhoto()));
                if(utilisateur.getCnib() != null && (!utilisateur.getCnib().equals(newUtilisateur.getCnib())))
                    utilisateur.setCnib(fu.convertAndSplitCnibAndEngin(utilisateur.getCnib()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            utilisateurRepository.save(utilisateur);
        }

        log.debug("Created Information for User: {}", newUtilisateur);
        Historique histo = new Historique();
        histo.setIdEntite(newUtilisateur.getIdUtilisateur());
        histo.setValeur(("Email: "+newUtilisateur.getEmail() +" Nom: "+ newUtilisateur.getNom() +" Prénom: "+ newUtilisateur.getPrenom() +" Cnib: "+
                newUtilisateur.getCnib() +" Tel: "+ newUtilisateur.getTel() +" Type fonction: "+ newUtilisateur.getIdTypeFonction()+" Modification du compte").toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.updateHistorique(histo);

        return utilisateur;
    }

    /****************************Affichage d'un Utilisateur ***************************************************/
    public Utilisateur getUtilisateur(int id) {
        Utilisateur utilisateur = utilisateurRepository.findOneById(id);
        /*System.out.println("********** Recherche ************");
        System.out.println(utilisateur.getPhoto());
        System.out.println("**********************");*/
        return utilisateur;
    }

    /****************************Affichage d'un Utilisateur par son email ***************************************************/
    public Utilisateur getUtilisateurByEmail(String email) {
        /*System.out.println("************* Dans le service **************");
        System.out.println(email);
        System.out.println("***************************");*/
        return utilisateurRepository.findUByEmail(email);
    }


    /****************************Affichage de tous les Utilisateurs ***************************************************/
    public List<Utilisateur> getAllUtilisateur() {
        List<Utilisateur> utilisateur = utilisateurRepository.findAll();
        return utilisateur;
    }

    /****************************Suppression d'un Utilisateur ***************************************************/
    public void deleteUtilisateur(int id) {

        Utilisateur newUtilisateur = utilisateurRepository.findOneById(id);

        Historique histo = new Historique();
        histo.setIdEntite(newUtilisateur.getIdUtilisateur());
        histo.setValeur(("Email: "+newUtilisateur.getEmail() +" Nom: "+ newUtilisateur.getNom() +" Prénom: "+ newUtilisateur.getPrenom() +" Cnib: "+
                newUtilisateur.getCnib() +" Tel: "+ newUtilisateur.getTel() +" Type fonction: "+ newUtilisateur.getIdTypeFonction()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.deleteHistorique(histo);

        utilisateurRepository.delete(newUtilisateur);

    }
}
