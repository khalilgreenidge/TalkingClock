# Time Clock 🕐 Challenge

This repository contains a simple REST API built for the Lloyd's bank take home exercise.
It was built using **Java SpringBoot** with **maven**, and the **Jersey Library**. 

## Getting Start
- Run `git clone https://github.com/khalilgreenidge/TalkingClock.git
  `.
- Open up this project in your favourite code editor. I built this using **intelliJ**, so maybe you can try that 😎.
- In your terminal run `./mvnw clean install -DskipTests` to build the application. *(I also like clicking the `green hammer` in intelliJ as a precaution but its optional)*
- Right-Click on `TalkingClockApplication` found in [here](src/main/java/com/khalilgreenidge/app/TalkingClock), then click the play button.


## How to connect to the API
- Simple run the following url: [http://localhost:8080/v1/time/{hours:mins}](http://localhost:8080/v1/time/{timeParam})
whereby, you replace `hours` and `mins` with the time you actually want. E.g.  [http://localhost:8080/v1/time/16:30](http://localhost:8000/v1/time/16:30)
- All requests are made using GET. See my example below using postman.
![pic showing postman](src/main/resources/postman.png)
- If a valid format for the time was requested, you will receive a JSON response containing the words in the body. If you did not formulate the time correct, you will get a customised 400 error message 🙂.
- If you have any questions, feel free to pop me an email - khalilgreenidge16@gmail.com


## Running Tests
There are two types of [tests](src/test/java/com/khalilgreenidge/app/TalkingClock) in this repository: **unit** and **integration**. The unit
test resides in the `units` folder and integration test resides in the `IT` folder. Before running the 
integration test, make sure you **build** and **run** the application first! The particular test needs to build first, otherwise it will
appear to fail when in reality it works.


## Tools/Libraries
- **SpringBoot** 2.6.7
- **Jersey** 
- Maven
- **httpclient** 4.3.6