package fr.lernejo.navy_battle.services.json_properties;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class StartJsonPropertyTest {
    private final String id = "id";
    private final String message = "message";
    private final String url = "http://localhost:22000/hello";

    @org.junit.jupiter.api.Test
    public void StartJsonPropertyTestObect(){
        final StartJsonProperty startJsonProperty = new StartJsonProperty(
            id,
            url,
            message
        );
        Assertions.assertEquals(id, startJsonProperty.id);
        Assertions.assertEquals(url, startJsonProperty.url);
        Assertions.assertEquals(message, startJsonProperty.message);
    }
}
