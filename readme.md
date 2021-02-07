to run tests : mvn test -Dcucumber.filter.tags=@test1

note comments included in code 
note the shared driver implementation is out of date / hacked up ( based on code from the last time i worked with cucumbe-jvm in
2014 ) 

the purpose of the coding exercise is the problem solving aspect of the scenario provided ( and i've noted considerations
how the scenario has been written that assumes it is always the same number of articles each time the carousel updates ) 

also note the scenario / test logic isn't really testing the carousel functions such as the images simply that there has been a text 'change'
a more in depth test might consider comparing the images 1 2 3 have also changed  
