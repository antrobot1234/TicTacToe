import javax.swing.*;
import java.awt.*;
import java.util.Map;

//This is an object to insert into the Board JFrame to draw on. this is so that lines that are drawn arent drawn underneath the title bar
public class Canvas extends JPanel {
    private int xSize;
    private int ySize;
    private int yScale;
    private int xScale;

    String text;
    public Canvas(){
        resize();
        text = "";
    }
    /**This is the method that will handle all the drawing for each frame. It calls sub-draw methods to modularize the process.
     *it is an overwrite method. This means it replaces a method from the original extended definition. we can use super to refer to the original
     * it is good practice to call super.[method name]() first in the method, to make sure we don't remove any functionality from the original
     *
     * There is no reason to call paint directly from main, as it is automatically handled by jFrame.*/
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawGrid(g);
        drawTiles(g);
        drawText(g);
    }


    public void resize(){
        xSize = getWidth();
        ySize = getHeight();
        xScale = xSize/3; yScale = ySize/3;
    }

    private void drawGrid(Graphics g){
        g.drawLine(xScale,0,xScale, ySize);
        g.drawLine(xScale*2,0,xScale*2, ySize);

        g.drawLine(0,yScale, xSize,yScale);
        g.drawLine(0,yScale*2, xSize,yScale*2);
    }
    private void drawX(Graphics g,int x, int y){
        int offsetX = xSize/20;
        int offsetY = ySize/20;
        Color temp = g.getColor();
        g.setColor(Color.RED);
        g.drawLine(xScale*x+offsetX,yScale*y+offsetY,xScale*(x+1)-offsetX,yScale*(y+1)-offsetY);
        g.drawLine(xScale*(x+1)-offsetX,yScale*y+offsetY,xScale*x+offsetX,yScale*(y+1)-offsetY);
        g.setColor(temp);
    }
    private void drawO(Graphics g, int x, int y){
        int offsetX = xSize/20;
        int offsetY = ySize/20;
        Color temp = g.getColor();
        g.setColor(Color.BLUE);
        g.drawOval(xScale*x+offsetX,yScale*y+offsetY,xScale-(2*offsetX),yScale-(2*offsetY));
        g.setColor(temp);
    }
    private void drawTiles(Graphics g){
        for(Map.Entry<Point,Character> entry: Main.storage.getStorageArray().entrySet()){
            Point p = entry.getKey();
            switch(entry.getValue()){
                case 'X':drawX(g,p.x,p.y);break;
                case 'O':drawO(g,p.x,p.y);break;
            }
        }
    }
    public void setText(String text){
        this.text = text;
    }
    private void drawText(Graphics g){
        g.setFont(new Font("Times New Roman",Font.PLAIN,75));
        FontMetrics font = g.getFontMetrics();
        int stringX = font.stringWidth(text);
        int stringY = font.getHeight();
        g.drawString(text,(xSize - stringX)/2,((ySize - stringY)/2)+font.getAscent());
    }
}
