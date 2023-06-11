package Diversos;

import java.awt.*;
import java.net.URI;

public class AbrirSiteGitHub {
    public static void abrirSiteGitHub(String url) {

        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}