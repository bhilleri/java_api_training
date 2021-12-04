package fr.lernejo.navy_battle.services;

import fr.lernejo.navy_battle.services.service.ServicePing;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class ListServicesTest {
    @org.junit.jupiter.api.Test
    void getAllServiceTest(){
        ListServices listServices = new ListServices();
        assertInstanceOf(ServicePing.class, listServices.getAllService().get("/ping"));
    }
}
