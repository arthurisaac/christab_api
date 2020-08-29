package com.bafagroupe.christab.service;

import com.bafagroupe.christab.dao.HistoriqueRepository;
import com.bafagroupe.christab.entities.Historique;
import com.bafagroupe.christab.security.SecurityUtils;
import com.bafagroupe.christab.web.rest.util.Constants;
import com.bafagroupe.christab.web.rest.util.FonctionUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class HistoriqueService {

    private  final Logger log = LoggerFactory.getLogger(HistoriqueService.class);
    private final HistoriqueRepository historiqueRepository;
    private final FonctionUtil fu = new FonctionUtil();


    /* =========================== Création =========================== */
    public Historique createHistorique(Historique historique){


        // historique.setIdHistorique(historiqueRepository.findMaxId());
        historique.setActif(true);
        historique.setDeleted(false);
        historique.setAction(Constants.ACTION_CR);
        historique.setAdresseIp(fu.getAdressIp());
        historique.setAgentWeb(fu.getUserAgent());
        historique.setDateOperation(ZonedDateTime.now());
        // historique.setPosition(historique.getPosition());
        historique.setUser(SecurityUtils.getCurrentUserLogin());

        /*System.out.println("*************** contenu **************");
        System.out.println(historique.getValeur());
        System.out.println(historique.getActif());
        System.out.println(historique.getAction());
        System.out.println(historique.getDateOperation());
        System.out.println(historique.getAdresseIp());
        System.out.println(historique.getAgentWeb());
        System.out.println("*************** Fin **************");*/

        Historique newHisto = historiqueRepository.save(historique);

        /*newHisto.setIdHistorique(historiqueRepository.findMaxId());
        newHisto.setIdEntite(historique.getIdEntite());
        newHisto.setActif(true);
        newHisto.setAction(Constants.ACTION_CR);
        newHisto.setAdresseIp(fu.getAdressIp());
        newHisto.setAgentWeb(fu.getUserAgent());
        newHisto.setDateOperation(ZonedDateTime.now());
        newHisto.setDeleted(false);
        newHisto.setNomEntite(historique.getNomEntite());
        newHisto.setLatitude(historique.getLatitude());
        newHisto.setLatitude(historique.getLatitude());
        newHisto.setValeur(historique.getValeur());
        newHisto.setUser(SecurityUtils.getCurrentUserLogin());*/

        log.debug("Created Information for Engin: {}", newHisto);
        return newHisto;
    }

    /* =========================== Modification =========================== */
    public Historique updateHistorique(Historique historique){

        // historique.setIdHistorique(historiqueRepository.findMaxId());
        historique.setActif(true);
        historique.setDeleted(false);
        historique.setAction(Constants.ACTION_U);
        historique.setAdresseIp(fu.getAdressIp());
        historique.setAgentWeb(fu.getUserAgent());
        historique.setDateOperation(ZonedDateTime.now());
        historique.setUser(SecurityUtils.getCurrentUserLogin());

        Historique newHisto = historiqueRepository.save(historique);

        return newHisto;
    }

    /* =========================== Suppression =========================== */
    public Historique deleteHistorique(Historique historique){

        // historique.setIdHistorique(historiqueRepository.findMaxId());
        historique.setActif(true);
        historique.setDeleted(true);
        historique.setAction(Constants.ACTION_D);
        historique.setAdresseIp(fu.getAdressIp());
        historique.setAgentWeb(fu.getUserAgent());
        historique.setDateOperation(ZonedDateTime.now());
        historique.setUser(SecurityUtils.getCurrentUserLogin());

        Historique newHisto = historiqueRepository.save(historique);

        return newHisto;
    }
}
