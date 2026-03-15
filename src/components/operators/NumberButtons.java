package components.operators;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberButtons extends JPanel {
    JButton b0,b1, b2,b3,b4,b5,b6,b7,b8,b9;

    public NumberButtons(){
        this.setLayout(new GridLayout(3,4,5,5));
        Font btnFont = new Font("Arial", Font.BOLD, 48);
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");

        b0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        this.add(b5);
        this.add(b6);
        this.add(b7);
        this.add(b8);
        this.add(b9);
        this.add(new JLabel());
        this.add(b0);
        this.add(new JLabel());
    }
}
