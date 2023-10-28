import javax.swing.*;
import java.awt.*;

public class GamePiece extends JPanel {
    int value;
    ImageIcon image;

    GamePiece(int value)  {
        this.value = value;
        this.setSize(150, 150);
        if(value != 0) {
            this.image = new ImageIcon("src/images/Tile.png");
        }else{
            this.image = new ImageIcon("src/images/0.png");
        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            image.paintIcon(this, g, 0, 0);

            if(value != 0) {
                g.setFont(new Font("Arial", Font.BOLD, 100));
                g.setColor(Color.BLACK);

                int textX;
                if (value < 10) {
                    textX = 40;
                } else {
                    textX = 10;
                }
                int textY = 110;

                g.drawString(String.valueOf(value), textX, textY);
            }
        }
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
