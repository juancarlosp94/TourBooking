package starter.step;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import starter.BaseWrapper;
import starter.Page.HomeUsuarioPage;

public class DestinosSteps extends PageObject {

    HomeUsuarioPage homeUsuarioPage;
    BaseWrapper baseWrapper = new BaseWrapper();
    @Step
    public void validacionHomeUserAunteticado() throws InterruptedException {
        homeUsuarioPage.validacionHomeUserAunteticado();
    }
    @Step
    public void seleccionCategoria(String string) throws InterruptedException {
        baseWrapper.Scroll(700);
        homeUsuarioPage.seleccionCategoria(string);
    }
    @Step
    public void validacionDestinos() throws InterruptedException {
        Thread.sleep(1000);
        homeUsuarioPage.validacionDestinos();
    }
}
