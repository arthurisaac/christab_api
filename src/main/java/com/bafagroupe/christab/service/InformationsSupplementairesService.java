package com.bafagroupe.christab.service;

import com.bafagroupe.christab.dao.InformationsSupplementairesRepository;
import com.bafagroupe.christab.entities.Historique;
import com.bafagroupe.christab.entities.InformationsSupplementaires;
import com.bafagroupe.christab.web.rest.util.FonctionUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class InformationsSupplementairesService {

    private  final Logger log = LoggerFactory.getLogger(AnnonceService.class);
    private static final String ENTITY_NAME = "InformationsSupplementaires";
    private final InformationsSupplementairesRepository supplementairesRepository;
    private final HistoriqueService historiqueService;
    private final FonctionUtil fu = new FonctionUtil();

    /**************************** Création d'une InformationsSupplementaires ***************************************************/
    public InformationsSupplementaires CreateInformationsSupplementaires(InformationsSupplementaires informationsSupplementaires) throws IOException {

        // informationsSupplementaires.setIdInformationsSupplementaires(supplementairesRepository.findMaxId());
        informationsSupplementaires.setLibelleInformationsSupplementaires(informationsSupplementaires.getLibelleInformationsSupplementaires());
        // informationsSupplementaires.setIconeInformationsSupplementaires(fu.StoreIcone(informationsSupplementaires.getIconeInformationsSupplementaires()));
        informationsSupplementaires.setIdAnnonce(informationsSupplementaires.getIdAnnonce());
        informationsSupplementaires.setIdUtilisateur(informationsSupplementaires.getIdUtilisateur());
        InformationsSupplementaires newInfoSupp = supplementairesRepository.save(informationsSupplementaires);
        log.debug("Created Information for InformationsSupplementaires: {}", newInfoSupp);

        Historique histo = new Historique();
        histo.setIdEntite(newInfoSupp.getIdInformationsSupplementaires());
        histo.setValeur(("Libellé: "+newInfoSupp.getLibelleInformationsSupplementaires() +" Annonce: "+ newInfoSupp.getIdAnnonce() +" Utilisateur: "+ newInfoSupp.getIdUtilisateur()));
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.createHistorique(histo);

        return newInfoSupp;
    }

    /**************************** Mise à jour d'une Information Supplémentaire ***************************************************/
    public InformationsSupplementaires UpdateInformationsSupplementaires(InformationsSupplementaires informationsSupplementaires) throws IOException {

        InformationsSupplementaires newInfoSupp = supplementairesRepository.findOneById(informationsSupplementaires.getIdInformationsSupplementaires());
        if(newInfoSupp != null) {
            // if(informationsSupplementaires.getIconeInformationsSupplementaires() != null && (!informationsSupplementaires.getIconeInformationsSupplementaires().equals(newInfoSupp.getIconeInformationsSupplementaires())))
                // informationsSupplementaires.setIconeInformationsSupplementaires(fu.StoreIcone(informationsSupplementaires.getIconeInformationsSupplementaires()));
            supplementairesRepository.save(informationsSupplementaires);
        }

        log.debug("Created Information for InformationsSupplementaires: {}", newInfoSupp);


        Historique histo = new Historique();
        histo.setIdEntite(newInfoSupp.getIdInformationsSupplementaires());
        histo.setValeur(("Libellé: "+newInfoSupp.getLibelleInformationsSupplementaires() +" Annonce: "+ newInfoSupp.getIdAnnonce() +" Utilisateur: "+ newInfoSupp.getIdUtilisateur()));
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.updateHistorique(histo);

        return informationsSupplementaires;
    }

    /****************************Affichage d'une Information Supplémentaire ***************************************************/
    public InformationsSupplementaires getInformationsSupplementaires(int id) {
        InformationsSupplementaires infoSup = supplementairesRepository.findOneById(id);
        return infoSup;
    }

    /****************************Affichage de toutes les Informations Supplémentaires ***************************************************/
    public List<InformationsSupplementaires> getAllInformationsSupplementaires() throws IOException {
        List<InformationsSupplementaires> informationsSupplementaires = supplementairesRepository.findAll();
        return informationsSupplementaires;
    }

    /****************************Affichage de toutes les Informations Supplémentaires d'un utilisateur ***************************************************/
    public List<InformationsSupplementaires> getAllInformationsSupplementairesByU(int id) {
        List<InformationsSupplementaires> informationsSupplementaires = supplementairesRepository.findAllByIdUtilisateur(id);
        return informationsSupplementaires; // supplementairesRepository.findAllByIdUtilisateur(id);
    }


    /**************************** Suppression d'une Information Supplémentaire ***************************************************/
    public void deleteInformationsSupplementaires(int id) {

        InformationsSupplementaires newInfoSupp = supplementairesRepository.findOneById(id);

        Historique histo = new Historique();
        histo.setIdEntite(newInfoSupp.getIdInformationsSupplementaires());
        histo.setValeur(("Libellé: "+newInfoSupp.getLibelleInformationsSupplementaires() +" Annonce: "+ newInfoSupp.getIdAnnonce() +" Utilisateur: "+ newInfoSupp.getIdUtilisateur()));
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.deleteHistorique(histo);

        supplementairesRepository.delete(newInfoSupp);

    }
}
