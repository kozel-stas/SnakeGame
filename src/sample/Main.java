package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static int Width=800,Height=600,SizeCub=20;
    public static Pane Approot=new Pane();
    public static Pane Lay=new Pane();
    public static Snake snake=new Snake();
    public static Apple apple=new Apple(5);


    public static void main(String[] args) { launch(args);}

    public Parent paint (){
        Lay.setPrefSize(Width-12,Height-12);
        Lay.getChildren().add(snake);
        Lay.getChildren().add(apple);
        Approot.getChildren().addAll(Lay);
        return Approot;
    }

    public void update(){
        snake.Move();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(paint());

        Thread myThready = new Thread(new Runnable() {
            public void run() {
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent ke) {
                        switch (ke.getCode()) {
                            case UP:
                                Snake.dir = Dir.Up;
                                break;
                            case DOWN:
                                Snake.dir = Dir.Down;
                                break;
                            case LEFT:
                                Snake.dir = Dir.Left;
                                break;
                            case RIGHT:
                                Snake.dir = Dir.Right;
                                break;
                        }
                    }
                });
            }
        });
        myThready.start();
        primaryStage.setScene(scene);
        primaryStage.setTitle("SnakeGame");
        Image ico = new Image("iconLogo.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setResizable(false);
        primaryStage.show();
        AnimationTimer time = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 99_999_999) {
                    update();
                    lastUpdate = now;
                }
            }
        };
        time.start();
    }
}

enum Dir{
    Up,Down,Left,Right
}