package Telas;

import Diversos.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial extends JDialog {
    private Timer timer;
    private JPanel panTelaInicial;
    private JButton btnNotasAtualizacao;
    private JButton btnFuturasAtualizacao;
    private JButton btnIniciar;
    private JLabel labTemporizador;
    private JButton btnPomodoro;
    private JButton btnCurto;
    private JButton btnLongo;
    private JButton btnPausar;
    private JButton btnPular;
    private JLabel labContador;
    private JButton btnTecnicaPomodoro;
    private JButton btnGithub;
    private JButton btnPerfil;
    private JButton btnTODO;

    public TelaInicial(JFrame parent) {
        super(parent);
        setTitle("Paradoro");
        setContentPane(panTelaInicial);
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

        btnFuturasAtualizacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaFuturasAtualizacao telaFuturasAtualizacao = new TelaFuturasAtualizacao(null);
                telaFuturasAtualizacao.setVisible(true);
            }
        });

        btnNotasAtualizacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaNotasAtualizacao telaNotasAtualizacao = new TelaNotasAtualizacao(null);
                telaNotasAtualizacao.setVisible(true);
            }
        });

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarTemporizador();
                btnPomodoro.setEnabled(false);
                btnCurto.setEnabled(false);
                btnLongo.setEnabled(false);
                btnIniciar.setEnabled(false);
                btnPular.setEnabled(false);
                btnPausar.setEnabled(true);
            }
        });

        btnPomodoro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labTemporizador.setText("25:00");
                btnIniciar.setEnabled(true);
            }
        });

        btnCurto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labTemporizador.setText("05:00");
                btnIniciar.setEnabled(true);
            }
        });

        btnLongo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labTemporizador.setText("15:00");
                btnIniciar.setEnabled(true);
            }
        });

        btnPausar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pausarTemporizador();
                btnIniciar.setEnabled(true);
                btnPular.setEnabled(true);
                btnPausar.setEnabled(false);
            }
        });

        btnPular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pularTemporizador();
                btnPomodoro.setEnabled(true);
                btnCurto.setEnabled(true);
                btnLongo.setEnabled(true);
                btnIniciar.setEnabled(false);
                btnPular.setEnabled(false);
                btnPausar.setEnabled(false);
            }
        });

        btnTecnicaPomodoro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirSitePomodoro();
            }
        });

        btnGithub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirSiteGitHub();
            }
        });

        btnPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPerfil telaPerfil = new TelaPerfil(null);
                telaPerfil.setVisible(true);
            }
        });
    }

    private void iniciarTemporizador() {
        String tempo = labTemporizador.getText();
        String[] partes = tempo.split(":");

        if (partes.length != 2) {
            // Tratar erro de formato inválido
            return;
        }

        final int[] minutos = {Integer.parseInt(partes[0])};
        final int[] segundos = {Integer.parseInt(partes[1])};

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (segundos[0] > 0) {
                    segundos[0]--;
                } else if (minutos[0] > 0) {
                    minutos[0]--;
                    segundos[0] = 59;
                } else {
                    ((Timer) e.getSource()).stop();
                    JOptionPane.showMessageDialog(TelaInicial.this, "Tempo encerrado!");
                }

                atualizarLabelTemporizador(minutos[0], segundos[0]);
            }
        });

        timer.start();
    }

    private void pausarTemporizador() {
        timer.stop();
    }

    private void pularTemporizador() {
        atualizarLabelTemporizador(0,0);
    }

        private void atualizarLabelTemporizador( int minutos, int segundos){
            String tempoFormatado = String.format("%02d:%02d", minutos, segundos);
            labTemporizador.setText(tempoFormatado);
        }

    private void abrirSitePomodoro() {
        String url = "https://napratica.org.br/pomodoro/";
        AbrirSitePomodoro abrirSitePomodoro = new AbrirSitePomodoro();
        abrirSitePomodoro.abrirSitePomodoro(url);
    }

    private void abrirSiteGitHub() {
        String url = "https://github.com/trumaTHS/Parodoro";
        AbrirSiteGitHub abrirSiteGitHub = new AbrirSiteGitHub();
        abrirSiteGitHub.abrirSiteGitHub(url);
    }
}