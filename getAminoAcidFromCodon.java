import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.BufferedReader;

import java.util.*;
public class getAminoAcidFromCodon {
    public static HashMap<String, String> table = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("codontable.txt"));
        
        for (int i = 0; i < 64; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String codon = st.nextToken();
            String aminoAcid = st.nextToken();
            table.put(codon, aminoAcid);
        }

        System.out.println(getAminoAcidFromCodon("CCC"));
    }
    
    public static String getAminoAcidFromCodon(String codon) {
        return table.get(codon);
    }
}