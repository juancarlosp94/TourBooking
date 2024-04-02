package starter.step;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import starter.Page.CrearClientePage;
import starter.Page.HomePage;

public class CrearClienteSteps extends PageObject {

    HomePage homePage;
    CrearClientePage crearClientePage;

    @Step
    public void ingresoCrearUsuario() throws InterruptedException {
        homePage.ingresoCrearUsuario();
    }
    @Step
    public void IngresoInfoCrearUsuario(String string, String string2, String string3, String string4, String string5, String string6) throws InterruptedException {
        crearClientePage.IngresoInfoCrearUsuario(string,string2,string3,string4,string5,string6);
        Thread.sleep(1000);
    }
    @Step
    public void clickOpcion() throws InterruptedException {
        crearClientePage.clickOpcion();
        Thread.sleep(10000);
    }
}
