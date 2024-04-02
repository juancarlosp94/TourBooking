#Autor: Andres David Suarez Gomez
  #Usario: andres93_sg@hotmail.com
Feature: Validacion funcionalidad listar producto

  Background:
    Given Una vez el usuario requiera ingresar al TourBooking
    When Ingrese al aplicativo
    Then vera el home del aplicativo
    Given Que usuario ingrese a la opcion de inicio de sesion
    When Ingrese la informacion del correo "juanP", contrasena "123456"
    Then Dara click en la opcion inicio de sesion
    And Se ingresara de forma exitosa

  @RunAll1 @ListarUsuario
  @Test10
  Scenario: Validacion de opciones admin
    Given Que el administrador se encuentra logueado
    When visualice las opciones
    When Seleccione la opcion de listar Usuario
    Then se visualizara el modal de carga usuario
    And se mostraran todos los usuarios creados