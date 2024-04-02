package starter.step;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import starter.Page.HomePage;

import static net.thucydides.core.environment.SystemEnvironmentVariables.createEnvironmentVariables;

public class HomeTourBookingSteps extends PageObject {

    HomePage homePage;
    @Step
    public void abrirUrl() throws InterruptedException {

        String environment = createEnvironmentVariables().getProperty("environment");

        if (environment.equals("local2")){

            openUrl("http://localhost:3000/");
            Thread.sleep(1000);
        } else if (environment.equals("local3")){

            openUrl("http://equipo5-tourbooking.s3-website.us-east-2.amazonaws.com/");
            Thread.sleep(1000);
        }

    }
    @Step
    public void validacionLogo() throws InterruptedException {
        homePage.validacionLogo();
    }
    @Step
    public void validacionHome() throws InterruptedException {
        homePage.validacionHome();
    }
    @Step
    public void seleccionFooter() throws InterruptedException {
        homePage.seleccionFooter();
    }
    @Step
    public void validacionFooter() throws InterruptedException {
        homePage.validacionFooter();
    }

}
