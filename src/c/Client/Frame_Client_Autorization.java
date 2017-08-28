package c.Client;

import c.Client.Frames.Buhgalter_Frame;
import c.Client.Frames.Director_Frame;
import c.Client.Frames.Kassir_Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class Frame_Client_Autorization {

    Frame_Client frame_client = new Frame_Client();

    Socket socket;

    JFrame frame = new JFrame("Мой 1С: Авторизация");

    JPanel panel = new JPanel(new GridBagLayout());
    JPanel panel_osn = new JPanel(new BorderLayout());

    JLabel l_name = new JLabel("Логин: ");
    JLabel l_password = new JLabel("Пароль: ");

    JLabel l_probel1 = new JLabel(" ");
    JLabel l_probel2 = new JLabel(" ");
    JLabel l_probel3 = new JLabel(" ");
    JLabel l_probel4 = new JLabel(" ");
    JLabel l_probel_settings = new JLabel("    ");

    JTextField t_name = new JTextField(15);
    JPasswordField password = new JPasswordField(15);
    JTextField t_ip = new JTextField(15);
    JTextField t_port = new JTextField(15);

    JButton button_Enter = new JButton("Вход");
    JButton button_settings = new JButton(new ImageIcon("images/nastroi.png"));

    String getName;
    String getPassword;
    String getIp;
    String getPort;

    int schetchik;

    public void frame_Client_Autoriazation(){

        frame.setSize(300, 250);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /** Дизайн */
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0; //Размищение елемента по оси х (по горизонтали)
        c.gridy = 0; //Размищение елемента по оси у (по вертикали)
        c.gridwidth = 1; //Это эелемент занимает 1 блок по ширине
        c.gridheight = 1; //Это элемент занимает 1 блок по высоте
        c.anchor = GridBagConstraints.CENTER; //Прикрепляем елемент к северной части
        c.fill = GridBagConstraints.HORIZONTAL; //Элемент будет распологаться по горизонтали
        panel.add(l_probel1, c); //Разместили на панели наш первый елемент

        c.gridx = 0;
        c.gridy = 1;
        panel.add(l_name, c);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(l_probel_settings, c);
        c.gridx = 2;
        c.gridy = 1;
        panel.add(button_settings, c);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(t_name, c);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(l_probel2, c);
        c.gridx = 0;
        c.gridy = 4;
        panel.add(l_password, c);
        c.gridx = 0;
        c.gridy = 5;
        panel.add(password, c);
        c.gridx = 0;
        c.gridy = 6;
        panel.add(l_probel3, c);
        c.gridx = 0;
        c.gridy = 7;
        c.gridx = 0;
        c.gridy = 8;
        panel.add(button_Enter, c);
        c.gridx = 0;
        c.gridy = 9;
        panel.add(l_probel4, c);

        button_settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client_Configuration client_configuration = new Client_Configuration();
                client_configuration.client_Configuration();
                frame.setVisible(false);  //Убираем окно авторизации, пока не введут IP и Port
            }
        });

        button_Enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonEnterAction();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        panel_osn.add(panel, BorderLayout.CENTER);
        frame.add(panel_osn);
    }

    public void buttonEnterAction() throws SQLException, IOException {

        getName = t_name.getText().toString();
        getPassword = password.getText().toString();
        getIp = t_ip.getText().toString();
        getPort = t_port.getText();

        ConnectToServer connectToServer = new ConnectToServer();
        connectToServer.start();

        socket = connectToServer.socketStatus();

        if (socket.isConnected()) {

            System.out.println("Что то уже хорошо");
            ActionAfterConnection actionAfterConnection = new ActionAfterConnection(getName, getPassword, "autorization", "users", "read", null, socket);
            actionAfterConnection.start();
            System.out.println(socket.isConnected() + " check socket");
            String checkAutorization = actionAfterConnection.checkAutorization();

            if (checkAutorization.equals("true")) {
                System.out.println("ok 2");
                String position = actionAfterConnection.returnClientPosition();
                System.out.println(position);

                if (position.equals("Директор")) {
                    System.out.println("ok 3");
                    Director_Frame director_frame = new Director_Frame();
                    director_frame.director_Frame();

//                    Table_Pri table_pri = new Table_Pri();
//                    table_pri.table_Pri();
                }
                if (position.equals("Касир")) {
                    Kassir_Frame kassir_frame = new Kassir_Frame();
                    kassir_frame.kassir_Frame();
                }
                if (position.equals("Бухгалтер")) {
                    Buhgalter_Frame buhgalter_frame = new Buhgalter_Frame();
                    buhgalter_frame.buhgalter_Frame();
                }
                frame.dispose();
            }
            if (actionAfterConnection.checkAutorization().equals("false")) {

                password.setText("");
                l_probel1.setForeground(Color.RED);
                l_probel1.setText("Неверный логин или пароль");

            }
        }
    }
}
