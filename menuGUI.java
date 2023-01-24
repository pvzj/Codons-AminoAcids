import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class menuGUI extends JFrame{
    private JPanel CAPanel = new JPanel();
    private JLabel CAInputLabel;
    private JTextArea CAInputTextField;
    private JButton CAButton; 
    private JLabel CAOutputLabel;
    private JTextArea CAOutputTextField;

    private JPanel ACPanel= new JPanel();

    public menuGUI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initMenu();
        panel1Init();
        
        setLayout(new FlowLayout());
    }

    private void panel1Init() {
        CAPanel.setLayout(new GridLayout(0,1));
        JPanel row1 = new JPanel();
        row1.setSize(new Dimension(100,10));
        JPanel row2 = new JPanel();

        CAInputLabel = new JLabel("                         Codon:       ");
        row1.add(CAInputLabel);

        CAInputTextField = new JTextArea(4,13);
        
        // JScrollPane j = new JScrollPane(CAInputTextField);
        CAInputTextField.setEditable(true);
        CAInputTextField.setLineWrap(true);
        row1.add(CAInputTextField);


        CAButton = new JButton("Submit");
        row1.add(CAButton);

        CAOutputLabel = new JLabel("Amino Acid:");
        row2.add(CAOutputLabel);

        
        CAOutputTextField = new JTextArea(7,13);
        CAOutputTextField.setLineWrap(true);
        CAOutputTextField.setWrapStyleWord(true);
        CAOutputTextField.setEditable(false);
        JScrollPane CAOutputTextFieldScrollPane = new JScrollPane(CAOutputTextField);
        row2.add(CAOutputTextFieldScrollPane);

        CAButtonListener listener = new CAButtonListener();
        CAButton.addActionListener(listener);

        CAPanel.add(row1);
        CAPanel.add(row2);

        CAPanel.setVisible(true);
    }

    private class MenuAction implements ActionListener {

        private JPanel panel;
        private MenuAction(JPanel pnl) {
            this.panel = pnl;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            changePanel(panel);
        }

    }

    private class CAButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            CAOutputTextField.setText("");
            String input = CAInputTextField.getText();
            String aminoAcid = CodonAminoAcid.codonsToAminoAcids(input.toUpperCase());
            System.out.println(aminoAcid);
            CAOutputTextField.setText(aminoAcid);
            CAInputTextField.setText("");
        }
    }

    private void initMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuItem1 = new JMenuItem("Codons-Amino Acids");
        JMenuItem menuItem2 = new JMenuItem("Amino Acids-Codons");
        menubar.add(menu);
        menu.add(menuItem1);
        menu.add(menuItem2);
        setJMenuBar(menubar);
        menuItem1.addActionListener(new MenuAction(CAPanel));
        menuItem2.addActionListener(new MenuAction(ACPanel));

    }

    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        update(getGraphics());
    }

    public static void main(String[] args) {
        menuGUI frame = new menuGUI();
        try {
            CodonAminoAcid.fillHashMap("codonTable.txt");
        } catch(IOException e) {
            System.out.println("Codon Table Not Found");
            System.exit(2);
        }
        frame.setBounds(200, 200, 300, 200);
        frame.setVisible(true);

    }
}