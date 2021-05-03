/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package times.tables.cadioid.visulization;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author Ranjeth
 */
public class TTCV extends Application {

    public static Group root;
    public static Scene scene;
    double screenW = 800, screenH = 800;
    //double totalPoints = 200;
    double radius = screenW / 2 - 16;
    double factor = 0;

    @Override
    public void start(Stage primaryStage) {

        root = new Group();

        scene = new Scene(root, screenW, screenH);
        scene.setFill(Color.BLACK);

        root.setTranslateX(screenW / 2);
        root.setTranslateY(screenH / 2);

        update();

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void draw() {
        root.getChildren().clear();
        
        factor += 0.01;
        double totalPoints = 200;

        Circle main = new Circle(0, 0, radius);
        main.setFill(null);
        main.setStroke(Color.WHITE);
        main.setStrokeWidth(1);
        root.getChildren().add(main);

        for (int i = 0; i < totalPoints; i++) {
//            double angle = map(i, 0, totalPoints, 0, 2 * Math.PI);
//            double x = radius * Math.cos(angle);
//            double y = radius * Math.sin(angle);
            Point2D v = getVector(i, totalPoints);
            Circle points = new Circle(v.getX(), v.getY(), 10);
            points.setFill(Color.WHITE);
            root.getChildren().add(points);
        }

        for (int i = 0; i < totalPoints; i++) {
            Point2D a = getVector(i, totalPoints);
            Point2D b = getVector(i * factor, totalPoints);

            Line line1 = new Line(a.getX(), a.getY(), b.getX(), b.getY());
            line1.setStroke(Color.WHITE);
            line1.setStrokeWidth(1);
            root.getChildren().add(line1);
        }
    }

    static public final double map(double value, double istart, double istop, double ostart, double ostop) {
        return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
    }

    public Point2D getVector(double index, double totalPoints) {
        double angle = map(index % totalPoints, 0, totalPoints, 0, 2 * Math.PI);
        Point2D v = new Point2D(radius * Math.cos(angle + Math.PI), radius * Math.sin(angle + Math.PI));
        v.multiply(radius);
        return v;

    }

    public void update() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                draw();
            }
        }.start();
    }

}
