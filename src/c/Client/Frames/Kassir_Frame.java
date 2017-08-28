package c.Client.Frames;

import java.sql.SQLException;

public class Kassir_Frame {

    public void kassir_Frame() throws SQLException {

        Table_Pri table_pri = new Table_Pri();
        table_pri.table_Pri();
        table_pri.frame.setTitle("Кассир");

        table_pri.l_sum_mes.setVisible(false);
        table_pri.comboBox_mesiaz.setVisible(false);
        table_pri.l_prob9.setVisible(false);
        table_pri.l_sum_mes_tov.setVisible(false);
        table_pri.t_sum_mes_tov.setVisible(false);
        table_pri.l_prob10.setVisible(false);
        table_pri.l_sum_mes_dengi.setVisible(false);
        table_pri.t_sum_mes_dengi.setVisible(false);
        table_pri.l_prob11.setVisible(false);
    }
}
