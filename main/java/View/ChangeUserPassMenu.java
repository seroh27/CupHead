package View;

import Application.App;
import Controller.Exceptions.EntryNullException;
import Controller.Exceptions.NoLoggedInUserException;
import Controller.Exceptions.RepetitiveUsernameException;
import Enums.Menus;
import Enums.MessageLog;
import Model.UserDataBase;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeUserPassMenu implements Initializable {
    @FXML
    public TextField newUsername;
    @FXML
    public TextField newPassword;
    @FXML
    public Label error;
    public Button submit;
    public Label successLabel;
    public AnchorPane pane;
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
    public void newUserPass(ActionEvent actionEvent) {
        if(changeUserPassErrors())return;
        changeUserPass();
    }
    public boolean changeUserPassErrors(){
        try {
            checkUserPass();
        }catch (Exception e){
            successLabel.setVisible(false);
            error.setVisible(true);
            error.setText(e.getMessage());
            return true;
        }
        return false;
    }
    public void changeUserPass(){
        UserDataBase.getInstance().getLoginUser().setUsername(newUsername.getText());
        UserDataBase.getInstance().getLoginUser().setPassword(newPassword.getText());
        error.setVisible(false);
        successLabel.setVisible(true);
        successLabel.setText(MessageLog.SUCCESSFULLY_CHANGED.message);
    }
    public void checkUserPass() throws EntryNullException, RepetitiveUsernameException, NoLoggedInUserException {
        if(UserDataBase.getInstance().getLoginUser() == null)
            throw new NoLoggedInUserException();
        if(newUsername.getText().length() == 0 || newPassword.getText().length() == 0) {
            throw new EntryNullException();
        }else if(UserDataBase.getInstance().getLoginUser().getUsername().equals(newUsername.getText())) {
            throw new RepetitiveUsernameException();
        }
    }
    public void back() {
        App.changeMenu(Menus.MAIN_MENU.value);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getTimeline().play();
    }
}
