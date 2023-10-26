import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Grid extends JFrame {
    private int columns = 4;
    private int rows = 4;
    private boolean fixedGame = false;

    public Grid(){
        GamePiece[][] gamePieces = new GamePiece[columns][rows];
        setLayout(new GridLayout(4,4));
        setSize(605, 630);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createGamePieces(gamePieces);

        constructBoard(fixedGame, gamePieces);

        setVisible(true);
    }

    private void createGamePieces(GamePiece[][] gamePieces) {
        int counter = 0;
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++) {
                gamePieces[i][j] = new GamePiece(i + 1, j + 1, counter, counter);

                counter++;
            }
        }
    }

    private void constructBoard(boolean fixedGame, GamePiece[][] gamePieces){
        if(!fixedGame){
            Random random = new Random();
            for (int i = gamePieces.length - 1; i > 0; i--) {
                for (int j = gamePieces[i].length - 1; j > 0; j--) {
                    int m = random.nextInt(i + 1);
                    int n = random.nextInt(j + 1);

                    GamePiece temp = gamePieces[i][j];
                    gamePieces[i][j] = gamePieces[m][n];
                    gamePieces[m][n] = temp;
                }
            }
        }
    }


}
