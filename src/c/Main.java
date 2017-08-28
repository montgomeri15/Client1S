package c;

import c.Client.Frame_Client_Autorization;
import c.Client.Frames.Table_Pri;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        Frame_Client_Autorization frame_client_autorization = new Frame_Client_Autorization();
        frame_client_autorization.frame_Client_Autoriazation();
    }
}