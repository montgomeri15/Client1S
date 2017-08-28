package c.Client;

import c.Server.Users_Server;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static javax.swing.JOptionPane.OK_OPTION;

public class ConnectToServer extends Thread {

    Frame_Client_Autorization frame_client_autorization = new Frame_Client_Autorization();

    int i = 0;

    String name;
    String password;
    String clientNamePosition;
    String tableName;
    String comand;
    List<Users_Server> list;

    public static Socket socket;
    int schetchik;
    public boolean socketcheck = false;

    public ConnectToServer connectToServer;

    public ConnectToServer(){

        connectToServer = this;
    }

    public void run(){

        try {

            /**  В тектовом документе должно быть только 2 строки, первая это айпи, вторая это порт, и никак иначе!*/
            List<String> mass = Files.readAllLines(Paths.get("text.txt")); //Считываем текстовый документ


            /**Задаем логический счетчик для проверки наполненности текстового файла, ибо если всё пхать в еще один цикл фор - пахать не будет: проверено*/
            for (String stroka : mass) {  //Создаем стринговую переменную и просваиваем ей каждый элемент массива, построчно

                if (stroka==null){
                    schetchik=0;
                } else {
                    schetchik=1;
                }
            }

            /**А вот теперь мы делаем: если текстовый файл НЕ пустой, ТО:*/
            if (schetchik!=0) {

                String ip = mass.get(0); //Получаем айпи с масива с текстового файла
                int port = Integer.parseInt(mass.get(1)); //Получаем порт с амсива с текстового файла

                try {

                    InetAddress inAddres = InetAddress.getByName(ip);
                    socket = new Socket(inAddres, port);
                    socketcheck = true;
                    /** Проверяем коннект к серверу, если мы подключены, то откроется основной фрейм, если нет то перекинет
                     * в фрейм настроек! */
                    if (socket.isConnected()) {
                        System.out.println("We connected to the server");
                        i++;
                    }

                } catch (IOException e) {
                    e.printStackTrace(); //Системная ошибка которая выводится в консоль

                }
            }else {

                /** Я убрал ошибку (краш) который выводил его в консоль и сдлал вместо него опшын пан,
                 * при нажатии на кнопку ок, открывается фрейм настроек */
                int i = JOptionPane.showConfirmDialog(null, "Сервер не настроен. Зовите админа!", "Client Error", OK_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    frame_client_autorization.frame.dispose();
                    Client_Configuration client_configuration = new Client_Configuration();
                    client_configuration.client_Configuration();
                } else {
                    frame_client_autorization.frame.dispose();
                }
            }

            if (socket.isOutputShutdown() && socket.isInputShutdown()){
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket socketStatus(){

        boolean socketCheck = false;

        while (socketCheck == false) {

            if (i != 0) {
                System.out.println("Проверка сокета в классе Connect To Client");
                socketCheck = true;
                System.out.println(socketCheck);
            }
        }
        i= 0;

        return socket;
    }
}
