package fr.lernejo.navy_battle.game.boat;

import java.util.ArrayList;
import java.util.List;

public class BoatBuilder implements IBoatBuilder{
    @Override
    public List<IBoat> GetBoat() {
        final List<IBoat> boatList = new ArrayList<>();
        boatList.add(new Boat("porte-avion", 5));
        boatList.add(new Boat("croiseur", 4));
        boatList.add(new Boat("contre-torpilleur1", 3));
        boatList.add(new Boat("contre-torpilleur2", 3));
        boatList.add(new Boat("torpilleur", 2));
        return boatList;
    }
}
