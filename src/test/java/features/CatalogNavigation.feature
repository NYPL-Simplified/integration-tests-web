Feature: Catalog Navigation

  Background:
    Given 'LYRASIS' library is opened

  @tier1 @desktop @mobile
  Scenario: Navigate Lists
    When I get names of books on screen and save them as 'listOfBooksOnMainPage'
      And I open 'Young Adult Fiction' category
    Then Current category name is 'Young Adult Fiction'
      And Following subcategories are present:
        | Fantasy                 |
        | Contemporary Fiction    |
        | Mystery & Thriller      |
        | Romance                 |
        | Science Fiction         |
        | All Young Adult Fiction |
      And List of books on screen is not equal to list of books saved as 'listOfBooksOnMainPage'
    When I return to previous screen
      And I open 'Children and Middle Grade' category
    Then Current category name is 'Children and Middle Grade'
      And Following subcategories are present:
        | Picture Books                 |
        | Easy Readers                  |
        | Chapter Books                 |
        | Poetry Books                  |
        | Folklore                      |
        | Fantasy                       |
        | Science Fiction               |
        | Realistic Fiction             |
        | Comics & Graphic Novels       |
        | Biography                     |
        | Historical Fiction            |
        | Informational Books           |
        | All Children and Middle Grade |
      And List of books on screen is not equal to list of books saved as 'listOfBooksOnMainPage'
    When I open 'Picture Books' subcategory
    Then Subcategory name is 'Picture Books'

  @tier1 @desktop @mobile
  Scenario: Filter books
    When I filter books by 'eBooks' format
    Then All present books are ebooks
    When I filter books by 'Audiobooks' format
    Then All present books are audiobooks
    When I get names of books on screen and save them as 'listOfBooksOnMainPage'
      And I filter books by 'All' format
    Then List of books on screen is not equal to list of books saved as 'listOfBooksOnMainPage'

  @tier1 @desktop @mobile
  Scenario: Browse Lanes/Categories
    When I open 'Young Adult Fiction' category
    Then Current category name is 'Young Adult Fiction'
      And Count of books in first lane is more than 2
      And Following subcategories are present:
        | Fantasy                 |
        | Contemporary Fiction    |
        | Mystery & Thriller      |
        | Romance                 |
        | Science Fiction         |
        | All Young Adult Fiction |
    When I open 'Fantasy' subcategory
    Then Subcategory name is 'Fantasy'
    When I open first book in subcategory list and save it as 'bookInfo'
    Then Book 'bookInfo' is opened

  @tier1 @desktop @mobile
  Scenario: Navigate Links
    When I open 'Fiction' category
    Then Current category name is 'Fiction'
    When I open 'Adventure' subcategory
    Then Subcategory screen is present
      And Subcategory name is 'Adventure'
      And I open 'Fiction' item from breadcrumbs
    Then Current category name is 'Fiction'
    When I open 'Drama' subcategory
    Then Subcategory screen is present
      And Subcategory name is 'Drama'
      And I return to previous screen
      And Current category name is 'Fiction'

  @tier1 @desktop @mobile
  Scenario: View Book Details
    When I open 'Everett Public Library' library
      And I search for 'Harry Potter and the Goblet of Fire' book
      And I open first book with 'Unavailable' status
    Then The following values in the information block are present:
      | key        | value                                               |
      | published  | November 20, 2015                                   |
      | publisher  | Pottermore Publishing                               |
      | categories | Children, 9-12, Fiction, Fantasy, Suspense/Thriller |
      And Description has text
      """
      "'There will be three tasks, spaced throughout the school year, and they will test the champions in many different ways ... their magical prowess - their daring - their powers of deduction - and, of course, their ability to cope with danger.'"
  The Triwizard Tournament is to be held at Hogwarts. Only wizards who are over seventeen are allowed to enter - but that doesn't stop Harry dreaming that he will win the competition. Then at Hallowe'en, when the Goblet of Fire makes its selection, Harry is amazed to find his name is one of those that the magical cup picks out. He will face death-defying tasks, dragons and Dark wizards, but with the help of his best friends, Ron and Hermione, he might just make it through - alive!
      """
      And Count of books in subcategory 'J. K. Rowling' lane is more than 2
    When I open 'J. K. Rowling' recommendation subcategory on book page
    Then Subcategory name is 'J. K. Rowling'
      And Author of all present books is 'J. K. Rowling'
    When I return to previous screen
      And Count of books in subcategory 'Harry Potter' lane is more than 2
    When I open 'Harry Potter' recommendation subcategory on book page
    Then Subcategory name is 'Harry Potter'
      And Title of all present books contains 'Harry Potter'
