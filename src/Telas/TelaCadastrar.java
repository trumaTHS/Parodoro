package Telas;

import Estrutura.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class TelaCadastrar extends JDialog {
    private JPanel panTelaCadastrar;
    private JTextField tfUsername;
    private JTextField tfEmail;
    private JTextField tfCelular;
    private JPasswordField ptfSenha;
    private JPasswordField ptfConfirmarSenha;
    private JButton btnCadastrar;
    private JButton btnVoltar;
    private JCheckBox cbSenha;

    public TelaCadastrar (JFrame parent) {
        super(parent);
        setTitle("Cadastrar");
        setContentPane(panTelaCadastrar);
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

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastrarUsuario();
            }
        });

        cbSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox checkBox = (JCheckBox) e.getSource();
                ptfSenha.setEchoChar(checkBox.isSelected() ? '\u0000' : '*');
                ptfConfirmarSenha.setEchoChar(checkBox.isSelected() ? '\u0000' : '*');
            }
        });
    }

    private void CadastrarUsuario() {
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String celular = tfCelular.getText();
        String senha = String.valueOf(ptfSenha.getPassword());
        String confirmarsenha=String.valueOf(ptfConfirmarSenha.getPassword());

        if(username.isEmpty() || email.isEmpty() || celular.isEmpty() ||
                senha.isEmpty()|| confirmarsenha.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Por favor! Preencha todos os campos",
                    "Tente novamente", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!senha.equals(confirmarsenha)){
            JOptionPane.showMessageDialog(this,
                    "Senha não corresponde",
                    "Tente novamente",JOptionPane.ERROR_MESSAGE);
            return;
        }

        usuario = addUserToDataBase(username, email, celular, senha);
        if(usuario!=null){
            //dispose();
            JOptionPane.showMessageDialog(this,
                    "Usuário Cadastrado",
                    "Sucesso",JOptionPane.INFORMATION_MESSAGE);

            tfUsername.setEditable(true);
            tfEmail.setEditable(true);
            tfCelular.setEditable(true);
            ptfSenha.setEditable(true);
            ptfConfirmarSenha.setEditable(true);

            tfUsername.setText("");
            tfEmail.setText("");
            tfCelular.setText("");
            ptfSenha.setText("");
            ptfConfirmarSenha.setText("");

            dispose();

        }
        else{
            JOptionPane.showMessageDialog(this,
                    "Falha ao registrar novo usuário",
                    "Tente novamente",JOptionPane.ERROR_MESSAGE);
        }
    }

    public Usuario usuario;
    private Usuario addUserToDataBase(String username, String email, String celular, String senha){
        Usuario usuario = null;

        final String DB_URL = "jdbc:mysql://192.168.2.38:3306/empresa";
        final String USERNAME ="p00266";
        final String PASSWORD ="s00266";

        /*final String DB_URL = "jdbc:mysql://localhost:3306/empresa";
        final String USERNAME="root";
        final String PASSWORD="";*/

        try{
            //  Connection comm = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Connection comm = Conexao.getConnection();
            Statement stmt = comm.createStatement();

            String sql ="INSERT INTO users_thiago (username, email, celular, senha) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = comm.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, celular);
            preparedStatement.setString(4, senha);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows>0){
                usuario = new Usuario();
                usuario.username = username;
                usuario.email = email;
                usuario.celular = celular;
                usuario.senha = senha;
            }

            //stmt.close();
            //comm.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return usuario;
    }
}
