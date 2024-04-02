package starter.Page;

import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import starter.BaseWrapper;

public class ReservaTourPage extends PageObject {

    String tourUno = "return document.querySelector(\"#root > div > div > div.bodydestinos > div.container-categorias-column > div:nth-child(1) > a:nth-child(1) > div > div.vermas-card > button\")";
    String nombreTour = "return document.querySelector(\"#root > div > div > div.tour-details-title > div.tour-details-title-left > h1\")";
    String descripcionTour = "return document.querySelector(\"#root > div > div.tour-details > div:nth-child(3) > div.container-subTourDetail-left > div:nth-child(1) > h5\")";
    String descripcion1 = "return document.querySelector(\"#root > div > div.tour-details > div:nth-child(3) > div.container-subTourDetail-left > div:nth-child(1) > p\")";
    String calendario1 = "return document.querySelector(\"#calendar\")";
    String btoReservarAhora = "return document.querySelector(\"#root > div > div.tour-details > div:nth-child(3) > div.container-subTourDetail-right > div > div.MuiContainer-root.MuiContainer-maxWidthLg.css-1oqqzyl-MuiContainer-root > a > button\")";
    String tituloDetallePedido = "return document.querySelector(\"#root > div > div.bookTour-container > h2\")";
    String politicasCancelacion = "return document.querySelector(\"#root > div > div:nth-child(3) > div.cancellation-policy-container\")";
    String like = "return document.querySelector(\"#root > div > div.tour-details > div.tour-details-title > div.tour-details-title-left > h1 > span\")";

    BaseWrapper baseWrapper = new BaseWrapper();
    public void seleccionTour() throws InterruptedException {
        baseWrapper.ClickShadowRootObject(tourUno);
        Thread.sleep(2000);
        baseWrapper.ScrollUpPage();
    }

    public void validacionEstructuraTour() throws InterruptedException {
        System.out.println("*** Inicio de Validacion Estructura *****");
        baseWrapper.FactoryShadowRootObjectVisibility(nombreTour);
        String actual1 = baseWrapper.FactoryShadowRootGetText(descripcionTour);
        String esperado1 = "Descripción del Tour";
        Assert.assertEquals(esperado1,actual1);
        baseWrapper.FactoryShadowRootObjectVisibility(descripcion1);
        System.out.println("*** Validacion de estructura del tour exitosa *****");
    }

    public void seleccionFecha() throws InterruptedException {
        Thread.sleep(2000);
        String fecha = "28/09/203";
        baseWrapper.Scroll(300);
        baseWrapper.FactoryShadowRootSendKeys(calendario1,fecha);
        System.out.println("*** Ingreso la fecha ***");
        Thread.sleep(2000);
        baseWrapper.ClickShadowRootObject(btoReservarAhora);
        Thread.sleep(2000);
        baseWrapper.ScrollUpPage();
        Thread.sleep(2000);
    }

    public void validacionDetallePedido() throws InterruptedException {
        baseWrapper.FactoryShadowRootObjectVisibility(tituloDetallePedido);
        String acual1 = baseWrapper.FactoryShadowRootGetText(tituloDetallePedido);
        String esperado1 = "Detalle de Pedido";
        Assert.assertEquals(esperado1,acual1);
        baseWrapper.FactoryShadowRootObjectVisibility(politicasCancelacion);
    }

    public void confirmacionReserva() throws InterruptedException {
        baseWrapper.ScrollDownPage();
        String btoConfirmacionReserva = "return document.querySelector(\"#root > div > div:nth-child(3) > button\")";
        baseWrapper.FactoryShadowRootObjectVisibility(btoConfirmacionReserva);
        String esperado = "Confirmar Reserva";
        String actual = baseWrapper.FactoryShadowRootGetText(btoConfirmacionReserva);
        Assert.assertEquals(esperado,actual);
    }

    public void seleccionFavorito() throws InterruptedException {

        Thread.sleep(2000);
        baseWrapper.ClickShadowRootObject(like);
        Thread.sleep(2000);
        System.out.println("*** Like Ok ***");
    }
}
