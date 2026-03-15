package components;

import javax.swing.*;
import java.awt.*;

public class Screen extends JTextField {
    public Screen() {
        setFont(new Font("Arial", Font.BOLD, 32));
        setHorizontalAlignment(JTextField.RIGHT);
        setEditable(false);
        setText("0");
    }
}
