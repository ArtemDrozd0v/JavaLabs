package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static String reverseWord(String str) {
        if (str == null) return null;
        if (str.isEmpty()) return str;

        List<String> parts = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean isWord = !Character.isWhitespace(str.charAt(0));

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            boolean isSpace = Character.isWhitespace(c);

            if (isSpace != isWord) {
                if (current.length() > 0) {
                    parts.add(current.toString());
                }
                current = new StringBuilder();
                isWord = !isWord;
            }

            current.append(c);
        }

        if (current.length() > 0) {
            parts.add(current.toString());
        }

        List<String> words = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty() && !Character.isWhitespace(part.charAt(0))) {
                words.add(part);
            }
        }

        Collections.reverse(words);

        StringBuilder result = new StringBuilder();
        int wordIndex = 0;

        for (String part : parts) {
            if (!part.isEmpty() && !Character.isWhitespace(part.charAt(0))) {
                result.append(words.get(wordIndex++));
            } else {
                result.append(part);
            }
        }

        return result.toString();
    }

    public static String replaceHex(String str) {
        if (str == null){
            return null;
        }

        StringBuilder result = new StringBuilder();
        int pos = 0;

        while (pos < str.length()) {
            if (pos <= str.length() - 10 && str.startsWith("0x", pos)) {
                try {
                    String hex = str.substring(pos + 2, pos + 10);
                    long number = Long.parseLong(hex, 16);
                    result.append(number);
                    pos += 10;
                } catch (NumberFormatException e) {
                    result.append(str.charAt(pos));
                    pos++;
                }
            } else {
                result.append(str.charAt(pos));
                pos++;
            }
        }

        return result.toString();
    }

}