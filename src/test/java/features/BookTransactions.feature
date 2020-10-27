Feature: Book Transactions

  @tier1 @logout
  Scenario: Check-out
    When I open 'LYRASIS' library
    Then Library is loaded
    When I login to the 'LYRASIS'
    Then Login is performed successfully
    When I open random book page
    Then Check book page was opened
    When I click borrow book button
    Then Check that download book button appeared
