import java.awt.event.*;

public class GameListener extends MouseAdapter {
    GamePiece[][] gamePieces;
    Grid activeGrid;

    public GameListener(GamePiece[][] gamePieces , Grid activeGrid) {
        this.gamePieces = gamePieces;
        this.activeGrid = activeGrid;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        moveGamePiece(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        moveGamePiece(e);
    }

    public void moveGamePiece(MouseEvent e){
        int[] indexZero = findIndex(0);
        int[] indexOfGamePiece = findIndex(((GamePiece) e.getSource()).getValue());

        if (zeroAdjacentGamePiece(indexZero,indexOfGamePiece)) {
            switchPositionGamePieces(indexZero, indexOfGamePiece);
            activeGrid.constructBoard(true,gamePieces);
        }
        activeGrid.revalidate();
        activeGrid.checkWin(gamePieces);
    }

    public boolean zeroAdjacentGamePiece(int[] indexZero, int[] indexOfGamePiece){
        return (indexZero[0] == indexOfGamePiece[0] - 1 && indexZero[1] == indexOfGamePiece[1]) ||
                (indexZero[0] == indexOfGamePiece[0] && indexZero[1] == indexOfGamePiece[1] + 1) ||
                (indexZero[0] == indexOfGamePiece[0] + 1 && indexZero[1] == indexOfGamePiece[1]) ||
                (indexZero[0] == indexOfGamePiece[0] && indexZero[1] == indexOfGamePiece[1] - 1);
    }
    public int[] findIndex(int numberToFind){
        int[] indexOf = new int[2];
        GamePiece[][] temp = gamePieces;
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[i].length; j++) {
                if (temp[i][j].getValue() == numberToFind){
                    indexOf[0] = i;
                    indexOf[1] = j;
                }
            }
        }
        return indexOf;
    }
    public void switchPositionGamePieces(int[] indexOfZero, int[] indexOfGamePiece){
        GamePiece[][] tempListOfGamePieces = gamePieces;

        GamePiece zeroGamePiece = gamePieces[indexOfZero[0]][indexOfZero[1]];
        GamePiece clickedGamePiece = gamePieces[indexOfGamePiece[0]][indexOfGamePiece[1]];

        tempListOfGamePieces[indexOfZero[0]][indexOfZero[1]] = clickedGamePiece;
        tempListOfGamePieces[indexOfGamePiece[0]][indexOfGamePiece[1]] = zeroGamePiece;

        activeGrid.setGamePieces(tempListOfGamePieces);
    }


}
