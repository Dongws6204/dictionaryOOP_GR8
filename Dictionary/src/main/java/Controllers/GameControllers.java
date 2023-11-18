package Controllers;

import dictionaryJava.HangmanGame;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class GameControllers extends  HangmanGame{
    private int curLevel = 1;
    private int atLeft = 6;
    private boolean check = false;
    private  boolean moveRight = true;
    private  Pane gamePane;

    public boolean isCheck() {
        if (atLeft <= 0) {
            check = true;
        }
        return check;
    }
    public GameControllers (Pane gamePane) {
        this.gamePane = gamePane;
    }

    private  String[] english = {"Apple",
            "Angle",
            "Amend",
            "Alarm",
            "Elevator",
            "Elephant",
            "Election",
            "Envelope",
            "Table",
            "Tiger",
            "Train",
            "Truth",
            "Rabbit",
            "Record",
            "Reform",
            "Ripple",
            "Television",
            "Technology",
            "Thermostat",
            "Tournament",
            "Nest",
            "Note",
            "Neck",
            "Noon",
            "Table",
            "Track",
            "Tower",
            "Tonic"
    };
    private  String[] vietNamEse = {"Táo", "Voi", "Hổ", "Thỏ","Tivi", "Ổ","Bàn"};
    private  String[] resWord = {
            "Apple",
            "Elephant",
            "Tiger",
            "Rabbit",
            "Television",
            "Nest",
            "Table",
            "Elephant"
    };
    List<Button> buttons = new ArrayList<>();


    public Group hangManBg() {
        Group group = new Group();
        Rectangle house = new Rectangle(140, 200); // Tạo hình vuông với chiều rộng 140 và chiều cao 200
        house.setFill(Color.WHITE); // Đặt màu nền là trắng
        house.setStroke(Color.BLACK); // Đặt màu viền là đen
        house.setStrokeWidth(1); // Đặt độ dày viền là 1 pixel

        // Vẽ hình tròn (O) bằng Circle
        Circle head = new Circle(70, 35, 20);
        head.setFill(Color.WHITE);  // Đặt màu nền là trắng
        head.setStroke(Color.BLACK); // Đặt màu viền là đen
        head.setStrokeWidth(2);     // Đặt độ dày viền là 2 pixel

        // Vẽ nhân vật đứng (|)
        Line body = new Line(70, 55, 70, 135);
        body.setStroke(Color.BLACK);
        body.setStrokeWidth(2);

        Line leftHand = new Line(70,70, 44, 112);
        leftHand.setStroke(Color.BLACK);
        leftHand.setStrokeWidth(2);

        Line leftLeg = new Line(70,135,44,177);
        leftLeg.setStroke(Color.BLACK);
        leftLeg.setStrokeWidth(2);

        Line rightHand = new Line(70,70,96,112);
        rightHand.setStroke(Color.BLACK);
        rightHand.setStrokeWidth(2);

        Line rightLeg = new Line(70,135,96,177);
        rightLeg.setStroke(Color.BLACK);
        rightLeg.setStrokeWidth(2);

        Color blue = Color.rgb(128, 128, 128);
        Line rope = new Line(0,0,70,67);
        rope.setStroke(blue);
        rope.setStrokeWidth(2);

        // Thêm các hình đã vẽ vào Group
        if (atLeft == 6) {

        } else if ( atLeft == 5) {
            group.getChildren().addAll(house,head);
        } else if (atLeft == 4) {
            group.getChildren().addAll(body);
        } else if (atLeft == 3) {
            group.getChildren().addAll(leftHand,rightHand);
        } else if (atLeft == 2) {
            group.getChildren().addAll(leftLeg,rightLeg);
        } else if (atLeft == 1) {
            group.getChildren().addAll(rope);
        }

        return group;
    }

    public Group hangManBg2() {
        Group group = new Group();
        Group gr1 = new Group();
        Rectangle house = new Rectangle(140, 200); // Tạo hình vuông với chiều rộng 140 và chiều cao 200
        house.setFill(Color.WHITE); // Đặt màu nền là trắng
        house.setStroke(Color.BLACK); // Đặt màu viền là đen
        house.setStrokeWidth(1); // Đặt độ dày viền là 1 pixel

        // Vẽ hình tròn (O) bằng Circle
        Circle head = new Circle(70, 35, 20);
        head.setFill(Color.WHITE);  // Đặt màu nền là trắng
        head.setStroke(Color.BLACK); // Đặt màu viền là đen
        head.setStrokeWidth(2);     // Đặt độ dày viền là 2 pixel

        // Vẽ nhân vật đứng (|)
        Line body = new Line(70, 55, 70, 135);
        body.setStroke(Color.BLACK);
        body.setStrokeWidth(2);

        Line leftHand = new Line(70,70, 44, 112);
        leftHand.setStroke(Color.BLACK);
        leftHand.setStrokeWidth(2);

        Line leftLeg = new Line(70,135,44,177);
        leftLeg.setStroke(Color.BLACK);
        leftLeg.setStrokeWidth(2);

        Line rightHand = new Line(70,70,96,112);
        rightHand.setStroke(Color.BLACK);
        rightHand.setStrokeWidth(2);

        Line rightLeg = new Line(70,135,96,177);
        rightLeg.setStroke(Color.BLACK);
        rightLeg.setStrokeWidth(2);

        Color blue = Color.rgb(128, 128, 128);
        Line rope = new Line(0,0,70,67);
        rope.setStroke(blue);
        rope.setStrokeWidth(2);

        TextField leftTurn = new TextField();
        leftTurn.setText("Attempts Left: " + Integer.toString(atLeft));
        leftTurn.setAlignment(Pos.CENTER);
        leftTurn.setLayoutX(160);
        leftTurn.setLayoutY(15);
        // Thêm các hình đã vẽ vào Group
        if (atLeft == 6) {
            group.getChildren().add(leftTurn);
        } else if ( atLeft == 5) {
            group.getChildren().remove(leftTurn);
            group.getChildren().addAll(house,head,leftTurn);
        } else if (atLeft == 4) {
            group.getChildren().remove(leftTurn);
            group.getChildren().addAll(house,head,body,leftTurn);
        } else if (atLeft == 3) {
            group.getChildren().remove(leftTurn);
            group.getChildren().addAll(house,head,body,leftHand,rightHand,leftTurn);
        } else if (atLeft == 2) {
            group.getChildren().remove(leftTurn);
            group.getChildren().addAll(house,head,body,leftHand,rightHand,leftLeg,rightLeg,leftTurn);
        } else if (atLeft == 1) {
            group.getChildren().remove(leftTurn);
            group.getChildren().addAll(house,head,body,leftHand,rightHand,leftLeg,rightLeg,rope,leftTurn);
        } else if (atLeft == 0) {
            gr1.getChildren().addAll(head,body,leftHand,rightHand,leftLeg,rightLeg);
            group.getChildren().addAll(house,gr1,rope);


            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
                // Kiểm tra hướng di chuyển
                if (moveRight) {
                    // Di chuyển sang phải
                    rope.setEndX(rope.getEndX() + 5);
                    gr1.setLayoutX(gr1.getLayoutX() + 5);
                    rope.setEndY(rope.getEndY() + 1);
                    gr1.setLayoutY(gr1.getLayoutY() + 1);


                    // Kiểm tra nếu vượt quá giới hạn, đặt hướng di chuyển là sang trái
                    if (rope.getEndX() > 55 && gr1.getLayoutX() > 55) {
                        moveRight = false;
                    }
                } else {
                    // Di chuyển sang trái
                    rope.setEndX(rope.getEndX() - 5);
                    rope.setEndY(rope.getEndY() - 1);
                    gr1.setLayoutX(gr1.getLayoutX() - 5);
                    gr1.setLayoutY(gr1.getLayoutY() - 1);

                    // Kiểm tra nếu vượt quá giới hạn, đặt hướng di chuyển là sang phải
                    if (rope.getEndX() < 0 && gr1.getLayoutX() < 0) {
                        moveRight = true;
                    }
                }
            }));

            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();

        }

        return group;
    }
    public Group setTextGame() {
        Group gr = new Group();
        buttons.clear();
//        hmGame.generateWordToGuess(2);

        StringBuilder  list =  generateWordDisplay(resWord[curLevel - 1], 1);
        // Tạo một đối tượng Font với kích thước và kiểu chữ mới
        Font font = Font.font("Times New Roman", FontWeight.BOLD, 18);
        Text res = new Text(list.toString());
        res.setLayoutX(35);
        res.setLayoutY(265);
        res.setFont(font);


//        leftTurn.setFont(font);

        int x = list.toString().length();
        Text size = new Text(Integer.toString(x));
        size.setLayoutX(135);
        size.setLayoutY(268);
        size.setFont(font);

//        Text left = new Text(Integer.toString(atLeft));
//        left.setLayoutX(160);
//        left.setLayoutY(15);
//        left.setFont(font);


        Rectangle houseText = new Rectangle(90,25);
        houseText.setLayoutX(30);
        houseText.setLayoutY(250);
        houseText.setFill(Color.WHITE);
        houseText.setStrokeWidth(1);
        houseText.setStroke(Color.BLACK);

        Button resOne = new Button();
        resOne.setLayoutX(220);
        resOne.setLayoutY(150);
        resOne.setFont(font);
        buttons.add(resOne);

        Button resTwo = new Button();
        resTwo.setLayoutX(220);
        resTwo.setLayoutY(250);
        resTwo.setFont(font);

        Button resThree = new Button();
        resThree.setLayoutX(350);
        resThree.setLayoutY(150);
        resThree.setFont(font);

        Button resFour = new Button();
        resFour.setLayoutX(350);
        resFour.setLayoutY(250);
        resFour.setFont(font);
        resFour.getStyleClass().add("button:hover");

        buttons.add(resTwo);
        buttons.add(resThree);
        buttons.add(resFour);

        setTextButton(buttons, curLevel);
        for (Button y : buttons) {
            y.setOnAction(new ButtonClickHandler(resWord[curLevel-1]));
        }

        gr.getChildren().addAll(houseText,res,size,resOne,resTwo,resThree,resFour);
        return gr;
    }

public void setTextButton(List<Button> buttons, int level) {
    for (int i = 0; i < 4 ; i++) {
        int index = 4 * (level - 1) + i;
        if (index < english.length) {
            buttons.get(i).setText(english[index]);
        } else {
            // Xử lý khi chỉ số vượt quá độ dài của mảng
            buttons.get(i).setText("Tiger"); // hoặc thực hiện một xử lý khác tùy thuộc vào yêu cầu của bạn
        }
    }
}


    public void play(Pane x) {
        x = gamePane;
            gamePane.getChildren().clear();
            gamePane.getChildren().add(hangManBg2());
            gamePane.getChildren().add(setTextGame());
    }


    // Lớp xử lý sự kiện
    // Lớp xử lý sự kiện
    private class ButtonClickHandler implements EventHandler<ActionEvent> {
        private String targetTitle;


        public ButtonClickHandler(String targetTitle) {
            this.targetTitle = targetTitle;
        }

        @Override
        public void handle(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();

            // Kiểm tra title và thay đổi màu sắc
            if (clickedButton.getText().toLowerCase().equals(targetTitle.toLowerCase())) {
                clickedButton.setStyle("-fx-background-color: green;");
                // Tạo một PauseTransition để chờ trong 2 giây
                Text x = new Text(vietNamEse[curLevel-1]);
                Font font = Font.font("Times New Roman", FontWeight.BOLD, 18);
                x.setLayoutX(125);
                x.setLayoutY(300);
                x.setFont(font);


                gamePane.getChildren().add(x);
                PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
                pause.setOnFinished(e -> {
                    // Sau khi chờ, thực hiện các thay đổi
                    curLevel++;
//                    gamePane.getChildren().clear();
                    if (atLeft > 0){
                        play(gamePane);
                    } else {
                        gamePane.getChildren().clear();

                        gamePane.getChildren().add(hangManBg2());
                    }

                });
                pause.play();
            } else {
                clickedButton.setStyle("-fx-background-color: red;");
                atLeft--;
                if (atLeft <= 0) {
                    check = true;
                    gamePane.getChildren().clear();
                    Font fontX = Font.font("Times New Roman", FontWeight.BOLD, 24);
                    Text loss = new Text("YOU LOSE!");
                    loss.setFill(Color.RED); // Thiết lập màu sắc của text là đỏ
                    loss.setFont(fontX);

// Đặt text ở giữa gamePane
                    loss.setX(gamePane.getWidth() / 2 - loss.getLayoutBounds().getWidth() / 2);
                    loss.setY(0); // Bắt đầu từ đỉnh gamePane

                    gamePane.getChildren().add(hangManBg2());
                    gamePane.getChildren().add(loss);

// Tạo hiệu ứng di chuyển
                    TranslateTransition tt = new TranslateTransition(Duration.seconds(2), loss);
                    tt.setToY(gamePane.getHeight() / 2 - loss.getLayoutBounds().getHeight() / 2);
                    tt.play();
                } else {
                    gamePane.getChildren().add(hangManBg());

                }

            }
        }
    }

    // Phương thức tạo đối tượng ButtonClickHandler và truyền giá trị curLevel từ lớp X
    public EventHandler<ActionEvent> createButtonClickHandler(String targetTitle) {
        return new ButtonClickHandler(targetTitle);
    }

    // Phương thức thiết lập giá trị cho biến curLevel
    public void setLevel(int curLevel) {
        this.curLevel = curLevel;
    }

    public StringBuilder generateWordDisplay(String wordToGuess, int i) {
        StringBuilder wordDisplay = new StringBuilder("_".repeat(wordToGuess.length()));
        for (int j = 0; j < i; j++) {
            wordDisplay.setCharAt(j, wordToGuess.charAt(j));
        }
        return wordDisplay;
    }
}
