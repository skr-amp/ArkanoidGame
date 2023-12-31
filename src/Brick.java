import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Brick {
    private double x, y;
    final int WIDTH = 60;
    final int HEIGHT = 20;
    Rectangle brickView = new Rectangle();
    public Brick(double x, double y, Pane pane){
        this.x = x;
        this.y = y;
        brickView.setX(this.x);
        brickView.setY(this.y);
        brickView.setWidth(WIDTH);
        brickView.setHeight(HEIGHT);
        brickView.setArcWidth(10);
        brickView.setFill(Color.TRANSPARENT);
        brickView.setStroke(Color.WHITE);
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
