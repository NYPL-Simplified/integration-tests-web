Feature: Book Transactions

  Background:
    Given I open 'LYRASIS' library
    When I login to the 'LYRASIS'
    Then Login is performed successfully

  @tier1 @logout @desktop @test
  Scenario: Check-out (Book Detail View)
    When I open random book page
      And I click BORROW book action button
    Then Check that download book button is present

  @tier1 @logout @desktop
  Scenario: Download (Book Detail View)
    When I open random book page
      And I click BORROW book action button
    Then Check that download book button is present
    When I download book
    Then Check the book was downloaded successfully

  @tier1 @logout @desktop
  Scenario: Return (Book Detail View)
    When I open random book page
      And I click BORROW book action button
    Then Check that download book button is present
    When I click RETURN book action button
    Then Check that BORROW book button appeared

  @logout @cancelHold @tier2
  Scenario: Hold
    When I open 'Children and Middle Grade' category
      And I open 'All Children and Middle Grade' category
      And I open the book details for book with button RESERVE and save it as 'bookInfo'
      And I click RESERVE book action button
    Then Check that CANCEL_RESERVATION book button appeared
      And Book status is 'Reserved'
      And Message ' patrons ahead of you in the queue.' is present

  @logout @cancelHold @tier2
  Scenario: Remove a Reserved Book (My Books)
    When I open main library page
    When I open 'Children and Middle Grade' category
      And I open 'All Children and Middle Grade' category
      And I open the book details for book with button RESERVE and save it as 'bookInfo'
      And I click RESERVE book action button
    Then Check that CANCEL_RESERVATION book button appeared
      And Book status is 'Reserved'
      And Message ' patrons ahead of you in the queue.' is present
    When I open My books
      And I cancel book 'bookInfo' reservation
    Then Book 'bookInfo' is not present in My Books

  @tier1 @logout @desktop
  Scenario: Download (My Books)
    When I open the book details for book with button BORROW and save it as 'bookInfo'
      And I click BORROW book action button
    Then Check that download book button is present
    When I open My books
      And I download book 'bookInfo'
    Then Check the book was downloaded successfully