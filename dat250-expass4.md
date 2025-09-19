#  **DATA250 Lab 4**
####  **Johnny Nhat Trung Le**

Added the dependencies needed for this exercise.  
Created the requested methods for the `User`- and `Poll`-class.  
Added the annotation needed to successfully pass the test.  

### Database explanation
The setup for the database is that every class is an entity, annotated with `@Entity` and has its own table, annotated with `@Table`
I have given all the classes their own id, annotated with `@Id`, in the form of an UUID. Which gets automatically generated with the `@GeneratedValue` annotation.

Now looking at how the relation is between all the classes we start with the `Poll`-class. 
The `Poll`-class had direct relation with the `User`-class and `VoteOption`-class. 
For the `User`-class there is a many-to-one relation, and the relation with the `VoteOption`-class is a one-to-many.
The `User`-class has a one-to-many relation with `Poll`.
`Vote` has a many-to-one relation with `VoteOption`.
And lastly `VoteOption` has a many-to-one relation with `Poll`.

[Link to test](backend/src/test/java/com/example/demo/PollsTest.java)  
[Link to User methods](backend/src/main/java/com/example/demo/entities/User.java)  
[Link to Poll methods](backend/src/main/java/com/example/demo/entities/Poll.java)

### Technical problems?
I had no problems during the installation and the use of Java Persistence Architecture (JPA).

### Pending Issues?
All test case passes, both the test scenario from expass2 and the one here, given to us from the assignment. 
Therefore, I believe that there are no issues, at least from what I am aware of.

