package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.InputStream;
import java.util.*;
import java.util.List;

public class Controller {
    private final int size = 4;
    private final int degreeOf2ForWin = 11;

    private Direction direction;
    private boolean possibilityOfMove;
    private List<Integer> list = new ArrayList<>();
    private int[][] arr = new int[size][size];
    private Random random = new Random();

    private Class<Controller> clazz = Controller.class;
    private InputStream inputNum0 = clazz.getResourceAsStream("images/num0.jpg");
    private Image num0Image = new Image(inputNum0);
    private InputStream inputNum1 = clazz.getResourceAsStream("images/num1.jpg");
    private Image num1Image = new Image(inputNum1);

    @FXML
    private Button startButton;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView lost;

    @FXML
    private ImageView win;

    @FXML
    void initialize() {
        startButton.setOnMousePressed(Event -> start());
        keyPressed();
    }

    private void start() {
        startButton.setLayoutX(400.0);
        startButton.setText("RESTART");
        lost.setVisible(false);
        win.setVisible(false);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = 0;
                gridPane.add(new ImageView(num0Image), i, j);
            }
        }
        int x = random.nextInt(size);
        int y = random.nextInt(size);
        gridPane.add(new ImageView(num1Image), x, y);
        arr[x][y] = 1;
    }

    private void isPossible() {
        switch (direction) {
            case RIGHT: {
                for (int i = 0; i < size - 1; i++) {
                    for (int j = 0; j < size; j++) {
                        if (arr[i][j] != 0 && arr[i + 1][j] == 0) possibilityOfMove = true;
                        if (arr[i][j] != 0 && arr[i][j] == arr[i + 1][j]) possibilityOfMove = true;
                    }
                }
            }
            break;
            case LEFT: {
                for (int i = size - 1; i > 0; i--) {
                    for (int j = 0; j < size; j++) {
                        if (arr[i][j] != 0 && arr[i - 1][j] == 0) possibilityOfMove = true;
                        if (arr[i][j] != 0 && arr[i][j] == arr[i - 1][j]) possibilityOfMove = true;
                    }
                }
            }
            break;
            case DOWN: {
                for (int j = 0; j < size - 1; j++) {
                    for (int i = 0; i < size; i++) {
                        if (arr[i][j] != 0 && arr[i][j + 1] == 0) possibilityOfMove = true;
                        if (arr[i][j] != 0 && arr[i][j] == arr[i][j + 1]) possibilityOfMove = true;
                    }
                }
            }
            break;
            case UP: {
                for (int j = size - 1; j > 0; j--) {
                    for (int i = 0; i < size; i++) {
                        if (arr[i][j] != 0 && arr[i][j - 1] == 0) possibilityOfMove = true;
                        if (arr[i][j] != 0 && arr[i][j] == arr[i][j - 1]) possibilityOfMove = true;
                    }
                }
            }
            break;
        }
    }

    private void moveHori() {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (arr[i][j] != 0) {
                    list.add(arr[i][j]);
                }
            }
            if (list.size() > 1) {
                switch (direction) {
                    case RIGHT: {
                        for (int u = list.size() - 2; u > -1; u--) {
                            if (list.get(u + 1).intValue() == list.get(u).intValue()) {
                                list.remove(u + 1);
                                list.add(u + 1, list.get(u) + 1);
                                list.remove(u);
                                list.add(u, 0);
                            }
                        }
                    }
                    break;
                    case LEFT: {
                        for (int u = 0; u < list.size() - 1; u++) {
                            if (list.get(u + 1).intValue() == list.get(u).intValue()) {
                                list.remove(u + 1);
                                list.add(u + 1, list.get(u) + 1);
                                list.remove(u);
                            }
                        }
                    }
                    break;
                }
            }
            for (int h = 0; h < list.size() - 1; h++) {
                if (list.get(h) == 0) {
                    list.remove(h);
                }
            }
            int p = list.size();
            for (int k = 0; k < size - p; k++) {
                switch (direction) {
                    case RIGHT:
                        list.add(k, 0);
                    case LEFT:
                        list.add(0);
                }
            }
            for (int k = 0; k < size; k++) {
                arr[k][j] = list.get(k);
            }
            list.clear();
        }
        repaint();
    }

    private void moveVert() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (arr[i][j] != 0) {
                    list.add(arr[i][j]);
                }
            }
            if (list.size() > 1) {
                switch (direction) {
                    case DOWN: {
                        for (int u = list.size() - 2; u > -1; u--) {
                            if (list.get(u + 1).intValue() == list.get(u).intValue()) {
                                list.remove(u + 1);
                                list.add(u + 1, list.get(u) + 1);
                                list.remove(u);
                                list.add(u, 0);
                            }
                        }
                    }
                    break;
                    case UP: {
                        for (int u = 0; u < list.size() - 1; u++) {
                            if (list.get(u + 1).intValue() == list.get(u).intValue()) {
                                list.remove(u + 1);
                                list.add(u + 1, list.get(u) + 1);
                                list.remove(u);
                            }
                        }
                    }
                    break;
                }
            }
            for (int h = 0; h < list.size() - 1; h++) {
                if (list.get(h) == 0) {
                    list.remove(h);
                }
            }
            int p = list.size();
            for (int k = 0; k < size - p; k++) {
                switch (direction) {
                    case DOWN:
                        list.add(k, 0);
                    case UP:
                        list.add(0);
                }
            }
            for (int k = 0; k < size; k++) {
                arr[i][k] = list.get(k);
            }
            list.clear();
        }
        repaint();
    }

    private void move() {
        switch (direction) {
            case RIGHT:
                moveHori();
                break;
            case DOWN:
                moveVert();
                break;
            case LEFT:
                moveHori();
                break;
            case UP:
                moveVert();
                break;
        }
    }

    private void isOver() {
        boolean gameOver = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (arr[i][j] == 0) {
                    gameOver = false;
                }
            }
        }
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                if (arr[i + 1][j] == arr[i][j]) {
                    gameOver = false;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (arr[i][j + 1] == arr[i][j]) {
                    gameOver = false;
                }
            }
        }
        if (gameOver) {
            lost.setVisible(true);
        }
    }

    private void isWin() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (arr[i][j] == degreeOf2ForWin) {
                    win.setVisible(true);
                }
            }
        }
    }

    private void createNewBlock() {
        int x = random.nextInt(size);
        int y = random.nextInt(size);
        if (arr[x][y] != 0) {
            while (arr[x][y] > 0) {
                x = random.nextInt(size);
                y = random.nextInt(size);
            }
        }
        gridPane.add(new ImageView(num1Image), x, y);
        arr[x][y] = 1;
        isOver();
        isWin();
    }

    private void repaint() {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                gridPane.add(new ImageView(new Image(clazz.getResourceAsStream("images/num" + arr[i][j] + ".jpg"))), i, j);
            }
        }
    }

    private void go() {
        isPossible();
        if (possibilityOfMove) {
            move();

            final int[] u = {0};
            Timeline fs = new Timeline(new KeyFrame(Duration.seconds(0.4), e -> {
                if (u[0] == 0) {
                    createNewBlock();
                    u[0] = 1;
                }
            }));
            fs.setCycleCount(Timeline.INDEFINITE);
            fs.play();
        }
        possibilityOfMove = false;
    }

    private void keyPressed() {
        startButton.setOnKeyPressed(ke ->
        {
            if (ke.getCode().getName().equals("D")) {
                direction = Direction.RIGHT;
                go();
            }
            if (ke.getCode().getName().equals("S")) {
                direction = Direction.DOWN;
                go();
            }
            if (ke.getCode().getName().equals("A")) {
                direction = Direction.LEFT;
                go();
            }
            if (ke.getCode().getName().equals("W")) {
                direction = Direction.UP;
                go();
            }
        });
    }
}