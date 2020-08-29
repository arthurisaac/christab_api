package com.bafagroupe.christab.service;

import com.bafagroupe.christab.dao.TypeFonctionRepository;
import com.bafagroupe.christab.entities.Historique;
import com.bafagroupe.christab.entities.TypeFonction;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeFonctionService {

    private  final Logger log = LoggerFactory.getLogger(TypeFonctionService.class);
    private static final String ENTITY_NAME = "TypeFonction";
    private final TypeFonctionRepository typeFonctionRepository;
    private final HistoriqueService historiqueService;

    /**************************** Création d'un TypeFonction ***************************************************/
    public TypeFonction CreateTypeFonction(TypeFonction typeFonction) {

        // typeFonction.setIdTypeFonction(typeFonctionRepository.findMaxId());
        TypeFonction newTypeFonction = typeFonctionRepository.save(typeFonction);
        log.debug("Created Information for TypeFonction: {}", newTypeFonction);

        /*System.out.println("****************** Contenu de typeFonction ****************** ");
        System.out.println(typeFonction.getIdTypeFonction());
        System.out.println("****************** Contenu de newTypeFonction ****************** ");
        System.out.println(newTypeFonction.getIdTypeFonction());*/

        Historique histo = new Historique();
        histo.setIdEntite((int)newTypeFonction.getIdTypeFonction());
        histo.setValeur(newTypeFonction.getLibelleTypeFonction());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.createHistorique(histo);

        return newTypeFonction;
    }

    /**************************** Mise à jour d'un TypeFonction ***************************************************/
    public TypeFonction UpdateTypeFonction(TypeFonction typeFonction) {

        TypeFonction newTypeFonction = typeFonctionRepository.findOneById(typeFonction.getIdTypeFonction());
        if(newTypeFonction != null) {
            typeFonctionRepository.save(typeFonction);
        }
        log.debug("Updated Information for TypeFonction: {}", newTypeFonction);

        Historique histo = new Historique();
        histo.setIdEntite((int)newTypeFonction.getIdTypeFonction());
        histo.setValeur(newTypeFonction.getLibelleTypeFonction());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.updateHistorique(histo);

        return typeFonction;
    }

    /****************************Affichage d'un TypeFonction ***************************************************/
    public TypeFonction getTypeFonction(short id) {
        return typeFonctionRepository.findOneById(id);
    }

    /****************************Affichage de tous les TypeFonctions ***************************************************/
    public List<TypeFonction> getAllTypeFonction(){
        /*System.out.println("************** Liste des fonctions *****************");
        System.out.println(typeFonctionRepository.findAll());
        System.out.println("*******************************");*/
        return typeFonctionRepository.findAll();
    }

    public List<TypeFonction> getAllTypeFonctionWOLast(){
        return typeFonctionRepository.findAllWithoutLast();
    }

    /****************************Suppression d'un TypeFonction ***************************************************/
    public void deleteTypeFonction(short id) {

        TypeFonction newTypeFonction = typeFonctionRepository.findOneById(id);

        Historique histo = new Historique();
        histo.setIdEntite((int)newTypeFonction.getIdTypeFonction());
        histo.setValeur(newTypeFonction.getLibelleTypeFonction());
        histo.setNomEntite(ENTITY_NAME);
        historiqueService.deleteHistorique(histo);

        log.debug("Deleted Information for TypeFonction: {}", newTypeFonction);

        typeFonctionRepository.delete(newTypeFonction);

    }
}
