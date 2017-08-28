package c.Client;

import c.Server.Users_Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ActionAfterConnection extends Thread {

    Socket socket;

    transient ObjectInputStream obin;
    transient ObjectOutputStream obout;

    String name;
    String password;
    String clientNamePosition;
    String tableName;
    String comand;
    List<Users_Server> list = new ArrayList<>();
    transient List<Users_Server> listReturn = new ArrayList<>();

    String trueFalse = null;
    String clientName = null;
    boolean trueC = false;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTrueFalse() {
        return trueFalse;
    }

    public void setTrueFalse(String trueFalse) {
        this.trueFalse = trueFalse;
    }

    public boolean isTrueC() {
        return trueC;
    }

    public void setTrueC(boolean trueC) {
        this.trueC = trueC;
    }

    private static ActionAfterConnection actionAfterConnection;
    public ActionAfterConnection(String name, String password, String clientNamePosition, String tableName, String comand, List<Users_Server> list, Socket socket){
        actionAfterConnection = this;
        this.name = name;
        this.password = password;
        this.clientNamePosition = clientNamePosition;
        this.tableName = tableName;
        this.comand = comand;
        this.list = list;
        this.socket = socket;
    }

    String True;
    public ActionAfterConnection(String TRue){
        this.True = TRue;
    }

    public void run(){

        System.out.println(socket.isConnected() + " check socket 2");
        System.out.println(socket.isConnected() + " check socket for shutdown");

        try{

            System.out.println("ok 3");
            obout = new ObjectOutputStream(socket.getOutputStream());
            obin = new ObjectInputStream(socket.getInputStream());

            System.out.println("The outPut is not shutdown!");
            /** Сначала отправляем данные на сервер */
            obout.writeObject(name);
            obout.writeObject(password);
            obout.writeObject(clientNamePosition);
            obout.writeObject(tableName);
            obout.writeObject(comand);
            obout.writeObject(list);
            System.out.println("Name: " + name + "\n pass: " + password + "\nposition: " + clientNamePosition + "\nTable name: " + tableName + "\ncomand: " + comand + "\nList: " + list);
            System.out.println("The sending is finished");

            /** Теперь получаем данные с сервера
             * для каждого из коннентов получаем данные по разному*/
            if (clientNamePosition.equals("autorization")) {
                System.out.println("The autorization is opened!");
                String trueF = (String) obin.readObject();
                String clientN = (String) obin.readObject();

                //listReturn = (List<Users_Server>) obin.readObject();
                System.out.println("ok 1");
                setTrueC(true);
                setTrueFalse(trueF);
                setClientName(clientN);
                System.out.println("Это то что мы получили при авторихации от сервера " + getTrueFalse() + "  " + getClientName());
            }
            else{
                if (comand.equals("read")){
                    listReturn = (List<Users_Server>) obin.readObject();
                    System.out.println(list + "\n Это то что мы только только получили от сервера при команде чтения! ");
                }
                if (comand.equals("remove")){
                    listReturn = (List<Users_Server>) obin.readObject();
                }
                if (comand.equals("write")){
                    listReturn = (List<Users_Server>) obin.readObject();
                }
            }

            System.out.println("exit");
            stop();

            obout.flush();
            //list.clear();

            socket.getInputStream().close();
            socket.getOutputStream().close();
            //socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** Этот метод возвращает значение True или False для авторизации (Если True - то авторизацию прошел, если False - то не прошел!) */
    public String checkAutorization(){

        System.out.println("Check autorization!");
        boolean trueF = false;
        while (trueF == false){
            System.out.println("Это то что мы получили при авторихации от сервера Уже далеко не первый раз: " + getTrueFalse() + "  " + getClientName());
            if (trueFalse != null) {
                System.out.println("Неожиданная проверка");
                if (getClientName().equals("Директор")) {

                    trueF = true;
                }
                if (getClientName().equals("Кассир")) {
                    trueF = true;
                }
                if (getClientName().equals("Бухгалтер")) {
                    trueF = true;
                }
            }
        }

        return trueFalse;
    }

    /** Этот метод возвращает название клиента, что бы потом в классе авторизации можно было выбрать какой фрейм открывать */
    public String returnClientPosition(){

        boolean trueM = false;
        while (trueM == false){
            if (clientName != null){

                trueM = true;
            }
        }
        return clientName;
    }

    /** Этот метод возвращает Коллекцию полученую из сервера, для заполнения таблиц */
    public List<Users_Server> dataServerList(){
        System.out.println("Здесь мы получаем лист");
        boolean trueF = false;
        while (trueF == false){
            if (listReturn.size() != 0){
                System.out.println("Мы получили лист от Сервера!");
                trueF = true;
            }
        }
        return listReturn;
    }
}
