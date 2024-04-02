package starter.step;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import starter.Page.HomePage;
import starter.Page.HomeUsuarioPage;
import starter.Page.InicioSesionPage;

public class InicioSesionTourBookingSteps extends PageObject {

    HomePage homePage;
    InicioSesionPage inicioSesionPage;
    HomeUsuarioPage homeUsuarioPage;
    @Step
    public void ingresoOpcionIncioSesion() throws InterruptedException {
        homePage.ingresoOpcionIncioSesion();
    }
    @Step
    public void ingrese_la_informacion_del_correo_contrasena(String string, String string1) throws InterruptedException {
        inicioSesionPage.ingrese_la_informacion_del_correo_contrasena(string,string1);
    }
    @Step
    public void clickOpcionIniciosesion() throws InterruptedException {
        inicioSesionPage.clickOpcionIniciosesion();
        Thread.sleep(5000);
    }

    @Step
    public void validacionIngresoExitosoAdmin()throws InterruptedException {
        homePage.validacionIngresoExitosoAdmin();
    }
    @Step
    public void validacionErrorIniciosesion() throws InterruptedException {
        homePage.validacionErrorIniciosesion();
    }
    @Step
    public void validacionIngresoExitosoNormal() throws InterruptedException {
        homePage.validacionIngresoExitosoNormal();
    }
}
