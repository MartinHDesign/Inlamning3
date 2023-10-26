import javax.swing.*;
import java.awt.*;

public class Grid extends JFrame {
    public Grid(){
        setLayout(new GridLayout(4,4));
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int counter = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                GamePiece tempPiece = new GamePiece(i + 1, j + 1, counter, counter);
                add(tempPiece);
                counter++;
            }
        }

        setVisible(true);
    }
}
