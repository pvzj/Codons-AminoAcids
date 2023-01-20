//Code is from:
//https://www3.ntu.edu.sg/home/ehchua/programming/java/j4a_gui.html
import java.awt.*;
import java.awt.event.*;

public class GUI extends Frame {
    private Label label;
    private TextField tf;
    private Button button;
    private String input;

    public GUI() {
        setLayout(new FlowLayout());
        label = new Label("Codon:");
        add(label);

        tf = new TextField("Input Codon");
        tf.setEditable(true);
        add(tf);

        button = new Button("Submit");
        add(button);

        setTitle("Try closing me bi***");
        setSize(300,100);
        setVisible(true);
       
    }

    public static void main(String[] args) {
        new GUI();
    }

    // private class BtnCountListener implements ActionListener {
    //     @Override
    //     public void actionPerformed(ActionEvent event) {
    //         input = tf.getName();
    //     }
    // }
}
