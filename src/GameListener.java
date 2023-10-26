import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameListener implements ActionListener {
    Grid g = new Grid();
    @Override
    public void actionPerformed(ActionEvent e) {
        GamePiece temp = (GamePiece) e.getSource();
        int[] indexZero = findIndex(0);
        int[] indexOfGamePiece = findIndex(temp.getValue());

        if (indexZero[0] == indexOfGamePiece[0]-1 && indexZero[1] == indexOfGamePiece[1]){
            //byt plats norr kod kommer snart
        } else if (indexZero[0] == indexOfGamePiece[0] && indexZero[1] == indexOfGamePiece[1]+1) {
            //byt plats öst kod kommer snart
        } else if (indexZero[0] == indexOfGamePiece[0]+1 && indexZero[1] == indexOfGamePiece[1]) {
            //byt plats söder kod kommer snart
        } else if (indexZero[0] == indexOfGamePiece[0] && indexZero[1] == indexOfGamePiece[1]-1) {
            //byt plats väster kod kommer snart
        }

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

}
