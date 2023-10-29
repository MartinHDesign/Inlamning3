import javax.swing.*;

public class Menu extends JMenuBar {

    private Grid grid;

    public Menu() {
        gameMenu();
        gameSettingsMenu();
        helpMenu();
    }

    private void gameMenu() {
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

    private void gameSettingsMenu(){
        JMenu gameSettingsMenu = new JMenu("Game settings");

        JMenu gridSizePopMenu = new JMenu("Grid size");

        JMenuItem Three = new JMenuItem ("3x3");
        Three.addActionListener(e -> setGridSize(3));

        JMenuItem Four = new JMenuItem ("4x4");
        Four.addActionListener(e -> setGridSize(4));

        JMenuItem Five = new JMenuItem ("5x5");
        Five.addActionListener(e -> setGridSize(5));

        JMenuItem Six = new JMenuItem("6x6");
        Six.addActionListener(e -> setGridSize(6));

        gridSizePopMenu.add(Three);
        gridSizePopMenu.add(Four);
        gridSizePopMenu.add(Five);
        gridSizePopMenu.add(Six);

        gameSettingsMenu.add(gridSizePopMenu);

        add(gameSettingsMenu);
    }

    private void setGridSize(int size){
        grid.rows = size;
        grid.columns = size;
        grid.newGame(false);
    }

    private void helpMenu(){
        JMenu helpMenu = new JMenu("Help");

        JMenuItem gameRules = new JMenuItem("Game rules");
        gameRules.addActionListener(e -> JOptionPane.showMessageDialog(null, """
                15 Game:
                
                [ 1 ] [ 2 ] [ 3 ] [ 4 ]
                [ 5 ] [ 6 ] [ 7 ] [ 8 ]
                [ 9 ] [10] [11] [    ]
                [13] [14] [15] [12]
                
                Your goal in this game is to have all of the tiles
                in sequential order from 1-15.
                The count skips to the next row once it has hit the end of the current row. 
                In the above example the next number counted after [4 ] would be [5 ].
                
                You are allowed to move a tile if it has an empty tile next to it.
                You do this by clicking the adjacent tile.
                If you click [12] in the above example it would move to the empty tile [    ].
                and the tile you clicked would now be empty.""",
                "Game Rules", JOptionPane.PLAIN_MESSAGE));

        JMenuItem about = new JMenuItem("About");
        about.addActionListener(e -> JOptionPane.showMessageDialog(null, """
                Created by:
                Martin Harrysson
                Daniel Isaksson
                
                Objektorienterad programmering - JAVA23""",
                "About", JOptionPane.PLAIN_MESSAGE));

        helpMenu.add(gameRules);
        helpMenu.add(about);

        add(helpMenu);
    }

    public void setGrid(Grid grid){
        this.grid = grid;
    }

}
