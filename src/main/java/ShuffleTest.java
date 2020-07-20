import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.util.List;

public class ShuffleTest {


    private static final String loginUrl = "https://www.filmweb.pl/login";
    private static final String clientUrl = "https://www.filmweb.pl/user/Grzesie_002/wantToSee?page=";
    private static final String login = "Grzesie_002";
    private static final String password = "qaztgbwsx11";



    private static final String streamingXPATH = "//div[@class='filmPreview__advertButton']";
    private static final String nameXPATH = "//h2[@class='filmPreview__title']";
    public static void main(String[] args) {

        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        client.getOptions().setUseInsecureSSL(true);

        try {
            HtmlPage loginPage = client.getPage(loginUrl);

            final HtmlForm loginForm = loginPage.getFormByName("form");
            loginForm.getInputByName("j_username").setValueAttribute(login);
            loginForm.getInputByName("j_password").setValueAttribute(password);
            HtmlButton loginButton = (HtmlButton) loginForm.getFirstByXPath("//button[@class='popupForm__button authButton authButton--submit materialForm__submit ']");
            loginButton.click();

            loginPage = client.getPage(clientUrl);
            List<HtmlElement> items = loginPage.getByXPath(nameXPATH);
            if(items.isEmpty()){
                System.out.println("List is empty");
            } else {
                for (HtmlElement element : items){
                    System.out.println(element.asText());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
