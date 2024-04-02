package starter.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.step.CrearTourSteps;

public class CrearTourStepsDefinitions {

    @Steps
    CrearTourSteps crearTourSteps;
    @When("Selecciona la opcion de crear tour")
    public void selecciona_la_opcion_de_crear_tour() throws InterruptedException {
        crearTourSteps.seleccionDeOpcionCT();

    }
    @Then("podra observar los campos")
    public void podra_observar_los_campos() throws InterruptedException {
        crearTourSteps.ValidacionOpcionCT();

    }
    @When("ingrese la informacion de {string}, {string}, {string}, {string}, {string}, {string}")
    public void ingrese_la_informacion_de(String string, String string2, String string3, String string4, String string5, String string6) throws InterruptedException {
        crearTourSteps.ingresoInfoCT(string,string2,string3,string4,string5,string6);
    }
    @Then("da click en la opcion guardar destino")
    public void da_click_en_la_opcion_guardar_destino() throws InterruptedException {
        crearTourSteps.clickOpcionGuardar();

    }
    @Then("se notifica la creacion del tour")
    public void se_notifica_la_creacion_del_tour() throws InterruptedException {
        crearTourSteps.notificacionCreacion();

    }
}
