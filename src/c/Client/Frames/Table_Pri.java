package c.Client.Frames;

import c.Client.ActionAfterConnection;
import c.Client.ConnectToServer;
import c.Server.Users_Server;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class Table_Pri {

    JFrame frame = new JFrame("Товары");
    JPanel panel_but = new JPanel(new GridBagLayout());
    JPanel panel_table = new JPanel();
    JPanel panel_fields = new JPanel(new GridBagLayout());
    JPanel panel_osn = new JPanel(new BorderLayout());

    JLabel l_prob0 = new JLabel(" ");
    JLabel l_prob1 = new JLabel("       ");
    JLabel l_prob2 = new JLabel("       ");
    JLabel l_prob3 = new JLabel("       ");
    JLabel l_prob4 = new JLabel(" ");
    JLabel l_prob5 = new JLabel(" ");
    JLabel l_prob6 = new JLabel("          ");
    JLabel l_prob7 = new JLabel("          ");
    JLabel l_prob8 = new JLabel(" ");
    JLabel l_prob9 = new JLabel("          ");
    JLabel l_prob10 = new JLabel("          ");
    JLabel l_prob11 = new JLabel(" ");

    JLabel l_sum_den = new JLabel("День (дд.мм.гггг):  ");
    JLabel l_sum_den_tov = new JLabel("Единиц за день:  ");
    JLabel l_sum_den_dengi = new JLabel("Денег за день:  ");
    JLabel l_sum_mes = new JLabel("Выберите месяц:  ");
    JLabel l_sum_mes_tov = new JLabel("Единиц за мес.:  ");
    JLabel l_sum_mes_dengi = new JLabel("Денег за мес.:  ");


    JTextField t_sum_den = new JTextField(10);
    JTextField t_sum_den_tov = new JTextField(10);
    JTextField t_sum_den_dengi = new JTextField(10);
    JTextField t_sum_mes_tov = new JTextField(10);
    JTextField t_sum_mes_dengi = new JTextField(10);

    String[] portMesiaz = {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    JComboBox comboBox_mesiaz = new JComboBox(portMesiaz);

    JButton plus = new JButton(new ImageIcon("images/plus.png"));
    JButton minus = new JButton(new ImageIcon("images/mines.png"));
    JButton refresh = new JButton(new ImageIcon("images/refresh.png"));
    JButton rashitat = new JButton(new ImageIcon("images/calc.png"));

    DefaultTableModel model = new DefaultTableModel(0, 6);  //Позволит нам изменять таблицу
    JTable table = new JTable(model);  //Приписываем сюда модель
    JScrollPane tableContainer = new JScrollPane(table);

    int select_row;  //Выбранная строка

    //Добавляем дату:
    Date date = new Date();
    SimpleDateFormat date_format = new SimpleDateFormat("dd.MM.yyyy");  //HH - это 24-часовой формат, а hh - 12-часовой

    private static int id_tov;
    private static String nameTovaraJTable;
    private static int priceTovaraJTable;
    private static int skolkoTovaraJTable;
    private static int summaTovaraJTable;
    private static String dataTovara;

    public Table_Pri() throws SQLException {
    }

    public int getId_tov() {
        return id_tov;
    }

    public void setId_tov(int id_tov) {
        this.id_tov = id_tov;
    }

    public String getNameTovaraJTable() {
        return nameTovaraJTable;
    }

    public void setNameTovaraJTable(String nameTovaraJTable) {
        this.nameTovaraJTable = nameTovaraJTable;
    }

    public int getPriceTovaraJTable() {
        return priceTovaraJTable;
    }

    public void setPriceTovaraJTable(int priceTovaraJTable) {
        this.priceTovaraJTable = priceTovaraJTable;
    }

    public int getSkolkoTovaraJTable() {
        return skolkoTovaraJTable;
    }

    public void setSkolkoTovaraJTable(int skolkoTovaraJTable) {
        this.skolkoTovaraJTable = skolkoTovaraJTable;
    }

    public int getSummaTovaraJTable() {
        return summaTovaraJTable;
    }

    public void setSummaTovaraJTable(int summaTovaraJTable) {
        this.summaTovaraJTable = summaTovaraJTable;
    }

    public static String getDataTovara() {
        return dataTovara;
    }

    public static void setDataTovara(String dataTovara) {
        Table_Pri.dataTovara = dataTovara;
    }

    public void table_Pri() throws SQLException {

        System.out.println(date_format.format(date));

        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();  //Определяем разрешение экрана монитора
        frame.setSize(sSize);  // Задаем размер
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());  //Для таблицы

        int size = frame.getWidth();  //Определяем ширину фрейма

        t_sum_den_tov.setEditable(false);
        t_sum_den_dengi.setEditable(false);

        tableContainer.setPreferredSize(new Dimension((size-120), 500));  //Задаем размеры скролл-поля


        //Размеры столбцов таблицы:
        table.getColumnModel().getColumn(0).setMinWidth(size/12);
        table.getColumnModel().getColumn(0).setMaxWidth(size/12);
        table.getColumnModel().getColumn(1).setMinWidth(size/3);
        table.getColumnModel().getColumn(1).setMaxWidth(size/3);
        table.getColumnModel().getColumn(2).setMinWidth(size/8);
        table.getColumnModel().getColumn(2).setMaxWidth(size/8);
        table.getColumnModel().getColumn(3).setMinWidth(size/8);
        table.getColumnModel().getColumn(3).setMaxWidth(size/8);
        table.getColumnModel().getColumn(4).setMinWidth(size/8);
        table.getColumnModel().getColumn(4).setMaxWidth(size/8);
        table.getColumnModel().getColumn(5).setMinWidth(size/8);
        table.getColumnModel().getColumn(5).setMaxWidth(size/8);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel_but.add(l_prob0, c);
        c.gridx = 0;
        c.gridy = 1;
        panel_but.add(plus, c);
        c.gridx = 1;
        c.gridy = 1;
        panel_but.add(l_prob1, c);
        c.gridx = 2;
        c.gridy = 1;
        panel_but.add(minus, c);
        c.gridx = 3;
        c.gridy = 1;
        panel_but.add(l_prob2, c);
        c.gridx = 4;
        c.gridy = 1;
        panel_but.add(rashitat, c);
        c.gridx = 5;
        c.gridy = 1;
        panel_but.add(l_prob3, c);
        c.gridx = 6;
        c.gridy = 1;
        panel_but.add(refresh, c);
        c.gridx = 0;
        c.gridy = 2;
        panel_but.add(l_prob4, c);

        panel_table.add(tableContainer);

        c.gridx = 0;
        c.gridy = 0;
        panel_fields.add(l_prob5, c);
        c.gridx = 0;
        c.gridy = 1;
        panel_fields.add(l_sum_den, c);
        c.gridx = 1;
        c.gridy = 1;
        panel_fields.add(t_sum_den, c);
        c.gridx = 2;
        c.gridy = 1;
        panel_fields.add(l_prob6, c);
        c.gridx = 3;
        c.gridy = 1;
        panel_fields.add(l_sum_den_tov, c);
        c.gridx = 4;
        c.gridy = 1;
        panel_fields.add(t_sum_den_tov, c);
        c.gridx = 5;
        c.gridy = 1;
        panel_fields.add(l_prob7, c);
        c.gridx = 6;
        c.gridy = 1;
        panel_fields.add(l_sum_den_dengi, c);
        c.gridx = 7;
        c.gridy = 1;
        panel_fields.add(t_sum_den_dengi, c);
        c.gridx = 0;
        c.gridy = 2;
        panel_fields.add(l_prob8, c);
        c.gridx = 0;
        c.gridy = 3;
        panel_fields.add(l_sum_mes, c);
        c.gridx = 1;
        c.gridy = 3;
        panel_fields.add(comboBox_mesiaz, c);
        c.gridx = 2;
        c.gridy = 3;
        panel_fields.add(l_prob9, c);
        c.gridx = 3;
        c.gridy = 3;
        panel_fields.add(l_sum_mes_tov, c);
        c.gridx = 4;
        c.gridy = 3;
        panel_fields.add(t_sum_mes_tov, c);
        c.gridx = 5;
        c.gridy = 3;
        panel_fields.add(l_prob10, c);
        c.gridx = 6;
        c.gridy = 3;
        panel_fields.add(l_sum_mes_dengi, c);
        c.gridx = 7;
        c.gridy = 3;
        panel_fields.add(t_sum_mes_dengi, c);
        c.gridx = 0;
        c.gridy = 4;
        panel_fields.add(l_prob11, c);

        panel_osn.add(panel_but, BorderLayout.NORTH);
        panel_osn.add(panel_table, BorderLayout.CENTER);
        panel_osn.add(panel_fields, BorderLayout.SOUTH);
        frame.add(panel_osn);

        table.setPreferredScrollableViewportSize(new Dimension(485, 350));

        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Plus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        minus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                select_row = table.getSelectedRow();
                int tableValue = Integer.parseInt(String.valueOf(table.getValueAt(select_row, 0)));

//                ConnectToServer connectToServer = new ConnectToServer();
//                connectToServer.start();
//
//                Socket socket = connectToServer.socketStatus();

                Users_Server us = new Users_Server();
                us.setId(tableValue);
                List<Users_Server> list = new ArrayList<>();
                list.add(us);

                List<Users_Server> list1 = toServer("items", "remove", list);
                System.out.println("remove");

//                ActionAfterConnection actionAfterConnection = new ActionAfterConnection(null, null, "null", "items", "remove", list, socket);
//                actionAfterConnection.start();

                model.removeRow(select_row);
            }
        });
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    cleanTable();
                    TableRefresh();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        rashitat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int total_den_tov = 0;
                int total_den_dengi = 0;
                int total_mes_tov = 0;
                int total_mes_dengi = 0;

                for (int i = 0; i < model.getRowCount(); i++) {

                    if (t_sum_den.getText().equals(model.getValueAt(i, 5))) {

                        int amount_den_tov = Integer.parseInt(model.getValueAt(i, 3) + "");
                        total_den_tov += amount_den_tov;
                        int amount_den_dengi = Integer.parseInt(model.getValueAt(i, 4) + "");
                        total_den_dengi += amount_den_dengi;

                        t_sum_den_tov.setText(String.valueOf(total_den_tov));
                        t_sum_den_dengi.setText(String.valueOf(total_den_dengi));
                        //  }
                    } else{
                    }

                    String chast_data = model.getValueAt(i, 5).toString().substring(3, 5);

                    if (comboBox_mesiaz.getSelectedItem().equals(chast_data)){

                        int amount_mes_tov = Integer.parseInt(model.getValueAt(i, 3) + "");
                        total_mes_tov += amount_mes_tov;
                        int amount_mes_dengi = Integer.parseInt(model.getValueAt(i, 4) + "");
                        total_mes_dengi += amount_mes_dengi;

                        t_sum_mes_tov.setText(String.valueOf(total_mes_tov));
                        t_sum_mes_dengi.setText(String.valueOf(total_mes_dengi));
                    } else{

                    }
                }
            }
        });
    }

    public void Plus() throws SQLException {

        JFrame frame_plus = new JFrame("Добавление");
        JPanel panel_plus = new JPanel(new GridBagLayout());
        JPanel panel_fields_plus = new JPanel(new GridBagLayout());
        JPanel panel_but_plus = new JPanel(new GridBagLayout());
        JPanel panel_osn_plus = new JPanel();

        JButton ochist = new JButton(new ImageIcon("images/delete_tov.png"));
        JButton save = new JButton(new ImageIcon("images/save_tov.png"));

        JLabel l_probel0 = new JLabel(" ");
        JLabel l_probel1 = new JLabel(" ");
        JLabel l_probel2 = new JLabel(" ");
        JLabel l_probel3 = new JLabel(" ");
        JLabel l_probel4 = new JLabel(" ");
        JLabel l_probel5 = new JLabel("        ");
        JLabel l_probel6 = new JLabel(" ");

        JTextField t_cena = new JTextField(15);
        //Запрещаем вводить буквы и пробелы
        t_cena.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char t = e.getKeyChar();
                if ( ((t < '0') || (t > '9'))) {
                    e.consume();
                }
            }
        });
        JTextField t_kolich = new JTextField(15);
        //Запрещаем вводить буквы и пробелы
        t_kolich.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char t = e.getKeyChar();
                if ( ((t < '0') || (t > '9'))) {
                    e.consume();
                }
            }
        });
        JTextField t_summa_za_den = new JTextField(15);
        //Запрещаем вводить буквы и пробелы
        t_summa_za_den.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char t = e.getKeyChar();
                if ( ((t < '0') || (t > '9'))) {
                    e.consume();
                }
            }
        });
        t_cena.setEditable(false);
        t_summa_za_den.setEditable(false);

        JLabel l_nazw = new JLabel("Товар:");
        JLabel l_cena = new JLabel("Цена:");
        JLabel l_kolich = new JLabel("Количество:");
        JLabel l_summa_za_den = new JLabel("Сумма:");

        List<Users_Server> list = toServer("itemsName", "read", null);
        System.out.println("Мы получили лист от сервера не еще один раз: \n" + list);

        //Работа с комбобоксом:
        String[] tovMass = {" ", list.get(0).getName_tov(), list.get(1).getName_tov(), list.get(2).getName_tov(), list.get(3).getName_tov(), list.get(4).getName_tov()};
        JComboBox comboBox = new JComboBox(tovMass);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < tovMass.length; i++){

                    String comboBoxText = comboBox.getSelectedItem().toString();
                    System.out.println(comboBoxText + " Это вывод только комбобокса!");
                    for (Users_Server el : list){
                        if (el.getName_tov().equals(comboBoxText)){
                            t_cena.setText(String.valueOf(el.getPrice_name()));
                            System.out.println("Это Прайс Нейм при выставлении цены на фрейме " + el.getPrice_name() + " " + comboBoxText);
                        }
                    }
                }
            }
        });



//        //Вывод цен:
//        comboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (comboBox.getSelectedIndex()==1){
//                    try {
//                        t_cena.setText(Integer.toString(db_helper.readTableItemsNames().get(0).getPrice_name()));
//                    } catch (SQLException e1) {e1.printStackTrace();}
//                }
//            }
//        });
//        comboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (comboBox.getSelectedIndex()==2){
//                    try {
//                        t_cena.setText(Integer.toString(db_helper.readTableItemsNames().get(1).getPrice_name()));
//                    } catch (SQLException e1) {e1.printStackTrace();}
//                }
//            }
//        });
//        comboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (comboBox.getSelectedIndex()==3){
//                    try {
//                        t_cena.setText(Integer.toString(db_helper.readTableItemsNames().get(2).getPrice_name()));
//                    } catch (SQLException e1) {e1.printStackTrace();}
//                }
//            }
//        });
//        comboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (comboBox.getSelectedIndex()==4){
//                    try {
//                        t_cena.setText(Integer.toString(db_helper.readTableItemsNames().get(3).getPrice_name()));
//                    } catch (SQLException e1) {e1.printStackTrace();}
//                }
//            }
//        });
//        comboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (comboBox.getSelectedIndex()==5){
//                    try {
//                        t_cena.setText(Integer.toString(db_helper.readTableItemsNames().get(4).getPrice_name()));
//                    } catch (SQLException e1) {e1.printStackTrace();}
//                }
//            }
//        });

        frame_plus.setSize(300, 340);
        frame_plus.setLocationRelativeTo(null);
        frame_plus.setVisible(true);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel_fields_plus.add(l_probel0, c);
        c.gridx = 0;
        c.gridy = 1;
        panel_fields_plus.add(l_nazw, c);
        c.gridx = 0;
        c.gridy = 2;
        panel_fields_plus.add(comboBox, c);
        c.gridx = 0;
        c.gridy = 3;
        panel_fields_plus.add(l_probel1, c);
        c.gridx = 0;
        c.gridy = 4;
        panel_fields_plus.add(l_cena, c);
        c.gridx = 0;
        c.gridy = 5;
        panel_fields_plus.add(t_cena, c);
        c.gridx = 0;
        c.gridy = 6;
        panel_fields_plus.add(l_probel2, c);
        c.gridx = 0;
        c.gridy = 7;
        panel_fields_plus.add(l_kolich, c);
        c.gridx = 0;
        c.gridy = 8;
        panel_fields_plus.add(t_kolich, c);
        c.gridx = 0;
        c.gridy = 9;
        panel_fields_plus.add(l_probel3, c);
        c.gridx = 0;
        c.gridy = 10;
        panel_fields_plus.add(l_summa_za_den, c);
        c.gridx = 0;
        c.gridy = 11;
        panel_fields_plus.add(t_summa_za_den, c);

        c.gridx = 0;
        c.gridy = 0;
        panel_but_plus.add(l_probel4, c);
        c.gridx = 0;
        c.gridy = 1;
        panel_but_plus.add(ochist, c);
        c.gridx = 1;
        c.gridy = 1;
        panel_but_plus.add(l_probel5, c);
        c.gridx = 2;
        c.gridy = 1;
        panel_but_plus.add(save, c);
        c.gridx = 0;
        c.gridy = 2;
        panel_but_plus.add(l_probel6, c);

        c.gridx = 0;
        c.gridy = 0;
        panel_plus.add(panel_fields_plus, c);
        c.gridx = 0;
        c.gridy = 1;
        panel_plus.add(panel_but_plus, c);

        panel_osn_plus.add(panel_plus);
        frame_plus.add(panel_osn_plus);

        ochist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t_cena.setText("");
                t_kolich.setText("");
                t_summa_za_den.setText("");
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             if (t_cena.getText().equals("") || comboBox.getSelectedItem().toString().equals("") || t_kolich.getText().equals("")) {
                 JOptionPane.showMessageDialog(null, "Задайте все параметры для заказа!", "Faild", JOptionPane.YES_OPTION);
             }
             if (!t_cena.getText().equals("") && !comboBox.getSelectedItem().toString().equals("") && !t_kolich.getText().equals("")) {

                 int a = Integer.valueOf(t_cena.getText());
                 int b = Integer.valueOf(t_kolich.getText());
                 int c = a * b;

                 t_summa_za_den.setText(String.valueOf(c));

                 String nameTov = comboBox.getSelectedItem().toString();
                 int price = Integer.valueOf(t_cena.getText());
                 int skolko = Integer.valueOf(t_kolich.getText());
                 int summaTov = c;
                 String data = date_format.format(date);

                 Users_Server us = new Users_Server();
                 us.setName_tov(nameTov);
                 us.setPrice_name(price);
                 us.setSkolko(skolko);
                 us.setSumma_za_den(summaTov);
                 us.setData(data);

                 List<Users_Server> list = new ArrayList<>();
                 list.add(us);

                 toServer("items", "write", list);
              }

//             if (t_summa_za_den.getText().equals("")) {
//
//                 int a = Integer.valueOf(t_cena.getText());
//                 int b = Integer.valueOf(t_kolich.getText());
//                 int c;
//
//                 c = a * b;
//
//                 t_summa_za_den.setText(Integer.toString(c));
//
//             }
//             else {
//
//                 String getName = comboBox.getSelectedItem().toString();
//                 int getPrice = Integer.parseInt(t_cena.getText());
//                 int getKolich = Integer.parseInt(t_kolich.getText());
//                 int getSumma_tov = getPrice * getKolich;
//                 String getData = date_format.format(date);
//                 /**  Присваивам переменным значения полученые с текст филдов */
//                 setNameTovaraJTable(getName);
//                 setPriceTovaraJTable(getPrice);
//                 setSkolkoTovaraJTable(getKolich);
//                 setSummaTovaraJTable(getSumma_tov);
//                 setDataTovara(getData);
//
//                 try {
//                     List<Users_Server> list = db_helper.readTableItems();
//                     System.out.println(list.size());
//                     db_helper.writeTableItems(); //Записываем данные полученные с фрейма
//                     TableRefresh(); //Переходим в метод чтобы отобразить даные обо всех пользователях
//                     frame_plus.dispose();
//
//                } catch (SQLException e1) {
//                     e1.printStackTrace();
//                }
            }
        });
    }

    public void TableRefresh() throws SQLException {

        cleanTable();//Метод котроый очищает данные по таблице

        /**  Создаем коллекцию, которая принимает в себя значение после чтения с базы данных
         * Она создается потому что если без нее то процес добавления, а потом вытаскивания с БД зацыкливается! */
        List<Users_Server> list;
        System.out.println("Проверка 1");

        list = toServer("items", "read", null); //Обращение к методу, который обратится к серверу

        if (list.size() != 0){
            System.out.println("Это коллекция в класе при добавлении таблицы" + "\n" + list);
            if (list.size() != 0) {
               for(int i = 0; i < list.size(); i++){

                   model.addRow(new Object[]{list.get(i).getId_tov(), list.get(i).getName_tov(), list.get(i).getPrice(), list.get(i).getSkolko(), list.get(i).getSumma_za_den(), list.get(i).getData()});
                   System.out.println(list.get(i).getId_tov() + " " + list.get(i).getName_tov() + " " + list.get(i).getPrice() + " " + list.get(i).getSkolko() + " " + list.get(i).getSumma_za_den() + " Это весь шмурдяк выведенный с коллекции");
               }
            }
        }

        list.clear();
    }

    public void cleanTable(){ //Метод котроый очищает данные по таблице

        /** Чистим масив который для JTable, потому что он хранит в себе совершенно все значения и
         после второго добавления дублирует их */
        while (model.getRowCount() > 0){
            model.removeRow(0);
        }
    }

    /** Этот медот нужно для того что бы неписать на каждой кнопке или каком то действии обращение к серверу, нужно только
     * обратится к тому методу, который отправит запрос на серер, получит ответ и вернет коллекцию */
    public List<Users_Server> toServer(String tableName, String comand, List<Users_Server> list){

        List<Users_Server> listReturn = new ArrayList<>(); //Колекция которую нужно вернуть
        /* Делаем проверку на то, есть ли в коллекции какие то объекты, и если есть, то чистим ее,
         * что бы в ней не было ничего лишнего */
        if (listReturn.size() > 0) {
            listReturn.clear();
        }

        /* Отправляем запрос в клас коннекта с Сервером, что бы активировать сокет */
        ConnectToServer connectToServer = new ConnectToServer();
        connectToServer.start();

        /* Получаем значение сокета, после того как он подключился к серверу, для того что бы
         * отправить этот сокет в класс обменна данными с сервером, и с этого сокета мы будем вытаскивать
          * Входящий и Исходящие потоки*/
        Socket socket = connectToServer.socketStatus();

        /* Что бы была правильная обработка данных на сервере, необходимо отправить команды для сервера, здесь перечисленны какие
         * именно нужно отправлялть */
        ActionAfterConnection actionAfterConnection = new ActionAfterConnection(null, null, "null", tableName, comand, list, socket);
        actionAfterConnection.start();

        if (comand.equals("read")){
            listReturn = actionAfterConnection.dataServerList();
        }

        return listReturn;
    }
}
