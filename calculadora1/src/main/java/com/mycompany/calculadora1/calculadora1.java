package com.mycompany.calculadora1;
import java.util.Stack;


public class calculadora1 {
    
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    
    public static int precedence(char c) {
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }

  
    public static String infixToPostfix(String expression) {
        Stack<Character> stack = new Stack<>();
        String postfix = "";
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                postfix += c;
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && isOperator(stack.peek()) && precedence(stack.peek()) >= precedence(c)) {
                    postfix += stack.pop();
                }
                stack.push(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix += stack.pop();
                }
                stack.pop();
            }
        }
        while (!stack.isEmpty()) {
            postfix += stack.pop();
        }
        return postfix;
    }

   
    public static double evaluatePostfix(String expression) {
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                stack.push((double) (c - '0'));
            } else if (isOperator(c)) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                switch (c) {
                    case '+' -> stack.push(operand1 + operand2);
                    case '-' -> stack.push(operand1 - operand2);
                    case '*' -> stack.push(operand1 * operand2);
                    case '/' -> stack.push(operand1 / operand2);
                    default -> {
                    }
                }
            }
        }
        return stack.pop();
    }

    
    public static String postfixToPrefix(String expression) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stack.push(Character.toString(c));
            } else if (isOperator(c)) {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                stack.push(c + operand1 + operand2);
            }
        }
        return stack.pop();
    }

    
    public static String postfixToInfix(String expression) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                stack.push(Character.toString(c));
            } else if (isOperator(c)) {
                String operand2 = stack.pop();
                String operand1 = stack.pop();
                stack.push("(" + operand1 + c + operand2 + ")");
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
         String expression = "2+3*4";
        String notation = "infixa";

        if (notation.equals("infixa")) {
            expression = infixToPostfix(expression);
        }

        double result = evaluatePostfix(expression);
        String prefix = postfixToPrefix(expression);
        String infix = postfixToInfix(expression);

        System.out.println("Resultado: " + result);
        System.out.println("Notação pré-fixa: " + prefix);
        System.out.println("Notação infixa: " + infix);
        System.out.println("Notação pós-fixa: " + expression);
    }
}