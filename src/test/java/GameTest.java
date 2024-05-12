import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @BeforeAll
    static void setUp() {
        System.out.println("Тесты запущены");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Тесты выполнены");
    }

    Game game = new Game();
    Player playerOne = new Player(1, "Ivan", 30);
    Player playerSecond = new Player(2, "Anna", 27);
    Player playerThree = new Player(3,"Arseniy", 30);
    Player playerFour = new Player(4, "Eva", 23);

    @Test
    public void shouldOnlyFirstPlayerRegistered() {
        game.register(playerOne);
        game.register(playerSecond);

        assertThrows(NotRegisteredException.class, () -> {
            game.round("Ivan", "Aleks");
        });
    }

    @Test
    public void shouldOnlySecondPlayerRegistered() {
        game.register(playerOne);
        game.register(playerSecond);

        assertThrows(NotRegisteredException.class, () -> {
            game.round("Eric", "Anna");
        });
    }

    @Test
    public void shouldNoRegisteredPlayers() {
        game.register(playerOne);
        game.register(playerSecond);

        assertThrows(NotRegisteredException.class, () -> {
            game.round("Eric", "Petya");
        });
    }

    @Test
    public void shouldDraw() {
        game.register(playerOne);
        game.register(playerThree);

        int expected = 0;
        int actual = game.round("Ivan", "Arseniy");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFirstPlayerWin() {
        game.register(playerOne);
        game.register(playerFour);

        int expected = 1;
        int actual = game.round("Ivan", "Eva");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldSecondPlayerWin() {
        game.register(playerFour);
        game.register(playerSecond);

        int expected = 2;
        int actual = game.round("Eva", "Anna");

        assertEquals(expected, actual);
    }
}