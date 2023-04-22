package com.example.storygame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TheWondersOfTheWorld extends Application {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("THE WONDERS OF THE WORLD!");

        VBox mainMenu = new VBox(20);
        mainMenu.setPadding(new Insets(50));
        mainMenu.setAlignment(Pos.CENTER);

        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> startNewGame());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> primaryStage.close());

        mainMenu.getChildren().addAll(newGameButton, exitButton);

        Scene scene = new Scene(mainMenu, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int currentImageIndex = 0;
    private ImageView imageView;
    String[] imagePaths = {
            "C:\\Users\\emahe\\IdeaProjects\\storygame\\images\\image1.jpg",
            "C:\\Users\\emahe\\IdeaProjects\\storygame\\images\\image2.jpg"
    };

    private void setImage(int index) {
        Image image = new Image(imagePaths[index]);
        imageView.setImage(image);
    }

    private void displayNextImage() {
        currentImageIndex++;
        if (currentImageIndex >= imagePaths.length) {
            // Reached the last image, go back to main menu
            currentImageIndex = 0;
            startNewGame();
        } else {
            setImage(currentImageIndex);
        }
    }

    private void startNewGame() {
        imageView = new ImageView();
        imageView.setOnMouseClicked(event -> {
            if (event.isPrimaryButtonDown()) {
                // increment the index and set the next image
                currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
                setImage(currentImageIndex);
            }
        });

        // set the first image
        setImage(currentImageIndex);

        // set the dimensions of the image view
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);

        // create a new scene with the ImageView as its root
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root);

        // set the dimensions of the root and the image view
        root.setPrefSize(600, 400);

        // set the new scene
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
