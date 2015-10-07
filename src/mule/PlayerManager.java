package mule;

import mule.model.Mule;
import mule.model.Player;
import mule.model.Store;
import mule.RoundManager;
import mule.StoreManager;
import mule.model.Tile;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class to manage player objects
 */
public class PlayerManager {
    private static GameManager gameManager = GameManager.getGameManager();

    private int startTime;
    private mule.view.timer time = new mule.view.timer(startTime);
    private IntegerProperty timeLeft = time.getTimeLeft();

    public static void createNewGamePlayer(String playerName2,
                                            String selectedRace2,
                                            String selectedColor2) {
        Player newplayer = new Player(playerName2, selectedRace2, selectedColor2);
        gameManager.getMulegame().addPlayerToArray(newplayer);
    }


    /**
     * Method to deduct the an amount of money a player spent on land
     * @param player
     * @param property
     */
    public static void buyProperty (Player player, Tile property) {
        player.setMoney(player.getMoney() - MapManager.costOfTile());
        player.addtile(property);

    }

    /** Stub method
     * Method to deduct the an amount of money a player spent
     * @param player
     * @param item
     * @param quantity
     */
    public static void buyResources (Player player, String item, int quantity) {
        if (item.equalsIgnoreCase("food")) {
            player.setFood(player.getFood() + quantity);
            player.setMoney(player.getMoney() - (Store.getFoodPrice() * quantity));
        } else if (item.equalsIgnoreCase("energy")) {
            player.setEnergy(player.getEnergy() + quantity);
            player.setMoney(player.getMoney() - (Store.getEnergyPrice() * quantity));
        } else if (item.equalsIgnoreCase("smithore")) {
            player.setSmithore(player.getSmithore() + quantity);
            player.setMoney(player.getMoney() - (Store.getSmithorePrice() * quantity));
        } else if (item.equals("crystite")) {
            player.setCrystite(player.getCrystite() + quantity);
            player.setMoney(player.getMoney() - (Store.getCrystitePrice() * quantity));
        }
    }

    public static void buyMule (Player player, Mule mule) throws Exception{
        if (player.getMoney() - mule.getCost() >= 0) {
            player.setMoney(player.getMoney() - mule.getCost());
            Store.setMuleQty(Store.getMuleQty() - 1);
            GameManager.getGameManager().updateStoreData();
            player.setUnplacedMule(mule);
            gameManager.goToMap();
        } else {
            gameManager.notEnoughMoney();
        }


    }
    /**
     * void method - add money to the account of the player
     * @param player - current player
     * @param item - item sold
     * @param quantity - amount sold
     */
    public static void sellResources (Player player, String item ,int quantity) {
        //
        if (item.equalsIgnoreCase("food")) {
            player.setFood(player.getFood() - quantity);
            player.setMoney(player.getMoney() + (Store.getFoodPrice() * quantity));
        } else if (item.equalsIgnoreCase("energy")) {
            player.setEnergy(player.getEnergy() - quantity);
            player.setMoney(player.getMoney() + (Store.getEnergyPrice() * quantity));
        } else if (item.equalsIgnoreCase("smithore")) {
            player.setSmithore(player.getSmithore() - quantity);
            player.setMoney(player.getMoney() + (Store.getSmithoreQty() * quantity));
        } else if (item.equals("crystite")) {
            player.setCrystite(player.getCrystite() - quantity);
            player.setMoney(player.getMoney() + (Store.getCrystitePrice() * quantity));
        }
    }

    /**
     * stub method - player starts his turn
     * @param player - player who has current turn
     * @param startTimer - time in player's turn which is determined by his score
     */
    public void startTurn(Player player, int startTimer) {
        mule.model.Round.getCurrentTurn().setPlayer(player);
        startTime = startTimer;
        time.startTimer();
    }


    public IntegerProperty getTimeLeftProperty() { return timeLeft; } // get time remaining in the
                                                                      // player's turn
    public int getTimeLeft() { return timeLeft.get(); }

    /**
     * stub method - player's turn ends when gambling
     */
    public void gambleInPub () {
        Pub.gamble(RoundManager.getCurrentPlayer(), timeLeft.get(), mule.model.Round.getRoundNum());
        //this method is all that needs to be called to finish a turn
        //the parameter tells you whether the turn is ending b/c a player
        //passed or because of something other than that
        RoundManager.playerFinishedTurn(false);

    }
}
