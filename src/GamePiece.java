import javax.swing.*;
import java.awt.event.*;

public class GamePiece extends JFrame implements ActionListener {
    int xCoordinate;
    int yCoordinate;
    int value;

    GamePiece(int x, int y, int value){
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.value = value;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
