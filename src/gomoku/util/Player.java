package gomoku.util;

/**
 * Created by Allen on 2014/11/14.
 */
public enum Player {

    PLAYER_A (1),
    PLAYER_B (2);

    private int id;

    Player(int id) {
        this.id = id;
    }   //Player()

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
