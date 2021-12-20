package fr.lernejo.navy_battle.services.tools;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.services.json_properties.FirerResponseJsonProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DeserializeFireResponseJsonPropertyTest {

    @Mock
    private Consequence consequence = Consequence.hint;
    private String shipleft = "true";
    private String JsonContent ="{\"consequence\":\""+consequence.toString() +"\",\"shipLeft\":"+shipleft +"}";
    private DeserializeFireResponseJsonProperty deserializeFireResponseJsonProperty;
    @BeforeEach
    public void initialize(){
        deserializeFireResponseJsonProperty = new DeserializeFireResponseJsonProperty();
    }

    @Test
    void deserializationFromString() {
        FirerResponseJsonProperty firerResponseJsonProperty = deserializeFireResponseJsonProperty.deserializationFromString(JsonContent);
        Assertions.assertEquals(consequence, firerResponseJsonProperty.consequence);
        Assertions.assertTrue(firerResponseJsonProperty.shipLeft);
    }
}
