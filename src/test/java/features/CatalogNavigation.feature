Feature: Catalog Navigation

  @tier1
  Scenario: Navigate Lists
    When I open 'LYRASIS' library
    Then Library is loaded
    When I get names of books on screen and save them as 'listOfBooksOnMainPage'
      And I open 'Nonfiction' category
    Then Current category name is 'Nonfiction'
      And Books feed is loaded
      And Following subcategories are present:
        | Gardening                   |
        | Art & Design                |
        | Biography & Memoir          |
        | Education                   |
        | Personal Finance & Business |
        | Parenting & Family          |
        | Food & Health               |
        | History                     |
        | Hobbies & Home              |
        | Humor                       |
        | Entertainment               |
        | Life Strategies             |
        | Literary Criticism          |
        | Philosophy                  |
        | Politics & Current Events   |
        | Reference & Study Aids      |
        | Religion & Spirituality     |
        | Science & Technology        |
        | Self-Help                   |
        | Sports                      |
        | Travel                      |
        | True Crime                  |
        | All Nonfiction              |
      And List of books on screen is not equal to list of books saved as 'listOfBooksOnMainPage'
    When I return to previous screen
      And I open 'Fiction' category
    Then Current category name is 'Fiction'
      And Books feed is loaded
      And Following subcategories are present:
        | gardening fun      |
        | Classics           |
        | Drama              |
        | Adventure          |
        | Jane Austen        |
        | Fantasy            |
        | Folklore           |
        | Historical Fiction |
        | Horror             |
        | Humor              |
        | Literary Fiction   |
        | Mystery            |
        | Poetry             |
        | Romance            |
        | Science Fiction    |
        | Short Stories      |
        | Thriller           |
        | Westerns           |
        | Women's Fiction    |
        | All Fiction        |
      And List of books on screen is not equal to list of books saved as 'listOfBooksOnMainPage'
    When I open 'Drama' subcategory
    Then Subcategory screen is present
      And Subcategory name is 'Drama'

  @tier1
  Scenario: Filter books
    When I open 'LYRASIS' library
    Then Library is loaded
    When I filter books by 'eBooks' format
    Then Books feed is loaded
      And All present books are ebooks
    When I filter books by 'Audiobooks' format
    Then Books feed is loaded
      And All present books are audiobooks
    When I get names of books on screen and save them as 'listOfBooksOnMainPage'
      And I filter books by 'All' format
    Then List of books on screen is not equal to list of books saved as 'listOfBooksOnMainPage'

  @tier1
  Scenario: Browse Lanes/Categories
    When I open 'LYRASIS' library
    Then Library is loaded
    When I open 'Fiction' category
    Then Current category name is 'Fiction'
      And Count of books in first lane is up to 15
      And Following subcategories are present:
          | gardening fun      |
          | Classics           |
          | Drama              |
          | Adventure          |
          | Jane Austen        |
          | Fantasy            |
          | Folklore           |
          | Historical Fiction |
          | Horror             |
          | Humor              |
          | Literary Fiction   |
          | Mystery            |
          | Poetry             |
          | Romance            |
          | Science Fiction    |
          | Short Stories      |
          | Thriller           |
          | Westerns           |
          | Women's Fiction    |
          | All Fiction        |
    When I open 'Adventure' subcategory
    Then Subcategory screen is present
      And Subcategory name is 'Adventure'
    When I open first book in subcategory list and save it as 'bookInfo'
    Then Book 'bookInfo' is opened

  @tier1
  Scenario: Navigate Links
    When I open 'LYRASIS' library
    Then Library is loaded
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

  @tier1
  Scenario: View Book Details
    When I open 'Howard County Library System' library
    Then Library is loaded
    When I search for 'Harry Potter and the Goblet of Fire' book
    When I switch to 'eBooks' book type in search result
    And I open first book with 'Unavailable' status
    Then Books info screen is present
    And The following values in the information block are present:
      | key        | value                          |
      | published  | December 8, 2015               |
      | publisher  | Pottermore Publishing          |
      | categories | Children, 18, Fiction, Fantasy |
    And Description has text
    """
    "'There will be three tasks, spaced throughout the school year, and they will test the champions in many different ways ... their magical prowess - their daring - their powers of deduction - and, of course, their ability to cope with danger.'"
The Triwizard Tournament is to be held at Hogwarts. Only wizards who are over seventeen are allowed to enter - but that doesn't stop Harry dreaming that he will win the competition. Then at Hallowe'en, when the Goblet of Fire makes its selection, Harry is amazed to find his name is one of those that the magical cup picks out. He will face death-defying tasks, dragons and Dark wizards, but with the help of his best friends, Ron and Hermione, he might just make it through - alive!
    """
    And Count of books in subcategory 'J. K. Rowling' lane is up to 17
    When I open 'J. K. Rowling' recommendation subcategory on book page
    Then Subcategory name is 'J. K. Rowling'
    And Author of all present books is 'J. K. Rowling'
    When I return to previous screen
    And Count of books in subcategory 'Harry Potter' lane is up to 17
    When I open 'Harry Potter' recommendation subcategory on book page
    Then Subcategory name is 'Harry Potter'
    And Title of all present books contains 'Harry Potter'
