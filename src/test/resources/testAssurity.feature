Feature: Assurity Detail Service Verification

  Scenario Outline: Response verification for Detail Service
    Given the user access "<URL>"
    Then verify the response code is equal to "<Statuscode>"
    And verify response header content-type as "<Header>"
    Examples:
      | URL                          | Statuscode | Header           |
      | Categories/6327/Details.JSON | 200        | application/json |
      | Categories/6327/test         | 404        | text/html        |

  Scenario: Name verification for Carbon credits in Detail Service
    Given the user access "Categories/6327/Details.JSON"
    And verify the name is equal to "Carbon credits"

  Scenario: CanRelist verification in Detail Service
    Given the user access "Categories/6327/Details.JSON"
    And verify the CanRelist is equal to "true"

  Scenario: Promotion element verification with Name and description in Detail Service
    Given the user access "Categories/6327/Details.JSON"
    And verify the promotion has name "Gallery" and contain "Good position in category" in the description



