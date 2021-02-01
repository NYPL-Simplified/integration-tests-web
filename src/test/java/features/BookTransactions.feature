Feature: Book Transactions

  Background:
    Given I open 'LYRASIS' library
    When I login to the 'LYRASIS'
    Then Login is performed successfully

  @tier1 @logout @desktop @cancelBorrow
  Scenario: Check-out (Book Detail View)
    When I open random book page
      And I click BORROW book action button
    Then Check that download book button is present

  @tier1 @logout @desktop @cancelBorrow
  Scenario: Download (Book Detail View)
    When I open random book page
      And I click BORROW book action button
    Then Check that download book button is present
    When I download book
    Then Check the book was downloaded successfully

  @tier1 @logout @desktop @cancelBorrow
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
    When I open My books
      And I download book and save it as 'bookInfo'
    Then Check the book 'bookInfo' was downloaded successfully

  @tier1 @logout @desktop @cancelBorrow
  Scenario Outline: Borrow book from publisher
    When I search for '<bookName>' book
      And I switch to '<bookType>' book type in search result
      And I borrow book if its possible
    Then Following buttons are present:
      | readyToReadOnSimplyEMessage   | readOnline   | downloadAdobeACSM   | openODWebReader   | readyToListenOnSimplyEMessage   | downloadEPUB   |
      | <readyToReadOnSimplyEMessage> | <readOnline> | <downloadAdobeACSM> | <openODWebReader> | <readyToListenOnSimplyEMessage> | <downloadEPUB> |

    Scenarios:
      | libraryName                           | bookType  | bookName | readyToReadOnSimplyEMessage | readOnline | downloadAdobeACSM | openODWebReader | readyToListenOnSimplyEMessage | downloadEPUB |
      | Overdrive                             | Ebook     |          | true                        | true       | true              | false           | false                         | false        |
      | Overdrive                             | Audiobook |          | true                        | false      | false             | false           | false                         | false        |
      | Overdrive Read Online                 |           |          | false                       | false      | false             | true            | false                         | false        |
      | Axis 360                              | Ebook     |          | true                        | false      | true              | false           | false                         | false        |
      | Axis 360                              | Audiobook |          | false                       | false      | false             | false           | true                          | false        |
      | Bibliotheca                           | Ebook     |          | true                        | false      | true              | false           | false                         | false        |
      | Bibliotheca                           | Audiobook |          | false                       | false      | false             | false           | true                          | false        |
      | DPLA Exchange                         | Ebook     |          | true                        | false      | true              | false           | false                         | false        |
      | DPLA Exchange                         | Audiobook |          | false                       | false      | false             | false           | true                          | false        |
      | Biblioboard                           | Ebook     |          | true                        | false      | true              | false           | false                         | false        |
      | ProQuest Ebook (DRM)                  |           |          | true                        | false      | true              | false           | false                         | false        |
      | ProQuest Ebook (DRM Free)             |           |          | true                        | false      | false             | false           | false                         | true         |
      | Self Hosted Content (Access Control)  |           |          | true                        | false      | false             | false           | false                         | true         |
      | Internet Archive Hosted (open access) |           |          | true                        | false      | false             | false           | false                         | true         |
      | Knowlege Unlatched (Open Acces)       |           |          | true                        | false      | false             | false           | false                         | true         |
      | Open Bookshelf (Open Acces)           |           |          | true                        | false      | false             | false           | false                         | true         |
      | Fulcrum (Open Acces)                  |           |          | true                        | false      | false             | false           | false                         | true         |
      | Self Curated OPDS (Static Feeds)      |           |          | true                        | false      | false             | false           | false                         | true         |
