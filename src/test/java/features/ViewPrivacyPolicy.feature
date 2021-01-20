Feature: View Privacy Policy

  Scenario: View Privacy Policy, EULA
    When I open 'Washington County Free Library' library
    Then Library is loaded
    When I open 'Privacy' footer link
    Then New tab with link containing 'http://notices.sailor.lib.md.us/simplye/' is opened
    When I open 'St. Mary\'s County Library' library
    Then Library is loaded
    When I open 'Terms of Use' footer link
    Then New tab with link containing 'EULA.html' is opened
    When I open 'Washington State Library' library
    Then Library is loaded
    When I open 'Help Website' footer link
    Then New tab with link containing 'ask_form.aspx' is opened
    When I open 'Columbia University Libraries' library
    Then Library is loaded
    When I open 'About' footer link
    Then New tab with link containing 'about.html' is opened
