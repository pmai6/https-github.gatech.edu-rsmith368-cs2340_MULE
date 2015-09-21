import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Game {

    private int numberOfPlayers;
    private List<Player> playerArray;
    private String difficulty;
    private String map;
    private int n;
    private static Game mulegame;
    private static int  playerCounter = 0;
    private List<String> colorList = new ArrayList<>(Arrays.asList
            ("PURPLE","GREEN","RED","BLUE"));

    private static boolean isLandSelectionPhase;


    public Game (String adifficulty, int anumPlayers, String amap, int number) {
        difficulty = adifficulty;
        numberOfPlayers = anumPlayers;
        playerArray = new ArrayList<>(anumPlayers);
        n = number;
        map = amap;

    }


    public static void createGame(String difficulty,
                           int numberOfPlayers,
                           String map, int n) {
        mulegame = new Game(difficulty, numberOfPlayers, map, n);
    }

    public static Game getMulegame() {
        return mulegame;
    }

    public static int getPlayerCounter() {
        return playerCounter;
    }

    public static void incrementPlayerCounter() {
        Game.playerCounter++;
    }

    public void addPlayerToArray(Player player) {
        playerArray.add(player);
    }
    public  List getColor() {
        return colorList;
    }

    public int getN() {return n;}

    public void setN(int t) {this.n = t;}

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public List<Player> getPlayerArray() {
        return playerArray;
    }

    public void setPlayerArray(List<Player> playerArray) {
        this.playerArray = playerArray;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public static boolean isLandSelectionPhase() {
        return isLandSelectionPhase;
    }

    public static void setIsLandSelectionPhase(boolean isLandSelect) {
        isLandSelectionPhase = isLandSelect;
    }
}