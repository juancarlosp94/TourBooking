package starter.Page;

import net.serenitybdd.core.pages.PageObject;
import starter.BaseWrapper;

public class ListarUsuarioPage extends PageObject {

    String filtroListarUsuario = "return document.querySelector(\"#root > div > div > div.user-container > div.search-bar > input[type=text]\")";
    String usuarioUno = "return document.querySelector(\"#root > div > div > div.user-container > div:nth-child(3)\")";

    BaseWrapper baseWrapper = new BaseWrapper();
    public void validacionModal() throws InterruptedException {
        Thread.sleep(2000);
    }

    public void validacionListaUsuarios() throws InterruptedException {
        baseWrapper.FactoryShadowRootObjectVisibility(filtroListarUsuario);
        baseWrapper.FactoryShadowRootObjectVisibility(usuarioUno);
        Thread.sleep(2000);
    }
}
