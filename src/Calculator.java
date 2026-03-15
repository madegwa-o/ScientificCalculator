import components.Screen;
import components.operators.NumberButtons;

import javax.swing.*;
import java.awt.*;

class Calculator extends JFrame {

    Calculator() {

        this.setTitle("Scientific Calculator");
        this.setLayout(new BorderLayout(10,10));

        this.setExtendedState(JFrame.NORMAL);
        this.setBounds(50,50,500,500);

         Screen myscreen = new Screen();
        this.add(myscreen, BorderLayout.NORTH);

        NumberButtons numberButtons = new NumberButtons();
        this.add(numberButtons, BorderLayout.CENTER);

        this.setVisible(true);
    }
}