package ViewModel;

import Model.IModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Aviadjo on 6/14/2017.
 */
public class MyViewModel extends Observable implements Observer {

    private IModel model;

    private int characterPositionRowIndex;
    private int characterPositionColumnIndex;


    public StringProperty characterPositionRow = new SimpleStringProperty(""+characterPositionRowIndex); //For Binding
    public StringProperty characterPositionColumn = new SimpleStringProperty(""+characterPositionColumnIndex); //For Binding
    // public StringProperty characterPositionRow;
    // public StringProperty characterPositionColumn;

    public MyViewModel(IModel model){
        this.model = model;
        //characterPositionRow = new SimpleStringProperty(""+model.getCharacterPositionRow());
        //characterPositionColumn = new SimpleStringProperty(""+model.getCharacterPositionColumn());


    }

    @Override
    public void update(Observable o, Object arg) {
        if (o==model){
            characterPositionRowIndex = model.getCharacterPositionRow();
            characterPositionRow.set(characterPositionRowIndex + "");
            characterPositionColumnIndex = model.getCharacterPositionColumn();
            characterPositionColumn.set(characterPositionColumnIndex + "");
            setChanged();
            notifyObservers();
        }
    }

    public void generateMaze(int width, int height){

        model.generateMaze(width, height);
    }

    public void moveCharacter(KeyCode movement){
        model.moveCharacter(movement);
    }

    public int[][] getMaze() {
        return model.getMaze();
    }

    public int getCharacterPositionRow() {
        return characterPositionRowIndex;
    }

    public int getCharacterPositionColumn() {
        return characterPositionColumnIndex;
    }

    public int getTargetPositionRow(){
        return model.getTargetPositionRow();
    }

    public int getTargetPositionColumn(){

        return model.getTargetPositionColumn();
    }

    public void solveMaze(ActionEvent actionEvent){
        model.solveMaze(actionEvent);
    }

    public ArrayList getMazeSolutionSteps(){
        return model.getMazeSolutionSteps();
    }
    public boolean getShowSolution(){
        return model.getShowSolution();
    }
    public void Save(File file){
        model.Save(file);
    }
    public void Load(File file){
        model.Load(file);
    }
    public void Exit (){
        model.close();
    }


}
