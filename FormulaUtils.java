import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormulaUtils {
    public static final String CELL_REFERENCE = "[A-Za-z]\\d{1,2}";
    public static final String NUMBER = "\\d+(\\.\\d+)?";
    public static final String TOKEN = "(" + NUMBER + "|" + CELL_REFERENCE + ")";
    public static final String PLACEHOLDER_TOKEN = "(" + TOKEN + "|X)";

    public static boolean areParenthesesBalanced(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (c == '(') {
                count++;
            }
            else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return (count == 0);
    }
    public static String evaluateParentheses(String expr) {
        Pattern p = Pattern.compile("\\([^()]*\\)");
        Matcher m = p.matcher(expr);

        while (m.find()) {
            String group = m.group();
            String inside = group.substring(1, group.length() - 1);
            double val = SCell.computeForm(inside);
            expr = expr.replace(group, String.valueOf(val));
            m = p.matcher(expr);
        }
        return expr;
    }

    public static String evaluateMulDiv(String expr) {
        Pattern p = Pattern.compile("(-?\\d+(\\.\\d+)?)([*/])(-?\\d+(\\.\\d+)?)");
        Matcher m = p.matcher(expr);

        while (m.find()) {
            double left = Double.parseDouble(m.group(1));
            String op   = m.group(3);
            double right= Double.parseDouble(m.group(4));

            double result;
            if (op.equals("*")) {
                result = left * right;
            } else {
                if (right == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = left / right;
            }
            String original = m.group(0);  // e.g. "2*3"
            expr = expr.replace(original, String.valueOf(result));
            m = p.matcher(expr);
        }
        return expr;
    }
    public static String evaluateAddSub(String expr) {
        Pattern p = Pattern.compile("(-?\\d+(\\.\\d+)?)([+\\-])(-?\\d+(\\.\\d+)?)");
        Matcher m = p.matcher(expr);

        while (m.find()) {
            double left = Double.parseDouble(m.group(1));
            String op   = m.group(3);
            double right= Double.parseDouble(m.group(4));

            double result;
            if (op.equals("+")) {
                result = left + right;
            } else {
                result = left - right;
            }
            // Replace the entire match with the computed result
            String original = m.group(0);
            expr = expr.replace(original, String.valueOf(result));

            // Reset the matcher
            m = p.matcher(expr);
        }
        return expr;
    }
    public static String replaceCellReferencesWithValues(String expr) {
        Pattern refPattern = Pattern.compile(CELL_REFERENCE);
        Matcher m = refPattern.matcher(expr);
        while (m.find()) {
            String ref = m.group(); // e.g. "A1"
            expr = expr.replace(ref, "1.0");
        }
        return expr;
    }

}
