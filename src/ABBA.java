/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexandre
 */
public class ABBA {

    private FileWriter out;
    private Scanner reader;
    private String report = "report.txt";
    private String initial;
    private String target;
    private StringBuilder initialSB = new StringBuilder();
    private long totalOfCombinations;

    public ABBA() {
        try {
            this.out = new FileWriter(report);
        } catch (IOException ex) {
            Logger.getLogger(ABBA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String canObtain(String initial, String target) {

        long init = System.nanoTime();

        System.out.println("\n\n=======================================================================================================================================\nTrying   to    obtain " + target + " from " + initial);

        String possibleOrImpossible = "Impossible to obtain " + target + " from " + initial + "\ttook " + (System.nanoTime() - init) / 1000000 + " ms\n=======================================================================================================================================";

        if (initial.length() >= target.length()) {
            return possibleOrImpossible;
        }

        possibleOrImpossible = tryObtainTargetFromInitialByUsingBruteForce(initial, target);// O(2^n)

        possibleOrImpossible = possibleOrImpossible.concat(" to obtain  " + target + " from " + initial + "\ttook " + (System.nanoTime() - init) / 1000000 + " ms\n=======================================================================================================================================");

        return possibleOrImpossible;

    }

    /**
     * Complexidade = O(2^n) Onde n = target.length - initial.lenght
     *
     */
    private String tryObtainTargetFromInitialByUsingBruteForce(String initial, String target) {

        this.initial = initial;
        this.target = target;
        this.totalOfCombinations = (long) Math.pow(2, target.length() - initial.length());

        initialSB = new StringBuilder(initial);

        String abba = "Impossible";

        abba = checkFirsHalf();

        if (abba.equals("Impossible")) {

            abba = checkSecondHalf();

        }

        return abba;

    }


    private String checkFirsHalf() {

        String abba = "Impossible";

        for (long combination = totalOfCombinations - 1; combination >= totalOfCombinations / 2; combination--) {

            char[] binaryCombination = Long.toBinaryString(combination).toCharArray();

            if (tryCombination(binaryCombination)) {
                abba = "Possible";
                describeMatchingCombination(binaryCombination, initial, target);
                break;
            }

            if (combination % 2 == 0) {
                binaryCombination = reverse(binaryCombination);
                initialSB = new StringBuilder(initial);
                if (tryCombination(binaryCombination)) {
                    describeMatchingCombination(binaryCombination, initial, target);
                    abba = "Possible";
                    break;
                }
            }

        }

        return abba;

    }

    private String checkSecondHalf() {

        String abba = "Impossible";

        int targetLength = target.length();
        int initialLength = initial.length();
        //long totalOfCombinations = (long) Math.pow(2, targetLength - initialLength);

        long start = 0;
        if ((totalOfCombinations / 2) - 2 >= 0) {
            start = (totalOfCombinations / 2) - 2;
        }

        for (long combination = start; combination >= 0; combination -= 2) {

            char[] binary = Long.toBinaryString(combination).toCharArray();
            binary = completeLeftZeroes(binary, targetLength - initialLength);

            if (tryCombination(binary)) {
                abba = "Possible";
                describeMatchingCombination(binary, initial, target);
                break;
            }
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

        return completed;

    }

    public boolean tryCombination(char[] combination) {

        for (char move : combination) {
            if (move == '0') {
                doMoveOne(initialSB);
            } else {
                doMoveTwo(initialSB);
            }
        }

        boolean matches = initialSB.toString().equals(target);

        initialSB.delete(0, initialSB.length());
        initialSB.append(this.initial);

        return matches;

    }

    public void doMoveOne(StringBuilder initial) {
        initial.append("A");
    }

    private void doMoveTwo(StringBuilder initialSB) {
        initialSB.reverse().append("B");
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

    public static void main(String[] args) {

        ABBA abba = new ABBA();
        
        long init = System.nanoTime();
        
        System.out.println(abba.canObtain("BBBBABABBBBBBA", "BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"));
        System.out.println(abba.canObtain("AAAAABBBB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("BABAB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABAAAAAAAA", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABAAAAAAAA", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABAAAAAAA", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABAAAAAA", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABAAAAAAB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABABABABBBBABABB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAABBBAAABABABABABAB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABBBBBBBABABABABAB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABAB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABBBB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABAB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAAABABB", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAAA", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("AAA", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("BBBA", "AAAAABABABABABBBBABAAAAAAAA"));
        System.out.println(abba.canObtain("A", "ABBA"));
        System.out.println(abba.canObtain("B", "ABBA"));
        
        System.out.println("\n\n\n took " + (System.nanoTime()-init)/1000);

    }

}
