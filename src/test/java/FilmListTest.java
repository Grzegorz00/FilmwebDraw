import org.junit.Assert;
import org.junit.Test;

public class FilmListTest {

    @Test
    public void shouldGetRandomFilm(){
        FilmList testList = new FilmList();
        testList.addFilm("TestName1", "TestPlatform");
        testList.addFilm("TestName2", "Oglądaj na netflix");
        testList.addFilm("TestName3", "Oglądaj w hbo");
        Assert.assertEquals("TestName1",testList.getRandomFilm("").getTitle());
        Assert.assertEquals("TestName2",testList.getRandomFilm("Oglądaj na netflix").getTitle());
        Assert.assertEquals("TestName3",testList.getRandomFilm("Oglądaj w hbo").getTitle());
    }
}
