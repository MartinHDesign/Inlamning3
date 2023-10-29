import javax.swing.*;
import java.awt.*;

public class GamePiece extends JPanel {
    int value;
    ImageIcon image;
    int size;

    GamePiece(int value, int rows, int columns)  {
        this.value = value;
        size = calculateSize(rows, columns);
        this.setSize(size, size);

        ImageIcon imageIcon;
        if(value != 0) {
            imageIcon = new ImageIcon("src/images/Tile.png");
        }else{
            imageIcon = new ImageIcon("src/images/0.png");
        }

        Image image = imageIcon.getImage();
        this.image = new ImageIcon(image.getScaledInstance(size, size, Image.SCALE_SMOOTH));
    }

    private int calculateSize(int rows, int columns){
        int width = 600 / columns - 1;
        int height = 600 / rows - 1;

        return Math.min(width, height);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            image.paintIcon(this, g, 0, 0);

            if(value != 0) {
                g.setFont(new Font("Arial", Font.BOLD, size/2));
                g.setColor(Color.BLACK);

                int textX;
                if (value < 10) {
                    textX = size/3;
                } else {
                    textX = size/5;
                }
                int textY = (int) (size/1.5);

                g.drawString(String.valueOf(value), textX, textY);
            }
        }
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image, int rows, int columns) {
        Image originalImage = image.getImage();
        this.image = new ImageIcon(originalImage.getScaledInstance(size, size, Image.SCALE_SMOOTH));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
