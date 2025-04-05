import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players = new ArrayList<>();

    public void register(Player player) {
        players.add(player);
    }

    public int round(String playerName1, String playerName2) {
        Player p1 = findPlayer(playerName1);
        Player p2 = findPlayer(playerName2);

        if (p1 == null || p2 == null) {
            throw new NotRegisteredException("Один или оба игрока не зарегистрированы");
        }

        return compareStrength(p1, p2);
    }

    private Player findPlayer(String name) {
        List<Player> found = new ArrayList<>();
        for (Player player : players) {
            if (player.getName().equals(name)) {
                found.add(player);
            }
        }
        return found.size() == 1 ? found.get(0) : null;
    }

    private int compareStrength(Player p1, Player p2) {
        if (p1.getStrength() > p2.getStrength()) return 1;
        else if (p1.getStrength() < p2.getStrength()) return 2;
        else return 0;
    }
}