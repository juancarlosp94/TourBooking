package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.step.DestinosSteps;

public class DestinosStepsDefinitions {
    @Steps
    DestinosSteps destinosSteps;
    @Given("Que usuario ya se encuentre logueado")
    public void que_usuario_ya_se_encuentre_logueado() throws InterruptedException {
        destinosSteps.validacionHomeUserAunteticado();

    }
    @When("Seleccione alguna de las categorias en el Home {string}")
    public void seleccione_alguna_de_las_categorias_en_el_home(String string) throws InterruptedException {
        destinosSteps.seleccionCategoria(string);

    }
    @Then("visualizara todos los destinos de la categoria")
    public void visualizara_todos_los_destinos_de_la_categoria() throws InterruptedException {
        destinosSteps.validacionDestinos();

    }
}
