package starter.step;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import starter.Page.ListarUsuarioPage;
import starter.Page.PanelAdminPage;

public class ListarUsuarioSteps extends PageObject {

    PanelAdminPage panelAdminPage;
    ListarUsuarioPage listarUsuarioPage;
    @Step
    public void seleccionOpcionListarUsuario() throws InterruptedException {
        panelAdminPage.seleccionListarUsuario();
    }
    @Step
    public void validacionModal() throws InterruptedException {
        listarUsuarioPage.validacionModal();
    }
    @Step
    public void validacionListaUsuarios() throws InterruptedException {
        listarUsuarioPage.validacionListaUsuarios();
    }
}
