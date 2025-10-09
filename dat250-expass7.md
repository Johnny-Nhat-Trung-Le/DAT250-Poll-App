#  **DATA250 Lab 7**
####  **Johnny Nhat Trung Le**

In this assignment I made an Dockerfile.
Its purpose is to containerize the poll application

Used the following commands to find what .jar I should put in the `RUN mv …`:  
`./gradlew bootjar `
--> 
`find . -name "*.jar"`

To see if it actually worked I first build it:
`docker build -t pollapp:dev .`  
Then checked if the image exists: `docker images`  

Checking if the image successfully runs I ran this command: `docker run --rm -p 8080:8080 pollapp:dev`

### Technical problems?
In this lab, I encountered no problems nor difficulties during the installation and use of Docker.


### Pending Issues?
The test still passes, the github action workflow works.  
Therefore, I believe this assignment was a success.




