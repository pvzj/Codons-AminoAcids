// Imports
import java.io.FileReader;
import java.io.IOException;
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
        if (!isValidCodonString(codons)) {
            output = "Error, invalid codon.";
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

    // Incomplete function, disregard for now
    public static String aminoAcidCodonChecker(String codons, String correctAminoAcids) {
        String output = "";
        String aminoAcids = codonsToAminoAcids(codons);
        System.out.println(aminoAcids);

        if (aminoAcids.equals(correctAminoAcids)) {
            output = "Correct.";
        } else {
            output = "Incorrect.";
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

    public static String parseInput(String input) {
        String output = "";
        input = input.replaceAll("\\s", "");
        input = input.toLowerCase();
        String words[] = input.split(",");

        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
            output += words[i];
        }

        return output;
    }

    public static boolean isValidCodonString(String s) {
        if (s == null || s.length() == 0) {
            System.out.println("Empty input");
            return false;
        }

        if (s.length() % 3 != 0) {
            System.out.println("Length of string not divisible by 3");
            return false;
        }

        if (new StringTokenizer(s, " ").countTokens() != 1) {
            System.out.println("More than one token long");
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            char c= s.charAt(i);
            if (!(c == 'A' || c == 'C' || c == 'G' || c == 'U')) {
                System.out.println("Contains invalid character");
                return false;
            }
        }

        return true;
    }


    // Main method, currently used for testing purposes
    public static void main(String[] args) throws IOException {
        // calls fillHashMap and fills the hashmap using codontable.txt
        fillHashMap("codontable.txt");

        // calls getAminoAcidFromCodon using "CCC" as a test codon and prints
        System.out.println(codonToAminoAcid("CCC"));

        // calls codonsToAminoAcids and spits out the corresponding
        // amino acid chain
        System.out.println(codonsToAminoAcids("CCCCCCCAAAUGACU"));

        // tests aminoAcidCodonChecker
        System.out.println(aminoAcidCodonChecker(
                "CCCCCCCAAAUGACU",
                "Proline Proline Glutamine Methionine Threonine "));

        // tests parseInput
        System.out.println(parseInput(
                "Proline, Proline, Glutamine, Methionine, Threonine"));
    }
}