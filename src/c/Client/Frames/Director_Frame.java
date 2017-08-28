package c.Client.Frames;

import java.sql.SQLException;

public class Director_Frame {

    public void director_Frame() throws SQLException {

        Table_Pri table_pri = new Table_Pri();
        table_pri.table_Pri();
        table_pri.frame.setTitle("Директор");
    }
}
