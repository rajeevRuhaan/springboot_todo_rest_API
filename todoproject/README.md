
## Springboot 17 Rest API application
### TODO apllication and Authentication

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
  
Rest API core features and endpoints
- **POST** */api/v1/signup*: Sign up as an user of the system, using email & password
-  **POST** */api/v1/signin*: Sign in using email & password. The system will return the JWT token that can be used to call the APIs that follow
- **PUT** */api/v1/changePassword*: Change user’s password (working on...... )
  
 ### ....Project on development phase
- **GET** */api/v1/todos?status=[status]*: Get a list of todo items. Optionally, a status query param can be included to return only items of specific status. If not present, return all items
- **POST** */api/v1/todos*: Create a new todo item
- **PUT** */api/v1/todos/:id*: Update a todo item
- **DELETE** */api/v1/todos/:id*: Delete a todo item

## Forget password (woking on......)
work flow:
- */api/v1/forget_password : this end point take body as email. the email verifies with database and set token. Using JavaMailSender it send password reeset link with token. (need to work on token expiration date verification is the end point hit more than one time.....)
- */api/v1/password-reset?token=1234 : this end point have token as param and it also need body with reset password. the token need to verify with expiration_date and resetpassword replaced old password.

![alt text](src\main\resources\static\image\forgotpassword.png)

