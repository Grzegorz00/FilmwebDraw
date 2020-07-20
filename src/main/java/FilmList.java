import java.util.ArrayList;

public class FilmList {

    private ArrayList<Film> filmArrayList = new ArrayList<Film>();

    public void addFilm(Film film){
        filmArrayList.add(film);
    }

    public Film getRandomFilm(String platform){
        int randomIndex = (int)(Math.random() * filmArrayList.size());

        // in case the video is not available on the selected platform, loop searches for new random index
        while (!platform.equals(filmArrayList.get(randomIndex).getPlatform())){
            randomIndex = (int)(Math.random() * filmArrayList.size());
        }
        return filmArrayList.get(randomIndex);
    }
}
