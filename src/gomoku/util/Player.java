package gomoku.util;

/**
 * Created by Allen on 2014/11/14.
 */
public enum Player {

    NO_END (0),
    PLAYER_A (1),
    PLAYER_B (2);

    private int id;

    Player(int id) {
        this.id = id;
    }   //Player()

    public static Player getPlayer(int id) {
        if (id == 1) return Player.PLAYER_A;
        else if (id == 2) return PLAYER_B;
        else return Player.NO_END;
    }   //getPlayer()

    /**
     *
     * @return the player for next round.
     */
    public Player switchPlayer() {
        if (this.id == 1)
            return Player.PLAYER_B;
        return Player.PLAYER_A;
    }   //switchPlayer()

    public int getId() { return id; }   //getId();
}   //Player
