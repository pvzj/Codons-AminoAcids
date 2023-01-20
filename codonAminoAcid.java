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

    public static String aminoAcidCodonChecker(String codons, String aminoAcids) {
        String output = "";

        for (int i = 0; i < codons.length(); i = i + 3) {
            
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
    }
}