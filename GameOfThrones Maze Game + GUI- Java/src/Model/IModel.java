package Model;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Aviadjo on 6/14/2017.
 */
public interface IModel {
    void generateMaze(int width, int height);
    void moveCharacter(KeyCode movement);
    int[][] getMaze();
    int getCharacterPositionRow();
    int getCharacterPositionColumn();
    int getTargetPositionRow();
    int getTargetPositionColumn();
    void solveMaze(ActionEvent actionEvent);
    ArrayList getMazeSolutionSteps();
    boolean getShowSolution();
    void Save(File file);
    void Load(File file);
    public void close();


}
