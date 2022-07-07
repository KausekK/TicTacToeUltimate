package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    Button button1 = new Button();
    Button button2 = new Button();
    Button button3 = new Button();
    Button button4 = new Button();
    Button button5 = new Button();
    Button button6 = new Button();
    Button button7 = new Button();
    Button button8 = new Button();
    Button button9 = new Button();

    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;
    Pane gameBoard = new Pane();
    Button[] tab1 = new Button[9];
    Button[] tab2 = new Button[9];
    Button[] tab3 = new Button[9];
    Button[] tab4 = new Button[9];
    Button[] tab5 = new Button[9];
    Button[] tab6 = new Button[9];
    Button[] tab7 = new Button[9];
    Button[] tab8 = new Button[9];
    Button[] tab9 = new Button[9];
    Button[] tab11 = {button1, button2, button3, button4, button5, button6, button7, button8, button9};

    Label display = new Label();
    Label title = new Label();
    Label gameText = new Label();
    Label whoWon = new Label();
    Label whoFirst = new Label();
    Button newGame = new Button("New Game");
    Button exit = new Button("Exit");
    ListView<String> listView = new ListView<>();
    RadioButton radioButton = new RadioButton("1 player -" + "\n" +
            "click on board " + "\n" + "to move computer");
    RadioButton radioButton2 = new RadioButton("2 players");
    RadioButton O = new RadioButton("O");
    RadioButton X = new RadioButton("X");
    ToggleGroup toggleGroup = new ToggleGroup();
    ToggleGroup sign = new ToggleGroup();
    private boolean win1 = false;
    private boolean win2 = false;
    private boolean win3 = false;
    private boolean win4 = false;
    private boolean win5 = false;
    private boolean win6 = false;
    private boolean win7 = false;
    private boolean win8 = false;
    private boolean win9 = false;
    private boolean finalWin = false;
    private boolean AI = true;
    private int random;
    private int board;
    private final Timer timer = new Timer();
    private String finalWinner;

    boolean isPlayer1 = true;
    BorderPane borderPane = new BorderPane();
    Random rnd = new Random();
    private int r = rnd.nextInt(9);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic Tac Toe Ultimate");
        whoFirst.setLayoutX(800);
        whoFirst.setLayoutY(200);
        whoFirst.setText("Which one is first");
        board();
        board2();
        board3();
        board4();
        board5();
        board6();
        board7();
        board8();
        board9();

        isPlayer1 = true;
//        r = rnd.nextInt(9);
//        switch (r) {
//            case 0 -> buttonOn(tab1);
//            case 1 -> buttonOn(tab2);
//            case 2 -> buttonOn(tab3);
//            case 3 -> buttonOn(tab4);
//            case 4 -> buttonOn(tab5);
//            case 5 -> buttonOn(tab6);
//            case 6 -> buttonOn(tab7);
//            case 7 -> buttonOn(tab8);
//            case 8 -> buttonOn(tab9);
//        }

        listView.setLayoutX(770);
        listView.setLayoutY(400);

        radioButton.setLayoutX(800);
        radioButton.setLayoutY(50);
        radioButton.setToggleGroup(toggleGroup);
        radioButton2.setSelected(true);
        X.setSelected(true);
        X.setLayoutX(900);
        X.setLayoutY(200);
        O.setLayoutX(940);
        O.setLayoutY(200);
        X.setToggleGroup(sign);
        O.setToggleGroup(sign);
        toggleGroup.selectedToggleProperty();

        toggleGroup.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            if (AI) {
                AI = false;
            } else {
                AI = true;
            }
        });
        sign.selectedToggleProperty().addListener((observableValue, oldValue, newValue) -> {
            if (isPlayer1) {
                isPlayer1 = false;
            } else {
                isPlayer1 = true;
            }
        });

        radioButton2.setLayoutX(950);
        radioButton2.setLayoutY(68);
        radioButton2.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty();

        button1.setMinSize(240, 240);
        gameBoard.getChildren().add(button1);
        button1.setDisable(true);

        button2.setMinSize(240, 240);
        gameBoard.getChildren().add(button2);
        button2.setDisable(true);
        button2.setLayoutY(260);

        button3.setMinSize(240, 240);
        gameBoard.getChildren().add(button3);
        button3.setDisable(true);
        button3.setLayoutY(520);

        button4.setMinSize(240, 240);
        gameBoard.getChildren().add(button4);
        button4.setDisable(true);
        button4.setLayoutX(260);

        button5.setMinSize(240, 240);
        gameBoard.getChildren().add(button5);
        button5.setDisable(true);
        button5.setLayoutX(260);
        button5.setLayoutY(260);

        button6.setMinSize(240, 240);
        gameBoard.getChildren().add(button6);
        button6.setDisable(true);
        button6.setLayoutX(260);
        button6.setLayoutY(520);

        button7.setMinSize(240, 240);
        gameBoard.getChildren().add(button7);
        button7.setDisable(true);
        button7.setLayoutX(520);

        button8.setMinSize(240, 240);
        gameBoard.getChildren().add(button8);
        button8.setDisable(true);
        button8.setLayoutX(520);
        button8.setLayoutY(260);

        button9.setMinSize(240, 240);
        gameBoard.getChildren().add(button9);
        button9.setDisable(true);
        button9.setLayoutX(520);
        button9.setLayoutY(520);

        newGame.setPrefSize(100, 50);
        exit.setPrefSize(100, 50);

        GridPane settings = new GridPane();

        display.setPrefSize(150, 50);
        display.setText(" Player 1 start the Game");

        gameText.setText("Game ");
        title.setText(" Tic Tac Toe Ultimate");

        settings.add(gameText, 0, 0);
        settings.setPadding(new Insets(15, 25, 15, 15));

        borderPane = new BorderPane();
        borderPane.setTop(title);
        borderPane.setLeft(settings);
        borderPane.setCenter(gameBoard);
        borderPane.setBottom(display);

        gameBoard.getChildren().add(listView);
        gameBoard.getChildren().add(radioButton);
        gameBoard.getChildren().add(radioButton2);
        gameBoard.getChildren().add(O);
        gameBoard.getChildren().add(X);
        gameBoard.getChildren().add(whoFirst);

        Scene scene = new Scene(borderPane, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.show();
        gameBoard.getChildren().add(exit);
        gameBoard.getChildren().add(newGame);
        newGame.setLayoutX(1200);
        newGame.setLayoutY(600);
        exit.setLayoutX(1200);
        exit.setLayoutY(650);
        gameText.setLayoutX(1200);
        gameText.setLayoutY(570);
        newGame.setOnAction(actionEvent -> {
            primaryStage.close();
            Platform.runLater(() -> new Main().start(new Stage()));
        });
        exit.setOnMouseClicked(actionEvent -> {
            System.out.println("EXIT");
            primaryStage.close();
        });
    }

    public void selected(int i) {
        board = i;
        if (isPlayer1) {
            tab1[i].setText("X");
            tab1[i].setTextFill(Paint.valueOf("red"));
            tab1[i].setGraphic(new ImageView(new Image("file:src/sample/X.png")));
            tab1[i].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            tab1[i].setDisable(true);
            isPlayer1 = false;
            display.setText("Player 2");
            gameHistory(0, i);
        } else {
            System.out.println("AI p1");
            if (AI) {
                AImove(tab1);
                System.out.println("AI p2:" + AI);
                gameHistory(0, random);
            } else {
                tab1[i].setText(" ");
                tab1[i].setGraphic(new ImageView(new Image("file:src/sample/O.png")));
                tab1[i].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                tab1[i].setDisable(true);
                isPlayer1 = true;
                display.setText("Player 1");
                gameHistory(0, i);
            }
        }
        tab1[i].setFont(Font.font(15));
        tab1[i].setDisable(true);

        findWinner();

        if (win1) {
            for (int j = 0; j < 9; j++) {
                tab1[j].setDisable(true);
            }
            if (isPlayer1 && button1.getText().equals("")) {
                display.setText("Player 2 won!!!");
                button1.setGraphic(new ImageView(new Image("file:src/sample/bigO.png")));
                button1.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                button1.setText("o");
                button1.setTextFill(Paint.valueOf("blue"));
            } else if (button1.getText().equals("")) {
                display.setText("Player 1 won!!!");
                button1.setGraphic(new ImageView(new Image("file:src/sample/bigX.png")));
                button1.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                button1.setText("X");
                button1.setTextFill(Paint.valueOf("red"));
            }
            button1.setDisable(false);
            finalWin();
            if (finalWin) {
                tab1[i].setDisable(true);
                showScore(finalWinner);
            }
        }
        checkDraw();
        win1 = false;
        if (!finalWin) {
            randomBoard(board);
        }
    }

    public void selected2(int i) {
        board = i;
        if (isPlayer1) {
            tab2[i].setText("X");
            tab2[i].setTextFill(Paint.valueOf("red"));
            tab2[i].setGraphic(new ImageView(new Image("file:src/sample/X.png")));
            tab2[i].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            tab2[i].setDisable(true);
            isPlayer1 = false;
            display.setText("Player 2");
            gameHistory(1, i);
        } else {
            if (AI) {
                System.out.println("AI :" + AI);
                AImove(tab2);
                gameHistory(1, random);
            } else {
                tab2[i].setText(" ");
                tab2[i].setGraphic(new ImageView(new Image("file:src/sample/O.png")));
                tab2[i].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                tab2[i].setDisable(true);
                isPlayer1 = true;
                display.setText("Player 1");
                gameHistory(1, i);
            }
        }
        tab2[i].setFont(Font.font(15));
        tab2[i].setDisable(true);

        findWinner();

        if (win2) {
            for (int j = 0; j < 9; j++) {
                tab2[j].setDisable(true);
            }
            if (isPlayer1 && button2.getText().equals("")) {
                display.setText("Player 2 won!!!");
                button2.setGraphic(new ImageView(new Image("file:src/sample/bigO.png")));
                button2.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                button2.setText("o");
                button2.setTextFill(Paint.valueOf("blue"));
            } else if (button2.getText().equals("")) {
                display.setText("Player 1 won!!!");
                button2.setGraphic(new ImageView(new Image("file:src/sample/bigX.png")));
                button2.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                button2.setText("X");
                button2.setTextFill(Paint.valueOf("red"));
            }
            button2.setDisable(false);
            finalWin();
            if (finalWin) {
                tab2[i].setDisable(true);
                showScore(finalWinner);
            }
        }
        checkDraw();
        win2 = false;
        if (!finalWin) {
            randomBoard(board);
        }
    }

    public void selected3(int i) {
        board = i;
        if (isPlayer1) {
            tab3[i].setText("X");
            tab3[i].setTextFill(Paint.valueOf("red"));
            tab3[i].setGraphic(new ImageView(new Image("file:src/sample/X.png")));
            tab3[i].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            tab3[i].setDisable(true);
            isPlayer1 = false;
            display.setText("Player 2");
            gameHistory(2, i);
        } else {
            if (AI) {
                AImove(tab3);
                gameHistory(2, random);
                System.out.println("AI :" + AI);
            } else {
                tab3[i].setText(" ");
                tab3[i].setGraphic(new ImageView(new Image("file:src/sample/O.png")));
                tab3[i].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                tab3[i].setDisable(true);
                isPlayer1 = true;
                display.setText("Player 1");
                gameHistory(2, i);
            }
        }
        tab3[i].setFont(Font.font(15));
        tab3[i].setDisable(true);

        findWinner();

        if (win3) {
            for (int j = 0; j < 9; j++) {
                tab3[j].setDisable(true);
            }
            if (isPlayer1 && button3.getText().equals("")) {
                display.setText("Player 2 won!!!");
                button3.setGraphic(new ImageView(new Image("file:src/sample/bigO.png")));
                button3.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                button3.setText("o");
                button3.setTextFill(Paint.valueOf("blue"));
            } else if (button3.getText().equals("")) {
                display.setText("Player 1 won!!!");
                button3.setGraphic(new ImageView(new Image("file:src/sample/bigX.png")));
                button3.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                button3.setText("X");
                button3.setTextFill(Paint.valueOf("red"));
            }
            button3.setDisable(false);
            finalWin();
            if (finalWin) {
                tab3[i].setDisable(true);
                showScore(finalWinner);
            }
        }
        checkDraw();
        win3 = false;
        if (!finalWin) {
            randomBoard(board);
        }
    }

    public void selected4(int i) {
        board = i;
        if (isPlayer1) {
            tab4[i].setText("X");
            tab4[i].setTextFill(Paint.valueOf("red"));
            tab4[i].setGraphic(new ImageView(new Image("file:src/sample/X.png")));
            tab4[i].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            tab4[i].setDisable(true);
            isPlayer1 = false;
            display.setText("Player 2");
            gameHistory(3, i);
        } else {
            if (AI) {
                AImove(tab4);
                gameHistory(3, random);
            } else {
                tab4[i].setText(" ");
                tab4[i].setGraphic(new ImageView(new Image("file:src/sample/O.png")));
                tab4[i].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                tab4[i].setDisable(true);
                isPlayer1 = true;
                display.setText("Player 1");
                gameHistory(3, i);
            }
        }
        tab4[i].setFont(Font.font(15));
        tab4[i].setDisable(true);

        findWinner();

        if (win4) {
            for (int j = 0; j < 9; j++) {
                tab4[j].setDisable(true);
            }
            if (isPlayer1 && button4.getText().equals("")) {
                display.setText("Player 2 won!!!");
                button4.setGraphic(new ImageView(new Image("file:src/sample/bigO.png")));
                button4.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                button4.setText("o");
                button4.setTextFill(Paint.valueOf("blue"));
            } else if (button4.getText().equals("")) {
                display.setText("Player 1 won!!!");
                button4.setGraphic(new ImageView(new Image("file:src/sample/bigX.png")));
                button4.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                button4.setText("X");
                button4.setTextFill(Paint.valueOf("red"));
            }
            button4.setDisable(false);
            finalWin();
            if (finalWin) {
                tab4[i].setDisable(true);
                showScore(finalWinner);
            }
        }
        checkDraw();
        win4 = false;
        if (!finalWin) {
            randomBoard(board);
        }
    }

    public void selected5(int i) {
        board = i;
        if (isPlayer1) {
            tab5[i].setText("X");
            tab5[i].setTextFill(Paint.valueOf("red"));
            tab5[i].setGraphic(new ImageView(new Image("file:src/sample/X.png")));
            tab5[i].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            tab5[i].setDisable(true);
            isPlayer1 = false;
            display.setText("Player 2");
            gameHistory(4, i);
        } else {
            if (AI) {
                AImove(tab5);
                gameHistory(4, random);
            } else {
                tab5[i].setText(" ");
                tab5[i].setGraphic(new ImageView(new Image("file:src/sample/O.png")));
                tab5[i].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                tab5[i].setDisable(true);
                isPlayer1 = true;
                display.setText("Player 1");
                gameHistory(4, i);
            }
        }
        tab5[i].setFont(Font.font(15));
        tab5[i].setDisable(true);

        findWinner();

        if (win5) {
            for (int j = 0; j < 9; j++) {
                tab5[j].setDisable(true);
            }
            if (isPlayer1 && button5.getText().equals("")) {
                display.setText("Player 2 won!!!");
                button5.setGraphic(new ImageView(new Image("file:src/sample/bigO.png")));
                button5.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                button5.setText("o");
                button5.setTextFill(Paint.valueOf("blue"));
            } else if (button5.getText().equals("")) {
                display.setText("Player 1 won!!!");
                button5.setGraphic(new ImageView(new Image("file:src/sample/bigX.png")));
                button5.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                button5.setText("X");
                button5.setTextFill(Paint.valueOf("red"));
            }
            button5.setDisable(false);
            finalWin();
            if (finalWin) {
                tab5[i].setDisable(true);
                showScore(finalWinner);
            }
        }
        checkDraw();
        win5 = false;
        if (!finalWin) {
            randomBoard(board);
        }
    }

    public void selected6(int i) {
        board = i;
        if (isPlayer1) {
            tab6[i].setText("X");
            tab6[i].setTextFill(Paint.valueOf("red"));
            tab6[i].setGraphic(new ImageView(new Image("file:src/sample/X.png")));
            tab6[i].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            tab6[i].setDisable(true);
            isPlayer1 = false;
            display.setText("Player 2");
            gameHistory(5, i);
        } else {
            if (AI) {
                AImove(tab6);
                gameHistory(5, random);
            } else {
                tab6[i].setText(" ");
                tab6[i].setGraphic(new ImageView(new Image("file:src/sample/O.png")));
                tab6[i].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                tab6[i].setDisable(true);
                isPlayer1 = true;
                display.setText("Player 1");
                gameHistory(5, i);
            }
        }
        tab6[i].setFont(Font.font(15));
        tab6[i].setDisable(true);

        findWinner();

        if (win6) {
            for (int j = 0; j < 9; j++) {
                tab6[j].setDisable(true);
            }
            if (isPlayer1 && button6.getText().equals("")) {
                display.setText("Player 2 won!!!");
                button6.setGraphic(new ImageView(new Image("file:src/sample/bigO.png")));
                button6.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                button6.setText("o");
                button6.setTextFill(Paint.valueOf("blue"));
            } else if (button6.getText().equals("")) {
                display.setText("Player 1 won!!!");
                button6.setGraphic(new ImageView(new Image("file:src/sample/bigX.png")));
                button6.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                button6.setText("X");
                button6.setTextFill(Paint.valueOf("red"));
            }
            button6.setDisable(false);
            finalWin();
            if (finalWin) {
                tab6[i].setDisable(true);
                showScore(finalWinner);
            }
        }
        checkDraw();
        win6 = false;
        if (!finalWin) {
            randomBoard(board);
        }
    }

    public void selected7(int i) {
        board = i;
        if (isPlayer1) {
            tab7[i].setText("X");
            tab7[i].setTextFill(Paint.valueOf("red"));
            tab7[i].setGraphic(new ImageView(new Image("file:src/sample/X.png")));
            tab7[i].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            tab7[i].setDisable(true);
            isPlayer1 = false;
            display.setText("Player 2");
            gameHistory(6, i);
        } else {
            if (AI) {
                AImove(tab7);
                gameHistory(6, random);
            } else {
                tab7[i].setText(" ");
                tab7[i].setGraphic(new ImageView(new Image("file:src/sample/O.png")));
                tab7[i].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                tab7[i].setDisable(true);
                isPlayer1 = true;
                display.setText("Player 1");
                gameHistory(6, i);
            }
        }
        tab7[i].setFont(Font.font(15));
        tab7[i].setDisable(true);

        findWinner();

        if (win7) {
            for (int j = 0; j < 9; j++) {
                tab7[j].setDisable(true);
            }
            if (isPlayer1 && button7.getText().equals("")) {
                display.setText("Player 2 won!!!");
                button7.setGraphic(new ImageView(new Image("file:src/sample/bigO.png")));
                button7.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                button7.setText("o");
                button7.setTextFill(Paint.valueOf("blue"));
            } else if (button7.getText().equals("")) {
                display.setText("Player 1 won!!!");
                button7.setGraphic(new ImageView(new Image("file:src/sample/bigX.png")));
                button7.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                button7.setText("X");
                button7.setTextFill(Paint.valueOf("red"));
            }
            button7.setDisable(false);
            finalWin();
            if (finalWin) {
                tab7[i].setDisable(true);
                showScore(finalWinner);
            }
        }
        checkDraw();
        win7 = false;
        if (!finalWin) {
            randomBoard(board);
        }
    }

    public void selected8(int i) {
        board = i;
        if (isPlayer1) {
            tab8[i].setText("X");
            tab8[i].setTextFill(Paint.valueOf("red"));
            tab8[i].setGraphic(new ImageView(new Image("file:src/sample/X.png")));
            tab8[i].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            tab8[i].setDisable(true);
            isPlayer1 = false;
            display.setText("Player 2");
            gameHistory(7, i);
        } else {
            if (AI) {
                AImove(tab8);
                gameHistory(7, random);
            } else {
                tab8[i].setText(" ");
                tab8[i].setGraphic(new ImageView(new Image("file:src/sample/O.png")));
                tab8[i].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                tab8[i].setDisable(true);
                isPlayer1 = true;
                display.setText("Player 1");
                gameHistory(7, i);
            }
        }
        tab8[i].setFont(Font.font(15));
        tab8[i].setDisable(true);

        findWinner();

        if (win8) {
            for (int j = 0; j < 9; j++) {
                tab8[j].setDisable(true);
            }
            if (isPlayer1 && button8.getText().equals("")) {
                display.setText("Player 2 won!!!");
                button8.setGraphic(new ImageView(new Image("file:src/sample/bigO.png")));
                button8.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                button8.setText("o");
                button8.setTextFill(Paint.valueOf("blue"));
            } else if (button8.getText().equals("")) {
                display.setText("Player 1 won!!!");
                button8.setGraphic(new ImageView(new Image("file:src/sample/bigX.png")));
                button8.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                button8.setText("X");
                button8.setTextFill(Paint.valueOf("red"));
            }
            button8.setDisable(false);
            finalWin();
            if (finalWin) {
                tab8[i].setDisable(true);
                showScore(finalWinner);
            }
        }
        checkDraw();
        win8 = false;
        if (!finalWin) {
            randomBoard(board);
        }
    }

    public void selected9(int i) {
        board = i;
        if (isPlayer1) {
            tab9[i].setText("X");
            tab9[i].setTextFill(Paint.valueOf("red"));
            tab9[i].setGraphic(new ImageView(new Image("file:src/sample/X.png")));
            tab9[i].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
            tab9[i].setDisable(true);
            isPlayer1 = false;
            display.setText("Player 2");
            gameHistory(8, i);
        } else {
            if (AI) {
                AImove(tab9);
                gameHistory(8, random);
            } else {
                tab9[i].setText(" ");
                tab9[i].setGraphic(new ImageView(new Image("file:src/sample/O.png")));
                tab9[i].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                tab9[i].setDisable(true);
                isPlayer1 = true;
                display.setText("Player 1");
                gameHistory(8, i);
            }
        }
        tab9[i].setFont(Font.font(15));
        tab9[i].setDisable(true);

        findWinner();

        if (win9) {
            for (int j = 0; j < 9; j++) {
                tab9[j].setDisable(true);
            }
            if (isPlayer1 && button9.getText().equals("")) {
                display.setText("Player 2 won!!!");
                button9.setGraphic(new ImageView(new Image("file:src/sample/bigO.png")));
                button9.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                button9.setText("o");
                button9.setTextFill(Paint.valueOf("blue"));
            } else if (button9.getText().equals("")) {
                display.setText("Player 1 won!!!");
                button9.setGraphic(new ImageView(new Image("file:src/sample/bigX.png")));
                button9.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                button9.setText("X");
                button9.setTextFill(Paint.valueOf("red"));
            }
            button9.setDisable(false);
            finalWin();
            if (finalWin) {
                tab9[i].setDisable(true);
                showScore(finalWinner);
            }
        }
        checkDraw();
        win9 = false;
        if (!finalWin) {
            randomBoard(board);
        }
    }

    public void AImove(Button[] buttons) {
        random = rnd.nextInt(9);
        while (!buttons[random].getText().isEmpty()) {
            random = rnd.nextInt(9);
        }
        buttons[random].setText(" ");
        buttons[random].setGraphic(new ImageView(new Image("file:src/sample/O.png")));
        buttons[random].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        isPlayer1 = true;
        display.setText("Player 1");
//        gameHistory(board, random);
    }


    public void checkDraw() {
        draw(tab1, win1, button1);
        draw(tab2, win2, button2);
        draw(tab3, win3, button3);
        draw(tab4, win4, button4);
        draw(tab5, win5, button5);
        draw(tab6, win6, button6);
        draw(tab7, win7, button7);
        draw(tab8, win8, button8);
        draw(tab9, win9, button9);
    }

    private void draw(Button[] tab1, boolean win1, Button button1) {
        for (int i = 0; i < tab1.length; i++) {
            if (tab1[i].getText().isEmpty()) {
                break;
            }
            if (tab1.length - 1 == i && tab1[i].isDisable() && !win1 && !finalWin) {
                display.setText("Draw");
                button1.setText("=");
                button1.setGraphic(new ImageView(new Image("file:src/sample/big=.png")));
                button1.setDisable(false);
            }
        }
    }

    public void findWinner() {
        checkColumns();
        checkDiagonals();
        checkRows();
    }

    public void randomBoard(int board) {
        buttonOFF();
        // r = rnd.nextInt(9);
//        if (AI && isPlayer1) {
//            board = random;
//        }
        if (board == 0 && button1.getText().isEmpty()) {
            buttonOn(tab1);
        } else if (board == 1 && button4.getText().isEmpty()) {
            buttonOn(tab4);
        } else if (board == 2 && button7.getText().isEmpty()) {
            buttonOn(tab7);
        } else if (board == 3 && button2.getText().isEmpty()) {
            buttonOn(tab2);
        } else if (board == 4 && button5.getText().isEmpty()) {
            buttonOn(tab5);
        } else if (board == 5 && button8.getText().isEmpty()) {
            buttonOn(tab8);
        } else if (board == 6 && button3.getText().isEmpty()) {
            buttonOn(tab3);
        } else if (board == 7 && button6.getText().isEmpty()) {
            buttonOn(tab6);
        } else if (board == 8 && button9.getText().isEmpty()) {
            buttonOn(tab9);
        } else {
            buttonOnv2(tab1, button1);
            buttonOnv2(tab2, button2);
            buttonOnv2(tab3, button3);
            buttonOnv2(tab4, button4);
            buttonOnv2(tab5, button5);
            buttonOnv2(tab6, button6);
            buttonOnv2(tab7, button7);
            buttonOnv2(tab8, button8);
            buttonOnv2(tab9, button9);
        }
    }

    public void checkDiagonals() {
        if (tab1[0].getText().equals(tab1[4].getText()) && tab1[4].getText().equals(tab1[8].getText()) && !tab1[8].getText().isEmpty()) {
            win1 = true;
        }
        if (tab1[2].getText().equals(tab1[4].getText()) && tab1[4].getText().equals(tab1[6].getText()) && !tab1[6].getText().isEmpty()) {
            win1 = true;
        }
        if (tab2[0].getText().equals(tab2[4].getText()) && tab2[4].getText().equals(tab2[8].getText()) && !tab2[8].getText().isEmpty()) {
            win2 = true;
        }
        if (tab2[2].getText().equals(tab2[4].getText()) && tab2[4].getText().equals(tab2[6].getText()) && !tab2[6].getText().isEmpty()) {
            win2 = true;
        }
        if (tab3[0].getText().equals(tab3[4].getText()) && tab3[4].getText().equals(tab3[8].getText()) && !tab3[8].getText().isEmpty()) {
            win3 = true;
        }
        if (tab3[2].getText().equals(tab3[4].getText()) && tab3[4].getText().equals(tab3[6].getText()) && !tab3[6].getText().isEmpty()) {
            win3 = true;
        }
        if (tab4[0].getText().equals(tab4[4].getText()) && tab4[4].getText().equals(tab4[8].getText()) && !tab4[8].getText().isEmpty()) {
            win4 = true;
        }
        if (tab4[2].getText().equals(tab4[4].getText()) && tab4[4].getText().equals(tab4[6].getText()) && !tab4[6].getText().isEmpty()) {
            win4 = true;
        }
        if (tab5[0].getText().equals(tab5[4].getText()) && tab5[4].getText().equals(tab5[8].getText()) && !tab5[8].getText().isEmpty()) {
            win5 = true;
        }
        if (tab5[2].getText().equals(tab5[4].getText()) && tab5[4].getText().equals(tab5[6].getText()) && !tab5[6].getText().isEmpty()) {
            win5 = true;
        }
        if (tab6[0].getText().equals(tab6[4].getText()) && tab6[4].getText().equals(tab6[8].getText()) && !tab6[8].getText().isEmpty()) {
            win6 = true;
        }
        if (tab6[2].getText().equals(tab6[4].getText()) && tab6[4].getText().equals(tab6[6].getText()) && !tab6[6].getText().isEmpty()) {
            win6 = true;
        }
        if (tab7[0].getText().equals(tab7[4].getText()) && tab7[4].getText().equals(tab7[8].getText()) && !tab7[8].getText().isEmpty()) {
            win7 = true;
        }
        if (tab7[2].getText().equals(tab7[4].getText()) && tab7[4].getText().equals(tab7[6].getText()) && !tab7[6].getText().isEmpty()) {
            win7 = true;
        }
        if (tab8[0].getText().equals(tab8[4].getText()) && tab8[4].getText().equals(tab8[8].getText()) && !tab8[8].getText().isEmpty()) {
            win8 = true;
        }
        if (tab8[2].getText().equals(tab8[4].getText()) && tab8[4].getText().equals(tab8[6].getText()) && !tab8[6].getText().isEmpty()) {
            win8 = true;
        }
        if (tab9[0].getText().equals(tab9[4].getText()) && tab9[4].getText().equals(tab9[8].getText()) && !tab9[8].getText().isEmpty()) {
            win9 = true;
        }
        if (tab9[2].getText().equals(tab9[4].getText()) && tab9[4].getText().equals(tab9[6].getText()) && !tab9[6].getText().isEmpty()) {
            win9 = true;
        }
    }

    public void checkRows() {
        if (tab1[0].getText().equals(tab1[1].getText()) && tab1[1].getText().equals(tab1[2].getText()) && !tab1[2].getText().isEmpty()) {
            win1 = true;
        }
        if (tab1[3].getText().equals(tab1[4].getText()) && tab1[4].getText().equals(tab1[5].getText()) && !tab1[5].getText().isEmpty()) {
            win1 = true;
        }
        if (tab1[6].getText().equals(tab1[7].getText()) && tab1[7].getText().equals(tab1[8].getText()) && !tab1[8].getText().isEmpty()) {
            win1 = true;
        }
        if (tab2[0].getText().equals(tab2[1].getText()) && tab2[1].getText().equals(tab2[2].getText()) && !tab2[2].getText().isEmpty()) {
            win2 = true;
        }
        if (tab2[3].getText().equals(tab2[4].getText()) && tab2[4].getText().equals(tab2[5].getText()) && !tab2[5].getText().isEmpty()) {
            win2 = true;
        }
        if (tab2[6].getText().equals(tab2[7].getText()) && tab2[7].getText().equals(tab2[8].getText()) && !tab2[8].getText().isEmpty()) {
            win2 = true;
        }
        if (tab3[0].getText().equals(tab3[1].getText()) && tab3[1].getText().equals(tab3[2].getText()) && !tab3[2].getText().isEmpty()) {
            win3 = true;
        }
        if (tab3[3].getText().equals(tab3[4].getText()) && tab3[4].getText().equals(tab3[5].getText()) && !tab3[5].getText().isEmpty()) {
            win3 = true;
        }
        if (tab3[6].getText().equals(tab3[7].getText()) && tab3[7].getText().equals(tab3[8].getText()) && !tab3[8].getText().isEmpty()) {
            win3 = true;
        }
        if (tab4[0].getText().equals(tab4[1].getText()) && tab4[1].getText().equals(tab4[2].getText()) && !tab4[2].getText().isEmpty()) {
            win4 = true;
        }
        if (tab4[3].getText().equals(tab4[4].getText()) && tab4[4].getText().equals(tab4[5].getText()) && !tab4[5].getText().isEmpty()) {
            win4 = true;
        }
        if (tab4[6].getText().equals(tab4[7].getText()) && tab4[7].getText().equals(tab4[8].getText()) && !tab4[8].getText().isEmpty()) {
            win4 = true;
        }
        if (tab5[0].getText().equals(tab5[1].getText()) && tab5[1].getText().equals(tab5[2].getText()) && !tab5[2].getText().isEmpty()) {
            win5 = true;
        }
        if (tab5[3].getText().equals(tab5[4].getText()) && tab5[4].getText().equals(tab5[5].getText()) && !tab5[5].getText().isEmpty()) {
            win5 = true;
        }
        if (tab5[6].getText().equals(tab5[7].getText()) && tab5[7].getText().equals(tab5[8].getText()) && !tab5[8].getText().isEmpty()) {
            win5 = true;
        }
        if (tab6[0].getText().equals(tab6[1].getText()) && tab6[1].getText().equals(tab6[2].getText()) && !tab6[2].getText().isEmpty()) {
            win6 = true;
        }
        if (tab6[3].getText().equals(tab6[4].getText()) && tab6[4].getText().equals(tab6[5].getText()) && !tab6[5].getText().isEmpty()) {
            win6 = true;
        }
        if (tab6[6].getText().equals(tab6[7].getText()) && tab6[7].getText().equals(tab6[8].getText()) && !tab6[8].getText().isEmpty()) {
            win6 = true;
        }
        if (tab7[0].getText().equals(tab7[1].getText()) && tab7[1].getText().equals(tab7[2].getText()) && !tab7[2].getText().isEmpty()) {
            win7 = true;
        }
        if (tab7[3].getText().equals(tab7[4].getText()) && tab7[4].getText().equals(tab7[5].getText()) && !tab7[5].getText().isEmpty()) {
            win7 = true;
        }
        if (tab7[6].getText().equals(tab7[7].getText()) && tab7[7].getText().equals(tab7[8].getText()) && !tab7[8].getText().isEmpty()) {
            win7 = true;
        }
        if (tab8[0].getText().equals(tab8[1].getText()) && tab8[1].getText().equals(tab8[2].getText()) && !tab8[2].getText().isEmpty()) {
            win8 = true;
        }
        if (tab8[3].getText().equals(tab8[4].getText()) && tab8[4].getText().equals(tab8[5].getText()) && !tab8[5].getText().isEmpty()) {
            win8 = true;
        }
        if (tab8[6].getText().equals(tab8[7].getText()) && tab8[7].getText().equals(tab8[8].getText()) && !tab8[8].getText().isEmpty()) {
            win8 = true;
        }
        if (tab9[0].getText().equals(tab9[1].getText()) && tab9[1].getText().equals(tab9[2].getText()) && !tab9[2].getText().isEmpty()) {
            win9 = true;
        }
        if (tab9[3].getText().equals(tab9[4].getText()) && tab9[4].getText().equals(tab9[5].getText()) && !tab9[5].getText().isEmpty()) {
            win9 = true;
        }
        if (tab9[6].getText().equals(tab9[7].getText()) && tab9[7].getText().equals(tab9[8].getText()) && !tab9[8].getText().isEmpty()) {
            win9 = true;
        }
    }

    public void checkColumns() {
        if (tab1[0].getText().equals(tab1[3].getText()) && tab1[3].getText().equals(tab1[6].getText()) && !tab1[6].getText().isEmpty()) {
            win1 = true;
        }
        if (tab1[1].getText().equals(tab1[4].getText()) && tab1[4].getText().equals(tab1[7].getText()) && !tab1[7].getText().isEmpty()) {
            win1 = true;
        }
        if (tab1[2].getText().equals(tab1[5].getText()) && tab1[5].getText().equals(tab1[8].getText()) && !tab1[8].getText().isEmpty()) {
            win1 = true;
        }
        if (tab2[0].getText().equals(tab2[3].getText()) && tab2[3].getText().equals(tab2[6].getText()) && !tab2[6].getText().isEmpty()) {
            win2 = true;
        }
        if (tab2[1].getText().equals(tab2[4].getText()) && tab2[4].getText().equals(tab2[7].getText()) && !tab2[7].getText().isEmpty()) {
            win2 = true;
        }
        if (tab2[2].getText().equals(tab2[5].getText()) && tab2[5].getText().equals(tab2[8].getText()) && !tab2[8].getText().isEmpty()) {
            win2 = true;
        }
        if (tab3[0].getText().equals(tab3[3].getText()) && tab3[3].getText().equals(tab3[6].getText()) && !tab3[6].getText().isEmpty()) {
            win3 = true;
        }
        if (tab3[1].getText().equals(tab3[4].getText()) && tab3[4].getText().equals(tab3[7].getText()) && !tab3[7].getText().isEmpty()) {
            win3 = true;
        }
        if (tab3[2].getText().equals(tab3[5].getText()) && tab3[5].getText().equals(tab3[8].getText()) && !tab3[8].getText().isEmpty()) {
            win3 = true;
        }
        if (tab4[0].getText().equals(tab4[3].getText()) && tab4[3].getText().equals(tab4[6].getText()) && !tab4[6].getText().isEmpty()) {
            win4 = true;
        }
        if (tab4[1].getText().equals(tab4[4].getText()) && tab4[4].getText().equals(tab4[7].getText()) && !tab4[7].getText().isEmpty()) {
            win4 = true;
        }
        if (tab4[2].getText().equals(tab4[5].getText()) && tab4[5].getText().equals(tab4[8].getText()) && !tab4[8].getText().isEmpty()) {
            win4 = true;
        }
        if (tab5[0].getText().equals(tab5[3].getText()) && tab5[3].getText().equals(tab5[6].getText()) && !tab5[6].getText().isEmpty()) {
            win5 = true;
        }
        if (tab5[1].getText().equals(tab5[4].getText()) && tab5[4].getText().equals(tab5[7].getText()) && !tab5[7].getText().isEmpty()) {
            win5 = true;
        }
        if (tab5[2].getText().equals(tab5[5].getText()) && tab5[5].getText().equals(tab5[8].getText()) && !tab5[8].getText().isEmpty()) {
            win5 = true;
        }
        if (tab6[0].getText().equals(tab6[3].getText()) && tab6[3].getText().equals(tab6[6].getText()) && !tab6[6].getText().isEmpty()) {
            win6 = true;
        }
        if (tab6[1].getText().equals(tab6[4].getText()) && tab6[4].getText().equals(tab6[7].getText()) && !tab6[7].getText().isEmpty()) {
            win6 = true;
        }
        if (tab6[2].getText().equals(tab6[5].getText()) && tab6[5].getText().equals(tab6[8].getText()) && !tab6[8].getText().isEmpty()) {
            win6 = true;
        }
        if (tab7[0].getText().equals(tab7[3].getText()) && tab7[3].getText().equals(tab7[6].getText()) && !tab7[6].getText().isEmpty()) {
            win7 = true;
        }
        if (tab7[1].getText().equals(tab7[4].getText()) && tab7[4].getText().equals(tab7[7].getText()) && !tab7[7].getText().isEmpty()) {
            win7 = true;
        }
        if (tab7[2].getText().equals(tab7[5].getText()) && tab7[5].getText().equals(tab7[8].getText()) && !tab7[8].getText().isEmpty()) {
            win7 = true;
        }
        if (tab8[0].getText().equals(tab8[3].getText()) && tab8[3].getText().equals(tab8[6].getText()) && !tab8[6].getText().isEmpty()) {
            win8 = true;
        }
        if (tab8[1].getText().equals(tab8[4].getText()) && tab8[4].getText().equals(tab8[7].getText()) && !tab8[7].getText().isEmpty()) {
            win8 = true;
        }
        if (tab8[2].getText().equals(tab8[5].getText()) && tab8[5].getText().equals(tab8[8].getText()) && !tab8[8].getText().isEmpty()) {
            win8 = true;
        }
        if (tab9[0].getText().equals(tab9[3].getText()) && tab9[3].getText().equals(tab9[6].getText()) && !tab9[6].getText().isEmpty()) {
            win9 = true;
        }
        if (tab9[1].getText().equals(tab9[4].getText()) && tab9[4].getText().equals(tab9[7].getText()) && !tab9[7].getText().isEmpty()) {
            win9 = true;
        }
        if (tab9[2].getText().equals(tab9[5].getText()) && tab9[5].getText().equals(tab9[8].getText()) && !tab9[8].getText().isEmpty()) {
            win9 = true;
        }
    }

    public void finalWin() {
        resultRows(tab11);
        resultColumns(tab11);
        if (button1.getText().equals(button5.getText()) && button5.getText().equals(button9.getText()) && !button9.getText().isEmpty() && !button9.getText().equals("=")) {
            System.out.println("finalWin");
            finalWin = true;
            if (button1.getText().equals("X")) {
                finalWinner = "X";
            } else {
                finalWinner = "O";
            }
        }
        if (button3.getText().equals(button5.getText()) && button5.getText().equals(button7.getText()) && !button7.getText().isEmpty() && !button7.getText().equals("=")) {
            System.out.println("finalWin");
            finalWin = true;
            if (button3.getText().equals("X")) {
                finalWinner = "X";
            } else {
                finalWinner = "O";
            }
        }
    }

    public void resultRows(Button[] button11) {
        for (int i = 0; i < 3; i++) {
            if (button11[i].getText().equals(button11[i + 3].getText()) && button11[i + 3].getText().equals(button11[i + 6].getText()) && !button11[i + 6].getText().isEmpty() && !(button11[i + 6].getText().equals("="))) {
                System.out.println("finalWin");
                finalWin = true;
                if (button11[i].getText().equals("X")) {
                    finalWinner = "X";
                } else {
                    finalWinner = "O";
                }
            }
        }
    }

    public void resultColumns(Button[] button11) {
        for (int i = 0; i < 8; i += 3) {
            if (button11[i].getText().equals(button11[i + 1].getText()) && button11[i + 1].getText().equals(button11[i + 2].getText()) && !button11[i + 2].getText().isEmpty() && !button11[i + 2].getText().equals("=")) {
                System.out.println("finalWin");
                System.out.println("column");
                finalWin = true;
                if (button11[i].getText().equals("X")) {
                    finalWinner = "X";
                } else {
                    finalWinner = "O";
                }
            }
        }
    }

    public void showScore(String x) {
        whoWon.setPrefSize(200, 200);
        whoWon.setText("Player " + x + " WIN");
        borderPane.setRight(whoWon);
    }

    public void buttonOFF() {
        for (int i = 0; i < 9; i++) {
            tab1[i].setDisable(true);
        }
        for (int i = 0; i < 9; i++) {
            tab2[i].setDisable(true);
        }
        for (int i = 0; i < 9; i++) {
            tab3[i].setDisable(true);
        }
        for (int i = 0; i < 9; i++) {
            tab4[i].setDisable(true);
        }
        for (int i = 0; i < 9; i++) {
            tab5[i].setDisable(true);
        }
        for (int i = 0; i < 9; i++) {
            tab6[i].setDisable(true);
        }
        for (int i = 0; i < 9; i++) {
            tab7[i].setDisable(true);
        }
        for (int i = 0; i < 9; i++) {
            tab8[i].setDisable(true);
        }
        for (int i = 0; i < 9; i++) {
            tab9[i].setDisable(true);
        }
    }

    public void buttonOn(Button[] buttons) {
        for (int i = 0; i < 9; i++) {
            buttons[i].setDisable(false);
        }
    }

    public void buttonOnv2(Button[] buttons, Button button) {
        if (button.getText().isEmpty()) {
            for (int i = 0; i < 9; i++) {
                buttons[i].setDisable(false);
            }
        }
    }

    public void board() {
        for (int i = 0; i < tab1.length; i++) {
            tab1[i] = new Button();
            tab1[i].setPrefSize(WIDTH, HEIGHT);
            tab1[i].setBorder(Border.stroke(Paint.valueOf("BLACK")));
            tab1[i].setLayoutX(i % 3 * WIDTH);
            tab1[i].setLayoutY(i / 3 * HEIGHT);
            tab1[i].setText("");
            int pos = i;
            tab1[i].setOnAction(e -> selected(pos));
            button1.setOnMouseEntered(e -> button1.setDisable(true));
            button1.setOnMouseExited(e -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    button1.setDisable(false);
                }
            }, 5000));
            gameBoard.getChildren().add(tab1[i]);
        }
    }

    public void board2() {
        for (int i = 0; i < tab2.length; i++) {
            tab2[i] = new Button();
            tab2[i].setPrefSize(WIDTH, HEIGHT);
            tab2[i].setBorder(Border.stroke(Paint.valueOf("BLACK")));
            tab2[i].setLayoutX(i % 3 * WIDTH);
            tab2[i].setLayoutY(i / 3 * HEIGHT + 260);
            tab2[i].setText("");
            int pos = i;
            tab2[i].setOnAction(e -> selected2(pos));
            button2.setOnMouseEntered(e -> button2.setDisable(true));
            button2.setOnMouseExited(e -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    button2.setDisable(false);
                }
            }, 5000));
            gameBoard.getChildren().add(tab2[i]);
        }
    }

    public void board3() {
        for (int i = 0; i < tab3.length; i++) {
            tab3[i] = new Button();
            tab3[i].setPrefSize(WIDTH, HEIGHT);
            tab3[i].setBorder(Border.stroke(Paint.valueOf("BLACK")));
            tab3[i].setLayoutX(i % 3 * WIDTH);
            tab3[i].setLayoutY(i / 3 * HEIGHT + 520);
            tab3[i].setText("");
            int pos = i;
            tab3[i].setOnAction(e -> selected3(pos));
            button3.setOnMouseEntered(e -> button3.setDisable(true));
            button3.setOnMouseExited(e -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    button3.setDisable(false);
                }
            }, 5000));
            gameBoard.getChildren().add(tab3[i]);
        }
    }

    public void board4() {
        for (int i = 0; i < tab4.length; i++) {
            tab4[i] = new Button();
            tab4[i].setPrefSize(WIDTH, HEIGHT);
            tab4[i].setBorder(Border.stroke(Paint.valueOf("BLACK")));
            tab4[i].setLayoutX(i % 3 * WIDTH + 260);
            tab4[i].setLayoutY(i / 3 * HEIGHT);
            tab4[i].setText("");
            int pos = i;
            tab4[i].setOnAction(e -> selected4(pos));
            button4.setOnMouseEntered(e -> button4.setDisable(true));
            button4.setOnMouseExited(e -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    button4.setDisable(false);
                }
            }, 5000));
            gameBoard.getChildren().add(tab4[i]);
        }
    }

    public void board5() {
        for (int i = 0; i < tab5.length; i++) {
            tab5[i] = new Button();
            tab5[i].setPrefSize(WIDTH, HEIGHT);
            tab5[i].setBorder(Border.stroke(Paint.valueOf("BLACK")));
            tab5[i].setLayoutX(i % 3 * WIDTH + 260);
            tab5[i].setLayoutY(i / 3 * HEIGHT + 260);
            tab5[i].setText("");
            int pos = i;
            tab5[i].setOnAction(e -> selected5(pos));
            button5.setOnMouseEntered(e -> button5.setDisable(true));
            button5.setOnMouseExited(e -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    button5.setDisable(false);
                }
            }, 5000));
            gameBoard.getChildren().add(tab5[i]);
        }
    }

    public void board6() {
        for (int i = 0; i < tab6.length; i++) {
            tab6[i] = new Button();
            tab6[i].setPrefSize(WIDTH, HEIGHT);
            tab6[i].setBorder(Border.stroke(Paint.valueOf("BLACK")));
            tab6[i].setLayoutX(i % 3 * WIDTH + 260);
            tab6[i].setLayoutY(i / 3 * HEIGHT + 520);
            tab6[i].setText("");
            int pos = i;
            tab6[i].setOnAction(e -> selected6(pos));
            button6.setOnMouseEntered(e -> button6.setDisable(true));
            button6.setOnMouseExited(e -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    button6.setDisable(false);
                }
            }, 5000));
            gameBoard.getChildren().add(tab6[i]);
        }
    }

    public void board7() {
        for (int i = 0; i < tab7.length; i++) {
            tab7[i] = new Button();
            tab7[i].setPrefSize(WIDTH, HEIGHT);
            tab7[i].setBorder(Border.stroke(Paint.valueOf("BLACK")));
            tab7[i].setLayoutX(i % 3 * WIDTH + 520);
            tab7[i].setLayoutY(i / 3 * HEIGHT);
            tab7[i].setText("");
            int pos = i;
            tab7[i].setOnAction(e -> selected7(pos));
            button7.setOnMouseEntered(e -> button7.setDisable(true));
            button7.setOnMouseExited(e -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    button7.setDisable(false);
                }
            }, 5000));
            gameBoard.getChildren().add(tab7[i]);
        }
    }

    public void board8() {
        for (int i = 0; i < tab8.length; i++) {
            tab8[i] = new Button();
            tab8[i].setPrefSize(WIDTH, HEIGHT);
            tab8[i].setBorder(Border.stroke(Paint.valueOf("BLACK")));
            tab8[i].setLayoutX(i % 3 * WIDTH + 520);
            tab8[i].setLayoutY(i / 3 * HEIGHT + 260);
            tab8[i].setText("");
            int pos = i;
            tab8[i].setOnAction(e -> selected8(pos));
            button8.setOnMouseEntered(e -> button8.setDisable(true));
            button8.setOnMouseExited(e -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    button8.setDisable(false);
                }
            }, 5000));
            gameBoard.getChildren().add(tab8[i]);
        }
    }

    public void board9() {
        for (int i = 0; i < tab9.length; i++) {
            tab9[i] = new Button();
            tab9[i].setPrefSize(WIDTH, HEIGHT);
            tab9[i].setBorder(Border.stroke(Paint.valueOf("BLACK")));
            tab9[i].setLayoutX(i % 3 * WIDTH + 520);
            tab9[i].setLayoutY(i / 3 * HEIGHT + 520);
            tab9[i].setText("");
            int pos = i;
            tab9[i].setOnAction(e -> selected9(pos));
            button9.setOnMouseEntered(e -> {
                button9.setDisable(true);
                for (int k = 0; k < 9; k++) {
                    tab9[k].setDisable(true);
                }
            });
            button9.setOnMouseExited(e -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    button9.setDisable(false);
                }
            }, 5000));
            gameBoard.getChildren().add(tab9[i]);
        }
    }

    public void gameHistory(int z, int i) {
        if (!isPlayer1) {
            listView.getItems().add("board: " + z + " spot: " + i + "   X");
        } else {
            listView.getItems().add("board: " + z + " spot: " + i + "   O");
        }
    }

}


