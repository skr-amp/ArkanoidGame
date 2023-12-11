import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform {
    final int WIDTH = 140;
    final int HEIGHT = 20;
    private double x, y;
    private double velocity = 25;
    Rectangle platformView = new Rectangle();
    public Platform(Pane pane) {
        this.x = 400 - WIDTH / 2;
        this.y = 520;
        platformView.setX(this.x);
        platformView.setY(this.y);
        platformView.setWidth(WIDTH);
        platformView.setHeight(HEIGHT);
        platformView.setArcHeight(15);
        platformView.setArcWidth(10);
        platformView.setFill(Color.TRANSPARENT);
        platformView.setStroke(Color.WHITE);
        platformView.setStrokeWidth(3);

        pane.getChildren().add(platformView);
    }

    public void moveRight() {
        if (this.x + WIDTH < 790) {
            this.x += velocity;
            platformView.setX(this.x);
        }
    }

    public void moveLeft() {
        if (this.x > 10){
            this.x -= velocity;
            platformView.setX(this.x);
        }
    }

    public double getX() {
        return x;
    }
}
