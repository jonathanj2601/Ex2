public class CellEntry implements Index2D {
    private final String cellString;

    //constructor
    public CellEntry(String cellString) {
        this.cellString = cellString;
    }

    @Override
    public boolean isValid() {
        // Must match one letter followed by 1 or 2 digits
        if (!cellString.matches("[A-Za-z]\\d{1,2}")) {
            return false;
        }
        // Extract the numeric portion (after the first character)
        int numericVal = Integer.parseInt(cellString.substring(1));
        // Must be between 0 and 99 inclusive
        return numericVal >= 0 && numericVal <= 99;
    }

    @Override
    public int getX() {
        if (!isValid()) {
            return Ex2Utils.ERR;
        }

        // Convert letter to uppercase for simplicity
        char letter = Character.toUpperCase(cellString.charAt(0));
        // 'A' -> 0, 'B' -> 1, ..., 'Z' -> 25
        int columnIndex = letter - 'A';

        // Ensure it's in the valid range 0..25
        if (columnIndex >= 0 && columnIndex < 26) {
            return columnIndex;
        } else {
            return Ex2Utils.ERR;
        }
    }

    @Override
    public int getY() {
        if (!isValid()) {
            return Ex2Utils.ERR;
        }
        // If valid, parse the substring(1..end) as an integer
        // 'A0' -> "0", 'A12' -> "12", etc.
        return Integer.parseInt(cellString.substring(1));
    }

    private String convertX(int num) {
        if (num >= 0 && num <= 25) {
            // Convert 0->'A', 1->'B', etc. 26 letters in english
            return String.valueOf((char) ('A' + num));
        }
        return null;
    }

    private String convertY(int num) {
        return String.valueOf(num);
    }

    @Override
    public String toString() {
        if (isValid()) {
            return cellString;
        } else {
            return "Wrong Format";
        }
    }

}
