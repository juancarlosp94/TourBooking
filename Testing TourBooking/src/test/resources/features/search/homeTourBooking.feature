#Autor: Andres David Suarez Gomez
  #Usario: andres93_sg@hotmail.com
Feature: Ingreso a la aplicacion TourBooking

  @RunAll1 @HomeTour
  @Test13
  Scenario: Validacion ingresar al aplicativo TourBooking
    Given Una vez el usuario requiera ingresar al TourBooking
    When Ingrese al aplicativo
    Then vera el home del aplicativo


  @RunAll1 @HomeTour
  @Test2
  Scenario: Validation de footer home TourBooking
    Given Una vez el usuario requiera ingresar al TourBooking
    When Valide el footer
    Then Visualizara todos los campos


