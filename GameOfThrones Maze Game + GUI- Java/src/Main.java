import Model.*;
import View.MyViewController;
import ViewModel.MyViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;

import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


import java.io.File;
import java.net.URL;
import java.util.Optional;

public class Main extends Application {
    static Media media;
    static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //final URL music = getClass().getResource("./View/resources/GOT/gotSong.mp3"); // insert song
     //   final URL music = getClass().getResource("resources/gotSong.mp3"); // insert song
    //    media = new Media(music.toString());
        Media m = new Media(new File("resources/gotSong.mp3").toURI().toString());
     //   MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaPlayer mediaPlayer = new MediaPlayer(m);
        mediaPlayer.setCycleCount(mediaPlayer.INDEFINITE);
        mediaPlayer.play();


        //ViewModel -> Model

        MyModel model = new MyModel();
        model.startServers();
        MyViewModel viewModel = new MyViewModel(model);
        model.addObserver(viewModel);

        //Loading Main Windows
        primaryStage.setTitle("GOT MAZE");
        primaryStage.setWidth(700);
        primaryStage.setHeight(600);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("View/MyView.fxml").openStream());
        Scene scene = new Scene(root, 800, 700);
        scene.getStylesheets().add(getClass().getResource("View/MyViewController.css").toExternalForm());
        primaryStage.setScene(scene);

        //View -> ViewModel
        MyViewController view = fxmlLoader.getController();
        view.initialize(viewModel,primaryStage,scene);
        viewModel.addObserver(view);
        //--------------
        setStageCloseEvent(primaryStage, model);
        //
        //Show the Main Window
        primaryStage.show();
    }

    private void setStageCloseEvent(Stage primaryStage, MyModel model) {
        primaryStage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to exit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                // ... user chose OK
                // Close the program properly
                model.close();
            } else {
                // ... user chose CANCEL or closed the dialog
                event.consume();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}