import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform {
    private double x, y;
    private double velocity = 25;
    Rectangle platformView = new Rectangle();
    public Platform(Pane pane) {
        this.x = 400 - 70;
        this.y = 520;
        platformView.setX(this.x);
        platformView.setY(this.y);
        platformView.setWidth(140);
        platformView.setHeight(20);
        platformView.setArcHeight(15);
        platformView.setArcWidth(10);
        platformView.setFill(Color.WHITE);
        platformView.setStroke(Color.BLACK);
        platformView.setStrokeWidth(3);

        pane.getChildren().add(platformView);
    }

    public void moveRight() {
        if (this.x + 140 < 790) {
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
