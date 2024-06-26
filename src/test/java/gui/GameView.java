package gui;

import geometry.IntCoordinates;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import model.MazeState;

import java.util.ArrayList;
import java.util.List;
import model.Fruit;
public class GameView {
    // class parameters
    private MazeState maze;
    private final Pane gameRoot; // main node of the game
    private final List<GraphicsUpdater> graphicsUpdaters;

    private void addGraphics(GraphicsUpdater updater) {
        gameRoot.getChildren().add(updater.getNode());
        graphicsUpdaters.add(updater);
    }
    public List<GraphicsUpdater> getGraphicsUpdaters() {
        return graphicsUpdaters;
    }
    public Pane getGameRoot() {
        return gameRoot;
    }
    public MazeState getMaze(){
        return maze;
    }
    public void setMaze(MazeState maze){
        this.maze=maze;
    }

    /**
     * @param maze  le "modèle" de cette vue (le labyrinthe et tout ce qui s'y
     *              trouve)
     * @param root  le nœud racine dans la scène JavaFX dans lequel le jeu sera
     *              affiché
     * @param scale le nombre de pixels représentant une unité du labyrinthe
     */
    public GameView(MazeState maze, Pane root, double scale) {
        this.maze = maze;
        this.gameRoot = root;
        // pixels per cell
        root.setMinWidth(maze.getWidth() * scale);
        root.setMinHeight(maze.getHeight() * scale);
        
        root.setStyle("-fx-background-color: #2D006A");
        var critterFactory = new CritterGraphicsFactory(scale);
        var cellFactory = new CellGraphicsFactory(scale);
        //var ScoreLives=new ScoreLives();
        graphicsUpdaters = new ArrayList<>();
        //addGraphics(ScoreLives.makeGraphics(maze, new IntCoordinates(1, 1)));

        for (var critter : maze.getCritters())
            addGraphics(critterFactory.makeGraphics(critter));
        for (int x = 0; x < maze.getWidth(); x++)
            for (int y = 0; y < maze.getHeight(); y++)
                addGraphics(cellFactory.makeGraphics(maze, new IntCoordinates(x, y)));
    }

}
