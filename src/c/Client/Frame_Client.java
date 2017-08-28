package c.Client;

import javax.swing.*;
import java.awt.*;

public class Frame_Client {

    JFrame frame = new JFrame("Клиент");
    JPanel panel = new JPanel(new GridBagLayout());
    JLabel label = new JLabel(" ");
    JButton start = new JButton("Старт");

    public void frame_Client(){

        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        /** Дизайн */
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(label, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(start, c);

        frame.add(panel);


    }
}

