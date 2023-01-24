//Code is inspired by:
//https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GUI extends Frame {
    private Frame frame;
    private Label label;
    private TextField tf;
    private Button button;
    private String input;
    private boolean pushed;

    public GUI() {
        frame = new Frame("Codons-Amino Acids");
        frame.setSize(400,400);
        frame.setLayout(new FlowLayout());
        frame.addWindowListener(new GUIWindowListener());

        label = new Label("Codon:");
        add(label);

        tf = new TextField(9);
        tf.setEditable(true);
        add(tf);

        button = new Button("Submit");
        add(button);

        BtnListener listener = new BtnListener();
        button.addActionListener(listener);

        pushed = false;

        addWindowListener(new GUIWindowListener());

        frame.add(label);
        frame.add(tf);
        frame.add(button);

        // frame.setVisible(true);
       
    }

    public String getText() {
        return tf.getText();
    }

    public boolean isPushed() {
        return pushed;
    }

    private void showMenu() {
        final MenuBar menuBar = new MenuBar();
        Menu Menu1 = new Menu("Shit1");
        Menu Menu2 = new Menu("Shit2");
        Menu Menu3 = new Menu("Shit3");
        
        
        menuBar.add(Menu1);
        menuBar.add(Menu2);
        menuBar.add(Menu3);
        frame.setMenuBar(menuBar);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        GUI g = new GUI();
        g.showMenu();
        try {
            CodonAminoAcid.fillHashMap("codonTable.txt");
        } catch(IOException e) {
            System.out.println("Codon Table Not Found");
            System.exit(2);
        }
    }

    private class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println(CodonAminoAcid.codonsToAminoAcids(tf.getText()));
            tf.setText("");
        }
    }

    private class GUIWindowListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosed(WindowEvent e) {}

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }

        @Override
        public void windowIconified(WindowEvent e) {
            System.out.println("Iconified");
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            System.out.println("Deiconified");
        }

        @Override
        public void windowActivated(WindowEvent e) {
            System.out.println("Window Activated");
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            System.out.println("Window Deactivated");
        }

    }

    class MenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {            
            label.setText(e.getActionCommand() 
              + " MenuItem clicked.");
        }    
     }
}
