package fr.lernejo.navy_battle.game;

import fr.lernejo.navy_battle.Constant;
import fr.lernejo.navy_battle.IController;
import fr.lernejo.navy_battle.clients.IClientManager;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.enumeration.Victory;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.point.Point;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {
    @Mock
    private IPlayer player = mock(IPlayer.class);
    @Mock
    private IController controller = mock(IController.class);
    @Mock
    private IClientManager iClientManager = mock(IClientManager.class);
    private Game game;
    List <List<IPoint>> shipPosition;
    List<Integer> shipSize;
    public void initialisation(boolean firstPlayer){
        // L'initialisation de Game est simillaire au test éffectué dans le Board, mais l'ordre des navires a été inversés
        shipPosition = new ArrayList<>();
        shipSize = new ArrayList<>();
        shipSize.add(5);
        shipSize.add(4);
        shipSize.add(3);
        shipSize.add(3);
        shipSize.add(2);
        for (int i = 0; i < 5 ; i++) {
            shipPosition.add(new ArrayList<>());
            for (int j = 0; j < shipSize.get(i); j++) {
                shipPosition.get(i).add(new Point(i, j));
            }
        }

        // Configuration du mock de Player afin de pouvoir saisir les positions des navires
        when(player.PositionABoat(2)).thenReturn(shipPosition.get(4));
        when(player.PositionABoat(3)).thenReturn(shipPosition.get(3)).thenReturn(shipPosition.get(2));
        when(player.PositionABoat(4)).thenReturn(shipPosition.get(1));
        when(player.PositionABoat(5)).thenReturn(shipPosition.get(0));

        // Détournement des affichages
        when(player.InformPlacement("torpilleur",shipPosition.get(4) )).thenReturn("");
        when(player.InformPlacement("contre-torpilleur1", shipPosition.get(3))).thenReturn("");
        when(player.InformPlacement("contre-torpilleur2", shipPosition.get(2))).thenReturn("");
        when(player.InformPlacement("croiseur",shipPosition.get(1) )).thenReturn("");
        when(player.InformPlacement("porte-avion",shipPosition.get(0) )).thenReturn("");

        game = new Game(controller, firstPlayer, player);
        // Positionnement des navires dans le Board
        game.Initialize();
        Assertions.assertEquals(Victory.undetermined, game.GetVictory());
    }

    @Test
    void getReadyTOShootTrue() {
        initialisation(true);
        Assertions.assertTrue(game.GetReadyTOShoot());
    }

    @Test
    void getReadyTOShootFalse() {
        initialisation(false);
        Assertions.assertFalse(game.GetReadyTOShoot());
    }

    @Test
    void receiveShoot() {
        initialisation(true);

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
            Consequence consequence = game.receiveShoot(pointWithoutShip);
            Assertions.assertEquals(Consequence.miss, consequence);
            Assertions.assertFalse(game.GetIfLost());
        }

        // Tir sur l'ensemble des points où il y a des navires à l'éxception du dernier
        for (int i = 0; i < allShipPosition.size()-1; i++) {
            Consequence consequence = game.receiveShoot(allShipPosition.get(i));
            Assertions.assertTrue(consequence.equals(Consequence.hint) || consequence.equals(Consequence.sunk));
            Assertions.assertFalse(game.GetIfLost());
        }

        // Tir sur le dernier point
        Consequence consequence = game.receiveShoot(allShipPosition.get(allShipPosition.size()-1));
        Assertions.assertTrue(consequence.equals(Consequence.sunk));
        Assertions.assertTrue(game.GetIfLost());
        Assertions.assertEquals(Victory.defeat, game.GetVictory());
    }

    public void ThreadSetTrueReadyTOShoot()
    {

    }
    @Test
    void start() throws UnknownHostException, InterruptedException {
        Answer targetedPoint = new Answer() {
            @Override
            public IPoint answer(InvocationOnMock invocationOnMock) throws Throwable {
                Runnable runner = new RunnerSetReadyToPlay(game);
                Thread thread = new Thread(runner);
                thread.start();
                return new Point(5,4);
            }
        };
        initialisation(true);
        when(player.Shoot()).thenAnswer(targetedPoint);
        when(controller.getIClientManager()).thenReturn(iClientManager);
        Answer<?> victoryResponse = new Answer<Object>() {
            @Override
            public Consequence answer(InvocationOnMock invocationOnMock) throws Throwable {
                Assertions.assertEquals(Victory.undetermined, game.GetVictory());
                game.SetVictory();
                return Consequence.sunk;
            }
        };
        when(iClientManager.Fire(new Point(5,4))).thenReturn(Consequence.miss).thenReturn(Consequence.hint).thenAnswer(victoryResponse);
        game.Start();
        Assertions.assertEquals(Victory.victory, game.GetVictory());

    }

    @Test
    void setTrueReadyTOShoot() {
        initialisation(false);
        game.SetTrueReadyTOShoot();
        Assertions.assertTrue(game.GetReadyTOShoot());
    }

    @Test
    void setVictory() {
        initialisation(true);
        when(player.InformVictory()).thenReturn("");
        game.SetVictory();
    }

    @Test
    void setDefeat() {
        initialisation(true);
        when(player.InformVictory()).thenReturn("");
        game.SetDefeat();
    }
}
