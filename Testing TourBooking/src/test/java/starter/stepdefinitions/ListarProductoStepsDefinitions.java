package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.step.ListarProductoSteps;

public class ListarProductoStepsDefinitions {

    @Steps
    ListarProductoSteps listarProductoSteps;

    @When("Seleccione la opcion de listar producto")
    public void seleccione_la_opcion_de_listar_producto() throws InterruptedException {
    listarProductoSteps.seleccionOpcionListarProducto();
    }
    @Then("se visualizara el modal de carga")
    public void se_visualizara_el_modal_de_carga() throws InterruptedException {
    listarProductoSteps.validacionModalDeCarga();
    }
    @And("se mostraran todos los tour existentes")
    public void se_mostraran_todos_los_tour_existentes() throws InterruptedException {
        listarProductoSteps.validacionTours();
    }
}
