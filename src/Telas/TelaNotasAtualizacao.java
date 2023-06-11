package Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaNotasAtualizacao extends JDialog {
    private JPanel panNotasAtualizacao;
    private JList lisNotasAtualizacao;
    private JButton btnVoltar;

    public TelaNotasAtualizacao (JFrame parent) {
        super(parent);
        setTitle("Notas de Atualização");
        setContentPane(panNotasAtualizacao);
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
