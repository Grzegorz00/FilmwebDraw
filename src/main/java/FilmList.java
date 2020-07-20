import java.util.ArrayList;

public class FilmList {

    private ArrayList<Film> filmArrayList = new ArrayList<>();

    public void addFilm(String title, String stramingPlatform){
        filmArrayList.add(new Film(title,stramingPlatform));
    }

    public Film getRandomFilm(String platform){
        int randomIndex = (int)(Math.random() * filmArrayList.size());

        // in case the video is not available on the selected platform, loop searches for new random index
        while (!platform.equals(filmArrayList.get(randomIndex).getPlatform())){
            randomIndex = (int)(Math.random() * filmArrayList.size());
        }
        return filmArrayList.get(randomIndex);
    }

    public void showElements(){
        for (Film film : filmArrayList){
            System.out.println(film.getTitle() + " - " + film.getPlatform());
        }
    }
}
