package View;

import Application.App;
import Enums.DifficultyLevels;
import Enums.Menus;
import Enums.MessageLog;
import Model.User;
import Model.UserDataBase;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ScoreboardPageController implements Initializable {
    @FXML
    public VBox score;
    @FXML
    public VBox nameBoard;
    @FXML
    public VBox rankBoard;

    private final String[] levels = {DifficultyLevels.NORMAL_LEVEL_ONE.message,
            DifficultyLevels.NORMAL_LEVEL_TWO.message,
            DifficultyLevels.NORMAL_LEVEL_THREE.message,
            DifficultyLevels.DEVIL_MODE.message};
    @FXML
    public ChoiceBox<String> choiceBox;
    public AnchorPane pane;
    public VBox time;

    public void back() {
        App.changeMenu(Menus.MAIN_MENU.value);
    }

    public void sortUsersAndShow(ActionEvent actionEvent){
        sortUsers();
        showUsers();
    }


    private void showUsers() {
        for(int i = 1;i < nameBoard.getChildren().size();i++) {
            if(UserDataBase.getInstance().getUsers().size() >= i) {
                ((Label) nameBoard.getChildren().get(i)).setText(createTextName(UserDataBase.getInstance().getUsers().get(UserDataBase.getInstance().getUsers().size() - i)));
                ((Label) score.getChildren().get(i)).setText(createTextScore(UserDataBase.getInstance().getUsers().get(UserDataBase.getInstance().getUsers().size() - i)));
                ((Label) rankBoard.getChildren().get(i)).setText(Integer.toString(i));
                ((Label) time.getChildren().get(i)).setText(Integer.toString ((int)UserDataBase.getInstance().getUsers().get(UserDataBase.getInstance().getUsers().size() - i).getGameTimeEnded()));
            }
            else
                ((Label)nameBoard.getChildren().get(i)).setText(MessageLog.EMPTY.message);
        }
    }
    private String createTextName(User user){
        return  user.getUsername();
    }
    private String createTextScore(User user){
        return  Double.toString(user.getScore(DifficultyLevels.getDifficulty(choiceBox.getValue())));
    }
    private Timeline timeline;
    public Timeline getTimeline(){
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        xPosition.addListener((observable, oldValue, newValue) -> setBackgroundPositions(pane, xPosition.get()));
        timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(200), new KeyValue(xPosition, -15000)));

        return timeline;
    }


    void setBackgroundPositions(Region region, double xPosition) {
        String style = "-fx-background-position: " +
                "left " + xPosition*1 + "px top," +
                "left " + xPosition*1.5 + "px top," +
                "left " + xPosition*1.8 + "px bottom," +
                "left " + xPosition*1.6 + "px bottom," +
                "left " + xPosition*2. + "px bottom," +
                "left " + xPosition*2.3 + "px bottom;" ;
        region.setStyle(style);
    }
    private void sortUsers() {
        UserDataBase.getInstance().getUsers().sort((o1, o2) -> {
            if(Double.compare(o1.getScore(DifficultyLevels.getDifficulty(choiceBox.getValue())), o2.getScore(DifficultyLevels.getDifficulty(choiceBox.getValue()))) == 0) {
                if (o1.getGameTimeEnded() < o2.getGameTimeEnded())
                    return 1;
                if (o1.getGameTimeEnded() == o2.getGameTimeEnded())
                    return 0;
                else
                    return -1;
            }
            else
                return Double.compare(o1.getScore(DifficultyLevels.getDifficulty(choiceBox.getValue())), o2.getScore(DifficultyLevels.getDifficulty(choiceBox.getValue())));
        });
        UserDataBase.getInstance().getUsers().sort((o1, o2) -> Double.compare(o1.getScore(DifficultyLevels.getDifficulty(choiceBox.getValue())), o2.getScore(DifficultyLevels.getDifficulty(choiceBox.getValue()))));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTimeline().play();
        choiceBox.getItems().addAll(levels);
        choiceBox.setOnAction(this::sortUsersAndShow);
    }

}
