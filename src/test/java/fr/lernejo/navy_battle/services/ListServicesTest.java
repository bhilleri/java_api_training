package fr.lernejo.navy_battle.services;

import fr.lernejo.navy_battle.Controller;
import fr.lernejo.navy_battle.services.service.ServicePing;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;


class ListServicesTest {
    @Mock
    Controller controller;
    @org.junit.jupiter.api.Test
    void getAllServiceTest(){
        ListServices listServices = new ListServices(controller);
        assertInstanceOf(ServicePing.class, listServices.getAllService().get("/ping"));
    }
}
