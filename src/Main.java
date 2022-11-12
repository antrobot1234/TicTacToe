public class Main {
    //Something to note. It is good practice not to clutter the main function. It serves to run the program, and putting too much actual logic will bog it down
    public static Board board;
    public static StorageObject storage;
    public static void main(String[] args){
        //Here is where code is actually executed. The main function serves as a starting point for all java programs

        //Here we create the event listeners for the window. They are created here so main has easy access to their outputs.
        //Here we create another listener for the mouse. This one is especially important since this app is mouse-controlled.
        MouseEvent mEvent = new MouseEvent();
        //create a new object for the menu
        board = new Board(mEvent);
        storage = new StorageObject();
    }
}
