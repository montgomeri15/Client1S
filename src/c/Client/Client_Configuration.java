package c.Client;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

public class Client_Configuration {

    Frame_Client_Autorization frame_client_autorization = new Frame_Client_Autorization();

    JFrame frame_Configuration = new JFrame();

    JPanel panel_osn = new JPanel(new BorderLayout());
    JPanel panelTop = new JPanel(new GridBagLayout());
    JPanel panelIP = new JPanel(new GridBagLayout());
    JPanel panelPortButton = new JPanel(new GridBagLayout());

    JLabel l_name = new JLabel("Логин:");
    JLabel l_password = new JLabel("Пароль:");
    JLabel l_ip = new JLabel("IP");
    JLabel l_port = new JLabel("port");

    JLabel l_probel1 = new JLabel(" ");
    JLabel l_probel2 = new JLabel(" ");
    JLabel l_probel3 = new JLabel(" ");
    JLabel l_probel4 = new JLabel(" ");
    JLabel l_probel5 = new JLabel(" ");

    JLabel l_point1 = new JLabel(". ");
    JLabel l_point2 = new JLabel(". ");
    JLabel l_point3 = new JLabel(". ");

    JTextField t_ip1 = new JTextField(3);
    JTextField t_ip2 = new JTextField(3);
    JTextField t_ip3 = new JTextField(3);
    JTextField t_ip4 = new JTextField(3);

    JTextField t_name = new JTextField(15);
    JPasswordField password = new JPasswordField(15);

    String[] portMass = {"7546", "5421", "3287", "1875", "1245", "6987", "8259", "3576", "1946", "9998"};

    JComboBox comboBox = new JComboBox(portMass);

    JButton button_Enter = new JButton("ОК");

    int port;
    String ip;

    String getName;
    String getPassword;

    int schetchik;  //Счетчик для проверки текстового файла: полон или пуст

    public void client_Configuration() {

        frame_Configuration.setTitle("Настройки клиента");
        frame_Configuration.setSize(300, 310);
        frame_Configuration.setVisible(true);
        frame_Configuration.setLocationRelativeTo(null);

        /** Дизайн */
        GridBagConstraints c = new GridBagConstraints();

        //Топовая панель:

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        panelTop.add(l_probel1, c);

        c.gridx = 0;
        c.gridy = 1;
        panelTop.add(l_name, c);
        c.gridx = 0;
        c.gridy = 2;
        panelTop.add(t_name, c);
        c.gridx = 0;
        c.gridy = 3;
        panelTop.add(l_probel2, c);
        c.gridx = 0;
        c.gridy = 4;
        panelTop.add(l_password, c);
        c.gridx = 0;
        c.gridy = 5;
        panelTop.add(password, c);
        c.gridx = 0;
        c.gridy = 6;
        panelTop.add(l_probel3, c);

        //Панель с IP:

        c.gridx = 0;
        c.gridy = 0;
        panelIP.add(l_ip, c);
        c.gridx = 0;
        c.gridy = 1;
        panelIP.add(t_ip1, c);
        c.gridx = 1;
        c.gridy = 1;
        panelIP.add(l_point1, c);
        c.gridx = 2;
        c.gridy = 1;
        panelIP.add(t_ip2, c);
        c.gridx = 3;
        c.gridy = 1;
        panelIP.add(l_point2, c);
        c.gridx = 4;
        c.gridy = 1;
        panelIP.add(t_ip3, c);
        c.gridx = 5;
        c.gridy = 1;
        panelIP.add(l_point3, c);
        c.gridx = 6;
        c.gridy = 1;
        panelIP.add(t_ip4, c);

        //Панель порт и кнопка:

        c.gridx = 0;
        c.gridy = 0;
        panelPortButton.add(l_port, c);
        c.gridx = 0;
        c.gridy = 1;
        panelPortButton.add(comboBox, c);
        c.gridx = 0;
        c.gridy = 2;
        panelPortButton.add(l_probel4, c);
        c.gridx = 0;
        c.gridy = 3;
        panelPortButton.add(button_Enter, c);
        c.gridx = 0;
        c.gridy = 4;
        panelPortButton.add(l_probel5, c);

        button_Enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonOK();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        /** Добавляем все елементы на панели и на фрейм */

        panel_osn.add(panelTop, BorderLayout.NORTH);
        panel_osn.add(panelIP, BorderLayout.CENTER);
        panel_osn.add(panelPortButton, BorderLayout.SOUTH);
        frame_Configuration.add(panel_osn);
    }

    public void textDocumentRead() throws IOException {  //Читаем текстовый документ, юзаем логический счетчик

        List<String> mass = Files.readAllLines(Paths.get("text.txt"));  //Выводим текстовое содержимое документа в виде массива
        for (String stroka : mass) {  //Создаем стринговую переменную и просваиваем ей каждый элемент массива, построчно

            if (stroka==null){
                schetchik=0;
            } else {
                schetchik=1;
            }
        }
    }

    public void buttonOK() throws SQLException, IOException {

        textDocumentRead();

        getName = t_name.getText();
        getPassword = password.getText();
        port = Integer.parseInt(String.valueOf(comboBox.getSelectedItem()));  //Выбираем нужный порт в комбобоксе и делаем его интовым
        ip = t_ip1.getText()+"."+t_ip2.getText()+"."+t_ip3.getText()+"."+t_ip4.getText();

        if (getName.equals("Admin") && getPassword.equals("Admin")){

            l_probel1.setForeground(Color.decode("#006400"));
            l_probel1.setText("Введите данные");

            //if (port==db_helper.readTableIpPort().get(0).getPort() && ip.equals(db_helper.readTableIpPort().get(0).getIp())){
            if (ip.equals("127.0.0.1")){

                /**Вносим данные в текстовый документ, если он пустой*/

                if (schetchik==0) {

                    System.out.println("Текстовый файл пустой. Создаем запись.");

                    try (FileWriter writer = new FileWriter("text.txt")) {
                        writer.write(ip + "\n" +port);
                        writer.flush();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    System.out.println("Текстовый файл заполнен");
                }

                frame_Configuration.dispose();
                frame_client_autorization.frame_Client_Autoriazation();
            }
            else {
                l_probel3.setForeground(Color.RED);
                l_probel3.setText("Неверный IP или порт");
            }

        } else {
            password.setText("");
            l_probel1.setForeground(Color.RED);
            l_probel1.setText("Неверный логин или пароль");
        }
    }
}
