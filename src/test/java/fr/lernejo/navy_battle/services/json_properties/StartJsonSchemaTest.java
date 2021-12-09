package fr.lernejo.navy_battle.services.json_properties;

import com.google.gson.annotations.Expose;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StartJsonSchemaTest {
    final String id = "0";
    final String url = "http:127.0.0.1:25000";
    final String message = "test";
    final String $schema = "http://json-schema.org/schema#";
    final String type = "object";
    @org.junit.jupiter.api.Test
    public void StartJsonSchemaConstructor() {
        final StartJsonSchema startJsonSchema = new StartJsonSchema(new StartJsonProperty(id, url, message));
        Assertions.assertEquals($schema ,startJsonSchema.$schema);
        Assertions.assertEquals(type, startJsonSchema.type);
        Assertions.assertEquals(id, startJsonSchema.properties.id);
        Assertions.assertEquals(url, startJsonSchema.properties.url);
        Assertions.assertEquals(message, startJsonSchema.properties.message);
    }
    public void StartJsonSchemaisCorrectTrue(){
        final StartJsonSchema startJsonSchema = new StartJsonSchema(new StartJsonProperty(id, url, message));
        final boolean result = startJsonSchema.isCorrect();
        Assertions.assertTrue(result);
    }

}
