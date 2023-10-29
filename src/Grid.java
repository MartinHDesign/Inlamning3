import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Grid extends JFrame {
    private static final int VERTICAL_OFFSET = 48;
    private GamePiece[][] gamePieces;
    private boolean fixedGame = false;
    private final JLabel winLabel = new JLabel(new ImageIcon("src/images/You win.gif"));
    private int rows = 4;
    private int columns = 4;

    public Grid(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600 + VERTICAL_OFFSET);

        createNewGameState();

        Menu menu = new Menu();
        menu.setGrid(this);
        setJMenuBar(menu);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createNewGameState(){
        gamePieces = new GamePiece[rows][columns];
        setLayout(new GridLayout(rows,columns));
        createGamePieces(gamePieces);
        constructBoard(fixedGame, gamePieces);
        addMouseListener(gamePieces);
    }

    public void newGame(boolean isGameFixed){
        remove(winLabel);
        removeGamePiecesFromBoard();
        fixedGame = isGameFixed;
        createNewGameState();
        addGamePiecesToBoard();

        revalidate();
        repaint();
    }

    private void createGamePieces(GamePiece[][] gamePieces) {
        int counter = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++) {
                gamePieces[i][j] = new GamePiece(counter, rows, columns);

                counter++;
            }
        }
    }
    private void addMouseListener(GamePiece[][] gamePieces){
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(gamePieces[i][j].getValue() != 0) {
                    gamePieces[i][j].addMouseListener(new GameListener(gamePieces, this));
                }
            }
        }
    }

    public void constructBoard(boolean fixedGame, GamePiece[][] gamePieces){
        if(!fixedGame){
            Random random = new Random();
            for (int i = 0; i < gamePieces.length; i++) {
                for (int j = 0; j < gamePieces[i].length; j++) {
                    int row = random.nextInt(gamePieces.length);
                    int column = random.nextInt(gamePieces[i].length);

                    GamePiece temp = gamePieces[i][j];
                    gamePieces[i][j] = gamePieces[row][column];
                    gamePieces[row][column] = temp;
                }
            }
        }
        addGamePiecesToBoard();
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
        Timer timer = new Timer(100, new ActionListener() {
            private int row = 0;
            private int column = 0;
            private boolean finished = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (finished) {
                    ((Timer) e.getSource()).stop();
                    playWinAnimation();
                } else {
                    gamePieces[row][column].setImage(new ImageIcon("src/images/WinTile.png"), rows, columns);
                    revalidate();
                    repaint();
                    column++;
                    if (column >= gamePieces[row].length) {
                        column = 0;
                        row++;
                        if (row == gamePieces.length) {
                            finished = true;
                        }
                    }
                }
            }
        });

        timer.start();
    }

    private void playWinAnimation() {
        removeGamePiecesFromBoard();
        setLayout(new BorderLayout());
        add(winLabel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setGamePieces(GamePiece[][] gamePieces) {
        this.gamePieces = gamePieces;
    }

    public void setRowsAndColumns(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
    }
}
