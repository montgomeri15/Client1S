package c.Client.Frames;

import java.sql.SQLException;

public class Buhgalter_Frame {

    public void buhgalter_Frame() throws SQLException {

        Table_Pri table_pri = new Table_Pri();
        table_pri.table_Pri();
        table_pri.frame.setTitle("Бухгалтер");
    }
}
