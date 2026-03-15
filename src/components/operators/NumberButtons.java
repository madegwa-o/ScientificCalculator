package components.operators;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NumberButtons extends JPanel {

    public NumberButtons(ActionListener listener) {
        setLayout(new GridLayout(6, 5, 6, 6));
        setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));

        Font btnFont = new Font("Arial", Font.BOLD, 20);

        String[] labels = {
                "C", "DEL", "(", ")", "/",
                "sin", "cos", "tan", "^", "*",
                "log", "ln", "sqrt", "%", "-",
                "7", "8", "9", "pi", "+",
                "4", "5", "6", "e", "=",
                "1", "2", "3", ".", "0"
        };

        for (String label : labels) {
            JButton button = new JButton(label);
            button.setFont(btnFont);
            button.setFocusable(false);
            button.addActionListener(listener);
            add(button);
        }
    }
}
