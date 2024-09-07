import java.util.Scanner;

class TreeNode {
    char data;
    TreeNode left;
    TreeNode right;

    public TreeNode(char data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class StmtEvltn {
    static final char LP = '(';
    static final char RP = ')';
    static final char OPERATOR = 'O';
    static final char OPERAND = 'N';

    static final int LPP = 0;
    static final int AP = 1;
    static final int SP = 1;
    static final int MP = 2;
    static final int DP = 2;
    static final int REMP = 2;
    static final int NONE = 9;

    static char[] infix, stack;
    static int top;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char ch;
        do {
            top = -1;
            System.out.print("\nEnter an Infix expression : ");
            infix = sc.nextLine().toCharArray();

            // Choose conversion type
            System.out.println("Choose conversion:");
            System.out.println("1. Infix to Postfix");
            System.out.println("2. Infix to Prefix");
            System.out.print("Enter choice (1 or 2) : ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            TreeNode root = null;
            if (choice == 1) {
                char[] postfix = infixToPostfix();
                System.out.println("Postfix expression is : " + new String(postfix));
                root = constructExpressionTree(postfix);
            } else if (choice == 2) {
                char[] prefix = infixToPrefix();
                System.out.println("Prefix expression is : " + new StringBuilder(new String(prefix)).reverse());
                root = constructExpressionTree(prefix);
            } else {
                System.out.println("Invalid choice. Please choose 1 or 2.");
            }

            if (root != null) {
                System.out.println("Expression Tree constructed.");
                // Optionally, evaluate the expression tree here
                // Example: int result = evaluateExpressionTree(root);
            }

            System.out.print("Do you want to convert one more (y/n) : ");
            ch = sc.next().charAt(0);
            sc.nextLine(); // consume newline
        } while (ch == 'y');
        sc.close();
    }

    static char[] infixToPostfix() {
        int i, p, l, type, prec;
        char next;
        i = p = 0;
        l = infix.length;
        char[] postfix = new char[infix.length];
        stack = new char[infix.length]; // Initialize the stack array
        while (i < l) {
            type = getType(infix[i]);
            switch (type) {
                case LP:
                    push(infix[i]);
                    break;

                case RP:
                    while ((next = pop()) != LP) {
                        postfix[p++] = next;
                    }
                    break;

                case OPERAND:
                    postfix[p++] = infix[i];
                    break;

                case OPERATOR:
                    prec = getPrecedence(infix[i]);
                    while ((top > -1) && (prec <= getPrecedence(stack[top]))) {
                        postfix[p++] = pop();
                    }
                    push(infix[i]);
                    break;
            }
            i++;
        }
        while (top > -1) {
            postfix[p++] = pop();
        }
        return postfix;
    }

    static char[] infixToPrefix() {
        int i, p, l, type, prec;
        char next;
        i = p = 0;
        l = infix.length;
        char[] prefix = new char[infix.length];
        stack = new char[infix.length]; // Initialize the stack array
        while (i < l) {
            type = getType(infix[i]);
            switch (type) {
                case RP:
                    push(infix[i]);
                    break;

                case LP:
                    while ((next = pop()) != RP) {
                        prefix[p++] = next;
                    }
                    break;

                case OPERAND:
                    prefix[p++] = infix[i];
                    break;

                case OPERATOR:
                    prec = getPrecedence(infix[i]);
                    while ((top > -1) && (prec < getPrecedence(stack[top]))) {
                        prefix[p++] = pop();
                    }
                    push(infix[i]);
                    break;
            }
            i++;
        }
        while (top > -1) {
            prefix[p++] = pop();
        }
        return prefix;
    }

    static TreeNode constructExpressionTree(char[] expression) {
        for (char ch : expression) {
            TreeNode newNode = new TreeNode(ch);
            if (isOperator(ch)) {
                newNode.right = new TreeNode(pop());
                newNode.left = new TreeNode(pop());
                push(ch);
            } else {
                push(ch);
            }
        }
        return new TreeNode(pop());
    }

    static int getType(char sym) {
        switch (sym) {
            case LP:
                return LP;
            case RP:
                return RP;
            case '+':
            case '-':
            case '*':
            case '/':
            case '%':
                return OPERATOR;
            default:
                return OPERAND;
        }
    }

    static int getPrecedence(char sym) {
        switch (sym) {
            case LP:
                return LPP;
            case '+':
                return AP;
            case '-':
                return SP;
            case '*':
                return MP;
            case '/':
                return DP;
            case '%':
                return REMP;
            default:
                return NONE;
        }
    }

    static boolean isOperator(char sym) {
        return (sym == '+' || sym == '-' || sym == '*' || sym == '/' || sym == '%');
    }

    static void push(char sym) {
        stack[++top] = sym;
    }

    static char pop() {
        return stack[top--];
    }
}
