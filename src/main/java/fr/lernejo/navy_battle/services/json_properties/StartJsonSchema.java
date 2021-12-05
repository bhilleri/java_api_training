package fr.lernejo.navy_battle.services.json_properties;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Classe permettant de désérialiser une requête faite au service Start
 */
public class StartJsonSchema {
    public StartJsonSchema(final StartJsonProperty properties)
    {
        this.required = new ArrayList<>();
        required.add("id");
        required.add("url");
        required.add("message");
        this.$schema = "http://json-schema.org/schema#";
        this.type = "object";
        this.properties = properties;
    }
    @Expose(serialize = true, deserialize = true)
    public final String $schema;
    @Expose(serialize = true, deserialize = true)
    public final String type;
    @Expose(serialize = true, deserialize = true)
    public final StartJsonProperty properties;
    @Expose(serialize = true, deserialize = true)
    public final List<String> required;

    /**
     *
     * @return Vrai si l'objet généré est conforme, sinon renvoie faux
     */
    public boolean isCorrect()
    {
        if($schema.equals("http://json-schema.org/schema#") && type.equals("object"))
        {
            final List <String> conformRequired = new ArrayList<>();
            conformRequired.add("id");
            conformRequired.add("url");
            conformRequired.add("message");
            if(required.containsAll(conformRequired))
                return true;
        }
        return  false;
    }
}
