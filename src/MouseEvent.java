import java.awt.event.MouseListener;
/**here is a quick tutorial on events in terms of JFrame. Events are interfaces that implement functionality for running code based off the state of the frame.
 * When an event happens, any linked listener will call the corresponding method. This is how our code gets called based off of this event.
 * For an interface, all methods must be defined somehow, even if they are left empty. This includes if you only need one function (in our case, the close window)
 * an Interface is a class "blueprint" that gives certain specifications on how a class should be built, and guarantees the methods exist for outside callers.*/
public class MouseEvent implements MouseListener {
    private static boolean breakState = false;
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        if(breakState){
            Main.board.setText("");
            Main.storage.reset();
            breakState = false;
            Main.board.repaint();
            return;
        }
        int[] temp = new int[2];
        temp[0] = e.getX();
        temp[1] = e.getY();
        temp = Main.board.calculateMouse(temp);
        if(Main.storage.putPiece(temp[0],temp[1])){
            Main.board.setText(""+Main.storage.getCurrent()+" WINS");
            breakState = true;
        }
        else if(Main.storage.isFull()){
            Main.board.setText("DRAW");
            breakState = true;
        }
        Main.board.repaint();
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
}
