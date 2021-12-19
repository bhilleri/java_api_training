package fr.lernejo.navy_battle.Init;

import fr.lernejo.navy_battle.Controller;
import fr.lernejo.navy_battle.IController;

import javax.swing.*;
import java.io.IOException;

public class InitWaitingPlayer implements IInitWaitingPlayer{
    @Override
    public boolean launch(int port) {
        try {
            final IController controller = new Controller(port);
            controller.StartServer();
            controller.startGame();
            controller.getGame().Fire();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
