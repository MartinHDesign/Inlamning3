import javax.swing.*;

public class Menu extends JMenuBar {

    private Grid grid;

    public Menu() {

    JMenu gameMenu = new JMenu("Game");

    JMenuItem newGame = new JMenuItem("New game");
    newGame.addActionListener(e -> grid.newGame(false));

    JMenuItem newFixedGame = new JMenuItem("New fixed Game");
    newFixedGame.addActionListener(e -> grid.newGame(true));

    JMenuItem close = new JMenuItem("Close game");
    close.addActionListener(e -> System.exit(0));

    gameMenu.add(newGame);
    gameMenu.add(newFixedGame);
    gameMenu.add(close);

    add(gameMenu);
    }

    public void setGrid(Grid grid){
        this.grid = grid;
    }

}
