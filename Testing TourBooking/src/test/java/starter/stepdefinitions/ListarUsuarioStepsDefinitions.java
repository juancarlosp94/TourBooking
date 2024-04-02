package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.step.ListarUsuarioSteps;

public class ListarUsuarioStepsDefinitions {
    @Steps
    ListarUsuarioSteps listarUsuarioSteps;

    @When("Seleccione la opcion de listar Usuario")
    public void seleccione_la_opcion_de_listar_usuario() throws InterruptedException {
        listarUsuarioSteps.seleccionOpcionListarUsuario();
    }
    @Then("se visualizara el modal de carga usuario")
    public void se_visualizara_el_modal_de_carga_usuario() throws InterruptedException {
        listarUsuarioSteps.validacionModal();
    }
    @And("se mostraran todos los usuarios creados")
    public void se_mostraran_todos_los_usuarios_creados() throws InterruptedException {
        listarUsuarioSteps.validacionListaUsuarios();
    }
}
