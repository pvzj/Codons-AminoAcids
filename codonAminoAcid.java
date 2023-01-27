// Imports
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.BufferedReader;


public class CodonAminoAcid {
    public static HashMap<String, String> table = new HashMap<>();
    // Creates a new HashMap object called table that takes only strings

    // Function that takes a three letter codon as its input and
    // returns the corresponding amino acid name
    public static String codonToAminoAcid(String codon) {
        return table.get(codon);
    }

    // Converts a string of nucleotide letters to a series of amino acids
    public static String codonsToAminoAcids(String codons) {
        String output = ""; // Output string
        int codonsLength = codons.length(); // codons string length

        // Checks to make sure codon string is in the correct format
        if (!(isValidCodonString(codons).equals("true"))) {
            output = isValidCodonString(codons);
            return output;
        }

        // Converts codons to amino acids
        for (int i = 0; i < codonsLength; i = i + 3) {
            String codon = codons.substring(i, i + 3);

            output += codonToAminoAcid(codon);

            if (i + 3 != codonsLength) {
                output += ", ";
            }
        }

        return output;
    }

    // Converts a string of nucleotide letters to an array of amino acids
    public static String[] codonsToAminoAcidsArray(String codons) {
        String[] output = new String[codons.length() / 3]; // Output string
        int codonsLength = codons.length(); // codons string length

        // Checks to make sure codon string is in the correct format
        if (!(isValidCodonString(codons).equals("true"))) {
            output[0] = "false";
            output[1] = isValidCodonString(codons);
            return output;
        }

        // Converts codons to amino acids
            for (int i = 0; i < codonsLength; i = i + 3) {
                String aminoAcid;
                String codon = codons.substring(i, i + 3);

                aminoAcid = codonToAminoAcid(codon);
                aminoAcid = aminoAcid.toLowerCase();

                output[i/3] = aminoAcid;
            }

        return output;
    }

    // Incomplete function, disregard for now
    public static boolean[] aminoAcidCodonChecker(String codons, String correctAminoAcids) {
        boolean[] output = new boolean[codons.length()/3];
        String[] aminoAcids = codonsToAminoAcidsArray(codons);

        if (aminoAcids[0].equals("false")) {
            System.out.println(aminoAcids[1]);

            return null;
        }

        String[] correctAminoAcidsArray = parseAminoAcids(correctAminoAcids);

        for (int i = 0; i < aminoAcids.length; i++) {
            if (aminoAcids[i].equals(correctAminoAcidsArray[i])) {
                output[i] = true;
            } else {
                output[i] = false;
            }
        }

        return output;
    }

    public static void fillHashMap(String fileName) throws IOException {
        // Creates a new file reader on fileName
        BufferedReader br =
                new BufferedReader(new FileReader(fileName));

        // Loops through the read in text file and
        // assigns value to the hashmap
        for (int i = 0; i < 64; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String codon = st.nextToken();
            String aminoAcid = st.nextToken();
            table.put(codon, aminoAcid);
        }
    }

    public static String[] parseAminoAcids(String input) {
        input = input.replaceAll("\\s", "");
        input = input.toLowerCase();
        String words[] = input.split(",");

        return words;
    }

    public static String parseCodons(String input) {
        String output = "";

        output = input.replaceAll("[^a-zA-Z]","");
        output = output.toUpperCase();

        return output;
    }

    public static String isValidCodonString(String s) {
        String output = "";

        if (s == null || s.length() == 0) {
            System.out.println("Empty input");
            output = "Empty input";
            return output;
        }

        if (s.length() % 3 != 0) {
            System.out.println("Length of string not divisible by 3");
            output = "Length of string not divisible by 3";
            return output;
        }

        for (int i = 0; i < s.length(); i++) {
            char c= s.charAt(i);
            if (!(c == 'A' || c == 'C' || c == 'G' || c == 'U')) {
                System.out.println("Contains invalid character");
                output = "Contains invalid character";
                return output;
            }
        }

        output = "true";
        return output;
    }


    // Main method, currently used for testing purposes
    public static void main(String[] args) throws IOException {
        // calls fillHashMap and fills the hashmap using codontable.txt
        fillHashMap("codontable.txt");

        // calls codonsToAminoAcids and spits out the corresponding
        // amino acid chain
        System.out.println();
        System.out.println(codonsToAminoAcids("CCCCCCCAAAUGACU"));
        System.out.println();

        // tests aminoAcidCodonChecker
        System.out.println(Arrays.toString(aminoAcidCodonChecker(
                "CCCCCCCAAAUGACU",
                "Proline, Proline, Glutamine, Methionine, Threonine")));


        System.out.println(Arrays.toString(codonsToAminoAcidsArray("CCCCCCCAAAUGACU")));

        // tests parseInput
        System.out.println(Arrays.toString(parseAminoAcids(
                "Proline, Proline, Glutamine, Methionine, Threonine")));
    }
}