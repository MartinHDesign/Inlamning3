import javax.swing.*;
import java.awt.*;

import java.util.Random;

public class Grid extends JFrame {
    private final int HORIZONTAL_SPACING = 151;
    private final int VERTICAL_SPACING = 155;
    private int rows = 4;
    private int columns = 4;
    private boolean fixedGame = true;
    GamePiece[][] gamePieces = new GamePiece[columns][rows];



    public Grid(){
        setLayout(new GridLayout(rows, columns));
        setSize(columns * HORIZONTAL_SPACING, rows * VERTICAL_SPACING);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createGamePieces(gamePieces);
        addMouseListener(gamePieces);

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
    private void addMouseListener(GamePiece[][] gamePieces){
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GamePiece temp = gamePieces[i][j];
                temp.addMouseListener(new GameListener());
                System.out.println(gamePieces[i][j].getValue());
//                gamePieces[i][j].addMouseListener(new GameListener());
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
        }else{
            GamePiece temp = gamePieces[0][0];
            gamePieces[0][0] = gamePieces[0][1];
            gamePieces[0][1] = temp;
        }
        for(int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                add(gamePieces[i][j]);
            }
        }
    }
    public void setGamePieces(GamePiece[][] gamePieces) {
        this.gamePieces = gamePieces;
    }

    public GamePiece[][] getGamePieces() {
        return gamePieces;
    }
}
