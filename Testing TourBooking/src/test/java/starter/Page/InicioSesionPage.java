package starter.Page;

import net.serenitybdd.core.pages.PageObject;
import starter.BaseWrapper;

public class InicioSesionPage extends PageObject {

    String correo = "return document.querySelector(\"#username\")";
    String contraseña = "return document.querySelector(\"#password\")";
    String btoIniciarSesion = "return document.querySelector(\"#root > div > div > div > div > div.login-container-right > form > button\")";


    BaseWrapper baseWrapper = new BaseWrapper();

    public void ingrese_la_informacion_del_correo_contrasena(String string, String string1) throws InterruptedException {
        baseWrapper.FactoryShadowRootSendKeys(correo,string);
        Thread.sleep(1000);
        baseWrapper.FactoryShadowRootSendKeys(contraseña,string1);
        Thread.sleep(1000);
    }

    public void clickOpcionIniciosesion() throws InterruptedException {
        baseWrapper.ClickShadowRootObject(btoIniciarSesion);
        Thread.sleep(1000);
    }
}
