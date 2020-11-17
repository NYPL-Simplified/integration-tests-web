Feature: Book Transactions

  Background:
    Given I open 'LYRASIS' library
    Then Library is loaded
    When I login to the 'LYRASIS'
    Then Login is performed successfully
    When I open random book page
    Then Check book page was opened

  @tier1 @logout @desktop @test
  Scenario: Check-out (Book Detail View)
    When I click BORROW book action button
    Then Check that DOWNLOAD_EPUB book button appeared

  @tier1 @logout @desktop
  Scenario: Download (Book Detail View)
    When I click BORROW book action button
    Then Check that DOWNLOAD_EPUB book button appeared
    When I click DOWNLOAD_EPUB book action button
    Then Check the book was downloaded successfully

  @tier1 @logout @desktop
  Scenario: Return (Book Detail View)
    When I click BORROW book action button
    Then Check that DOWNLOAD_EPUB book button appeared
    When I click RETURN book action button
    Then Check that BORROW book button appeared
