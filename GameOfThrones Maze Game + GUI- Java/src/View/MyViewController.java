package View;

import Server.Configurations;
import ViewModel.MyViewModel;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.ASearchingAlgorithm;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;

public class MyViewController<pane> implements Observer, IView {

    @FXML
    private MyViewModel viewModel;

    private Scene mainScene;
    private Stage mainStage;
    public MazeDisplayer mazeDisplayer;
    public javafx.scene.control.TextField txtfld_rowsNum;
    public javafx.scene.control.TextField txtfld_columnsNum;
    public javafx.scene.control.Button btn_generateMaze;
    public javafx.scene.control.Button btn_solveMaze;
    public javafx.scene.control.MenuItem lbl_Properties;
    public BorderPane board;
    public Pane pane;
    public MenuItem btn_save;





    @Override
    public void update(Observable o, Object arg) {
        if (o == viewModel) {
            displayMaze(viewModel.getMaze());
            btn_generateMaze.setDisable(false);
        }
    }

    @Override
    public void displayMaze(int[][] maze) {
        mazeDisplayer.setMaze(maze);
        mazeDisplayer.setShowSolution(viewModel.getShowSolution(), viewModel.getMazeSolutionSteps());
        int characterPositionRow = viewModel.getCharacterPositionRow();
        int characterPositionColumn = viewModel.getCharacterPositionColumn();
        int targetPositionRow = viewModel.getTargetPositionRow();
        int targetPositionColumn = viewModel.getTargetPositionColumn();
        mazeDisplayer.setCharacterPosition(characterPositionRow, characterPositionColumn);
        mazeDisplayer.setTargetPosition(targetPositionRow,targetPositionColumn);
       // this.characterPositionRow.setValue(characterPositionRow + "");
        //this.characterPositionColumn.set(characterPositionColumn + "");
        btn_solveMaze.setDisable(false);

    }

    public void generateMaze() {
        try {
            Integer.parseInt(txtfld_rowsNum.getText());
            Integer.parseInt(txtfld_columnsNum.getText());

        // only got here if we didn't return false



            int heigth = Integer.valueOf(txtfld_rowsNum.getText());
            int width = Integer.valueOf(txtfld_columnsNum.getText());
            if (heigth > 1 && width > 1) {
                btn_generateMaze.setDisable(true);
                viewModel.generateMaze(width, heigth);
                btn_solveMaze.setDisable(false);
                btn_save.setDisable(false);
            }
            else{
                showAlert("Please insert integer value greater than 1");
            }
        } catch(NumberFormatException e) {
            showAlert("Please insert integer value greater than 1");
        } catch(NullPointerException e) {
            showAlert("Please insert integer value greater than 1");

        }

    }

    public void solveMaze(ActionEvent actionEvent) {
       // showAlert("Solving maze..");
        actionEvent.consume();
        viewModel.solveMaze(actionEvent);
    }

    private void showAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(alertMessage);
        alert.show();
    }

    public void Scroll(ScrollEvent s){
        if(this.viewModel.getMaze()!=null&&s.isControlDown()) {
            double factor = 1.1D;
            double delta = s.getDeltaY();
            if (delta > 0.0D) {
                mazeDisplayer.setZoomSize(mazeDisplayer.getZoomSize() * factor);
            } else {
                mazeDisplayer.setZoomSize(mazeDisplayer.getZoomSize() / factor);
            }
            mazeDisplayer.redraw();
            s.consume();
        }
    }
    public void KeyPressed(KeyEvent keyEvent) {
        viewModel.moveCharacter(keyEvent.getCode());
        keyEvent.consume();
    }

    //region String Property for Binding
    public StringProperty characterPositionRow = new SimpleStringProperty("0");

    public StringProperty characterPositionColumn = new SimpleStringProperty("0");

    public String getCharacterPositionRow() {
        return characterPositionRow.get();
    }

    public StringProperty characterPositionRowProperty() {
        return characterPositionRow;
    }

    public String getCharacterPositionColumn() {
        return characterPositionColumn.get();
    }

    public StringProperty characterPositionColumnProperty() {
        return characterPositionColumn;
    }

    public void setResizeEvent() {
        this.mainScene.widthProperty().addListener((observable, oldValue, newValue) -> {
            //mazeDisplayer.redraw();
            System.out.println("Width: " + newValue);
        });

        this.mainScene.heightProperty().addListener((observable, oldValue, newValue) -> {
            //mazeDisplayer.redraw();
            System.out.println("Height: " + newValue);
        });
    }

    public void About(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("AboutController");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("About.fxml").openStream());
            Scene scene = new Scene(root, 600, 400);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }
    public void Help(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("HelpController");
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = fxmlLoader.load(getClass().getResource("Help.fxml").openStream());
            Scene scene = new Scene(root, 600, 320);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {

        }
    }


    public void Save(ActionEvent actionEvent) {
        ////////////////////////////////
        Stage stage = new Stage();
        stage.setTitle("SaveController");

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Maze files","*.maze");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(stage);
        if(file != null){
            viewModel.Save(file);
        }

        //stage.show();
    }




    public void New(ActionEvent actionEvent) {

        generateMaze();
    }


    public void Load(ActionEvent actionEvent) {
        try {
            Stage stage = new Stage();
            stage.setTitle("LoadController");
            FileChooser fileChooser = new FileChooser();


            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Maze files", "*.maze"));


            File file = fileChooser.showOpenDialog(stage);

            String name = file.getName();
            int lastIndexOf = name.lastIndexOf(".");
            if (lastIndexOf == -1) {
                showAlert("Please choose Maze file");
            } else {
                String extension = name.substring(lastIndexOf);
                if (!extension.equals(".maze")) {
                    showAlert("Please choose Maze file");
                } else {
                    if (file != null) {
                        mazeDisplayer.setFinish(true);
                        viewModel.Load(file);
                    }
                }
            }

        }
        catch (Exception e){

        }
    }


    public void Properties(ActionEvent actionEvent) {
        try {
            MenuItem Properties =new MenuItem("Properties");

            Stage stage = new Stage();
            stage.setTitle("PropertiesController");
            FXMLLoader fxmlLoader = new FXMLLoader();

            StackPane root = new StackPane();
            Scene scene = new Scene(root, 400, 350);
            stage.setScene(scene);
            VBox vBox = new VBox();
            GridPane gridPane = new GridPane();

            String AMazeInfo;
            String ASearchInfo=""+ Configurations.getSearchingAlgorithm().getName();
            String TreadPoolInfo=""+ Configurations.getTreadPoll();

            AMazeGenerator temp = Configurations.getMazeGenerator();

            if(temp instanceof MyMazeGenerator){
                AMazeInfo="myMaze";
            }
            else if(temp instanceof SimpleMazeGenerator){
                AMazeInfo="simpleMaze";
            }else{
                AMazeInfo="emptyMaze";
            }


            Label AMaze = new Label("mazeGenerate = " +AMazeInfo );

            Label Asearch = new Label("searchingAlgorithm = " +ASearchInfo );

            Label treadPool = new Label("numberOfTreads = "+TreadPoolInfo);

            gridPane.add(AMaze,1,0);
            gridPane.add(Asearch,1,1);
            gridPane.add(treadPool,1,2);
            vBox.getChildren().add(gridPane);

            root.getChildren().add(vBox);










            stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Exit(ActionEvent actionEvent) {
        Stage stage = mainStage;
        stage.setTitle("ExitController");


                viewModel.Exit();



    }

    public void mouseClicked(MouseEvent mouseEvent) {
        this.mazeDisplayer.requestFocus();
    }

    public void initialize(MyViewModel viewModel, Stage mainStage, Scene mainScene) {
        this.viewModel = viewModel;
        this.mainScene = mainScene;
        this.mainStage = mainStage;

        setResizeEvent();


        pane.prefHeightProperty().bind(board.heightProperty());
        pane.prefWidthProperty().bind(board.widthProperty());
        mazeDisplayer.heightProperty().bind(pane.heightProperty());
        mazeDisplayer.widthProperty().bind(pane.widthProperty());
        mazeDisplayer.heightProperty().addListener((observable, oldValue, newValue) -> {
            if(viewModel.getMaze() != null)
                displayMaze(viewModel.getMaze());
        });
        mazeDisplayer.widthProperty().addListener((observable, oldValue, newValue) -> {
            if(viewModel.getMaze() != null)
                displayMaze(viewModel.getMaze());
        });
    }



    //endregion

}
