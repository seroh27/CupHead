package Model;

import Enums.DifficultyLevels;

public class User {
    private String username;
    private String password;
    private Score score = new Score();
    public void setGameTimeEnded(double gameTimeEnded) {
        this.gameTimeEnded = gameTimeEnded;
    }
    private double gameTimeEnded = 0;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getScore(DifficultyLevels difficultyLevels) {
        if (difficultyLevels.message.equals(DifficultyLevels.NORMAL_LEVEL_ONE.message))
            return score.getNormalOne();
        else if (difficultyLevels.message.equals(DifficultyLevels.NORMAL_LEVEL_TWO.message))
            return score.getNormalTwo();
        else if (difficultyLevels.message.equals(DifficultyLevels.NORMAL_LEVEL_THREE.message))
            return score.getNormalThree();
        else
            return score.getDevilMode();
    }
    public void setScore(DifficultyLevels difficultyLevels, double score) {
        if (difficultyLevels.message.equals(DifficultyLevels.NORMAL_LEVEL_ONE.message))
            this.score.setNormalOne(score);
        else if (difficultyLevels.message.equals(DifficultyLevels.NORMAL_LEVEL_TWO.message))
            this.score.setNormalTwo(score);
        else if (difficultyLevels.message.equals(DifficultyLevels.NORMAL_LEVEL_THREE.message))
            this.score.setNormalThree(score);
        else
            this.score.setDevilMode(score);
    }
    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        return user.getUsername().equals(this.username) &&
                user.getPassword().equals(this.password);
    }
    public double getGameTimeEnded() {
        return gameTimeEnded;
    }
}

