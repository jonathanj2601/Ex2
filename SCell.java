// Add your documentation below:

public class SCell implements Cell {
    private String line;
    private int type;
    // Add your code here

    public SCell(String s) {
        // Add your code here
        setData(s);
    }

    @Override
    public int getOrder() {
        // Add your code here

        return 0;
        // ///////////////////
    }

    //@Override
    @Override
    public String toString() {
        return getData();
    }

    @Override
public void setData(String s) {
        // Add your code here
        line = s;
        /////////////////////
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
    public void setType(int t) {
        type = t;
    }

    @Override
    public void setOrder(int t) {
        // Add your code here
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
    public static boolean isForm(String text){
        if(text.isEmpty())
            return false;
        if (text==null)
            return false;
        if (!(text.charAt(0) =='='))
            return false;

        return true;
    }
    public static boolean isText(String text){
        if(!isNumber(text) && !isForm(text))
            return true;
        return false;
    }
}
