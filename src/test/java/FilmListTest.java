import org.junit.Assert;
import org.junit.Test;

public class FilmListTest {

    @Test
    public void shouldGetRandomFilm(){
        FilmList testList = new FilmList();
        testList.addFilm("TestName", "TestPlatform");
        testList.addFilm("TestName2", "Oglądaj na netflix");
        Assert.assertEquals("TestName",testList.getRandomFilm("").getTitle());
        Assert.assertEquals("TestName2",testList.getRandomFilm("Oglądaj na netflix").getTitle());
    }
}
