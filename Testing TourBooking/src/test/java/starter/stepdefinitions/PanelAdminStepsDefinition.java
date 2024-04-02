package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.step.PanelAdminSteps;

public class PanelAdminStepsDefinition {
    @Steps
    PanelAdminSteps panelAdminSteps;

    @Given("Que el administrador se encuentra logueado")
    public void que_el_administrador_se_encuentra_logueado() throws InterruptedException {
       Thread.sleep(1000);
    }
    @When("visualice las opciones")
    public void visualice_las_opciones() throws InterruptedException {
        panelAdminSteps.validacionOpcionesAdmin();

    }
    @Then("podra seleccionar alguna")
    public void podra_seleccionar_alguna() throws InterruptedException {
        this.visualice_las_opciones();
        Thread.sleep(5000);
    }
}
