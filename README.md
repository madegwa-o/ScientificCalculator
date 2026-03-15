# ScientificCalculator

A Java Swing scientific calculator with support for:

- Basic arithmetic (`+`, `-`, `*`, `/`, `%`)
- Parentheses
- Power operator (`^`)
- Constants (`pi`, `e`)
- Scientific functions: `sin`, `cos`, `tan`, `log`, `ln`, `sqrt`

## Run

```bash
javac -d out src/Main.java src/Calculator.java src/ExpressionEvaluator.java src/components/Screen.java src/components/operators/NumberButtons.java
java -cp out Main
```
