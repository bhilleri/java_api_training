package fr.lernejo.navy_battle.game.player.Computer;

import fr.lernejo.navy_battle.Constant;
import fr.lernejo.navy_battle.game.player.IPlayer;
import fr.lernejo.navy_battle.game.point.IPoint;

import java.util.List;

public class ComputerPlayer implements IPlayer {
    private final int sizeOfBoard;
    private final IComputerSetBoatPosition computerSetBoatPosition;
    public ComputerPlayer(){
        sizeOfBoard = new Constant().GetSizeOfBoard();
        computerSetBoatPosition = new ComputerSetBoatPosition();
    }
    @Override
    public List<IPoint> PositionABoat(final int size) {
        return computerSetBoatPosition.PositionABoat(size);
    }

    @Override
    public IPoint Fire() {
        return null;
    }
}
