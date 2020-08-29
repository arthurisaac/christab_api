package com.bafagroupe.christab.service;

import com.bafagroupe.christab.dao.EnginRepository;
import com.bafagroupe.christab.entities.Engin;
import com.bafagroupe.christab.entities.Historique;
import com.bafagroupe.christab.web.rest.util.FonctionUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class EnginService {

    private  final Logger log = LoggerFactory.getLogger(EnginService.class);
    private static final String ENTITY_NAME = "Engin";
    private final EnginRepository enginRepository;
    private final HistoriqueService historiqueService;
    private final FonctionUtil fu = new FonctionUtil();

    /**************************** Création d'un Engin ***************************************************/
    public Engin CreateEngin(Engin engin) throws IOException {

        // engin.setIdEngin(enginRepository.findMaxId());
        engin.setIdUtilisateur(engin.getIdUtilisateur());
        /*********** Conversion de la photo en image *********/
        if(engin.getPhotoEngin() != null)
            engin.setPhotoEngin(fu.convertAndSplitCnibAndEngin(engin.getPhotoEngin()));
        if(engin.getCarteGrise() != null)
            engin.setCarteGrise(fu.convertAndSplitCarteGriseAssurPermis(engin.getCarteGrise()));
        if(engin.getPhotoPermis() != null)
        engin.setPhotoPermis(fu.convertAndSplitCarteGriseAssurPermis(engin.getPhotoPermis()));
        if(engin.getPhotoAssurance() != null)
            engin.setPhotoAssurance(fu.convertAndSplitCarteGriseAssurPermis(engin.getPhotoAssurance()));

        Engin newEngin = enginRepository.save(engin);
        log.debug("Created Information for Engin: {}", newEngin);

        Historique histo = new Historique();
        histo.setIdEntite(newEngin.getIdEngin());
        histo.setValeur(("Marque: "+newEngin.getMarque() +" Modèle: "+ newEngin.getModele() +" Année: "+ newEngin.getAnneeModele() +" Carte grise: "+ newEngin.getCarteGrise()).toString());
        // histo.setValeur(newEngin.getCarteGrise());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.createHistorique(histo);

        return newEngin;
    }

    /**************************** Mise à jour d'un Engin ***************************************************/
    public Engin UpdateEngin(Engin engin) throws IOException {

        Engin newEngin = enginRepository.findOneById(engin.getIdEngin());
        if(newEngin != null) {
            /*********** Conversion de la photo en image *********/
            if(engin.getPhotoEngin() != null && (!engin.getPhotoEngin().equals(newEngin.getPhotoEngin())))
                engin.setPhotoEngin(fu.convertAndSplitCnibAndEngin(engin.getPhotoEngin()));
            if(engin.getCarteGrise() != null && (!engin.getCarteGrise().equals(newEngin.getCarteGrise())))
                engin.setCarteGrise(fu.convertAndSplitCarteGriseAssurPermis(engin.getCarteGrise()));
            if(engin.getPhotoPermis() != null && (!engin.getPhotoPermis().equals(newEngin.getPhotoPermis())))
                engin.setPhotoPermis(fu.convertAndSplitCarteGriseAssurPermis(engin.getPhotoPermis()));
            if(engin.getPhotoAssurance() != null && (!engin.getPhotoAssurance().equals(newEngin.getPhotoAssurance())))
                engin.setPhotoAssurance(fu.convertAndSplitCarteGriseAssurPermis(engin.getPhotoAssurance()));

            enginRepository.save(engin);
        }

        log.debug("Updated Information for Engin: {}", newEngin);


        Historique histo = new Historique();
        histo.setIdEntite(newEngin.getIdEngin());
        histo.setValeur(("Marque: "+newEngin.getMarque() +" Modèle: "+ newEngin.getModele() +" Année: "+ newEngin.getAnneeModele() +" Carte grise: "+ newEngin.getCarteGrise()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.updateHistorique(histo);

        return engin;
    }

    /****************************Affichage d'un Engin ***************************************************/
    public Engin getEngin(int id) throws IOException {
        Engin engin = enginRepository.findOneById(id);
        return engin;
    }

    /****************************Affichage de tous les Engins ***************************************************/
    public List<Engin> getAllEngin() {

        List<Engin> engin = enginRepository.findAll();
        return enginRepository.findAll();
    }

    /****************************Affichage de la liste des engins d'un utilisateur ***************************************************/
    public List<Engin> getAllEnginByUser(int id) {

        List<Engin> engin = enginRepository.findAllByIdUtilisateur(id);
        return engin;
    }

    /****************************Suppression d'un Engin ***************************************************/
    public void deleteEngin(int id) {

        Engin newEngin = enginRepository.findOneById(id);

        Historique histo = new Historique();
        histo.setIdEntite(newEngin.getIdEngin());
        histo.setValeur(("Marque: "+newEngin.getMarque() +" Modèle: "+ newEngin.getModele() +" Année: "+ newEngin.getAnneeModele() +" Carte grise: "+ newEngin.getCarteGrise()).toString());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.deleteHistorique(histo);

        log.debug("Deleted Information for Engin: {}", newEngin);

        enginRepository.delete(newEngin);

    }

}
