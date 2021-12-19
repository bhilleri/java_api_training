package fr.lernejo.navy_battle.game.ship;

import java.util.ArrayList;
import java.util.List;

public class ShipBuilder implements IShipBuilder {
    @Override
    public List<IShip> GetBoat() {
        final List<IShip> boatList = new ArrayList<>();
        boatList.add(new Ship("porte-avion", 5));
        boatList.add(new Ship("croiseur", 4));
        boatList.add(new Ship("contre-torpilleur1", 3));
        boatList.add(new Ship("contre-torpilleur2", 3));
        boatList.add(new Ship("torpilleur", 2));
        return boatList;
    }
}
