#Autor: Andres David Suarez Gomez
  #Usario: andres93_sg@hotmail.com
Feature: Validacion inicio de sesion aplicacion TourBooking

  Background:
    Given Una vez el usuario requiera ingresar al TourBooking
    When Ingrese al aplicativo
    Then vera el home del aplicativo

  @RunAll1 @InicioSecion
  @Test3
  Scenario: Validacion inicio de sesion exitoso al aplicativo TourBooking Rol Admin
    Given Que usuario ingrese a la opcion de inicio de sesion
    When Ingrese la informacion del correo "juanP", contrasena "123456"
    Then Dara click en la opcion inicio de sesion
    And Se ingresara de forma exitosa


  @RunAll1 @InicioSecion
  @Test4
  Scenario: Validacion inicio de sesion exitoso al aplicativo TourBooking Rol normal
    Given Que usuario ingrese a la opcion de inicio de sesion
    When Ingrese la informacion del correo "ULAN3535", contrasena "Asuarez40*"
    Then Dara click en la opcion inicio de sesion
    And Se ingresara de forma exitosa con ron normal


  @RunAll1 @InicioSecion
  @Test5
  Scenario: Validacion inicio de sesion fallido al aplicativo TourBooking
    Given Que usuario ingrese a la opcion de inicio de sesion
    When Ingrese la informacion del correo "andres@pruebas.com", contrasena "Pruebas147*"
    Then Dara click en la opcion inicio de sesion
    And Se niega el ingreso