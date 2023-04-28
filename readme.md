### Voting system for deciding where to have lunch

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at only one vote counted per user
- If user votes again the same day:
    * If it is before 11:00 we assume that he changed his mind.
    * If it is after 11:00 then it is too late, vote can't be changed

-------------------------------------------------------------
#### Stack: 
JDK 15, Spring Boot 2.5, H2, Caffeine Cache, Swagger/OpenAPI 3.0

-------------------------------------------------------------
#### Deploy:
Download [voit.jar](https://github.com/Gradio2000/grad_project/blob/master/voit.jar)
and run from your folder:
```sh
java -jar voit.jar
```
and you can use API
-----------------------------------------------------
[REST API documentation](http://localhost:8080/swagger-ui.html)  

-----------------------------------------------------
#### Credentions:
```
User:  user / user
Admin: admin / admin
