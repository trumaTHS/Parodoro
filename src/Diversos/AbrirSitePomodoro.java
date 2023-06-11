package Diversos;

import java.awt.*;
import java.net.URI;

public class AbrirSitePomodoro {

    public static void abrirSitePomodoro(String url) {

        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
