import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    void shouldRegisterPlayers() {
        Game game = new Game();
        Player player1 = new Player(1, "Alice", 10);
        Player player2 = new Player(2, "Bob", 15);

        game.register(player1);
        game.register(player2);

        assertDoesNotThrow(() -> game.round("Alice", "Bob"));
    }

    @Test
    void shouldThrowExceptionWhenPlayerNotRegistered() {
        Game game = new Game();
        game.register(new Player(1, "Alice", 10));

        assertThrows(NotRegisteredException.class, () -> game.round("Alice", "Charlie"));
    }

    @Test
    void shouldThrowExceptionWhenMultiplePlayersWithSameName() {
        Game game = new Game();
        game.register(new Player(1, "Alice", 10));
        game.register(new Player(2, "Alice", 15));

        assertThrows(NotRegisteredException.class, () -> game.round("Alice", "Bob"));
    }

    @Test
    void shouldReturn1WhenFirstPlayerWins() {
        Game game = new Game();
        game.register(new Player(1, "Alice", 20));
        game.register(new Player(2, "Bob", 15));

        assertEquals(1, game.round("Alice", "Bob"));
    }

    @Test
    void shouldReturn2WhenSecondPlayerWins() {
        Game game = new Game();
        game.register(new Player(1, "Alice", 15));
        game.register(new Player(2, "Bob", 20));

        assertEquals(2, game.round("Alice", "Bob"));
    }

    @Test
    void shouldReturn0WhenDraw() {
        Game game = new Game();
        game.register(new Player(1, "Alice", 20));
        game.register(new Player(2, "Bob", 20));

        assertEquals(0, game.round("Alice", "Bob"));
    }

    @Test
    void shouldThrowExceptionWhenBothPlayersNotRegistered() {
        Game game = new Game();
        assertThrows(NotRegisteredException.class, () -> game.round("Alice", "Bob"));
    }

    @Test
    void shouldThrowExceptionForDuplicateNamesInRound() {
        Game game = new Game();
        game.register(new Player(1, "Alice", 10));
        game.register(new Player(2, "Alice", 15));

        assertThrows(NotRegisteredException.class, () -> game.round("Alice", "Alice"));
    }
}