package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.ColorConsole;
import fr.lernejo.navy_battle.Constant;
import fr.lernejo.navy_battle.enumeration.Consequence;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

public class ComputerPlayer implements IPlayer {
    private final int sizeOfBoard;
    private final IComputerShootOnEnemy computerShootOnEnemy;
    private final IComputerSetBoatPosition computerSetBoatPosition;
    private final ColorConsole color = new ColorConsole();
    public ComputerPlayer(){
        sizeOfBoard = new Constant().GetSizeOfBoard();
        computerSetBoatPosition = new ComputerSetBoatPosition();
        this.computerShootOnEnemy = new ComputerShootOnEnemy();
    }
    @Override
    public List<IPoint> PositionABoat(final int size) {
        return computerSetBoatPosition.PositionABoat(size);
    }

    @Override
    public IPoint Shoot() {
        IPoint point = this.computerShootOnEnemy.shoot();
        return point;
    }
    @Override
    public void InformEnemySHoot(IPoint point, Consequence consequence)
    {
        System.out.println(color.Red() + "L'adversaire a tiré en : " + point.toString() + " conséquance :" + consequence.toString() + color.Reset());
    }

    @Override
    public void InformShipLost(List<String> shipNameList) {
        for (String shipName : shipNameList) {
            System.out.println(color.Purple() + "L'adversaire à coulé :" + shipName.toString() + color.Reset());
        }
    }

    @Override
    public void InformConsequenceOfShoot(IPoint point, Consequence consequence) {
        System.out.println(color.Green() + "Tir sur : " + point.toString() + " : " + consequence.toString());
    }

    @Override
    public void InformVictory() {
        System.out.println("\n" + color.Green() + "Victoire");
    }
}
