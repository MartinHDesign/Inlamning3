import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class Grid extends JFrame {
    ArrayList<GamePiece> pieces = new ArrayList<>();
    public Grid(){
        setLayout(new GridLayout(4,4));
        setSize(605, 630);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int counter = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                GamePiece tempPiece = new GamePiece(i + 1, j + 1, counter, counter);
                pieces.add(tempPiece);
                counter++;
            }
        }
        Collections.shuffle(pieces);
        counter = 0;
        for(GamePiece g: pieces){
            g.setCurrentSlot(counter);
            add(g);
            counter++;
        }

        setVisible(true);
    }

}
