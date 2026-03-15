import components.Screen;
import components.operators.NumberButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

class Calculator extends JFrame implements ActionListener {

    private final Screen screen;
    private final StringBuilder expression;
    private final ExpressionEvaluator evaluator;

    Calculator() {
        setTitle("Scientific Calculator");
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 50, 540, 650);

        expression = new StringBuilder();
        evaluator = new ExpressionEvaluator();

        screen = new Screen();
        add(screen, BorderLayout.NORTH);

        NumberButtons buttons = new NumberButtons(this);
        add(buttons, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String command = event.getActionCommand();

        switch (command) {
            case "C" -> {
                expression.setLength(0);
                updateScreen("0");
            }
            case "DEL" -> {
                if (!expression.isEmpty()) {
                    expression.deleteCharAt(expression.length() - 1);
                }
                updateScreen(expression.isEmpty() ? "0" : expression.toString());
            }
            case "=" -> evaluateExpression();
            case "sin", "cos", "tan", "log", "ln", "sqrt" -> {
                expression.append(command).append("(");
                updateScreen(expression.toString());
            }
            case "pi" -> appendToken("pi");
            case "e" -> appendToken("e");
            default -> appendToken(command);
        }
    }

    private void evaluateExpression() {
        if (expression.isEmpty()) {
            return;
        }

        try {
            double result = evaluator.evaluate(expression.toString());
            String formatted = formatResult(result);
            expression.setLength(0);
            expression.append(formatted);
            updateScreen(formatted);
        } catch (RuntimeException ex) {
            expression.setLength(0);
            updateScreen("Error");
        }
    }

    private String formatResult(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return "Error";
        }
        DecimalFormat formatter = new DecimalFormat("0.##########");
        return formatter.format(value);
    }

    private void appendToken(String token) {
        expression.append(token);
        updateScreen(expression.toString());
    }

    private void updateScreen(String value) {
        screen.setText(value);
    }
}
