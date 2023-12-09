import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
    final int RADIUS = 10;
    private double x, y;
    private double dx, dy;

    Circle ballView = new Circle();
    public Ball(int x, int y, Pane pane){
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        ballView.setCenterX(this.x);
        ballView.setCenterY(this.y);
        ballView.setRadius(10);
        ballView.setFill(Color.WHITE);
        ballView.setStroke(Color.BLACK);
        ballView.setStrokeWidth(3);
        pane.getChildren().add(ballView);
    }

    public void move(){
        this.x = this.x + this.dx;
        this.y = this.y + this.dy;
        ballView.setCenterX(this.x);
        ballView.setCenterY(this.y);
    }

    public void moveTo(double x, double y){
        this.x = x;
        this.y = y;
        ballView.setCenterX(this.x);
        ballView.setCenterY(this.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public void reversX() {
        dx = -dx;
    }

    public void reversY() {
        dy = -dy;
    }
}
