package Controllers;

import dictionaryJava.DictionaryManagement;
import dictionaryJava.HangmanGame;
import dictionaryJava.Word;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GameControllers extends HangmanGame {
    private int curLevel = 1;
    private int atLeft = 6;
    private boolean check = false;
    private boolean moveRight = true;
    private int fitSize = 100;
    private int animationX = 400;
    private int animationY = -85;
    private Pane gamePane;
    private DictionaryManagement dm = new DictionaryManagement();
    private List<Word> words = dm.getWords();
    private int i = 10;
    private ImageView animationOne = new ImageView();
    private ImageView animationTwo = new ImageView();
    private ImageView animationThree = new ImageView();

    public boolean isCheck() {
        if (atLeft <= 0) {
            check = true;
        }
        return check;
    }

    public GameControllers(Pane gamePane) {
//        dm = new DictionaryManagement();
        this.gamePane = gamePane;
//        this.words = dm.getWords();
    }

    private String[] english = {"Apple",
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
    private String[] vietNamEse = {"Táo", "Voi", "Hổ", "Thỏ", "Tivi", "Ổ", "Bàn"};
    private String[] resWord = {
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

        // Vẽ hình tròn (O) bằng Circle
        Circle head = new Circle(70, 35, 20);
        head.setFill(Color.WHITE);  // Đặt màu nền là trắng

        head.setStroke(Color.rgb(87,87,87)); // Đặt màu viền là đen

        head.setStrokeWidth(4);     // Đặt độ dày viền là 2 pixel

        // Vẽ nhân vật đứng (|)
        Line body = new Line(70, 55, 70, 135);

        body.setStroke(Color.rgb(87,87,87));
        body.setStrokeWidth(4);

        Line leftHand = new Line(70,70, 44, 112);
        leftHand.setStroke(Color.rgb(87,87,87));
        leftHand.setStrokeWidth(4);

        Line leftLeg = new Line(70,135,44,177);
        leftLeg.setStroke(Color.rgb(87,87,87));
        leftLeg.setStrokeWidth(4);

        Line rightHand = new Line(70,70,96,112);
        rightHand.setStroke(Color.rgb(87,87,87));
        rightHand.setStrokeWidth(4);

        Line rightLeg = new Line(70,135,96,177);
        rightLeg.setStroke(Color.rgb(87,87,87));
        rightLeg.setStrokeWidth(4);

        Color blue = Color.rgb(108, 65, 65);
        Line rope = new Line(8,8,70,67);
        rope.setStroke(blue);
        rope.setStrokeWidth(5);

        // Thêm các hình đã vẽ vào Group
        if (atLeft == 6) {

        } else if (atLeft == 5) {
            group.getChildren().addAll(head);
        } else if (atLeft == 4) {
            group.getChildren().addAll(body);
        } else if (atLeft == 3) {
            group.getChildren().addAll(leftHand, rightHand);
        } else if (atLeft == 2) {
            group.getChildren().addAll(leftLeg, rightLeg);
        } else if (atLeft == 1) {
            group.getChildren().addAll(rope);
        }

        return group;
    }

    public Group hangManBg2() {
        Group group = new Group();
        Group gr1 = new Group();

        // Vẽ hình tròn (O) bằng Circle
        Circle head = new Circle(70, 35, 20);
        head.setFill(Color.WHITE);  // Đặt màu nền là trắng

        head.setStroke(Color.rgb(87, 87, 87)); // Đặt màu viền là đen

        head.setStrokeWidth(4);     // Đặt độ dày viền là 2 pixel

        // Vẽ nhân vật đứng (|)
        Line body = new Line(70, 55, 70, 135);
        body.setStroke(Color.rgb(87, 87, 87));
        body.setStrokeWidth(4);

        Line leftHand = new Line(70, 70, 44, 112);
        leftHand.setStroke(Color.rgb(87, 87, 87));
        leftHand.setStrokeWidth(4);

        Line leftLeg = new Line(70, 135, 44, 177);
        leftLeg.setStroke(Color.rgb(87, 87, 87));
        leftLeg.setStrokeWidth(4);

        Line rightHand = new Line(70, 70, 96, 112);
        rightHand.setStroke(Color.rgb(87, 87, 87));
        rightHand.setStrokeWidth(4);

        Line rightLeg = new Line(70, 135, 96, 177);
        rightLeg.setStroke(Color.rgb(87, 87, 87));
        rightLeg.setStrokeWidth(4);

        Color blue = Color.rgb(108, 65, 65);
        Line rope = new Line(8, 8, 70, 67);

        rope.setStroke(blue);
        rope.setStrokeWidth(4);

        TextField leftTurn = new TextField();
        Font font = Font.font("r0c0i Linotte", FontWeight.NORMAL, 18);
        leftTurn.setText("Attempts Left: " + Integer.toString(atLeft));
        leftTurn.setAlignment(Pos.CENTER);
        leftTurn.setLayoutX(260);
        leftTurn.setLayoutY(15);
        leftTurn.setFont(font);

        leftTurn.setStyle("-fx-background-color:#efefef;" +
                "-fx-background-radius: 10");

        // Thêm các hình đã vẽ vào Group
        if (atLeft == 6) {
            group.getChildren().add(leftTurn);
        } else if (atLeft == 5) {
            group.getChildren().remove(leftTurn);
            group.getChildren().addAll(head, leftTurn);
        } else if (atLeft == 4) {
            group.getChildren().remove(leftTurn);
            group.getChildren().addAll(head, body, leftTurn);
        } else if (atLeft == 3) {
            group.getChildren().remove(leftTurn);
            group.getChildren().addAll(head, body, leftHand, rightHand, leftTurn);
        } else if (atLeft == 2) {
            group.getChildren().remove(leftTurn);
            group.getChildren().addAll(head, body, leftHand, rightHand, leftLeg, rightLeg, leftTurn);
        } else if (atLeft == 1) {
            group.getChildren().remove(leftTurn);
            group.getChildren().addAll(head, body, leftHand, rightHand, leftLeg, rightLeg, rope, leftTurn);
        } else if (atLeft == 0) {
            gr1.getChildren().addAll(head, body, leftHand, rightHand, leftLeg, rightLeg);
            group.getChildren().addAll(gr1, rope);


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

        StringBuilder list = generateWordDisplay(resWord[curLevel - 1], 1);
        // Tạo một đối tượng Font với kích thước và kiểu chữ mới
        Font font = Font.font("r0c0i Linotte", FontWeight.SEMI_BOLD, 18);
        Text res = new Text(list.toString());
        res.setLayoutX(39);
        res.setLayoutY(270);
        res.setFont(font);
        res.setFill(Color.rgb(42, 42, 42));


//        leftTurn.setFont(font);

        int x = list.toString().length();
        Text size = new Text(Integer.toString(x));
        size.setLayoutX(135);
        size.setLayoutY(270);
        size.setFont(font);
        size.setFill(Color.rgb(42, 42, 42));

//        Text left = new Text(Integer.toString(atLeft));
//        left.setLayoutX(160);
//        left.setLayoutY(15);
//        left.setFont(font);


        Rectangle houseText = new Rectangle(130, 30);

        houseText.setLayoutX(30);
        houseText.setLayoutY(250);
        houseText.setFill(Color.rgb(238, 238, 238));
        houseText.setArcWidth(20);
        houseText.setArcHeight(20);

        String ButtonStyle = "-fx-background-color: #dadada;" +
                "-fx-background-radius: 20;";
        String hoverButtonStyle = "-fx-background-color: #7e7e7e;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-background-radius: 20;";

        Button resOne = new Button();
        resOne.setLayoutX(260);
        resOne.setLayoutY(100);
        resOne.setFont(font);
        resOne.setStyle(ButtonStyle);
        resOne.setOnMouseEntered(e -> resOne.setStyle(hoverButtonStyle));
        resOne.setOnMouseExited(e -> resOne.setStyle(ButtonStyle));


        Button resTwo = new Button();
        resTwo.setLayoutX(260);
        resTwo.setLayoutY(150);
        resTwo.setFont(font);
        resTwo.setStyle(ButtonStyle);
        resTwo.setOnMouseEntered(e -> resTwo.setStyle(hoverButtonStyle));
        resTwo.setOnMouseExited(e -> resTwo.setStyle(ButtonStyle));

        Button resThree = new Button();
        resThree.setLayoutX(390);
        resThree.setLayoutY(100);
        resThree.setFont(font);
        resThree.setStyle(ButtonStyle);
        resThree.setOnMouseEntered(e -> resThree.setStyle(hoverButtonStyle));
        resThree.setOnMouseExited(e -> resThree.setStyle(ButtonStyle));

        Button resFour = new Button();
        resFour.setLayoutX(390);
        resFour.setLayoutY(150);
        resFour.setFont(font);
        resFour.setStyle(ButtonStyle);
        resFour.setOnMouseEntered(e -> resFour.setStyle(hoverButtonStyle));
        resFour.setOnMouseExited(e -> resFour.setStyle(ButtonStyle));

        buttons.add(resOne);
        buttons.add(resTwo);
        buttons.add(resThree);
        buttons.add(resFour);

        setTextButton(buttons, curLevel);
        for (Button y : buttons) {
            y.setOnAction(new ButtonClickHandler(resWord[curLevel - 1]));
        }

        gr.getChildren().addAll(houseText, res, size, resOne, resTwo, resThree, resFour);
        return gr;
    }

    public void setTextButton(List<Button> buttons, int level) {
        for (int i = 0; i < 4; i++) {
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

    public void play2() {
        String path1 = "file:/C:/OOP/GR8_dictionary/dictionaryOOP_GR8/Dictionary/src/main/resources/assets/1.png";
        String path2 = "file:/C:/OOP/GR8_dictionary/dictionaryOOP_GR8/Dictionary/src/main/resources/assets/2.png";
        String path3 = "file:/C:/OOP/GR8_dictionary/dictionaryOOP_GR8/Dictionary/src/main/resources/assets/3.png";

//        animationOne = upLoad(animationOne,path1,fitSize, fitSize,animationX, animationY);
        Text x = new Text();
        x.setLayoutX(185);
        x.setLayoutY(-20);

        Image image = new Image(path1);
        animationOne.setImage(image);
        animationOne.setFitWidth(fitSize);
        animationOne.setFitHeight(fitSize);
        animationOne.setLayoutX(animationX);
        animationOne.setLayoutY(animationY);

        Image image2 = new Image(path2);
        animationTwo.setImage(image2);
        animationTwo.setFitWidth(fitSize);
        animationTwo.setFitHeight(fitSize);
        animationTwo.setLayoutX(animationX);
        animationTwo.setLayoutY(animationY);
//        animationTwo = upLoad(animationTwo,path2,fitSize, fitSize,animationX, animationY);

        Image image3 = new Image(path3);
        animationThree.setImage(image3);
        animationThree.setFitWidth(fitSize);
        animationThree.setFitHeight(fitSize);
        animationThree.setLayoutX(animationX);
        animationThree.setLayoutY(animationY);
//        animationThree = upLoad(animationThree,path3,fitSize, fitSize,animationX, animationY);

        gamePane.getChildren().addAll(animationOne, animationTwo, animationThree, x);

        // Tạo KeyFrame
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0), e -> {
            playFadeAnimation(animationOne);
            animationTwo.setOpacity(0.0);
            animationThree.setOpacity(0.0);
        });
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(4), e -> {
            playFadeAnimation(animationTwo);
            animationOne.setOpacity(0.0);
            animationThree.setOpacity(0.0);
        });
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(8), e -> {
            playFadeAnimation(animationThree);
            animationOne.setOpacity(0.0);
            animationTwo.setOpacity(0.0);
        });

        // Gán giá trị cho tl
        Timeline tl = new Timeline(keyFrame1, keyFrame2, keyFrame3);

        // Tạo PauseTransition để đặt lại thời gian của tl sau khi nó kết thúc
        PauseTransition pt = new PauseTransition(Duration.seconds(12));
        pt.setOnFinished(e -> {
            Word w = words.get(i);
            String target = w.getWordTarget();
            String explain = w.getWordExplain();
            String res = target + " : " + getString(explain);
            x.setText(res);
            tl.playFromStart();
            pt.playFromStart();
            i = i + 20;

            // Kiểm tra nếu danh sách words không chứa đủ số lượng từ cần thiết
            if (i >= words.size()) {
                // Nếu không đủ từ, có thể thông báo hoặc thực hiện hành động khác
                System.err.println("Không đủ từ trong danh sách.");
                return;
            }

//            x.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
//                double lastCharacterX = x.getLayoutX() + newValue.getMaxX();
//                // Đặt lại layoutX để đặt Text theo vị trí cuối cùng của nó
//                x.setLayoutX(x.getLayoutX() - lastCharacterX);
//            });

            TranslateTransition tt = new TranslateTransition(Duration.seconds(11.5), x);
            tt.setFromX(185);
            tt.setToX(-gamePane.getWidth() + 315);
            tt.play();

        });


        // Bắt đầu Timeline và PauseTransition
        tl.play();
        pt.play();
    }

    //    private ImageView setUpImageAndTransition(ImageView imageView, String imagePath) {
//        imageView = upLoad(imageView, imagePath, fitSize, fitSize, animationX, animationY);
//        return imageView;
//    }
    private ImageView upLoad(ImageView imageView, String imagePath, int fitSW, int fitSH, int layoutX, int layoutY) {
        Image image = new Image(imagePath);
        imageView.setImage(image);
        imageView.setFitWidth(fitSW);
        imageView.setFitHeight(fitSH);
        imageView.setLayoutX(layoutX);
        imageView.setLayoutY(layoutY);

        return imageView;
    }

    private void playFadeAnimation(ImageView imageView) {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), imageView);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(2.0);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), imageView);
        fadeOut.setFromValue(2.0);
        fadeOut.setToValue(0.0);

        fadeIn.setOnFinished(event -> fadeOut.play());

        fadeIn.play();
    }
    private  String getString(String s) {
        String res = "";
        int sSize = s.length();
        for ( int i = 0; i < sSize; i++) {
            if ( s.charAt(i) == '-') {
                for (int j = i+1; j < sSize; j ++) {
                   res += s.charAt(j);
                }
                break;
            }

        }
        return res;
    }

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
                clickedButton.setStyle("-fx-background-color: #408a40;"
                        + "-fx-background-radius: 20;");
                // Tạo một PauseTransition để chờ trong 2 giây
                Text x = new Text(vietNamEse[curLevel - 1]);

                Font font = Font.font("r0c0i Linotte", FontWeight.BOLD, 20);
                x.setLayoutX(347);
                x.setLayoutY(270);
                x.setFont(font);


                gamePane.getChildren().add(x);
                PauseTransition pause = new PauseTransition(Duration.seconds(2.5));
                pause.setOnFinished(e -> {
                    // Sau khi chờ, thực hiện các thay đổi
                    curLevel++;
//                    gamePane.getChildren().clear();
                    if (atLeft > 0) {
                        play(gamePane);
                    } else {
                        gamePane.getChildren().clear();

                        gamePane.getChildren().add(hangManBg2());
                    }

                });
                pause.play();
            } else {
                clickedButton.setStyle("-fx-background-color: #8d4f4f;" +
                        "-fx-background-radius: 20;");
                atLeft--;
                if (atLeft <= 0) {
                    check = true;
                    gamePane.getChildren().clear();
                    Font fontX = Font.font("r0c0i Linotte", FontWeight.BOLD, 24);
                    Text loss = new Text("YOU LOSE!");
                    loss.setFill(Color.rgb(180, 76, 63)); // Thiết lập màu sắc của text là đỏ
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
