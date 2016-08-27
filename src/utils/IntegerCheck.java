package utils;

/**
 * Created by Dima on 16.05.2016.
 */
public class IntegerCheck {
    private static final int radix_10 = 10;

    public static boolean isPositiveInteger(String s) {
        return isPositiveInteger(s,radix_10);
    }

    private static boolean isPositiveInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        if (s.charAt(0) == '-') return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}
