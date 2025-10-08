#  **DATA250 Lab 6**
####  **Johnny Nhat Trung Le**

In this assignment I have implemented a message broker.
For every poll that gets created a new topic is made.
Then for every vote that gets sent, a message gets sent to the message broker such that the database gets updated.

### Technical problems?
In this lab, I encountered no problems nor difficulties during the installation and use of RabbitMQ.
I had some issues on the test though, that resolved by adding `Awaitility` which is used for asynchronous testing.

### Pending Issues?
Testing with Bruno and looking at the RabbitMQ UI I believe that the tasks presented in this assignment is fulfilled.  
The test still passes, the github action workflow works, creating a poll makes a new topic and a vote sends a new message to message broker.  
Therefore, I believe this assignment was a success.