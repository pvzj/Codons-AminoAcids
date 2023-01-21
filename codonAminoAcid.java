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

        // Checks to make sure codon string is made of complete codons
        if (codonsLength % 3 != 0 ) {
            output = "Error, incomplete codon.";
            return output;
        }

        // Converts codons to amino acids
        for (int i = 0; i < codonsLength; i = i + 3) {
            String codon = codons.substring(i, i + 3);

            output += codonToAminoAcid(codon);
            output += " ";
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
        System.out.println(aminoAcidCodonChecker("CCCCCCCAAAUGACU", "Proline Proline Glutamine Methionine Threonine "));
    }
}