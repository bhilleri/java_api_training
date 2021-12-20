package fr.lernejo.navy_battle.game.selectPosition;

import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;
import fr.lernejo.navy_battle.game.ship.IShip;
import fr.lernejo.navy_battle.game.ship.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConfigurePositionTest {
    @Mock
    IPlayer player = mock(IPlayer.class);

    @Test
    void positionAllBoatCorrect() {
        List<IShip> shipList = new ArrayList<>();
        shipList.add(new Ship("sous-marin", 2));
        shipList.add(new Ship("corvette", 3));
        shipList.add(new Ship("frégate", 4));
        shipList.add(new Ship("porte-avion",5));
        List<IPoint> sousMarinPoints = new ArrayList<>();
        sousMarinPoints.add(new Point(3,1));
        sousMarinPoints.add(new Point(3,2));
        List<IPoint> corvettePoints = new ArrayList<>();
        corvettePoints.add(new Point(2,2));
        corvettePoints.add(new Point(2,3));
        corvettePoints.add(new Point(2,4));
        List <IPoint> fregatePoints = new ArrayList<>();
        fregatePoints.add(new Point(1,2));
        fregatePoints.add(new Point(1,3));
        fregatePoints.add(new Point(1,4));
        fregatePoints.add(new Point(1,5));
        List <IPoint> porteAvionPoints = new ArrayList<>();
        porteAvionPoints.add(new Point(4,1));
        porteAvionPoints.add(new Point(4,2));
        porteAvionPoints.add(new Point(4,3));
        porteAvionPoints.add(new Point(4,4));
        porteAvionPoints.add(new Point(4,5));
        when(player.PositionABoat(2)).thenReturn(sousMarinPoints);
        when(player.PositionABoat(3)).thenReturn(corvettePoints);
        when(player.PositionABoat(4)).thenReturn(fregatePoints);
        when(player.PositionABoat(5)).thenReturn(porteAvionPoints);
        when(player.InformPlacement(shipList.get(0).getName(), shipList.get(0).GetPosition())).thenReturn("");
        when(player.InformPlacement(shipList.get(1).getName(), shipList.get(1).GetPosition())).thenReturn("");
        when(player.InformPlacement(shipList.get(2).getName(), shipList.get(2).GetPosition())).thenReturn("");
        when(player.InformPlacement(shipList.get(3).getName(), shipList.get(3).GetPosition())).thenReturn("");
        ConfigurePosition configurePosition = new ConfigurePosition(player);
        configurePosition.PositionAllBoat(shipList);
        Assertions.assertTrue(shipList.get(0).GetPosition().containsAll(sousMarinPoints));
        Assertions.assertTrue(shipList.get(1).GetPosition().containsAll(corvettePoints));
        Assertions.assertTrue(shipList.get(2).GetPosition().containsAll(fregatePoints));
        Assertions.assertTrue(shipList.get(3).GetPosition().containsAll(porteAvionPoints));
    }

    @Test
    void validPosition() {
        List<IShip> shipList = new ArrayList<>();
        shipList.add(new Ship("sous-marin", 2));
        shipList.add(new Ship("corvette", 3));
        List<IPoint> sousMarinPoints = new ArrayList<>();
        sousMarinPoints.add(new Point(3,1));
        sousMarinPoints.add(new Point(3,2));
        List<IPoint> corvettePoints = new ArrayList<>();
        corvettePoints.add(new Point(2,2));
        corvettePoints.add(new Point(3,2));
        corvettePoints.add(new Point(4,2));
        List <IPoint> corvettePoints2 = new ArrayList<>();
        corvettePoints2.add(new Point(4,1));
        corvettePoints2.add(new Point(4,2));
        corvettePoints2.add(new Point(4,3));
        when(player.PositionABoat(2)).thenReturn(sousMarinPoints);
        when(player.PositionABoat(3)).thenReturn(corvettePoints).thenReturn(corvettePoints2);
        when(player.InformPlacement(shipList.get(0).getName(), shipList.get(0).GetPosition())).thenReturn("");
        when(player.InformPlacement(shipList.get(1).getName(), shipList.get(1).GetPosition())).thenReturn("");
        ConfigurePosition configurePosition = new ConfigurePosition(player);
        configurePosition.PositionAllBoat(shipList);
        Assertions.assertTrue(shipList.get(0).GetPosition().containsAll(sousMarinPoints));
        Assertions.assertTrue(shipList.get(1).GetPosition().containsAll(corvettePoints2));
        Assertions.assertFalse(shipList.get(1).GetPosition().containsAll(corvettePoints));
    }
}
