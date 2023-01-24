import java.awt.*; //Gui libraries
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class menuGUI extends JFrame{ //main class
    //components of codon-amino acid page
    private JPanel CAPanel = new JPanel(); 
    private JLabel CAInputLabel;
    private JTextArea CAInputTextField;
    private JButton CAButton; 
    private JLabel CAOutputLabel;
    private JTextArea CAOutputTextField;

    //components of amino acid-codon page
    private JPanel ACPanel= new JPanel();

    //constructor
    public menuGUI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initMenu(); //initialize menu bar
        panel1Init(); //initialize first page
        setLayout(new FlowLayout());
    }

    private void panel1Init() { //first page initialization
        CAPanel.setLayout(new GridLayout(0,1)); //overall framework

        //two rows
        JPanel row1 = new JPanel();
        row1.setSize(new Dimension(100,10));
        JPanel row2 = new JPanel();
        //codon label
        CAInputLabel = new JLabel("                         Codon:       ");
        row1.add(CAInputLabel);
        //text input
        CAInputTextField = new JTextArea(4,13);
        CAInputTextField.setEditable(true);
        CAInputTextField.setLineWrap(true);
        row1.add(CAInputTextField);
        //button
        CAButton = new JButton("Submit");
        row1.add(CAButton);
        //amino acid label
        CAOutputLabel = new JLabel("Amino Acid:");
        row2.add(CAOutputLabel);
        //text output 
        CAOutputTextField = new JTextArea(7,13);
        CAOutputTextField.setLineWrap(true);
        CAOutputTextField.setWrapStyleWord(true);
        CAOutputTextField.setEditable(false);
        JScrollPane CAOutputTextFieldScrollPane = new JScrollPane(CAOutputTextField);
        row2.add(CAOutputTextFieldScrollPane);
        //listen for button click
        CAButtonListener listener = new CAButtonListener();
        CAButton.addActionListener(listener);
        //add components
        CAPanel.add(row1);
        CAPanel.add(row2);

        CAPanel.setVisible(true);
    }

    private class MenuAction implements ActionListener { //listens for when to change the panel
        private JPanel panel;
        private MenuAction(JPanel pnl) {
            this.panel = pnl;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            changePanel(panel);
        }

    }

    private class CAButtonListener implements ActionListener { //listens for button click on codon-amino acid page
        @Override
        public void actionPerformed(ActionEvent event) {
            String input = CAInputTextField.getText(); //get text currently in text box
            String aminoAcid = CodonAminoAcid.codonsToAminoAcids(input.toUpperCase()); //send to conversion function
            System.out.println(aminoAcid);
            CAOutputTextField.setText(aminoAcid); //set output text box
            CAInputTextField.setText(""); //reset input box
        }
    }

    private void initMenu() { //initialize menu bar
        JMenuBar menubar = new JMenuBar();
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

    private void changePanel(JPanel panel) { //changes the panel
        getContentPane().removeAll(); //removes old panel
        getContentPane().add(panel, BorderLayout.CENTER); //adds new panel
        getContentPane().doLayout();
        update(getGraphics());
    }

    public static void main(String[] args) { //main method, program starts from here
        menuGUI frame = new menuGUI(); //create new GUI
        try { //try to find the codon table file
            CodonAminoAcid.fillHashMap("codonTable.txt");
        } catch(IOException e) { // if not found, close program
            System.out.println("Codon Table Not Found");
            System.exit(2);
        }
        frame.setBounds(200, 200, 300, 200); //set size of window
        frame.setVisible(true);

    }
}