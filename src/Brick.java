import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick {
    double x, y;
    Rectangle brickView = new Rectangle();
    public Brick(double x, double y, Pane pane){
        this.x = x;
        this.y = y;
        brickView.setX(this.x);
        brickView.setY(this.y);
        brickView.setWidth(60);
        brickView.setHeight(20);
        brickView.setArcWidth(10);
        brickView.setFill(Color.WHITE);
        brickView.setStroke(Color.BLACK);
        brickView.setStrokeWidth(3);

        pane.getChildren().add(brickView);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setInvisible() {
        brickView.setVisible(false);
    }
}
