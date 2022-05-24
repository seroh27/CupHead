package View;

import Application.App;
import Enums.Menus;
import javafx.event.ActionEvent;

public class ChooseAvatarPage {


    public void back(ActionEvent actionEvent) {
        App.changeMenu(Menus.PROFILE_MENU.value);
    }
}
