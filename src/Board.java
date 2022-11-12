import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

/**A class which handles the display of the board and pieces, as well as the menu.
 * It extends JFrame, a basic graphics API for java. Extending, in java terms, just means that it inherits
 * all attributes of the object it extends, including methods and variables.*/
public class Board extends JFrame {
    private final Canvas canvas;
    public Board(MouseListener mListener){
        canvas = new Canvas();
        addMouseListener(mListener);
        add(canvas,BorderLayout.CENTER);
        addComponentListener(new ComponentAdapter() {//Adapters are a special varient of listeners that can be implemented on the fly with a single method definition.
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                canvas.resize();
            }
        });
        setup(); //This separation is done to keep the constructor concise and to allow the re-call of setup later.
    }
    public void setup(){
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //This method sets the program to terminate when the "X" button in the top right is clicked
        setSize(1000,1023);
        setLocationRelativeTo(null);//Sets the starting point of the window. Null is used as a special case in that it centers the window. THIS IS A SPECIAL CASE FOR NULL.
        setMinimumSize(new Dimension(250,250)); //Makes it so that the window cannot be shrunk smaller than 250x250
        setVisible(true);
    }
    //This function calculates what tile was clicked based off of coordinates. It is in the board class since this is where the sizes are stored
    public int[] calculateMouse(int[] size){
        if(size.length != 2){return null;}
        int[] out = new int[2];
        out[0] = size[0] / (getWidth()/3);
        out[1] = size[1] / (getHeight()/3);
        return out;
    }
    public void setText(String text){
        canvas.setText(text);
    }
}
