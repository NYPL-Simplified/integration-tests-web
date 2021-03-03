Feature: Book Transactions

  Background:
    Given I open 'LYRASIS' library
    When I login to the 'LYRASIS'
    Then Login is performed successfully

  @tier1 @logout @desktop @cancelBorrow
  Scenario: Check-out (Book Detail View)
    When I open 'Young Adult Fiction' category
      And I open 'All Young Adult Fiction' category
      And I open the book details for book with button BORROW and save it as 'bookInfo'
      And I click BORROW book action button
    Then Check that download book button is present

  @tier1 @logout @desktop @cancelBorrow
  Scenario: Download (Book Detail View)
    When I open 'Fiction' category
      And I open 'All Fiction' category
      And I open the book details for book with button BORROW and save it as 'bookInfo'
      And I click BORROW book action button
    Then Check that download book button is present
    When I download book
    Then Check the book was downloaded successfully

  @tier1 @logout @desktop @cancelBorrow
  Scenario: Return (Book Detail View)
    When I open 'Nonfiction' category
      And I open 'All Nonfiction' category
      And I open the book details for book with button BORROW and save it as 'bookInfo'
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

  @tier1 @logout @desktop @cancelBorrow
  Scenario: Download (My Books)
    When I search for 'Aspiring Saints' book
      And I open first book with name equal to 'Aspiring Saints'
      And I BORROW book if it's possible
    When I open My books
      And I download book and save it as 'bookInfo'
    Then Book is not available for loan
      And Check the book 'bookInfo' was downloaded successfully

  @tier1 @logout @desktop @cancelBorrow
  Scenario Outline: Borrow book from publisher
    When I search for '<bookName>' book
      And I switch to '<bookType>' book type in search result
      And I open first book with name equal to '<bookName>'
      And I BORROW book if it's possible
    Then Book is not available for loan
      And Following buttons are present:
        | readyToReadOnSimplyEMessage   | downloadAdobeACSM   | readyToListenOnSimplyEMessage   | downloadEPUB   |
        | <readyToReadOnSimplyEMessage> | <downloadAdobeACSM> | <readyToListenOnSimplyEMessage> | <downloadEPUB> |

    Scenarios:
      | libraryName   | bookType  | bookName                                    | readyToReadOnSimplyEMessage | downloadAdobeACSM | readyToListenOnSimplyEMessage | downloadEPUB |
      | Axis 360      | Ebook     | Broken Wing                                 | true                        | true              | false                         | false        |
      | Axis 360      | Audiobook | Ladies\' Night                              | false                       | false             | true                          | false        |
      | DPLA Exchange | Ebook     | An Invitation to Dream                      | true                        | true              | false                         | false        |
      | DPLA Exchange | Audiobook | The Lost Book of Adana Moreau               | false                       | false             | true                          | false        |
      | Bibliotheca   | Ebook     | Avengers: Everybody Wants to Rule the World | true                        | true              | false                         | false        |
      | Bibliotheca   | Audiobook | Highlights of the Perfect Sales Process     | false                       | false             | true                          | false        |
      #| Open Bookshelf (Open Access) | Ebook     |                                 | true                        | false             | false                         | true         |
