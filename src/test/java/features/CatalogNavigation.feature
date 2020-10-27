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