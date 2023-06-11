package Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TelaPerfil extends JDialog {
    private JPanel panTelaPerfil;
    private JTextField tfUsername;
    private JTextField tfEmail;
    private JButton btnCarregarDados;

    public TelaPerfil(JFrame parent) {
        super(parent);
        setTitle("Perfil");
        setContentPane(panTelaPerfil);
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

        btnCarregarDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarregarDados();
            }
        });
    }

    private void CarregarDados() {
        try {
            // Estabelecer conexão com o banco de dados
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "");

            // Executar a consulta SQL
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username, email FROM users_thiago WHERE id = 1");

            // Processar o resultado
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");

                // Exibir os dados no formulário
                tfUsername.setText(username);
                tfEmail.setText(email);
            }

            // Fechar recursos
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
