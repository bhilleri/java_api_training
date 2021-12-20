package fr.lernejo.navy_battle.Init;

import fr.lernejo.navy_battle.Controller;
import fr.lernejo.navy_battle.IController;

import java.io.IOException;

public class InitConnectPlayer implements IInitConnectPlayer {
    @Override
    public boolean launch(int port, String uri) {
        try {
            final IController controller = new Controller(port, uri);
            controller.StartServer();
            controller.StartGameWithConnexion();
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }

    }
}
