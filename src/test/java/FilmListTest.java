import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FilmListTest {

    @Test
    public void shouldGetRandomFilm(){
        FilmList testList = new FilmList();
        testList.addFilm("TestName1", "TestPlatform");
        testList.addFilm("TestName2", "Oglądaj na netflix");
        testList.addFilm("TestName3", "Oglądaj w hbo");
        assertEquals("TestName1",testList.getRandomFilm("").getTitle());
        assertEquals("TestName2",testList.getRandomFilm("Oglądaj na netflix").getTitle());
        assertEquals("TestName3",testList.getRandomFilm("Oglądaj w hbo").getTitle());
    }
}
