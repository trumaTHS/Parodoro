import Estrutura.*;
import Telas.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JDialog {
    private JPanel panLogin;
    private JButton btnEntrar;
    private JTextField tfUsername;
    private JPasswordField ptfSenha;
    private JCheckBox cbSenha;
    private JButton btnCadastrar;

    public Login(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(panLogin);
        setMinimumSize(new Dimension(800, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        Image iconImage = Toolkit.getDefaultToolkit().getImage("E:/Paradoro/src/images/iconeParadoro.png");
        iconeParadoro.setAppIcon(iconImage);

        Image appIcon = iconeParadoro.getAppIcon();
        if (appIcon != null) {
            setIconImage(appIcon);
        }

        btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identifier = tfUsername.getText(); // Pode ser tanto o username quanto o email e celular
                String senha = String.valueOf(ptfSenha.getPassword());

                user = (Usuario) getAuthenticatedUser(identifier, senha);

                if (user != null) {
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(Login.this,
                            "Nome de usuário ou senha inválida",
                            "Credenciais inválidas",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        cbSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                ptfSenha.setEchoChar(checkBox.isSelected() ? '\u0000' : '*');
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastrar telaCadastrar = new TelaCadastrar(null);
                telaCadastrar.setVisible(true);
            }
        });

        setVisible(true);
    }

    public Usuario user;
    private Object getAuthenticatedUser(String identifier, String password) {
        Usuario user = null;

        /*final String DB_URL = "jdbc:mysql://192.168.2.38:3306/empresa";
        final String USERNAME="p00266";
        final String PASSWORD="s00266";*/

        final String DB_URL = "jdbc:mysql://localhost:3306/empresa";
        final String USERNAME="root";
        final String PASSWORD="";

        Connection conn = Conexao.getConnection();

        try{
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users_thiago WHERE (username=? OR email=? OR celular=?) AND senha=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, identifier);
            preparedStatement.setString(2, identifier);
            preparedStatement.setString(3, identifier);
            preparedStatement.setString(4, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new Usuario();
                user.username = resultSet.getString("username");
                user.email = resultSet.getString("email");
                user.celular = resultSet.getString("celular");
                user.senha = resultSet.getString("senha");
            }

            //stmt.close();
            //conn.close();

        } catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static void main(String[] args) {
        Login login = new Login(null);

        Usuario user = login.user;
        if (user != null) {
            TelaInicial telaInicial = new TelaInicial(null);
            telaInicial.setVisible(true);
        }
    }
}
