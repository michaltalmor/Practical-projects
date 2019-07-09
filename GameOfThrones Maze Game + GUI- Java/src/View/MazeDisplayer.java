package View;

import Model.MyModel;
import algorithms.search.AState;
import algorithms.search.MazeState;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.awt.image.FileImageSource;
import sun.awt.image.GifImageDecoder;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Aviadjo on 3/9/2017.
 */
public class MazeDisplayer extends Canvas {

    private int[][] maze;
    private int characterPositionRow = getCharacterPositionRow();
    private int characterPositionColumn = getCharacterPositionColumn();

    private int targetPositionRow = getTargetPositionRow();
    private int targetPositionColumn = getTargetPositionColumn();

    private double zoomSize = 1;

    private boolean showSolution=false;
    ArrayList <AState> solutionSteps;
    boolean finish=false;

    public void setMaze(int[][] maze) {
        this.maze = maze;
        redraw();
    }
    public void setShowSolution(boolean showSolution, ArrayList <AState> solutionSteps){
        this.showSolution = showSolution;
        this.solutionSteps=solutionSteps;
        redraw();
    }

    public void setCharacterPosition(int row, int column) {
        characterPositionRow = row;
        characterPositionColumn = column;
        finish=false;
        redraw();
    }

    public void setTargetPosition(int row, int column) {

        targetPositionRow = row;
        targetPositionColumn = column;
        redraw();
    }

    public int getCharacterPositionRow() {
        return characterPositionRow;
    }

    public int getCharacterPositionColumn() {
        return characterPositionColumn;
    }
    public int getTargetPositionRow() {
        return targetPositionRow;
    }

    public int getTargetPositionColumn() {
        return targetPositionColumn;
    }

    public void redraw() {
        if (maze != null) {
            double canvasHeight = getHeight() * zoomSize;
            double canvasWidth = getWidth() * zoomSize;
            double cellHeight = (canvasHeight / maze.length) * zoomSize;
            double cellWidth = (canvasWidth / maze[0].length) * zoomSize;

            try {
                Image wallImage = new Image(new FileInputStream(ImageFileNameWall.get()));
                Image characterImage = new Image(new FileInputStream(ImageFileNameCharacter.get()));
                Image targetImage = new Image(new FileInputStream(ImageFileNameChair.get()));
                Image solImage = new Image(new FileInputStream(ImageFileNameSol.get()));


                GraphicsContext gc = getGraphicsContext2D();
                gc.clearRect(0, 0, getWidth(), getHeight());

                //Draw Maze
                for (int i = 0; i < maze.length; i++) {
                    for (int j = 0; j < maze[i].length; j++) {
                        if (maze[i][j] == 1) {
                            //gc.fillRect(i * cellHeight, j * cellWidth, cellHeight, cellWidth);
                            gc.drawImage(wallImage, j * cellWidth, i * cellHeight , cellWidth,cellHeight);
                        }
                    }
                }
                if(showSolution){
                    int rowIndex;
                    int columnIndex;
                    for(int i=0 ; i<solutionSteps.size() ; i++){
                        rowIndex=((MazeState)(solutionSteps.get(i))).getPosition().getRowIndex();
                        columnIndex=((MazeState)(solutionSteps.get(i))).getPosition().getColumnIndex();
                        gc.drawImage(solImage, columnIndex * cellWidth, rowIndex * cellHeight , cellWidth,cellHeight);

                    }
                }
                //Draw Character
                //gc.setFill(Color.RED);
                //gc.fillOval(characterPositionColumn * cellHeight, characterPositionRow * cellWidth, cellHeight, cellWidth);
                gc.drawImage(characterImage, characterPositionColumn *cellWidth , characterPositionRow * cellHeight, cellWidth, cellHeight);
                gc.drawImage(targetImage, targetPositionColumn *cellWidth , targetPositionRow *cellHeight , cellWidth, cellHeight);

                if(characterPositionRow==targetPositionRow && characterPositionColumn==targetPositionColumn && !finish){
                    //gc.drawImage(finalImage,0,0,canvasWidth,canvasHeight);
                    try {
                        Stage stage = new Stage();
                        stage.setTitle("finalGameController");
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        Parent root = fxmlLoader.load(getClass().getResource("finalGame.fxml").openStream());
                        Scene scene = new Scene(root, 400, 350);
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL); //Lock the window until it closes
                        stage.show();
                        finish=true;

                        ////
                        final URL music = getClass().getResource("resources/GOT/Applause.mp3");
                        Media media = new Media(music.toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                        //mediaPlayer.setCycleCount(mediaPlayer);
                        mediaPlayer.play();


                    } catch (Exception e) {

                    }
               //     finalGame();
                }



            } catch (FileNotFoundException e) {
                //e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //region Properties
    private StringProperty ImageFileNameWall = new SimpleStringProperty();
    private StringProperty ImageFileNameCharacter = new SimpleStringProperty();
    private StringProperty ImageFileNameChair = new SimpleStringProperty();
    private StringProperty ImageFileNameSol = new SimpleStringProperty();

    public void setFinish(boolean bool){
        finish=bool;
    }
    public String getImageFileNameWall() {
        return ImageFileNameWall.get();
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.ImageFileNameWall.set(imageFileNameWall);
    }

    public String getImageFileNameCharacter() {
        return ImageFileNameCharacter.get();
    }

    public void setImageFileNameCharacter(String imageFileNameCharacter) {
        this.ImageFileNameCharacter.set(imageFileNameCharacter);
    }

    public String getImageFileNameChair() {
        return ImageFileNameChair.get();
    }

    public void setImageFileNameChair(String ImageFileNameChair) {
        this.ImageFileNameChair.set(ImageFileNameChair);
    }

    public String getImageFileNameSol() {
        return ImageFileNameSol.get();
    }

    public void setImageFileNameSol(String ImageFileNameSol) {
        this.ImageFileNameSol.set(ImageFileNameSol);
    }
    //endregion

    public void setZoomSize (double size){
        zoomSize = size;
    }
    public double getZoomSize (){
        return zoomSize;
    }

}
