package fr.lernejo.navy_battle.game.board;

import fr.lernejo.navy_battle.Constant;
import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;
import fr.lernejo.navy_battle.game.ship.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BoardTest {
    @Mock
    private IController controller = mock(IController.class);
    @Mock
    private IPlayer player = mock(IPlayer.class);
    private Board board;
    @BeforeEach
    public void initialize(){
        board = new Board(controller, player);
    }

    //Place et détruit tous les navires
    @Test
    void enemyShootAndInitialisation() {

        //Préparation de la création des navires
        List <List <IPoint>> shipPosition = new ArrayList<>();
        List<Integer> shipSize = new ArrayList<>();
        shipSize.add(2);
        shipSize.add(3);
        shipSize.add(3);
        shipSize.add(4);
        shipSize.add(5);
        for (int i = 0; i < 5 ; i++) {
            shipPosition.add(new ArrayList<>());
            for (int j = 0; j < shipSize.get(i); j++) {
                shipPosition.get(i).add(new Point(i, j));
            }
        }

        // Configuration du mock de Player afin de pouvoir saisir les positions des navires
        when(player.PositionABoat(2)).thenReturn(shipPosition.get(0));
        when(player.PositionABoat(3)).thenReturn(shipPosition.get(1)).thenReturn(shipPosition.get(2));
        when(player.PositionABoat(4)).thenReturn(shipPosition.get(3));
        when(player.PositionABoat(5)).thenReturn(shipPosition.get(4));

        // Détournement des affichages
        when(player.InformPlacement("torpilleur",shipPosition.get(0) )).thenReturn("");
        when(player.InformPlacement("contre-torpilleur1", shipPosition.get(1))).thenReturn("");
        when(player.InformPlacement("contre-torpilleur2", shipPosition.get(2))).thenReturn("");
        when(player.InformPlacement("croiseur",shipPosition.get(3) )).thenReturn("");
        when(player.InformPlacement("porte-avion",shipPosition.get(4) )).thenReturn("");

        // Positionnement des navires dans le Board
        board.InitializeShip();

        // Regroupement dans une liste de l'ensemble des points où un navire est présent
        List<IPoint> allShipPosition = new ArrayList<>();
        for (List<IPoint> pointList : shipPosition) {
            allShipPosition.addAll(pointList);
        }

        // Création d'une liste contenant tous les points où il n'y a pas de navire
        List <IPoint> allPositionWithoutShip = new ArrayList<>();
        int sizeMax = new Constant().GetSizeOfBoard();
        for (int i = 0; i < sizeMax; i++) {
            for (int j = 0; j < sizeMax; j++) {
                IPoint point = new Point(i, j);
                if(!allShipPosition.contains(point))
                    allPositionWithoutShip.add(point);
            }
        }

        // Tir sur l'ensemble des points où il n'y a pas de navire
        for (IPoint pointWithoutShip : allPositionWithoutShip) {
            Consequence consequence = board.EnemyShoot(pointWithoutShip);
            Assertions.assertEquals(Consequence.miss, consequence);
            Assertions.assertFalse(board.GetIfLost());
        }

        // Tir sur l'ensemble des points où il y a des navires à l'éxception du dernier
        for (int i = 0; i < allShipPosition.size()-1; i++) {
            Consequence consequence = board.EnemyShoot(allShipPosition.get(i));
            Assertions.assertTrue(consequence.equals(Consequence.hint) || consequence.equals(Consequence.sunk));
            Assertions.assertFalse(board.GetIfLost());
        }

        // Tir sur le dernier point
        Consequence consequence = board.EnemyShoot(allShipPosition.get(allShipPosition.size()-1));
        Assertions.assertTrue(consequence.equals(Consequence.sunk));
        Assertions.assertTrue(board.GetIfLost());
    }
}
