import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Grid extends JFrame {
    private final int HORIZONTAL_SPACING = 151;
    private final int VERTICAL_SPACING = 155;
    private final int MENU_OFFSET = 30;
    private int rows = 4;
    private int columns = 4;
    GamePiece[][] gamePieces = new GamePiece[columns][rows];
    private boolean fixedGame = false;
    private final JLabel winLabel = new JLabel(new ImageIcon("src/images/You win.gif"));

    public Grid(){
        setLayout(new GridLayout(rows, columns));
        setSize(columns * HORIZONTAL_SPACING, rows * VERTICAL_SPACING + MENU_OFFSET);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createGamePieces(gamePieces);

        constructBoard(fixedGame, gamePieces);
        addMouseListener(gamePieces);

        Menu menu = new Menu();
        menu.setGrid(this);
        setJMenuBar(menu);

        setVisible(true);
    }

    private void createGamePieces(GamePiece[][] gamePieces) {
        int counter = 0;
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++) {
                gamePieces[i][j] = new GamePiece(counter);

                counter++;
            }
        }
    }
    private void addMouseListener(GamePiece[][] gamePieces){
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                gamePieces[i][j].addMouseListener(new GameListener(gamePieces, this));
            }
        }
    }

    public void constructBoard(boolean fixedGame, GamePiece[][] gamePieces){
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
        }
        addGamePiecesToBoard();
    }

    void newGame(boolean isGameFixed){
        remove(winLabel);
        setLayout(new GridLayout(rows,columns));

        removeGamePiecesFromBoard();

        fixedGame = isGameFixed;
        createGamePieces(gamePieces);
        constructBoard(fixedGame, gamePieces);
        addMouseListener(gamePieces);

        addGamePiecesToBoard();

        revalidate();
        repaint();
    }

    private void addGamePiecesToBoard() {
        for(GamePiece[] row: gamePieces){
            for(GamePiece piece: row){
                add(piece);
            }
        }
    }

    private void removeGamePiecesFromBoard() {
        for(GamePiece[] row: gamePieces){
            for(GamePiece piece: row){
                remove(piece);
            }
        }
    }

    public void setGamePieces(GamePiece[][] gamePieces) {
        this.gamePieces = gamePieces;
    }

    public void checkWin(GamePiece[][] gamePieces){
        int currentValue = 0;
        for(GamePiece[] g: gamePieces){
                for (GamePiece gp : g) {
                    if (gp.getValue() != 0 && gp.getValue() != currentValue + 1) {
                        return;
                    }
                    currentValue = gp.getValue();
                }
            }
        win();
    }

    private void win(){
        removeGamePiecesFromBoard();
        setLayout(new BorderLayout());
        add(winLabel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
