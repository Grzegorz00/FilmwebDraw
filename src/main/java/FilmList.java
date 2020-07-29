import java.util.ArrayList;

public class FilmList {

    private ArrayList<Film> filmArrayList = new ArrayList();

    //indexes of films in filmArrayList depending on streaming platform
    private ArrayList<Integer> netflixList = new ArrayList<>();
    private ArrayList<Integer> hboList = new ArrayList<>();

    public void addFilm(String title, String stramingPlatform){
        filmArrayList.add(new Film(title,stramingPlatform));

        if(stramingPlatform.equals("Oglądaj na netflix"))
            netflixList.add(filmArrayList.size() - 1);
        if(stramingPlatform.equals("Oglądaj w hbo"))
            hboList.add(filmArrayList.size() - 1);
    }

    public Film getRandomFilm(String platform){
        int randomIndex;
        if(platform.equals("Oglądaj na netflix")) {
            randomIndex = (int) (Math.random() * netflixList.size());
            return filmArrayList.get(netflixList.get(randomIndex));
        }else if(platform.equals("Oglądaj w hbo")){
            randomIndex = (int)(Math.random() * hboList.size());
            return filmArrayList.get(hboList.get(randomIndex));
        }
        randomIndex = (int) (Math.random() * filmArrayList.size());
        return filmArrayList.get(randomIndex);
    }

    public void showElements(){
        for (Film film : filmArrayList){
            System.out.println(film.getTitle() + " - " + film.getPlatform());
        }
    }
}
