package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.step.ReservaTourSteps;

public class ReservaTourStepsDefinitions {

    @Steps
    ReservaTourSteps reservaTourSteps;

    @Given("Que usuario ya se encuntra dentro de la categoria")
    public void que_usuario_ya_se_encuntra_dentro_de_la_categoria() throws InterruptedException {
        Thread.sleep(1000);
    }
    @When("Selecciona uno de los tour")
    public void selecciona_uno_de_los_tour() throws InterruptedException {
        reservaTourSteps.seleccionTour();

    }
    @Then("valida las secciones del tour")
    public void valida_las_secciones_del_tour() throws InterruptedException {
        reservaTourSteps.validacionEstructuraTour();
    }

    @When("selecciona la fecha y da click reservar")
    public void selecciona_la_fecha_y_da_click_reservar() throws InterruptedException {
        reservaTourSteps.seleccionFecha();
    }
    @Then("Visualizara el detalle del pedido")
    public void visualizara_el_detalle_del_pedido() throws InterruptedException {
        reservaTourSteps.validacionDetallePedido();
    }
    @Then("Confirmara la reserva")
    public void confirmara_la_reserva() throws InterruptedException {
        reservaTourSteps.confirmara_la_reserva();
    }

}
