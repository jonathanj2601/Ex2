import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SCell implements Cell {
    private String line;
    private int type;
    private int order;
    String OriginalLine;

    public SCell(String s) {
        this.OriginalLine = s;
        setData(s);

    }

    @Override
    public int getOrder() {
        return this.order;
    }
    @Override
    public String getData() {
        return line;
    }
    @Override
    public int getType() {
        return type;
    }
    @Override
    public String toString() {
        return OriginalLine;
    }
    @Override
    public void setData(String s) {
        line = s;
    }
    @Override
    public void setType(int t) {
        type = t;
    }

    @Override
    public void setOrder(int t) {
        this.order = t;
    }

    public static boolean isNumber(String text)
    {
        try{
            double d = Double.parseDouble(text);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
    public static boolean isForm(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        if (!text.startsWith("="))
            return false;
        text = text.substring(1).trim();
        if (text.isEmpty())
            return false;
        if (!FormulaUtils.areParenthesesBalanced(text)) {
            return false;
        }
        String innerParensPattern = "\\([^()]*\\)"; // parentheses with no nested parentheses inside
        while (text.matches(".*" + innerParensPattern + ".*")) {
            text = text.replaceAll(innerParensPattern, "X");
        }

        String finalPattern =
                "^" + FormulaUtils.PLACEHOLDER_TOKEN +
                        "([+\\-*/]" + FormulaUtils.PLACEHOLDER_TOKEN + ")*$";

        return text.matches(finalPattern);
    }
    public static double computeForm(String text) {
        if (text == null || text.trim().isEmpty())
            return 0.0;
        if (text.startsWith("="))
            text = text.substring(1).trim();
        // Replace all known dash variants (e.g. "−", "–", "—") with a standard ASCII hyphen
        text = text.replaceAll("[−–—]", "-");
        if (text.isEmpty()) {
            return 0.0;
        }

        // 1) Evaluate innermost parentheses
        text = FormulaUtils.evaluateParentheses(text);

        // 2) Replace references like A1, B2 with actual numeric values
        //    (for now, we can stub them out or set them to 0.0 in a real scenario).
        text = FormulaUtils.replaceCellReferencesWithValues(text);

        // 3) Evaluate * and / from left to right
        text = FormulaUtils.evaluateMulDiv(text);

        // 4) Evaluate + and - from left to right
        text = FormulaUtils.evaluateAddSub(text);

        // At the end, "text" should be a single numeric value
        return Double.parseDouble(text);
    }

    public static boolean isText(String text){
        if(!isNumber(text) && !isForm(text))
            return true;
        return false;
    }
    public static int findLastOperatorIndex(String text) { //finds the last operator index
        //step 1) first pass: look for top-level '+' or '-'
        int depth = 0;
        int lastAddSubIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '(')
                depth++;
            else if (c == ')')
                depth--;
            else if ((c == '+' || c == '-') && depth == 0)
                lastAddSubIndex = i;//track the rightmost '+' or '-'
        }
        if (lastAddSubIndex != -1) //if any top-level '+'/'-' was found, return it (they happen last)
            return lastAddSubIndex;

        //step 2) second pass: look for top-level '*' or '/'
        depth = 0;
        int lastMulDivIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
            } else if ((c == '*' || c == '/') && depth == 0) {
                lastMulDivIndex = i;  // Track the rightmost '*' or '/'
            }
        }
        if (lastMulDivIndex != -1)
            return lastMulDivIndex;

        //step 3) If no operator found at all, return -1
        return -1;
    }

}
