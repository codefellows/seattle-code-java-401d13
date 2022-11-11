# Authentication with Java BCrypt

This application is a simple experimentation with the `bCrypt` technology that allows for the hashing of passwords. This application allows for the user to create an account and store their password securely on a database. Once a user has created an account, they can login and be navigated to a recipe page that contains their own, super secret recipe, collection.

## Requirements

Access to localhost:8080 (this can be changed by in the repo). In addition, the user will need to have an external database that they can wire into the application properties file under resources.

Run the application using `./gradlew bootRun`.
