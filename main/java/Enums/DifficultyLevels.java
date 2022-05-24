package Enums;

public enum DifficultyLevels {
    NORMAL_LEVEL_ONE("Hard difficulty"),
    NORMAL_LEVEL_TWO("Normal difficulty"),
    NORMAL_LEVEL_THREE("Easy difficulty"),
    DEVIL_MODE("Devil Mode"),
    NORMAL_MODE("Normal Mode");
    public final  String message;

    DifficultyLevels(String message) {
        this.message = message;
    }

    public static DifficultyLevels getDifficulty(String str){
        switch (str) {
            case "Hard difficulty":
                return NORMAL_LEVEL_ONE;
            case "Normal difficulty":
                return NORMAL_LEVEL_TWO;
            case "Easy difficulty":
                return NORMAL_LEVEL_THREE;
            default:
                return DEVIL_MODE;
        }
    }
}
