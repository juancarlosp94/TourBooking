package starter.step;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import starter.BaseWrapper;
import starter.Page.PanelAdminPage;

public class PanelAdminSteps extends PageObject {

    PanelAdminPage panelAdminPage;
    BaseWrapper baseWrapper = new BaseWrapper();
    @Step
    public void validacionOpcionesAdmin() throws InterruptedException {
        panelAdminPage.seleccionOpcionAdmin();
        panelAdminPage.validacionOpcionesAdmin();
    }
}
