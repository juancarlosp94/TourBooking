package starter.step;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import starter.Page.CrearTourPage;
import starter.Page.PanelAdminPage;

public class CrearTourSteps extends PageObject {

    PanelAdminPage panelAdminPage;
    CrearTourPage crearTourPage;
    @Step
    public void seleccionDeOpcionCT() throws InterruptedException {
        panelAdminPage.PanelAdminPage();
    }

    @Step
    public void ValidacionOpcionCT() throws InterruptedException {
        crearTourPage.ValidacionOpcionCT();
    }

    @Step
    public void ingresoInfoCT(String string, String string2, String string3, String string4, String string5, String string6) throws InterruptedException {
        crearTourPage.ingresoInfoCT(string,string2,string3,string4,string5,string6);
    }
    @Step
    public void clickOpcionGuardar() throws InterruptedException {
        crearTourPage.clickOpcionGuardar();
    }
    @Step
    public void notificacionCreacion() throws InterruptedException {
        crearTourPage.notificacionCreacion();
    }
}
