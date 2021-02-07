Feature: pulse website

	# comments
	# if nothing else on the screen changes other than the carousel articles this test logic could also work
	# using screen compare		
	# 
	
	@test1
  Scenario: validate the carousel next and previous
    Given I navigate to the PwC Digital Pulse website
    When I am viewing the ‘Home’ page    
    Then I am presented with a carousel displaying 3 featured articles
    # note need to test scenario where click next loads only e.g 2 more articles and not 3 
    # noted in app carousel has 12 articles so perfect multiple of 3 x 4 but should test with odd number of articles  
    And Clicking the ‘Next’ button on the carousel will load the next 3 featured articles
    And Clicking the ‘Previous’ button on the carousel will load the previous 3 featured articles
