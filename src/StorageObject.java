import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StorageObject {
    //This class serves the purpose of storing the tic-tac-toe board, as well as handling logic.
    private final HashMap<Point,Character> storageMap;
    /**Java's variable definition has 2 parts. Declaration and initialization. Declaration asserts the type of the variable and the name,
     so java can save it to memory. Initialization is where the variable actually gets assigned a value. A variable declared in this manner is an object parameter.
     Each instance of StorageObject will have a unique instance of storageArray.*/
    private boolean isXTurn;
    public StorageObject(){ //This is the constructor definition. This is how you tell java how to build an object.
        storageMap = new HashMap<>(); //Initialize the storageArray to a 3x3 grid.
        isXTurn = true;
    }
    /**This is how a java method is defined. The public keyword states that any other part of the code can call this function if it has a StorageObject object
     * The second parameter is the return value. In this case, the void keyword states that it returns nothing
     * The variables within the () are parameters. They are variables that are passed during function call and can be used locally within the function*/
    public Character putPiece(int x, int y, char symbol){
        if(x < 0 || x >= 3 || y < 0 || y >= 3)return null;
        return storageMap.putIfAbsent(new Point(x,y),symbol);
    }
    public char getPiece(int x, int y){ //This function gets the piece at a certain coordinate. if a piece does not exist there, it returns 'n'
        return storageMap.getOrDefault(new Point(x,y),'-');
    }
    //TODO make actually work.
    public boolean isWin(char symbol){
        String winCon = ""+symbol+symbol+symbol;
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0;i<=2;i++){
            temp.add(""+ getPiece(0,i)+ getPiece(1,i)+ getPiece(2,i));
        }
        if(temp.contains(winCon)){return true;}
        temp.clear();
        for (int i=0;i<=2;i++){
            temp.add(""+ getPiece(i,0)+ getPiece(i,1)+ getPiece(i,2));
        }
        if(temp.contains(winCon)){return true;}
        temp.clear();
        temp.add(""+getPiece(0,0)+getPiece(1,1)+getPiece(2,2));
        temp.add(""+getPiece(0,2)+getPiece(1,1)+getPiece(2,0));
        return temp.contains(winCon);
    }
    public void reset(){
        storageMap.clear();
    }
    //It is good practice to use getter and setter functions. This lets you control how and when certain variables are accessed and allows data validation.
    public HashMap<Point,Character> getStorageArray() {
        return storageMap;
    }
    //an overloaded method is a method that has the same name but different signature (parameter types and counts). java dynamically chooses which method is referenced
    public boolean putPiece(int x, int y){
        char c;
        if(isXTurn){c = 'X';}
        else{c = 'O';}
        if(putPiece(x,y,c) == null){
            isXTurn = !isXTurn;
        }
        return isWin(c);
    }
    public boolean isFull(){
        return storageMap.size() >= 9;
    }
    //Due to the time when this is called, the opposite of the boolean isXTurn is actually who wins in that state.
    public char getCurrent(){
        if(!isXTurn){return 'X';}
        return 'O';
    }
}
