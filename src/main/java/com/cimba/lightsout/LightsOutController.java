package com.cimba.lightsout;

import com.cimba.lightsout.factory.LightsOutFactory;
import com.cimba.lightsout.observer.LightsOutObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class LightsOutController implements LightsOutObserver {
    @FXML
    private Button newGame;

    @FXML
    private Pane pane;

    @FXML
    private Label label;

    private int moveCount;

    private LightsOut game;

    private LightsOutFactory factory;

    public LightsOutController(LightsOutFactory factory) {
        this.factory = factory;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void reset() {
        game = factory.createLightsOut(5);
        game.addObserver(this);
        game.randomMoves(20);
        moveCount = 0;
        updateView();

    }

    public void updateView() {
        clearPane();

        int size = game.getSize();
        double cellWidth = pane.getWidth() / size;
        double cellHeight = pane.getHeight() / size;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Rectangle cell = createCell(x, y, cellWidth, cellHeight);
                addCellToPane(cell);
            }
        }
        updateLabel();
    }

    private void clearPane() {
        pane.getChildren().clear();
    }

    private Rectangle createCell(int x, int y, double width, double height) {
        Rectangle cell = new Rectangle(x * width, y * height, width, height);
        Position p = new Position(x, y);

        Color fillColor = game.getState(x, y) ? Color.DARKVIOLET : Color.GRAY;
        cell.setFill(fillColor);
        cell.setStroke(Color.BLACK);

        cell.setOnMouseClicked(event -> handleCellClick(p.x(), p.y()));

        return cell;
    }

    private void addCellToPane(Rectangle cell) {
        pane.getChildren().add(cell);
    }

    private void handleCellClick(int x, int y) {
        game.toggle(x, y);
        moveCount++;
        updateView();
    }

    private void updateLabel() {
        label.setText(game.isSolved() ? "SOLVED!" : "Moves: " + moveCount);
    }

    @Override
    public void updateLights() {
        updateView();
    }
}
