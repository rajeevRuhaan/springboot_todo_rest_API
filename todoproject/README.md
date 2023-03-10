
## Springboot 17 Rest API application using MySql database
### TODO apllication and Authentication

```script
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3308/todo_api
spring.datasource.username=root
spring.datasource.password=

spring.datasource.driver-calss-name=com.mysql.cj.jdbc.Driver
```

Data model
# Todo:
    - Id: Unique identifier
    - Name: Name of the todo item
    - Description (optional): Description of the toto item
    - User id: Id of the user who owns this todo item
    - Created timestamp: When the item is created
    - Updated timestamp: When the item is last updated
    - Status: An enum of either: NotStarted, OnGoing, Completed

## User:
    - Id: Unique identifier
    - Email: Email address
    - Password: Hash of the password
    - Created timestamp: When the user is created
    - Updated timestamp: When the user is last updated
  
### Completed these three end point 
Rest API core features and endpoints
- **POST** */api/v1/signup*: Sign up as an user of the system, using email & password(completed)
-  **POST** */api/v1/signin*: Sign in using email & password. The system will return the JWT token that can be used to call the APIs that follow (completed)
- **PUT** */api/v1/changePassword*: Change user’s password (completed)
  
 ### ....Project on development phase (ongoing)
- **GET** */api/v1/todos?status=[status]*: Get a list of todo items. Optionally, a status query param can be included to return only items of specific status. If not present, return all items (not yet started)
- **POST** */api/v1/todos*: Create a new todo item (**started...)
- **PUT** */api/v1/todos/:id*: Update a todo item ( not yet started)
- **DELETE** */api/v1/todos/:id*: Delete a todo item (not yet started)

## Forget password (completed)
work flow:
- */api/v1/forget_password : this end point take body as email. the email verifies with database and set token. Using JavaMailSender it send password reeset link with token. (need to work on token expiration date verification is the end point hit more than one time.....)
- */api/v1/password-reset?token=1234 : this end point have token as param and it also need body with reset password. the token need to verify with expiration_date and resetpassword replaced old password.

![alt text](src/main/resources/static/image/forgotpassword.png)

