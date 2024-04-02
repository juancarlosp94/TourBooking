#Autor: Andres David Suarez Gomez
  #Usario: andres93_sg@hotmail.com
Feature: Validacion funcionalidad crear tour TourBooking

  Background:
    Given Una vez el usuario requiera ingresar al TourBooking
    When Ingrese al aplicativo
    Then vera el home del aplicativo
    Given Que usuario ingrese a la opcion de inicio de sesion
    When Ingrese la informacion del correo "juanP", contrasena "123456"
    Then Dara click en la opcion inicio de sesion
    And Se ingresara de forma exitosa
    Given Que el administrador se encuentra logueado
    When visualice las opciones


  @RunAll1 @CrearTour
  @Test8
  Scenario Outline: Validacion de crear tour
    Given Que el administrador se encuentra logueado
    When Selecciona la opcion de crear tour
    Then podra observar los campos
    When ingrese la informacion de "<Nombre Tour>", "<Descripcion Tour>", "<Descripcion Corta>", "<Categoria>", "<precio>", "<Imagenes>"
    Then da click en la opcion guardar destino
    And se notifica la creacion del tour
    Examples:
    |Nombre Tour         |Descripcion Tour                         |Descripcion Corta    |Categoria|precio|Imagenes|
    |Prueba Automatizada |Es un prueba de descripcion Automatizada |Automatizacion corta |Playa    |120.32|Local   |