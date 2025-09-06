#  **DATA250 Lab 2** 
####  **Johnny Nhat Trung Le** 

In this lab, Lab 2, I implement a simple REST API for Poll app using Spring Boot.
I created two packages, one for storing the entities presented by the domain model in the assignment.
And controllers containing all my controller classes.

Every controller class has the `@RestController` annotation, whilst the my `PollManager` class has the `@Component`.
I added all the fields for the class according to the domain model from the assignment, whilst also added some of my own.
In addition, for each of my entities classes, I made their own `equals()` and `hashCode()` methods, 
and added lombok `@Getter`and `@Setter` annotations where I saw fit. 

In the `PollManager` class I made all the necessary methods such that all the steps in the test scenario in the assignments gets fulfilled.

Afterward, I implemented all the `@GetMapping`,`@PostMapping`,`@PutMapping` and `@DeleteMapping` where I believe they should reside, 
and where I thought they were needed.

Lastly, I created a test that goes through the test scenario from the assignment, using `RestClient`.

### Technical problems?
In this lab, I encountered no problems nor difficulties during the installation of any of the plugins or dependencies.


### Pending Issues?
Currently, the application successfully runs the test scenario. 
So I believe that I managed to solve all the issues/tasks that this lab presented. 
Maybe there are some oversight on some edge cases,
but I think that the most important edge cases are dealt with, alongside some extra features.
Though, in this lab I never used the `@Json…`annotations so my knowledge on them is not be the best. 
