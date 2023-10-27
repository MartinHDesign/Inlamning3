import java.awt.event.*;

public class GameListener implements MouseListener {
    private Grid g;
    @Override
    public void mouseClicked(MouseEvent e) {
        GamePiece temp = (GamePiece) e.getSource();
        int[] indexZero = findIndex(0);
        int[] indexOfGamePiece = findIndex(temp.getValue());

        if (zeroAdjacentGamePiece(indexZero,indexOfGamePiece))
            switchPositionGamePieces(indexZero,indexOfGamePiece);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean zeroAdjacentGamePiece(int[] indexZero, int[] indexOfGamePiece){
        return (indexZero[0] == indexOfGamePiece[0] - 1 && indexZero[1] == indexOfGamePiece[1]) ||
                (indexZero[0] == indexOfGamePiece[0] && indexZero[1] == indexOfGamePiece[1] + 1) ||
                (indexZero[0] == indexOfGamePiece[0] + 1 && indexZero[1] == indexOfGamePiece[1]) ||
                (indexZero[0] == indexOfGamePiece[0] && indexZero[1] == indexOfGamePiece[1] - 1);
    }
    public int[] findIndex(int numberToFind){
        int[] indexOf = new int[2];
        GamePiece[][] temp = g.getGamePieces();
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
        GamePiece[][] tempListOfGamePieces = g.getGamePieces();

        GamePiece zeroGamePiece = tempListOfGamePieces[indexOfZero[0]][indexOfZero[1]];
        GamePiece clickedGamePiece = tempListOfGamePieces[indexOfGamePiece[0]][indexOfGamePiece[1]];

        tempListOfGamePieces[indexOfZero[0]][indexOfZero[1]] = clickedGamePiece;
        tempListOfGamePieces[indexOfGamePiece[0]][indexOfGamePiece[1]] = zeroGamePiece;

        g.setGamePieces(tempListOfGamePieces);

    }


}
