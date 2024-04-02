package starter.Page;

import net.serenitybdd.core.pages.PageObject;
import starter.BaseWrapper;

public class CrearClientePage extends PageObject {

    String imputNombreUsuario = "return document.querySelector(\"#root > div > div > div.registro-usuario-container-right > form > div:nth-child(1) > input[type=text]\")";
    String imputNombre = "return document.querySelector(\"#root > div > div > div.registro-usuario-container-right > form > div:nth-child(2) > input[type=text]\")";
    String imputApellido ="return document.querySelector(\"#root > div > div > div.registro-usuario-container-right > form > div:nth-child(3) > input[type=text]\")";
    String imputPaisOrigen = "return document.querySelector(\"#root > div > div > div.registro-usuario-container-right > form > div:nth-child(4) > input[type=text]\")";
    String imputEmail = "return document.querySelector(\"#root > div > div > div.registro-usuario-container-right > form > div:nth-child(5) > input[type=text]\")";
    String imputContrasena = "return document.querySelector(\"#root > div > div > div.registro-usuario-container-right > form > div:nth-child(6) > input[type=password]\")";
    String btoRegistrar = "return document.querySelector(\"#root > div > div > div.registro-usuario-container-right > form > button\")";

    BaseWrapper baseWrapper = new BaseWrapper();
    public void IngresoInfoCrearUsuario(String string, String string2, String string3, String string4, String string5, String string6) throws InterruptedException {
        baseWrapper.FactoryShadowRootSendKeys(imputNombreUsuario,string);
        Thread.sleep(1000);
        baseWrapper.FactoryShadowRootSendKeys(imputNombre,string2);
        Thread.sleep(1000);
        baseWrapper.FactoryShadowRootSendKeys(imputApellido,string3);
        Thread.sleep(1000);
        baseWrapper.FactoryShadowRootSendKeys(imputPaisOrigen,string4);
        Thread.sleep(1000);
        baseWrapper.FactoryShadowRootSendKeys(imputEmail,string5);
        Thread.sleep(1000);
        baseWrapper.FactoryShadowRootSendKeys(imputContrasena,string6);
        Thread.sleep(1000);
        System.out.println("**** Realizo el ingreso de la información de crear usuario ****");
        baseWrapper.ScrollDownPage();
        Thread.sleep(1000);
    }

    public void clickOpcion() throws InterruptedException {
        baseWrapper.FactoryShadowRootObjectVisibility(btoRegistrar);

        String llave1 = "Si Ejecutar";
        String llave2 = "No Ejecutar";

        if (llave2.equals(llave1)){
            baseWrapper.ClickShadowRootObject(btoRegistrar);
            System.out.println("**** Ingreso Al envio de info Crear Cliente ****");
            Thread.sleep(9000);
        }
    }
}
