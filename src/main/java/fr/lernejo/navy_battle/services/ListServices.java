package fr.lernejo.navy_battle.services;

import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.services.service.ServiceFire;
import fr.lernejo.navy_battle.services.service.ServicePing;
import fr.lernejo.navy_battle.services.service.ServiceStart;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Contient la liste des web-services chargés par le projet
 */
public final class ListServices {
    final private Map<String, IService> listService;
    /**
     * Gènere la liste des services accessibles. L'ajout ou la suppression d'un service doit être répercuté dans les tests unitaires
     */
    public ListServices(IController controller){
        this.listService = new HashMap<>();
        this.listService.put("/ping", new ServicePing());
        this.listService.put("/api/game/start", new ServiceStart(controller));
        this.listService.put("/api/game/fire", new ServiceFire(controller));
    }

    /**
     *
     * @return Une map ayant pour clé l'URL d'un service et pour valeur le service
     */
    public Map<String, IService> getAllService(){
        return this.listService;
    }

}
