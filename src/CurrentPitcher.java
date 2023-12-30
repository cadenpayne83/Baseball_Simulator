import java.util.Random;

// Essentially a Player instance in the context of a single ongoing game.
public class CurrentPitcher {

    private Player associatedPlayer;
    private int pitchesThrown;

    public CurrentPitcher(Player associatedPlayer, int pitchesThrown) {
        this.associatedPlayer = associatedPlayer;
        this.pitchesThrown = pitchesThrown;
    }

    // Returns true for strikes, false for balls.
    public boolean throwPitch() {
        Random strikeOrBallChance = new Random();
        int strikeOrBall = strikeOrBallChance.nextInt(3); // 1 to 2

        return strikeOrBall != 1;
    }



}
