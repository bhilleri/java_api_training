package fr.lernejo.navy_battle.game.board.shoot;

import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.point.IPoint;
import fr.lernejo.navy_battle.game.ship.IShip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShootFromEnemyTest {

    @Mock
    private IShip ship1= mock(IShip.class);
    @Mock
    private IShip ship2 = mock(IShip.class);
    @Mock
    private IShip ship3 = mock(IShip.class);
    @Mock
    private IShip ship4 = mock(IShip.class);
    @Mock
    private IPoint point = mock(IPoint.class);
    private List<IShip> shipList;
    @BeforeEach
    public void initialize(){
        shipList = new ArrayList<>();
        shipList.add(ship1);
        shipList.add(ship2);
        shipList.add(ship3);
        shipList.add(ship4);
    }
    @Test
    void shootConsequenceHint() {
        ShootFromEnemy shootFromEnemy = new ShootFromEnemy(shipList);
        when(ship3.hit(point)).thenReturn(Consequence.hint);
        when(ship1.hit(point)).thenReturn(Consequence.miss);
        when(ship2.hit(point)).thenReturn(Consequence.miss);
        when(ship4.hit(point)).thenReturn(Consequence.miss);
        Consequence consequence = shootFromEnemy.shoot(point);
        Assertions.assertEquals(Consequence.hint, consequence);
    }
    @Test
    void shootConsequenceMiss() {
        ShootFromEnemy shootFromEnemy = new ShootFromEnemy(shipList);
        when(ship3.hit(point)).thenReturn(Consequence.miss);
        when(ship1.hit(point)).thenReturn(Consequence.miss);
        when(ship2.hit(point)).thenReturn(Consequence.miss);
        when(ship4.hit(point)).thenReturn(Consequence.miss);
        Consequence consequence = shootFromEnemy.shoot(point);
        Assertions.assertEquals(Consequence.miss, consequence);
    }
    @Test
    void shootConsequenceSunk() {
        ShootFromEnemy shootFromEnemy = new ShootFromEnemy(shipList);
        when(ship3.hit(point)).thenReturn(Consequence.sunk);
        when(ship1.hit(point)).thenReturn(Consequence.miss);
        when(ship2.hit(point)).thenReturn(Consequence.miss);
        when(ship4.hit(point)).thenReturn(Consequence.miss);
        Consequence consequence = shootFromEnemy.shoot(point);
        Assertions.assertEquals(Consequence.sunk, consequence);
    }

    @Test
    void updateDestroyedShipNoShipDestroyed() {
        ShootFromEnemy shootFromEnemy = new ShootFromEnemy(shipList);
        when(ship1.IsDestroy()).thenReturn(false);
        when(ship2.IsDestroy()).thenReturn(false);
        when(ship3.IsDestroy()).thenReturn(false);
        when(ship4.IsDestroy()).thenReturn(false);
        List <String> result = shootFromEnemy.UpdateDestroyedShip();
        Assertions.assertEquals(0, result.size());
    }

    @Test
    void updateDestroyedShipOne() {
        String ship3Name = "ship3";
        ShootFromEnemy shootFromEnemy = new ShootFromEnemy(shipList);
        when(ship1.IsDestroy()).thenReturn(false);
        when(ship2.IsDestroy()).thenReturn(false);
        when(ship3.IsDestroy()).thenReturn(true);
        when(ship3.getName()).thenReturn(ship3Name);
        when(ship4.IsDestroy()).thenReturn(false);
        List <String> result = shootFromEnemy.UpdateDestroyedShip();
        Assertions.assertEquals(1, result.size());
        Assertions.assertFalse(shipList.contains(ship3));
        Assertions.assertTrue(result.contains(ship3Name));
    }

    @Test
    void updateDestroyedMoreThanOne() {
        String ship1Name = "ship1";
        String ship3Name = "ship3";
        String ship4Name = "ship4";
        ShootFromEnemy shootFromEnemy = new ShootFromEnemy(shipList);
        when(ship1.IsDestroy()).thenReturn(true);
        when(ship2.IsDestroy()).thenReturn(false);
        when(ship3.IsDestroy()).thenReturn(true);
        when(ship4.IsDestroy()).thenReturn(true);
        when(ship1.getName()).thenReturn(ship1Name);
        when(ship3.getName()).thenReturn(ship3Name);
        when(ship4.getName()).thenReturn(ship4Name);
        List <String> result = shootFromEnemy.UpdateDestroyedShip();
        Assertions.assertEquals(3, result.size());
        Assertions.assertTrue(result.contains(ship1Name));
        Assertions.assertTrue(result.contains(ship3Name));
        Assertions.assertTrue(result.contains(ship4Name));
    }
}
