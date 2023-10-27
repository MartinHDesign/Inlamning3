import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Grid extends JFrame {
    private final int HORIZONTAL_SPACING = 151;
    private final int VERTICAL_SPACING = 155;
    private final int MENU_OFFSET = 30;
    private int rows = 4;
    private int columns = 4;
    GamePiece[][] gamePieces = new GamePiece[columns][rows];
    private boolean fixedGame = false;

    public Grid(){
        setLayout(new GridLayout(rows, columns));
        setSize(columns * HORIZONTAL_SPACING, rows * VERTICAL_SPACING + MENU_OFFSET);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createGamePieces(gamePieces);

        constructBoard(fixedGame, gamePieces);

        Menu menu = new Menu();
        menu.setGrid(this);
        setJMenuBar(menu);

        setVisible(true);
    }

    private void createGamePieces(GamePiece[][] gamePieces) {
        int counter = 0;
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++) {
                gamePieces[i][j] = new GamePiece(i + 1, j + 1, counter);

                counter++;
            }
        }
    }

    private void constructBoard(boolean fixedGame, GamePiece[][] gamePieces){
        if(!fixedGame){
            Random random = new Random();
            for (int i = gamePieces.length - 1; i >= 0; i--) {
                for (int j = gamePieces[i].length - 1; j >= 0; j--) {
                    int m = random.nextInt(i + 1);
                    int n = random.nextInt(j + 1);

                    GamePiece temp = gamePieces[i][j];
                    gamePieces[i][j] = gamePieces[m][n];
                    gamePieces[m][n] = temp;
                }
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    add(gamePieces[i][j]);
                }
            }
        }
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                add(gamePieces[j][i]);
            }
        }
    }

    void newGame(boolean isGameFixed){
        for(GamePiece[] g: gamePieces){
            for(GamePiece gp: g){
                remove(gp);
            }
        }
        if(isGameFixed){
            fixedGame = true;
        }else{
            fixedGame = false;
        }
        createGamePieces(gamePieces);
        constructBoard(fixedGame, gamePieces);

        for(GamePiece[] g: gamePieces){
            for(GamePiece gp: g){
                add(gp);
            }
        }

        revalidate();
        repaint();
    }

}
