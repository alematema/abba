/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexandre
 */
public class ABBA {

    FileWriter out;
    FileWriter fileWriter;
    Scanner reader;
    private String report = "report.txt";

    public ABBA() {
        try {
            this.out = new FileWriter(report);
            fileWriter = new FileWriter("zeroes_report.txt");
        } catch (IOException ex) {
            Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String canObtain(String initial, String target) {

        long init = System.nanoTime();

        if (initial.length() >= target.length()) {
            return "Impossible";
        }

        return bruteForce(initial, target) + " " + (System.nanoTime() - init) / 1000000; // O(2^n)

    }

    /**
     * Complexidade = O(2^n) Onde n = target.length - initial.lenght
     *
     */
    private String bruteForce(String initial, String target) {

        String abba = "Impossible";

        long totalOfCombinations = (long) Math.pow(2, target.length() - initial.length());

        abba = checkFirsHalf(initial, target, totalOfCombinations);

        if (abba.equals("Impossible")) {

            abba = checkSecondHalf(initial, target, totalOfCombinations);

        }

        return abba;

    }

    public char[] reverse(char[] chars) {

        int length = chars.length;

        char[] reversed = Arrays.copyOf(chars, length);

        for (int i = 0; i < length; i++) {
            reversed[i] = chars[length - i - 1];
        }

        return reversed;
    }

    public char[] completeLeftZeroes(char[] binary, int maxLength) {

        char[] completed = Arrays.copyOf(binary, maxLength);

        for (int i = 0; i < maxLength - binary.length; i++) {
            completed[i] = '0';
        }
        int j = 0;
        for (int i = maxLength - binary.length; i < maxLength; i++) {
            completed[i] = binary[j++];
        }

        try {
            fileWriter.write("\n" + new String(binary));
            fileWriter.write("\n" + new String(completed));
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
        }
//        

        return completed;

    }

    public boolean tryCombination(char[] combination, StringBuilder initial, String target, long actual, long total) {

        for (char move : combination) {
            if (move == '0') {
                doMoveOne(initial);
            } else {
                doMoveTwo(initial);
            }
        }

        if (initial.toString().equals(target)) {

            try {
                out.write("\n\t" + String.format("[%tT]", Calendar.getInstance()) + " : tryied combination " + new String(combination) + " : resulted TRUE : " + "[ " + actual + " / " + total + " ] ");
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
            }

            return Boolean.TRUE;

        } else {

            try {
                out.write("\n\t" + String.format("[%tT]", Calendar.getInstance()) + " : tryied combination " + new String(combination) + " : resulted FALSE : " + "[ " + actual + " / " + total + " ] ");
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
            }

            return Boolean.FALSE;
        }
    }

    public boolean tryCombination(char[] combination, StringBuilder initial, String target) {

        for (char move : combination) {
            if (move == '0') {
                doMoveOne(initial);
            } else {
                doMoveTwo(initial);
            }
        }

        if (initial.toString().equals(target)) {
            System.out.println("\n\ttryied combination " + new String(combination) + " resulted : TRUE");
            return Boolean.TRUE;
        } else {
            System.out.println("\n\ttryied combination " + new String(combination) + " resulted : FALSE");
            return Boolean.FALSE;
        }

    }

    public void doMoveOne(StringBuilder initial) {
        initial.append("A");
    }

    private void doMoveTwo(StringBuilder initialSB) {
        initialSB.reverse().append("B");
    }

    private String checkFirsHalf(String initial, String target, long totalOfCombinations) {

        String abba = "Impossible";

        for (long combination = totalOfCombinations - 1; combination >= totalOfCombinations / 2; combination--) {

            char[] binary = Long.toBinaryString(combination).toCharArray();

            StringBuilder initialSB = new StringBuilder(initial);

            if (tryCombination(binary, initialSB, target.intern(), combination, totalOfCombinations)) {
                abba = "Possible";
                describeMatchingCombination(binary, initial, target);
                break;
            }

            if (combination % 2 == 0) {
                binary = reverse(binary);
                initialSB = new StringBuilder(initial);
                if (tryCombination(binary, initialSB, target, combination, totalOfCombinations)) {
                    describeMatchingCombination(binary, initial, target);
                    abba = "Possible";
                    break;
                }
            }

        }

        return abba;

    }

    private String checkSecondHalf(String initial, String target, long totalOfCombinations) {

        String abba = "Impossible";

        int targetLength = target.length();
        int initialLength = initial.length();

        long start = 0;
        if ((totalOfCombinations / 2) - 2 >= 0) {
            start = (totalOfCombinations / 2) - 2;
        }

        for (long combination = start; combination >= 0; combination -= 2) {

            char[] binary = Long.toBinaryString(combination).toCharArray();
            StringBuilder initialSB = new StringBuilder(initial);
            binary = completeLeftZeroes(binary, targetLength - initialLength);

            if (tryCombination(binary, initialSB, target, combination, (totalOfCombinations / 2))) {
                abba = "Possible";
                describeMatchingCombination(binary, initial, target);
                break;
            }
        }
        return abba;
    }

    public String reverseVowels(String s) {
//        Example 1:
//        Given s = "hello", return "holle".Example 2:
//        Given s = "leetcode", return "leotcede".
        StringBuilder sb = new StringBuilder(s);
        int left = 0;
        int right = sb.length() - 1;

        while (left <= right) {

            if (!(sb.charAt(left) == 'a' || sb.charAt(left) == 'e'
                    || sb.charAt(left) == 'i' || sb.charAt(left) == 'o'
                    || sb.charAt(left) == 'u' || sb.charAt(left) == 'A'
                    || sb.charAt(left) == 'E' || sb.charAt(left) == 'I'
                    || sb.charAt(left) == 'O' || sb.charAt(left) == 'U')) {

                left++;
                continue;

            } else {// achou alguma vogal em left

                while (!(sb.charAt(right) == 'a' || sb.charAt(right) == 'e'
                        || sb.charAt(right) == 'i' || sb.charAt(right) == 'o'
                        || sb.charAt(right) == 'u' || sb.charAt(right) == 'A'
                        || sb.charAt(right) == 'E' || sb.charAt(right) == 'I'
                        || sb.charAt(right) == 'O' || sb.charAt(right) == 'U')) {
                    right--;
                }
            }

            //left and rigth point to vowels
            char aux = sb.charAt(left);
            //swaps according to ascCode table
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, aux);

            left++;
            right--;

        }

        return sb.toString();

    }

    public String test(String s) {

        StringBuilder sb = new StringBuilder(s);

        int[] positions = new int[s.length()];

        int left = 0;
        int right = s.length() - 1;

        System.out.println("size=" + s.length());
        int pos = 0;
        while (left < right) {

            System.out.println("left=" + left + "=" + s.charAt(left) + "  right=" + right + "=" + s.charAt(right));

            if ((sb.charAt(left) == 'a' || sb.charAt(left) == 'e'
                    || sb.charAt(left) == 'i' || sb.charAt(left) == 'o'
                    || sb.charAt(left) == 'u' || sb.charAt(left) == 'A'
                    || sb.charAt(left) == 'E' || sb.charAt(left) == 'I'
                    || sb.charAt(left) == 'O' || sb.charAt(left) == 'U')) {
                positions[pos++] = left;
            }

            if (sb.charAt(right) == 'a' || sb.charAt(right) == 'e'
                    || sb.charAt(right) == 'i' || sb.charAt(right) == 'o'
                    || sb.charAt(right) == 'u' || sb.charAt(right) == 'A'
                    || sb.charAt(right) == 'E' || sb.charAt(right) == 'I'
                    || sb.charAt(right) == 'O' || sb.charAt(right) == 'U') {
                positions[pos++] = right;
            }

            left++;
            right--;
        }

        left = 0;
        right = pos - 1;
//        for(int i=0;i<pos;i++){
//            System.out.println("vowels " + positions[i]);
//        }

        while (left < pos) {
            System.out.println("\nleft=" + left + "=" + positions[left] + "  right=" + right + "=" + positions[right]);
            left++;
            pos--;
        }
        ;

        return null;
    }

    private void describeMatchingCombination(char[] combination, String init, String target) {

        //      report();
        StringBuilder initial = new StringBuilder(init);
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("describing operation " + new String(combination) + " on initial string " + init + " to get target string " + target + " -------------------------------------");
        System.out.println("\n\t0/1 \t\tmeaning\t\t\t\tresults\t\t\ttarget\n");
        try {
            out.write("\n-------------------------------------------------------------------------------------------------------------------------------------------------\n");
            out.write("describing operation " + new String(combination) + " on initial string " + init + " to get target string " + target + " -------------------------------------");
            out.write("\n\t0/1 \t\tmeaning\tresults\ttarget\n");
        } catch (IOException ex) {
            Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (char move : combination) {
            if (move == '0') {
                doMoveOne(initial);
                System.out.println("\t 0" + "\tadds  A to the end of the string \t" + initial + "\t\t\t" + target);
                try {
                    out.write("\t 0" + "\tadds  A to the end of the string \t" + initial + "\t" + target);
                    out.flush();

                } catch (IOException ex) {
                    Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                doMoveTwo(initial);
                System.out.println("\t 1" + "\treverses s and adds B to  s' end  \t" + initial + "\t\t\t" + target);
                try {
                    out.write("\t 1" + "\treverses s and adds B to  s' end  \t" + initial + "\t" + target);
                    out.flush();
                } catch (IOException ex) {
                    Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
    }

    public void report() {

        try {
            reader = new Scanner(new FileInputStream(report));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (reader.hasNextLine()) {
            System.out.println(reader.nextLine());
        }

        try {
            reader = new Scanner(new FileInputStream("zeroes_report.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (reader.hasNextLine()) {
            System.out.println(reader.nextLine());
        }

        reader.close();
    }

    public static void main(String[] args) {
        ABBA abba = new ABBA();
        System.out.println(abba.canObtain("BBBBABABBBBBBA", "BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"));
//          System.out.println(abba.canObtain("BBBBABABBBBBBA", "BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"));

        //System.out.println(abba.canObtain("AAAAABB", "AAAAABABABABABBBBABAAAAAAAA"));
        //abba.report();
        // System.out.println(abba.canObtain("ABBAABABBBBBAB", "BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"));

//        System.out.println(abba.canObtain("BBBBAAAAAAABBBBBB", "BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"));
//        System.out.println("0123456789ABCDEFGHIJKLMNOPQRSTUVWXZ");
//        System.out.println(new StringBuilder("0123456789ABCDEFGHIJKLMNOPQRSTUVWXZ").reverse().toString());
//        System.out.println(abba.reverseVowels("A man, a plan, a canal: Panama"));
//
//        long init = System.nanoTime();
//
//        abba.test("01e23456789abcde");
    }

}
