package Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaFuturasAtualizacao extends JDialog {
    private JPanel panFuturasAtualizacao;
    private JList lisPSVM;
    private JList lisTODO;
    private JList lisMetodos;
    private JButton btnVoltar;
    private JList lisContador;

    public TelaFuturasAtualizacao (JFrame parent) {
        super(parent);
        setTitle("Futuras Atualizações");
        setContentPane(panFuturasAtualizacao);
        setMinimumSize(new Dimension(800, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Image iconImage = Toolkit.getDefaultToolkit().getImage("E:/Paradoro/src/images/iconeParadoro.png");
        iconeParadoro.setAppIcon(iconImage);

        Image appIcon = iconeParadoro.getAppIcon();
        if (appIcon != null) {
            setIconImage(appIcon);
        }

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
