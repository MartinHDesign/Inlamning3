import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePiece extends JPanel implements ActionListener {
    int xCoordinate;
    int yCoordinate;
    int value;
    int currentSlot;
    ImageIcon image;

    GamePiece(int x, int y, int value, int imageName)  {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.value = value;
        this.setSize(150, 150);
        this.image = new ImageIcon("src/images/" + imageName + ".png");
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            image.paintIcon(this, g, 0, 0);
        }
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

    public int getCurrentSlot() {
        return currentSlot;
    }

    public void setCurrentSlot(int currentSlot) {
        this.currentSlot = currentSlot;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
