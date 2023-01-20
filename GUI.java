//Code is from:
//https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
import java.awt.*;
import java.awt.event.*;

public class GUI extends Frame {
    private Label label;
    private TextField tf;
    private Button button;
    private String input;
    private boolean pushed;

    public GUI() {
        setLayout(new FlowLayout());
        label = new Label("Codon:");
        add(label);

        tf = new TextField("Input Codon");
        tf.setEditable(true);
        add(tf);

        button = new Button("Submit");
        add(button);

        BtnListener listener = new BtnListener();
        button.addActionListener(listener);

        pushed = false;

        setTitle("Try closing me bi***");
        setSize(300,100);
        setVisible(true);
       
    }

    public String getText() {
        return tf.getText();
    }

    public boolean isPushed() {
        return pushed;
    }

    public static void main(String[] args) {
        GUI g = new GUI();
        
    }

    private class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            System.out.println(tf.getText());
        }
    }
}
