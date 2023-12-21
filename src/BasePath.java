import java.util.LinkedList;

public class BasePath {

    public Base firstBase;
    public Base secondBase;
    public Base thirdBase;
    private LinkedList<Base> basePath;

    public class Base {

        private Player playerOnBase;

        public Base(Player player) {
            this.playerOnBase = player;
        }

        public void addPlayer(Player player) {
            this.playerOnBase = player;
        }

        public boolean isOccupied() {
            return this.playerOnBase != null;
        }
    }

    public BasePath() {
        this.firstBase = new Base(null);
        this.secondBase = new Base(null);
        this.thirdBase = new Base(null);

        this.basePath = new LinkedList<>();
        basePath.add(firstBase);
        basePath.add(secondBase);
        basePath.add(thirdBase);
    }

    public LinkedList<Base> getBasePathLinkedList() {
        return this.basePath;
    }
}
