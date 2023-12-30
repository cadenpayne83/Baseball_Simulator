import java.util.LinkedList;

public class BasePath {

    public GameState gameState;
    public Base firstBase;
    public Base secondBase;
    public Base thirdBase;
    public HomePlate homePlate;
    private LinkedList<Base> basePath;

    public class Base {

        private CurrentBatter playerOnBase;

        public Base(CurrentBatter player) {
            this.playerOnBase = player;
        }

        public void addPlayer(CurrentBatter player) {
            this.playerOnBase = player;
        }

        public void removePlayer() {
            this.playerOnBase = null;
        }

        public boolean isOccupied() {
            return this.playerOnBase != null;
        }

        public CurrentBatter getPlayerOnBase() {
            return this.playerOnBase;
        }

        public boolean isHomePlate() {
            return this instanceof HomePlate;
        }
    }

    public class HomePlate extends Base {

        public HomePlate(CurrentBatter player) {
            super(player);
        }
    }


    public BasePath(GameState gameState) {
        this.gameState = gameState;
        this.firstBase = new Base(null);
        this.secondBase = new Base(null);
        this.thirdBase = new Base(null);
        this.homePlate = new HomePlate(null);

        this.basePath = new LinkedList<>();

        basePath.add(firstBase);
        basePath.add(secondBase);
        basePath.add(thirdBase);
        basePath.add(homePlate);
    }

    public LinkedList<Base> getBasePathLinkedList() {
        return this.basePath;
    }

    public int numberOfRunnersOnBase() {
        int returnVal = 0;
        for (Base base : basePath) {
            if (base.isOccupied()) {
                returnVal += 1;
            }
        }
        return returnVal;
    }

    public void walk(CurrentBatter batter) {
        System.out.println("Walk! Take your base!");
        movePlayer(firstBase);
        firstBase.playerOnBase = batter;
    }

    // Helper method for walking, can probably find some use for it in other methods.
    private void movePlayer(Base currentBase) {
        int currentBaseIndex = getBasePathLinkedList().indexOf(currentBase);
        Base nextBase = basePath.get(currentBaseIndex + 1);
        if (nextBase.isHomePlate()) {
            nextBase.playerOnBase = currentBase.playerOnBase;
            gameState.incrementScore(1);
            System.out.println("Runner scores!");
        } else if (nextBase.isOccupied()) { // home plate is considered "occupied"
            movePlayer(nextBase);
            nextBase.playerOnBase = currentBase.playerOnBase;
            currentBase.removePlayer();
        } else {
            nextBase.playerOnBase = currentBase.playerOnBase;
            currentBase.removePlayer();
        }
    }

    public void single(CurrentBatter batter){
        System.out.println("Single!");
        int thirdBasePos = basePath.size() - 2;
        for (int i = thirdBasePos; i >= 0; i--) {
            Base oldDestination = basePath.get(i);
            Base newDestination = basePath.get(i + 1);
            CurrentBatter runner = oldDestination.getPlayerOnBase();

            // run to next base
            if (newDestination.isHomePlate() && oldDestination.isOccupied()) {
                gameState.incrementScore(1);
                System.out.println("Runner scores!");
                oldDestination.playerOnBase = null;
            } else {
                newDestination.playerOnBase = runner;
                oldDestination.playerOnBase = null;
            }
        }
        firstBase.addPlayer(batter);
    }

    public void double_(CurrentBatter batter){
        System.out.println("Double!");
        int secondBasePos = basePath.size() - 3;

        // runner on third base scores
        if (thirdBase.isOccupied()) {
            thirdBase.playerOnBase = null;
            System.out.println("Runner scores!");
            gameState.incrementScore(1);
        }

        for (int i = secondBasePos; i >= 0; i--) {
            Base oldDestination = basePath.get(i);
            Base newDestination = basePath.get(i + 2);
            CurrentBatter runner = oldDestination.getPlayerOnBase();

            // run to next base
            if (newDestination.isHomePlate() && oldDestination.isOccupied()) {
                gameState.incrementScore(1);
                oldDestination.playerOnBase = null;
            } else {
                newDestination.playerOnBase = runner;
                oldDestination.playerOnBase = null;
            }
        }
        secondBase.addPlayer(batter);
    }

    public void triple(CurrentBatter batter) {
        System.out.println("Triple!");
        // all runners score on a triple
        int runnersOnBase = numberOfRunnersOnBase();
        if (runnersOnBase == 1) {
            System.out.println("Runner scores!");
            gameState.incrementScore(1);
        } else if (runnersOnBase != 0) {
            System.out.println(runnersOnBase + " runners score!");
            gameState.incrementScore(runnersOnBase);
        }


        // clear runners
        for (int i = 0; i < basePath.size() - 1; i++) {
            Base base = basePath.get(i);
            base.removePlayer();
        }

        thirdBase.addPlayer(batter);
    }

    public void homeRun(CurrentBatter batter){
        System.out.println("Home Run!");
        int runnersOnBase = this.numberOfRunnersOnBase();
        gameState.incrementScore(runnersOnBase + 1); // + 1 for current batter
        for (Base base : basePath) {
            base.removePlayer();
        }
    }

    public void announceRunners() {
        boolean thirdBaseIsOccupied = thirdBase.isOccupied();
        boolean secondBaseIsOccupied = secondBase.isOccupied();
        boolean firstBaseIsOccupied = firstBase.isOccupied();

        if (firstBaseIsOccupied && secondBaseIsOccupied && thirdBaseIsOccupied) {
            System.out.println("Bases loaded!");
        } else if (firstBaseIsOccupied && secondBaseIsOccupied && !thirdBaseIsOccupied) {
            System.out.println("Runners on first and second!");
        } else if (firstBaseIsOccupied && !secondBaseIsOccupied && !thirdBaseIsOccupied) {
            System.out.println("Runner on first!");
        } else if (!firstBaseIsOccupied && secondBaseIsOccupied && thirdBaseIsOccupied) {
            System.out.println("Runners on second and third!");
        } else if (firstBaseIsOccupied && !secondBaseIsOccupied && thirdBaseIsOccupied) {
            System.out.println("Runners on first and third!");
        } else if (!firstBaseIsOccupied && !secondBaseIsOccupied && !thirdBaseIsOccupied) {
            System.out.println("No runners on!");
        } else if (!firstBaseIsOccupied && !secondBaseIsOccupied && thirdBaseIsOccupied) {
            System.out.println("Runner on third!");
        } else if (!firstBaseIsOccupied && secondBaseIsOccupied && !thirdBaseIsOccupied) {
            System.out.println("Runner on second!");
        }
        System.out.println();
    }

    public void resetRunners() {
        for (Base base : basePath) {
            base.removePlayer();
        }
    }

}
