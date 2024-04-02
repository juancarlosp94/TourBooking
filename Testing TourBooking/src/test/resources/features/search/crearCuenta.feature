#Autor: Andres David Suarez Gomez
  #Usario: andres93_sg@hotmail.com
Feature: Validacion inicion crear usuario aplicacion TourBooking

  Background:
    Given Una vez el usuario requiera ingresar al TourBooking
    When Ingrese al aplicativo
    Then vera el home del aplicativo


  @RunAll1 @CrearCuenta
  @Test6
 Scenario Outline: Validacion crear usuario en aplicativo TourBooking
    Given Que usuario ingrese a la opcion de crear usuario
    When Ingrese la informacion del Nombre Usuario "<NombreUsuario>", Nombre "<Nombre>", Apellido "<Apellido>", pais de origen "<Pais de origen>", email "<email>",contrasena "<contrasena>"
    Then Dara click en la opcion de Reguistar
    And Se notifica el reguistro exitoso
    Examples:
    |NombreUsuario |Nombre        |Apellido      |Pais de origen|email                          |contrasena   |
    | Andres1      | Andres David |Suarez Gomez  |Colombia      |ulan.suarez.gomez@gmail.com    |Colombia1*   |
