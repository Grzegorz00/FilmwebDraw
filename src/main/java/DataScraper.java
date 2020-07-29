import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.util.List;

public class DataScraper {

    //URLs and user's data
    private static final String loginUrl = "https://www.filmweb.pl/login";
    private static final String clientUrl = "https://www.filmweb.pl/user/Grzesie_002/wantToSee?page=";

    //XPATHs
    private static final String filmBoxXPATH = "//div[@class='filmPreview__card']";
    private static final String filmTitleXPATH = ".//div[@class='filmPreview__titleDetails']/a";
    private static final String streamingPlatformXPATH = ".//div[@class='filmPreview__advertButton']/div/a";


    private WebClient webClient = new WebClient();
    private HtmlPage filmwebPage;
    private FilmList filmList = new FilmList();

    public DataScraper(){
        setWebClient();
        loginToFilmweb();
        scrapFilmBoxes();
    }

    public void setWebClient(){
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setUseInsecureSSL(true);
    }

    public void loginToFilmweb(){

        try {
            filmwebPage = webClient.getPage(loginUrl);
            final HtmlForm loginForm = filmwebPage.getFormByName("form");
            loginForm.getInputByName("j_username").setValueAttribute(TemporaryUserData.getLogin());
            loginForm.getInputByName("j_password").setValueAttribute(TemporaryUserData.getPassword());
            HtmlButton loginButton = loginForm.getFirstByXPath("//button[@class='popupForm__button authButton authButton--submit materialForm__submit ']");
            loginButton.click();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void scrapFilmBoxes(){
        try {
            char pageCounter = '1';
            boolean isFilmwebListEmpty = false;

            do {
                filmwebPage = webClient.getPage(clientUrl + pageCounter++);
                List<HtmlElement> filmBoxList = filmwebPage.getByXPath(filmBoxXPATH);
                
                if (filmBoxList.isEmpty()) {
                    isFilmwebListEmpty = true;
                    if(pageCounter == '1'){
                        System.out.println("There are no films on your account");
                    }
                } else {
                    scrapFilmDataFromBox(filmBoxList);
                }
            } while (!isFilmwebListEmpty);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //testing results
        filmList.showElements();
        System.out.println("------\n" + filmList.getRandomFilm("Oglądaj na netflix").getTitle());
    }

    public void scrapFilmDataFromBox(List<HtmlElement> filmBoxList){
        HtmlAnchor filmTitle;
        HtmlAnchor streamingPlatform;

        for (HtmlElement filmBox : filmBoxList){
            filmTitle = filmBox.getFirstByXPath(filmTitleXPATH);
            streamingPlatform = filmBox.getFirstByXPath(streamingPlatformXPATH);
            filmList.addFilm(filmTitle.asText(), streamingPlatform == null? "" : streamingPlatform.asText());
        }
    }
}
