package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private Controller controller;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane rootGridPane=loader.load();
        controller=loader.getController();
        controller.createPlayground();

        MenuBar menuBar=createMenu();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        Pane menuPane= (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().add(menuBar);

        Scene scene=new Scene(rootGridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Connect 4");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public MenuBar createMenu(){

        Menu fileMenu=new Menu("File");
        MenuItem newGame=new MenuItem("New Game");
        newGame.setOnAction(event -> controller.resetGame());

        MenuItem resetGame=new MenuItem("Reset Game");
        resetGame.setOnAction(event -> controller.resetGame());

        SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();
        MenuItem exitGame=new MenuItem("Exit Game");
        exitGame.setOnAction(event -> {
            Platform.exit();
            System.exit(0);
        });

        fileMenu.getItems().addAll(newGame,resetGame,separatorMenuItem,exitGame);

        Menu helpMenu=new Menu("Help");

        MenuItem about=new MenuItem("About");
        about.setOnAction(event -> {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("How To Play");
            alert.setContentText("CONNECT FOUR is a two-player connection game in which the players first choose a color and " +
                    "then take turns dropping one colored disc from the top into a seven-column, " +
                    "six-row vertically suspended grid. The pieces fall straight down, " +
                    "occupying the lowest available space within the column. The objective of the game is to be the " +
                    "first to form a horizontal, vertical, or diagonal line of four of one's own discs." +
                    " Connect Four is a solved game. The first player can always win by playing the right moves.");
            alert.show();
        });

        SeparatorMenuItem separatorMenuItem1=new SeparatorMenuItem();
        MenuItem aboutMe=new MenuItem("About Me");
        aboutMe.setOnAction(event -> {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Developer");
            alert.setContentText("Hello I am Apoorva Agnihotri and I have developed this Game ");
            alert.show();
        });

        helpMenu.getItems().addAll(about,separatorMenuItem1,aboutMe);

        MenuBar menuBar=new MenuBar();
        menuBar.getMenus().addAll(fileMenu,helpMenu);
        return menuBar;
    }

}
