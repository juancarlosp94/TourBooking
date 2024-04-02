package starter.step;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import starter.Page.ReservaTourPage;

public class ReservaTourSteps extends PageObject {

    ReservaTourPage reservaTourPage;
    @Step
    public void seleccionTour() throws InterruptedException {
        reservaTourPage.seleccionTour();
    }
    @Step
    public void validacionEstructuraTour() throws InterruptedException {
        reservaTourPage.validacionEstructuraTour();
    }
    @Step
    public void seleccionFecha() throws InterruptedException {
        reservaTourPage.seleccionFecha();
    }
    @Step
    public void validacionDetallePedido() throws InterruptedException {
        reservaTourPage.validacionDetallePedido();
    }
    @Step
    public void confirmara_la_reserva() throws InterruptedException {
        reservaTourPage.confirmacionReserva();
    }
}
