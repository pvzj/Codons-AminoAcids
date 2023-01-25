import java.awt.*; //Gui libraries
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class menuGUI extends JFrame{ //main class
    //components of codon-amino acid page
    private JPanel CAPanel = new JPanel();
    private JLabel CAInputLabel = new JLabel();
    private JTextArea CAInputTextArea = new JTextArea();
    private JButton CAButton = new JButton();
    private JLabel CAOutputLabel = new JLabel();
    private JTextArea CAOutputTextArea = new JTextArea();

    //components of amino acid-codon page
    private JPanel ACPanel = new JPanel();
    private JLabel ACAminoAcidLabel = new JLabel();
    private JTextArea ACAminoAcidTextArea = new JTextArea();
    private JLabel ACCodonLabel = new JLabel();
    private JTextArea ACCodonTextArea = new JTextArea();
    private JButton ACButton = new JButton();
    private JTextArea ACOutputTextArea = new JTextArea();
    //rows of codon-amino acid page
    private JPanel CArow1 = new JPanel();
    private JPanel CArow2 = new JPanel();
    //rows of amino acid-codon page
    private JPanel ACrow1 = new JPanel();
    private JPanel ACrow2 = new JPanel();
    private JPanel ACrow3 = new JPanel();
    //constructor
    public menuGUI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initMenu(); //initialize menu bar
        panel1Init(); //initialize panels
        panel2Init();
        setLayout(new FlowLayout());
    }

    private void panel1Init() { //first page initialization
        CAPanel.setLayout(new GridLayout(0,1)); //overall framework
        //set size
        CArow1.setSize(new Dimension(100,10));
        //codon label
        CAInputLabel = new JLabel("                         Codon:       ");
        CArow1.add(CAInputLabel);
        //text input
        CAInputTextArea = new JTextArea(4,13);
        CAInputTextArea.setEditable(true);
        CAInputTextArea.setLineWrap(true);
        CArow1.add(CAInputTextArea);
        //button
        CAButton = new JButton("Submit");
        CArow1.add(CAButton);
        //amino acid label
        CAOutputLabel = new JLabel("Amino Acid:");
        CArow2.add(CAOutputLabel);
        //text output 
        CAOutputTextArea = new JTextArea(7,13);
        CAOutputTextArea.setLineWrap(true);
        CAOutputTextArea.setWrapStyleWord(true);
        CAOutputTextArea.setEditable(false);
        JScrollPane CAOutputTextAreaScrollPane = new JScrollPane(CAOutputTextArea);
        CArow2.add(CAOutputTextAreaScrollPane);
        //listen for button click
        CAButtonListener listener = new CAButtonListener();
        CAButton.addActionListener(listener);
        //add components
        CAPanel.add(CArow1);
        CAPanel.add(CArow2);
    
        CAPanel.setVisible(true);
    }

    private void panel2Init() {
        //overall framework
        ACPanel.setLayout(new GridLayout(0,1));
        //set size
        ACrow1.setSize(new Dimension(100,10));
        ACrow2.setSize(new Dimension(100,10));
        //amino acid label
        ACAminoAcidLabel = new JLabel("Amino Acids:");
        ACrow1.add(ACAminoAcidLabel);
        //amino acid text area
        ACAminoAcidTextArea = new JTextArea(4,13);
        ACAminoAcidTextArea.setEditable(true);
        ACAminoAcidTextArea.setLineWrap(true);
        ACAminoAcidTextArea.setWrapStyleWord(true);
        //scroll bar
        JScrollPane aminoAcidTextAreaScrollPane = new JScrollPane(ACAminoAcidTextArea);
        ACrow1.add(aminoAcidTextAreaScrollPane);
        //codon label
        ACCodonLabel = new JLabel("                          Codon:       ");
        ACrow2.add(ACCodonLabel);
        //codon text area
        ACCodonTextArea = new JTextArea(4,13);
        ACCodonTextArea.setEditable(true);
        ACCodonTextArea.setLineWrap(true);
        ACrow2.add(ACCodonTextArea);
        //scroll bar
        JScrollPane codonTextAreaScrollPane = new JScrollPane(ACCodonTextArea);
        ACrow1.add(codonTextAreaScrollPane);
        //button
        ACButton = new JButton("Submit");
        ACrow2.add(ACButton);
        //listen for button click
        ACButtonListener listener = new ACButtonListener();
        ACButton.addActionListener(listener);
        //spacing
        ACrow3.add(new JLabel("                     "));
        //output text area
        ACOutputTextArea = new JTextArea(1,13);
        ACOutputTextArea.setEditable(false);
        ACrow3.add(ACOutputTextArea);
        //add elements
        ACPanel.add(ACrow1);
        ACPanel.add(ACrow2);
        ACPanel.add(ACrow3);
        ACPanel.setVisible(true);
    }

    private class MenuAction implements ActionListener { //listens for when to change the panel
        private JPanel panel;
        private MenuAction(JPanel pnl) { //constructs panel
            this.panel = pnl;
        }
        @Override
        public void actionPerformed(ActionEvent e) { //if button is clicked
            changePanel(panel);
        }
    }
    //replace the panel currently on the screen
    private void changePanel(JPanel panel) { //changes the panel
        getContentPane().removeAll(); //removes old panel
        getContentPane().add(panel, BorderLayout.CENTER); //adds new panel
        getContentPane().doLayout();
        update(getGraphics()); //update graphics
    }

    private class CAButtonListener implements ActionListener { //listens for button click on codon-amino acid page
        @Override
        public void actionPerformed(ActionEvent event) {
            String input = CAInputTextArea.getText(); //get text currently in text box
            String aminoAcid = CodonAminoAcid.codonsToAminoAcids(input.toUpperCase()); //send to conversion function
            System.out.println(aminoAcid);
            CAOutputTextArea.setText(aminoAcid); //set output text box
            CAInputTextArea.setText(""); //reset input box
        }
    }

    private class ACButtonListener implements ActionListener { //listens for button click on amino acid-codon page
        @Override
        public void actionPerformed(ActionEvent event) {
            String aminoAcid = ACAminoAcidTextArea.getText(); //get text from text box
            String codons = ACCodonTextArea.getText();
            String output = CodonAminoAcid.aminoAcidCodonChecker(codons, aminoAcid); //send to function
            System.out.println(output); //print output for debugging
            ACOutputTextArea.setText(output); //set output text
            ACAminoAcidTextArea.setText(""); //clear input boxes
            ACCodonTextArea.setText("");
        }
    }

    private void initMenu() { //initialize menu bar
        JMenuBar menubar = new JMenuBar(); //create menu bar
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItem1 = new JMenuItem("Codons-Amino Acids");
        JMenuItem menuItem2 = new JMenuItem("Amino Acids-Codons");
        menubar.add(menu); //add components
        menu.add(menuItem1);
        menu.add(menuItem2);
        setJMenuBar(menubar);
        menuItem1.addActionListener(new MenuAction(CAPanel)); //listen for menu bar click
        menuItem2.addActionListener(new MenuAction(ACPanel));
    }

    public static void main(String[] args) { //main method, program starts from here
        menuGUI frame = new menuGUI(); //create new GUI
        try { //try to find the codon table file
            CodonAminoAcid.fillHashMap("codonTable.txt");
        } catch(IOException e) { // if not found, close program
            System.out.println("Codon Table Not Found");
            System.exit(2);
        }
        frame.setBounds(400, 400, 600, 400); //set size of window
        frame.setVisible(true);
    }
}