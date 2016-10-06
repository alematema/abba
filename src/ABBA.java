/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        
        System.out.println("\n\n=======================================================================================================================================\nTrying   to    obtain " + target + " from " + initial);
        
        String possibleOrImpossible = "Impossible to obtain "+  target + " from " + initial + "\ttook " + (System.nanoTime() - init) / 1000000  +" ms\n=======================================================================================================================================";

        if (initial.length() >= target.length()) {
            return possibleOrImpossible;
        }

        possibleOrImpossible = canObtainByUsingBruteForce(initial, target);// O(2^n)
        
        possibleOrImpossible = possibleOrImpossible.concat(" to obtain  " + target + " from " + initial + "\ttook " + (System.nanoTime() - init) / 1000000  + " ms\n=======================================================================================================================================");
        
        return possibleOrImpossible;

    }

    /**
     * Complexidade = O(2^n) Onde n = target.length - initial.lenght
     *
     */
    private String canObtainByUsingBruteForce(String initial, String target) {

        String abba = "Impossible";        

        abba = checkFirsHalf(initial, target);

        if (abba.equals("Impossible")) {

            abba = checkSecondHalf(initial, target);

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

//        try {
//            fileWriter.write("\n" + new String(binary));
//            fileWriter.write("\n" + new String(completed));
//            fileWriter.flush();
//        } catch (IOException ex) {
//            Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
//        }
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

//            try {
//                out.write("\n\t" + String.format("[%tT]", Calendar.getInstance()) + " : tryied combination " + new String(combination) + " : resulted TRUE : " + "[ " + actual + " / " + total + " ] ");
//                out.flush();
//            } catch (IOException ex) {
//                Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
//            }

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

    private String checkFirsHalf(String initial, String target) {

        String abba = "Impossible";
        
        long totalOfCombinations = (long) Math.pow(2, target.length() - initial.length());

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

    private String checkSecondHalf(String initial, String target) {

        String abba = "Impossible";

        int targetLength = target.length();
        int initialLength = initial.length();
        long totalOfCombinations = (long) Math.pow(2, targetLength - initialLength);

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

   private void describeMatchingCombination(char[] combination, String init, String target) {

        //      report();
        StringBuilder initial = new StringBuilder(init);
        System.out.println("\n\t\t-------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("\t\tdescribing operation " + new String(combination) + " on initial string " + init + " to obtain target string " + target + " -------------------------------------");
        System.out.println("\n\t\t0/1 \t\tmeaning\t\t\t\tresults\t\t\ttarget\n");
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
                System.out.println("\t\t 0" + "\tadds  A to the end of the string \t" + initial + "\t\t\t" + target);
                try {
                    out.write("\t\t 0" + "\tadds  A to the end of the string \t" + initial + "\t" + target);
                    out.flush();

                } catch (IOException ex) {
                    Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                doMoveTwo(initial);
                System.out.println("\t\t 1" + "\treverses s and adds B to  s' end  \t" + initial + "\t\t\t" + target);
                try {
                    out.write("\t\t 1" + "\treverses s and adds B to  s' end  \t" + initial + "\t" + target);
                    out.flush();
                } catch (IOException ex) {
                    Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println("\n\t\t-------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
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
        System.out.println(abba.canObtain("AAAAABB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABAAAAAAAA", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABAAAAAAAA", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABAAAAAAA", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABAAAAAA", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABAAAAAAB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABABB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAABBBAAABABABABABAB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABBBBBBBABABABABAB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABBBB", "AAAAABABABABABBBBABAAAAAAAA"));
       
    }

}
