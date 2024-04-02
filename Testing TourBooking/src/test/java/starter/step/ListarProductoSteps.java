package starter.step;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import starter.Page.ListarProductoPage;
import starter.Page.PanelAdminPage;

public class ListarProductoSteps extends PageObject {

    PanelAdminPage panelAdminPage;
    ListarProductoPage listarProductoPage;
    @Step
    public void seleccionOpcionListarProducto() throws InterruptedException {
        panelAdminPage.seleccionOpcionListarProducto();
    }
    @Step
    public void validacionModalDeCarga() throws InterruptedException {
        listarProductoPage.validacionModalDeCarga();
    }
    @Step
    public void validacionTours() throws InterruptedException {
        listarProductoPage.validacionTours();
    }
}
