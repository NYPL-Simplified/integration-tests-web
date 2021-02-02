Feature: Book Transactions without login

  Background:
    Given I open 'LYRASIS' library

  @tier1 @logout @desktop @cancelBorrow
  Scenario: Check-out with login (Book Detail View)
    When I open 'Children and Middle Grade' category
      And I open 'All Children and Middle Grade' category
      And I open the book details for book with button BORROW and save it as 'bookInfo'
      And I click BORROW book action button
      And I login using 'LYRASIS' credentials
    Then Login is performed successfully
    When I click BORROW book action button
    Then Check that download book button is present