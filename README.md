<h1 align="center"> ms-notes </h1> <br>  

<p align="center">  
 Microservice for CRUD operations with note
</p>  
## Table of Contents

- [Introduction](#introduction)
- [How to build & How to run](#how-to-build--how-to-run)
- [Dependencies](#dependencies)
- [API](#api)


## Introduction
Microservice for customers who can do CRUD operations with Note

## How to build & How to run

```shell script
$ ./gradlew build
```

```shell script
$ docker-compose up
$ java -jar ms-notes.jar
```
First execute docker-compose.yml file and next run application.

## Dependencies

1. MongoDB

## Api
### ms-notes contracts

- #### Get all notes:
    <details>
    <summary>/v1/notes/ - HTTP GET:</summary>

  Doesn't require authentication:

  Return data example:

   ```json
        [
          {
              "id": "643a953b63bdc72755d9532c",
              "content": "First note",
              "likes": 0,
              "createdAt": "2023-04-15T16:14:51.587"
          },
          {
             "id": "643a9038967c4000f963ed2e",
             "content": "Second note",
             "likes": 10,
             "createdAt": "2023-04-14T15:53:28.398"
          }
        ]
   ```
   </details>


- #### Get note by id:
    <details>
    <summary>/v1/notes/{id} - HTTP GET:</summary>

  Doesn't require authentication:

  Return data example:

   ```json
          {
              "id": "643a953b63bdc72755d9532c",
              "content": "First note",
              "likes": 0,
              "createdAt": "2023-04-15T16:14:51.587"
          }
   ```
   </details>


- #### Create a note:
    <details>
    <summary>/v1/notes/{id} - HTTP POST:</summary>
  
  Doesn't require authentication:

  Request body example:
  
  ```json
        {
           "content": "First note"
        }
  ```

  Return data example:

   ```json
         {
              "id": "643a96b2d10e430de993be2a",
              "content": "Alik note",
              "likes": 0,
              "createdAt": "2023-04-15T16:21:06.154169"
        }
   ```
   </details>


- #### Update a note:
    <details>
    <summary>/v1/notes/{id} - HTTP PUT:</summary>

  Doesn't require authentication:

  Request body example:

  ```json
        {
           "content": "Updated note"
        }
  ```

  Return data example:

   ```json
         {
              "id": "643a96b2d10e430de993be2a",
              "content": "Updated note",
              "likes": 0,
              "createdAt": "2023-04-15T16:21:06.154169"
        }
   ```
   </details>


- #### Remove a note:
    <details>
    <summary>/v1/notes/{id} - HTTP DELETE:</summary>

   </details>


- #### Add a like to note:
    <details>
    <summary>/v1/notes/{id}/like - HTTP PUT:</summary>

  Require authentication:

  - username and password

    Return data example:

     ```json
           {
                "id": "643a96b2d10e430de993be2a",
                "content": "Updated note",
                "likes": 1,
                "createdAt": "2023-04-15T16:21:06.154169"
          }
     ```
     </details>


- #### Remove a like from note:
    <details>
    <summary>/v1/notes/{id}/like - HTTP DELETE:</summary>
    Request header: User-Name

  Doesn't require authentication:


  Return data example:

   ```json
         {
              "id": "643a96b2d10e430de993be2a",
              "content": "Updated note",
              "likes": 0,
              "createdAt": "2023-04-15T16:21:06.154169"
        }
   ```
   </details>


- #### Get all users:
    <details>
    <summary>/v1/users - HTTP GET:</summary>

  Doesn't require authentication:

  Return data example:

   ```json
         {
              "id": "643a96b2d10e430de993be2a",
              "content": "Updated note",
              "likes": 0,
              "createdAt": "2023-04-15T16:21:06.154169"
        }
   ```
   </details>



- #### Add a new user:
    <details>
    <summary>/v1/users - HTTP POST:</summary>

  Doesn't require authentication:

  Request body example:

  ```json
         {
            "username": "test6",
            "password": "123"
         }
  ```

  Return data example:

   ```json
         {
              "username": "test5",
        }
   ```
  </details>
