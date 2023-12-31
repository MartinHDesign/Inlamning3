import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovementLogic extends MouseAdapter {
    private final GamePiece[][] gamePieces;
    private final Grid activeGrid;
    private boolean moveArbitrarilyGamePieces = false;

    public MovementLogic(GamePiece[][] gamePieces, Grid activeGrid) {
        this.gamePieces = gamePieces;
        this.activeGrid = activeGrid;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        moveGamePiece(e);
    }

    public void moveGamePiece(MouseEvent e) {
        int[] indexZero = findIndex(0);
        int[] indexOfGamePiece = findIndex(((GamePiece) e.getSource()).getValue());

        if (moveArbitrarilyGamePieces) {
            arbitrarilySwitchGamePiecesPositions(indexZero, indexOfGamePiece);
            activeGrid.constructBoard(true, gamePieces);
        } else if (zeroAdjacentGamePiece(indexZero, indexOfGamePiece)) {
            switchGamePiecePositions(indexZero, indexOfGamePiece);
            activeGrid.constructBoard(true, gamePieces);
        }
        activeGrid.revalidate();
        activeGrid.checkWin(gamePieces);
    }

    public boolean zeroAdjacentGamePiece(int[] indexZero, int[] indexOfGamePiece) {
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
                if (temp[i][j].getValue() == numberToFind) {
                    indexOf[0] = i;
                    indexOf[1] = j;
                }
            }
        }
        return indexOf;
    }

    public void switchGamePiecePositions(int[] indexOfZero, int[] indexOfGamePiece) {
        GamePiece[][] tempListOfGamePieces = gamePieces;

        GamePiece zeroGamePiece = gamePieces[indexOfZero[0]][indexOfZero[1]];
        GamePiece clickedGamePiece = gamePieces[indexOfGamePiece[0]][indexOfGamePiece[1]];

        tempListOfGamePieces[indexOfZero[0]][indexOfZero[1]] = clickedGamePiece;
        tempListOfGamePieces[indexOfGamePiece[0]][indexOfGamePiece[1]] = zeroGamePiece;

        activeGrid.setGamePieces(tempListOfGamePieces);
    }

    public void arbitrarilySwitchGamePiecesPositions(int[] indexOfZero, int[] indexOfGamePiece) {
        //north
        if (zeroIsDirectionNorth(indexOfZero, indexOfGamePiece)) {
            arbitrarilyMoveGamePiecesNorth(indexOfZero, indexOfGamePiece);
        } else if (zeroIsDirectionEast(indexOfZero, indexOfGamePiece)) {
            arbitrarilyMoveGamePiecesEast(indexOfZero, indexOfGamePiece);
        } else if (zeroIsDirectionSouth(indexOfZero, indexOfGamePiece)) {
            arbitrarilyMoveGamePiecesSouth(indexOfZero, indexOfGamePiece);
        } else if (zeroIsDirectionWest(indexOfZero, indexOfGamePiece)) {
            arbitrarilyMoveGamePiecesWest(indexOfZero, indexOfGamePiece);
        }

    }

    public boolean zeroIsDirectionNorth(int[] indexZero, int[] indexOfGamePiece) {
        return (indexZero[1] == indexOfGamePiece[1] && indexZero[0] < indexOfGamePiece[0]);
    }

    public boolean zeroIsDirectionEast(int[] indexZero, int[] indexOfGamePiece) {
        return (indexZero[0] == indexOfGamePiece[0] && indexZero[1] < indexOfGamePiece[1]);
    }

    public boolean zeroIsDirectionSouth(int[] indexZero, int[] indexOfGamePiece) {
        return (indexZero[1] == indexOfGamePiece[1] && indexZero[0] > indexOfGamePiece[0]);
    }

    public boolean zeroIsDirectionWest(int[] indexZero, int[] indexOfGamePiece) {
        return (indexZero[0] == indexOfGamePiece[0] && indexZero[1] > indexOfGamePiece[1]);
    }

    public void arbitrarilyMoveGamePiecesNorth(int[] indexZero, int[] indexOfGamePiece) {
        List<GamePiece> gamePiecesToMoveNorth = new ArrayList<>();
        GamePiece gamePieceZero = gamePieces[indexZero[0]][indexZero[1]];

        for (int i = indexZero[0] + 1; i < indexOfGamePiece[0] + 1; i++) {
            GamePiece temp = gamePieces[i][indexZero[1]];
            gamePiecesToMoveNorth.add(temp);
        }

        gamePiecesToMoveNorth.add(gamePieceZero);

        int tempRowIndex = 0;
        for (int i = indexZero[0]; i < indexOfGamePiece[0]+1; i++) {
            gamePieces[i][indexZero[1]] = gamePiecesToMoveNorth.get(tempRowIndex);
            tempRowIndex ++;
        }
    }

    public void arbitrarilyMoveGamePiecesEast(int[] indexZero, int[] indexOfGamePiece) {
        List<GamePiece> gamePiecesToMoveEast = new ArrayList<>();
        GamePiece gamePieceZero = gamePieces[indexZero[0]][indexZero[1]];

        for (int i = indexZero[1] + 1; i < indexOfGamePiece[1] + 1; i++) {
            GamePiece temp = gamePieces[indexZero[0]][i];
            gamePiecesToMoveEast.add(temp);
        }

        gamePiecesToMoveEast.add(gamePieceZero);

        int tempRowIndex = 0;
        for (int i = indexZero[1]; i < indexOfGamePiece[1] + 1; i++) {
            gamePieces[indexZero[0]][i] = gamePiecesToMoveEast.get(tempRowIndex);
            tempRowIndex++;
        }
    }

    public void arbitrarilyMoveGamePiecesSouth(int[] indexZero, int[] indexOfGamePiece) {
        List<GamePiece> gamePiecesToMoveSouth = new ArrayList<>();
        GamePiece gamePieceZero = gamePieces[indexZero[0]][indexZero[1]];

        for (int i = indexZero[0] - 1; i > indexOfGamePiece[0] - 1; i--) {
            GamePiece temp = gamePieces[i][indexZero[1]];
            gamePiecesToMoveSouth.add(temp);
        }

        gamePiecesToMoveSouth.add(gamePieceZero);
        Collections.reverse(gamePiecesToMoveSouth);

        int tempRowIndex = 0;
        for (int i = indexOfGamePiece[0]; i < indexZero[0] + 1; i++) {
            gamePieces[i][indexZero[1]] = gamePiecesToMoveSouth.get(tempRowIndex);
            tempRowIndex ++;
        }
    }

    public void arbitrarilyMoveGamePiecesWest(int[] indexZero, int[] indexOfGamePiece) {
        List<GamePiece> gamePiecesToMoveWest = new ArrayList<>();
        GamePiece gamePieceZero = gamePieces[indexZero[0]][indexZero[1]];

        for (int i = indexZero[1] - 1; i > indexOfGamePiece[1] - 1; i--) {
            GamePiece temp = gamePieces[indexZero[0]][i];
            gamePiecesToMoveWest.add(temp);
        }

        gamePiecesToMoveWest.add(gamePieceZero);
        Collections.reverse(gamePiecesToMoveWest);

        int tempRowIndex = 0;
        for (int i = indexOfGamePiece[1]; i < indexZero[1] + 1; i++) {
            gamePieces[indexZero[0]][i] = gamePiecesToMoveWest.get(tempRowIndex);
            tempRowIndex++;
        }
    }

    public void setArbitrarilyMoveGamePieces(boolean moveArbitrarilyGamePieces) {
        this.moveArbitrarilyGamePieces = moveArbitrarilyGamePieces;
    }
}