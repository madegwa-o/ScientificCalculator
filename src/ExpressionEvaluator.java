class ExpressionEvaluator {

    public double evaluate(String expression) {
        Parser parser = new Parser(expression);
        double result = parser.parseExpression();
        if (parser.hasRemaining()) {
            throw new IllegalArgumentException("Unexpected token at position " + parser.position());
        }
        return result;
    }

    private static final class Parser {
        private final String input;
        private int index;

        private Parser(String input) {
            this.input = input.replaceAll("\\s+", "");
            this.index = 0;
        }

        private int position() {
            return index;
        }

        private boolean hasRemaining() {
            return index < input.length();
        }

        private double parseExpression() {
            double value = parseTerm();
            while (hasRemaining()) {
                char ch = input.charAt(index);
                if (ch == '+') {
                    index++;
                    value += parseTerm();
                } else if (ch == '-') {
                    index++;
                    value -= parseTerm();
                } else {
                    break;
                }
            }
            return value;
        }

        private double parseTerm() {
            double value = parseFactor();
            while (hasRemaining()) {
                char ch = input.charAt(index);
                if (ch == '*') {
                    index++;
                    value *= parseFactor();
                } else if (ch == '/') {
                    index++;
                    double denominator = parseFactor();
                    if (denominator == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    value /= denominator;
                } else if (ch == '%') {
                    index++;
                    double denominator = parseFactor();
                    if (denominator == 0) {
                        throw new ArithmeticException("Modulo by zero");
                    }
                    value %= denominator;
                } else {
                    break;
                }
            }
            return value;
        }

        private double parseFactor() {
            double base = parseUnary();
            while (hasRemaining() && input.charAt(index) == '^') {
                index++;
                double exponent = parseUnary();
                base = Math.pow(base, exponent);
            }
            return base;
        }

        private double parseUnary() {
            if (!hasRemaining()) {
                throw new IllegalArgumentException("Incomplete expression");
            }

            char ch = input.charAt(index);
            if (ch == '+') {
                index++;
                return parseUnary();
            }
            if (ch == '-') {
                index++;
                return -parseUnary();
            }
            return parsePrimary();
        }

        private double parsePrimary() {
            if (!hasRemaining()) {
                throw new IllegalArgumentException("Unexpected end of expression");
            }

            if (input.charAt(index) == '(') {
                index++;
                double value = parseExpression();
                if (!hasRemaining() || input.charAt(index) != ')') {
                    throw new IllegalArgumentException("Missing closing parenthesis");
                }
                index++;
                return value;
            }

            if (Character.isLetter(input.charAt(index))) {
                String name = parseIdentifier();

                if ("pi".equals(name)) {
                    return Math.PI;
                }
                if ("e".equals(name)) {
                    return Math.E;
                }

                if (!hasRemaining() || input.charAt(index) != '(') {
                    throw new IllegalArgumentException("Expected '(' after function " + name);
                }
                index++;
                double argument = parseExpression();
                if (!hasRemaining() || input.charAt(index) != ')') {
                    throw new IllegalArgumentException("Missing ')' after function " + name);
                }
                index++;

                return switch (name) {
                    case "sin" -> Math.sin(Math.toRadians(argument));
                    case "cos" -> Math.cos(Math.toRadians(argument));
                    case "tan" -> Math.tan(Math.toRadians(argument));
                    case "log" -> Math.log10(argument);
                    case "ln" -> Math.log(argument);
                    case "sqrt" -> Math.sqrt(argument);
                    default -> throw new IllegalArgumentException("Unknown function: " + name);
                };
            }

            return parseNumber();
        }

        private String parseIdentifier() {
            int start = index;
            while (hasRemaining() && Character.isLetter(input.charAt(index))) {
                index++;
            }
            return input.substring(start, index);
        }

        private double parseNumber() {
            int start = index;
            boolean seenDot = false;

            while (hasRemaining()) {
                char ch = input.charAt(index);
                if (Character.isDigit(ch)) {
                    index++;
                } else if (ch == '.' && !seenDot) {
                    seenDot = true;
                    index++;
                } else {
                    break;
                }
            }

            if (start == index) {
                throw new IllegalArgumentException("Expected number at position " + index);
            }

            return Double.parseDouble(input.substring(start, index));
        }
    }
}
