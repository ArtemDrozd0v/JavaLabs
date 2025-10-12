package org.example;

public class StringProcessor {

    public static String copyString(String s, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n не может быть отрицательным");
        }
        if (s == null || n == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(s);
        }

        return result.toString();
    }

    public static int countStr(String mainString, String subString) {
        if (subString == null || subString.isEmpty()) {
            throw new IllegalArgumentException("подстрока не может быть пустой или null");
        }
        if (mainString == null || mainString.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;
        while ((index = mainString.indexOf(subString, index)) != -1) {
            count++;
            index+=subString.length();;
        }
        return count;
    }

    public static String replaceNumbers(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            switch (c) {
                case '1':
                    result.append("один");
                    break;
                case '2':
                    result.append("два");
                    break;
                case '3':
                    result.append("три");
                    break;
                default:
                    result.append(c);
                    break;
            }
        }
        return result.toString();
    }

    public static void removeChars(StringBuilder str) {
        if (str == null || str.length() == 0) {
            return;
        }

        for (int i = str.length() - 1; i >= 0; i--) {
            if (i % 2 == 1) {
                str.deleteCharAt(i);
            }
        }
    }

}