## Environment:
- Java version: 1.8
- Maven version: 3.*
- Spring Boot version: 2.2.1.RELEASE

## Consideraciones especiales
- Por falta de tiempo para realizar el test (trabajo actual y familia), se utilizó archivo de texto plano para almacenar la información. 
- Se desarrollaron enpoints REST para consultar los archivos de datos a traves de algún cliente (ej. postman)
- Se utilizó springboot y springboot security para la capa back y thymeleaf para la capa front (la capa front solo se desarrollo para presentar los datos, no me llevo muy bien con el frontend)

## Data:
Sample example of JSON user data object:
```json
{
    "username": "fmengoni",
    "password": "password",
    "lsRoles": [
      {
        "idRole": 1,
        "description": "Admin"
      },
      {
        "idRole": 2,
        "description": "Usuario Final"
      }
    ]
  }
```

Sample example of JSON meetup data object:
```json
  {
    "idMeetup": 1,
    "participants": 1,
    "meetupDate": "10/01/2021 08:11",
    "location": {
      "idLocation": 3435907,
      "description": "Buenos Aires",
      "lat": -36.0,
      "lng": -60.0
    },
    "weather": {
      "pk": {
        "idLocation": 3435907,
        "date": "10/01/2021"
      },
      "temperature": 25.0
    }
  }
```
  
Sample example of JSON location data object:
```json
  {
    "location": {
      "idLocation": 3435907,
      "description": "Buenos Aires",
      "lat": -36.0,
      "lng": -60.0
    },
  }
```

Sample example of JSON weather data object:
```json
  {
    "weather": {
      "pk": {
        "idLocation": 3435907,
        "date": "10/01/2021"
      },
      "temperature": 66.0
    }
  }
```

## APIRest:
## USER

`POST` request to `/v1/user` :
* creates a new user data record
* the response code is 201 and the response body is the created record, including its unique username


`GET` request to `/v1/user`:
* the response code is 200
* the response body is an array of matching records, ordered by their username in increasing order


`GET` request to `/v1/user/<username>`:
* returns a record with the given id and status code 200
* if there is no record in the database with the given id, the response code is 404


`DELETE` request to `/v1/user/<username>`:
* deletes the record with the given id from the database and return status code 200
* if there was no record in the database with the given id, the response code is 404


## MEETUP
`POST` request to `/v1/meetup` :
* creates a new meetup data record
* the response code is 201 and the response body is the created record, including its unique idMeetup


`GET` request to `/v1/meetup`:
* the response code is 200
* the response body is an array of matching records, ordered by their ids in increasing order


`GET` request to `/v1/meetup/<idMeetup>`:
* returns a record with the given id and status code 200
* if there is no record in the database with the given id, the response code is 404


`DELETE` request to `/v1/meetup/<meetup>`:
* deletes the record with the given id from the database and return status code 200
* if there was no record in the database with the given id, the response code is 404

`GET` request to `/v1/meetup/<meetup>/calcBirras
* returns the amount of birras per meetup

## WEATHER
`GET` request to `/v1/weather/<meetupdate>`:
* Nos informa la temperatura de la ciudad de Buenos aires para la fecha de terminada. Se utilizó el endpoint "16 Day Forecas" del Api de Clim https://rapidapi.com/weatherbit/api/weather

## Commands
- run: 
```bash
mvn clean package; java -jar target/challengeMeetup-1.0.jar
```
- test: 
```bash
mvn clean test
```
- - Para poder ejecutar el test correctamente, se debe limpiar el archivo meetups y users que almacenan los datos cargados

## FORMA DE USO
- ¿Como admin quiero saber cuántas cajas de birras tengo que comprar para poder aprovisionar la meetup?
Una vez iniciado el proyecto con el comando run previamente indicado, se deberá ingresar a la url http://localhost:8080 e ingresar las credenciales del usuario administrador
Usuario: admin Contraseña: admin
Una vez creada, el proceso nos informará la cantidad de Birras y la temperatura del dia seleccionado.


- ¿Como admin y usuario quiero conocer la temperatura del dia de la meetup pra saber si va a hacer calor o no?
Una vez iniciado el proyecto con el comando run previamente indicado, se deberá ingresar a la url http://localhost:8080 e ingresar cualquiera de las siguientes credenciales 
Usuario: admin Contraseña: admin
Usuario: final Contraseña: final
Se deberá ingresra la fecha con formato dd/MM/yyyy. El api seleccionado nos brinda información de los próximos 16 dias, con lo cual se deberá utilizar el rango de fechas - Fecha actual - Fecha actual + 16 dias 

## POSTMAN
A traves de postman se pueden consultar las diferentes meetups creadas utilizando el metodo `GET` request to `/v1/meetup`. El resultado, en formato json, nos indicará la temperatura y la cantidad de birras que se requieren para aprovisionar en cada una de las meetups almacenadas.




