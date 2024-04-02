package starter.Page;

import net.serenitybdd.core.pages.PageObject;
import starter.BaseWrapper;

public class ListarProductoPage extends PageObject {

    String modalDeCarga = "return document.querySelector(\"img\")";
    String filtroListarProdusto = "return document.querySelector(\"#root > div > div > div.product-list-container > div.search-bar > input[type=text]\")";
    String tourUno = "return document.querySelector(\"#root > div > div > div.product-list-container > div:nth-child(3) > p:nth-child(2)\")";

    BaseWrapper baseWrapper = new BaseWrapper();
    public void validacionModalDeCarga() throws InterruptedException {
        baseWrapper.FactoryShadowRootObjectVisibility(modalDeCarga);
        Thread.sleep(10000);
    }

    public void validacionTours() throws InterruptedException {
        Thread.sleep(5000);
        baseWrapper.FactoryShadowRootObjectVisibility(filtroListarProdusto);
        baseWrapper.FactoryShadowRootObjectVisibility(tourUno);
        Thread.sleep(2000);
    }
}
