Este Proyecto consta de dos parte, Backend y Frontend.

  Frontend: https://github.com/Dan52w/Front-Aerolinea

Para poder crear la imagen con la base de datos en Docker usar el siguiente comando:

  docker run --name postgres-pweb -e POSTGRES_PASSWORD=1234 -e POSTGRES_USER=dan -e POSTGRES_DB=Airline -p 15432:5432 -d postgres

Ahora para la insercion de datos se puede usar Postman, para ingresar primarios como lo serian algunos usuarios, Aeropuertos, Aerolineas... etc.
Dejare unos ejemplos para el uso de estos:

Lo primero seria Registrar un usuario:

Registrarse:
  
  {
  "username": "newuser123",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "123456789",
  "address": "123 Main St",
  "dob": "2000-01-01"
  }
  
Lo segundo podria ser Logianor para poder obtener el token de seguridad:

  {
      "username":"newuser123",
      "password":"password123"
  }
  
Despues de esto podemos iniciar con la insertar mas datos de la siguiente manera:
  1. Aerolinea:

     {
        "name": "Lufthansa",
        "codeAirline": "LH",
        "countryOrigin": "Germany"
     }
     
  3. Aeropuerto:
     
     {
        "name": "Berlin Brandenburg Airport",
        "city": "Berlin",
        "country": "Germany"
     }
    
  4. Vuelo:

     {
        "origin": "Mexico",
        "destination": "Berlin",
        "departureDate": "2024-12-11T13:00:00",
        "timeArrival": "2024-12-12T06:00:00",
        "duration": "PT21H",
        "ability": 220,
        "airportOrigin":1,
        "airportDestiny":2,
        "airline":1
      }
     
  5. Pasajero:

     {
        "firstName":"Juan",
        "lastName":"Perez",
        "age":20,
        "identification":123,
        "email":"perez@juan.com",
        "phone":1204,
        "gender":"Masculino",
        "flightdate":"2024-12-11T13:00:00"
      }
     
  7. Reserva:

     {
        "iduser": 1,
        "reservationDate" : "2024-12-11T13:00:00",
        "numberPassengers" : "1"
      }
     
De esta manera usted podria guardar datos en la base de datos, recuerde que debe tener la imagen de Docker y la API en ejecucion para que esto funcione.
