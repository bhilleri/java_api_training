package fr.lernejo.navy_battle.services.json_properties;

import com.google.gson.annotations.Expose;
import fr.lernejo.navy_battle.enumeration.Consequence;

public class FirerResponseJsonProperty {
    public FirerResponseJsonProperty(final Consequence consequence, final boolean shipLeft)
    {
        this.consequence = consequence;
        this.shipLeft = shipLeft;
    }
    @Expose(serialize = true, deserialize = true)
    public final Consequence consequence;
    @Expose(serialize = true, deserialize = true)
    public final boolean shipLeft;
}
