import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main extends Application {


    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    int score;
    int lives;
    boolean gameIsStop;
    boolean gameIsOver;
    Ball ball;
    Platform platform;
    Set<Brick> bricks;

    Text livesView;
    Text scoreView;

    public static void main(String[] args)  {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        Pane gameOverPane = new Pane();
        Scene gameOverScene = new Scene(gameOverPane, WIDTH, HEIGHT);
        Text gameOverText = new Text();
        gameOverText.setX(150);
        gameOverText.setY(250);
        gameOverText.setFont(new Font(40));
        gameOverText.setTextAlignment(TextAlignment.CENTER);
        gameOverText.setText("Вы проиграли:(\nДля начала новой игры\nнажмите ENTER");
        gameOverPane.getChildren().add(gameOverText);

        stage.setScene(scene);
        stage.setTitle("Arkanoid");
        stage.setResizable(false);
        initGame(pane);
        stage.show();


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameIsOver){
                    stage.setScene(gameOverScene);
                }
                collideCheck(ball, platform, bricks);
                ball.move();
                scoreView.setText("Очки: " + score);
                livesView.setText("Жизни: " + lives);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.RIGHT){
                    platform.moveRight();
                    if (gameIsStop){
                        ball.moveTo(platform.getX() + 70, 510);
                    }
                }
                else if(keyEvent.getCode() == KeyCode.LEFT){
                    platform.moveLeft();
                    if (gameIsStop){
                        ball.moveTo(platform.getX() + 70, 510);
                    }
                }
                else if (keyEvent.getCode() == KeyCode.SPACE){
                    if (gameIsStop){ball.setDy(-1.2);}
                    gameIsStop = false;
                }
                else if (keyEvent.getCode() == KeyCode.ENTER) {
                    gameIsOver = false;
                    gameIsStop = true;
                    System.out.println("Enter");
                    stage.setScene(scene);
                }
            }
        });

        gameOverScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    gameIsOver = false;
                    gameIsStop = true;
                    initGame(pane);
                    stage.setScene(scene);
                }
            }
        });
    }

    public void initGame(Pane pane){
        gameIsStop = true;
        gameIsOver = false;
        score = 0;
        lives = 1;
        pane.getChildren().clear();
        scoreView = new Text();
        scoreView.setX(620);
        scoreView.setY(580);
        scoreView.setFont(new Font(30));
        scoreView.setText("Очки: " + score);
        pane.getChildren().add(scoreView);
        livesView = new Text();
        livesView.setX(30);
        livesView.setY(580);
        livesView.setFont(new Font(30));
        livesView.setText("Жизни: " + lives);
        pane.getChildren().add(livesView);

        ball = new Ball(400, 510, pane);

        platform = new Platform(pane);

        bricks = new HashSet<>();
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 4; j++){
                bricks.add(new Brick(80 * i + 50, 40 * j + 20, pane));
            }
        }
    }

    public void collideCheck(Ball ball, Platform platform, Set bricks){
        if((ball.getX() + 10 > WIDTH)||(ball.getX() - 10 < 0)) {ball.reversX();}
        if(ball.getY() - 10 < 0) {ball.reversY();}
        if((ball.getY() > 520 - 10) && (ball.getX() > platform.getX()) && (ball.getX() < platform.getX() + 140)){
            //ball.reversY();
            double x = ball.getX() - platform.getX();
            ball.setDy(-1.2 * Math.cos(x / 140 - 0.5));
            ball.setDx(1.2 * Math.sin(x / 140 - 0.5));
        }

        if((ball.getY() > 550) && ((ball.getX() < platform.getX()) || (ball.getX() > platform.getX() + 140))){
            gameIsStop = true;
            lives--;
            if(lives == 0){
                gameIsOver = true;
            }
            ball.setDy(0);
            ball.moveTo(platform.getX() + 70, 510);
        }

        Iterator<Brick> iterator = bricks.iterator();
        while (iterator.hasNext()){
            Brick brick = iterator.next();
            if((ball.getY() + 10 > brick.getY()) &&
                    (ball.getY() - 10 < brick.getY() + 20) &&
                    (ball.getX() + 10 > brick.getX()) &&
                    (ball.getX() - 10 < brick.getX() + 60)) {
                if (Math.abs(brick.getY() + 10 - ball.getY()) > Math.abs(brick.getX()) + 30 - ball.getX()) {
                    ball.reversY();
                    brick.setInvisible();
                    bricks.remove(brick);
                    score += 20;
                    break;
                } else {
                    ball.reversX();
                    brick.setInvisible();
                    bricks.remove(brick);
                    score += 20;
                    break;
                }
                }
        }
    }
}