# JWT-Authentication
![Json Web Token](https://miro.medium.com/max/1000/1*9pWUdYPAwCQoWdEnvZli4A.jpeg)
>## Project Architecture

**The project is based on three-layers architecture and respect SOLID principles.**
<br/><br/>
***- Repository(DAO) layer***
<br/>
*This layer of the program is only responsible for selecting data from the database and should not depend on other layers.*
<br/><br/>
***- Service layer***
<br/>
*The service layer is responsible for executing the entire business logic of the project. Uses the dependencies of the DAO layer.*
<br><br/>
***- Controller layer***
<br/>
*This layer is responsible for handling HTTP requests from the user. It uses the service layer dependencies.*

<hr style="background-color: aqua">

>## Instructions for launching


<ol>
    <li>
        Download this project locally and open it in your IDE.
    </li>
    <li>
        Install MySQL and create a jwt_project schema in it.
    </li>
    <li>
        Go to "application.properties" file and change login and password to your own.
    </li>
    <li>
        Run the program.
    </li>
    <li>
        Go to http://localhost:8080 and use any account from ROLES article to login
    </li>
</ol>

<hr style="background-color: aqua">

>## Technologies used

<ul>
    <li>
        Spring Core
    </li>
    <li>
        Spring Web
    </li>
    <li>
        Spring Security
    </li>
    <li>
        Spring JPA Data
    </li>
    <li>
        DB: SQL, MySQL
    </li>
    <li>
        JJWT library
    </li>
    <li>
        HTML, CSS, Thymeleaf
    </li>
    <li>
        VCS: Git
    </li>
</ul>

<hr style="background-color: aqua">

>## Project end-points

### Browser end-points:
<ul>
    <li>
        Login page. (HttpMethod.Get, HttpMethod.POST, "/login") for unauthenticated users
    </li>
    <li>
        Home page. (HttpMethod.GET, "/index" or "/") for any role
    </li>
    <li>
        Launch page. (HttpMethod.GET, "/launch") for any role
    </li>
    <li>
        Technologies page. (HttpMethod.GET, "/launch") for any role
    </li>
    <li>
        Functionality page. (HttpMethod.GET, "/endpoints") for any role
    </li>
</ul>

### Postman end-points:

<ul>
    <li>
        Get all users. (HttpMethod.GET, "/users") for admin role
    </li>
    <li>
        Get user by id. (HttpMethod.GET, "/users/{id}") for admin role
    </li>
    <li>
        Create user. (HttpMethod.POST, "/users") for admin role
    </li>
    <li>
        Update user by id. (HttpMethod.PUT, "/users/{id}") for admin role
    </li>
    <li>
        Delete user by id. (HttpMethod.DELETE, "/users/{id}") for admin role
    </li>
</ul>
