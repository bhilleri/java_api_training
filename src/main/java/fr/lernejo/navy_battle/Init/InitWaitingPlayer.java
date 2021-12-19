package fr.lernejo.navy_battle.Init;

import fr.lernejo.navy_battle.Controller;
import fr.lernejo.navy_battle.IController;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class InitWaitingPlayer implements IInitWaitingPlayer{
    @Override
    public boolean launch(int port) {
        try {
            final IController controller = new Controller(port);
            controller.StartServer();
            controller.StartGameWithoutConnexion();
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
