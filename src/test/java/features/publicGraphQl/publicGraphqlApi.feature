Feature: Public Graphql Api Feature

  Background:
    Given url graphQlBaseUrl
    * def mutation = read(graphqlPath + "/publicGraphqlApi/publicGraphqlApi.graphql")
    * def model = read(modelPath + "/publicGraphqlApi/publicGraphqlApi.json")

  Scenario: Create Request
    And request { query: '#(mutation)', variables: '#(model.request)' }
    When method post
    Then status 200
