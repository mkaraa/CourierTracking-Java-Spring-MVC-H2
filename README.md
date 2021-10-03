## Courier Tracking Project

You can clone / download a project directly and only execute. <br>
<br>
Language: Java<br>
Framework : Spring <br>
IDE: Intellij Idea <br>
Database: H2SQL <br>
-  username : trackinguser
-  password : trackingpassword

## DESCRIPTION
All stores are loading at the beginning of the project.
Logging all courier information with movements and entrance of stores. If any courier reenter any store  under one minute, it is not showing in logs. Creating a courier at first, his total distance is accepted zero (0). While he is moving,a total distance calculates and adding to total distance. Besides, unit tests are included.   

## USAGE OF APIs
### courier/create
- http://localhost:8081/courier/create <br>
<br>

**input like:**

```json
POST 
Content-Type: application/json

{
 {
     "personalNumber": "1231242ABC1231",
     "code": "Migros12",
     "lat": 40.986106,
     "lng": 29.1161293
 }
}
```

**output like:**
```json
POST
Content-type: application/json
{
 {
    "id": "e45e2c48-ef82-4919-a048-e9be656b8cc6",
    "personalNumber": "1231242ABC1231",
    "code": "Migros12",
    "lat": 40.986106,
    "lng": 29.1161293,
    "totalDistance": 0.0
  }
}
```  

**if the same courier created again, output like**
```json
POST
Content-type: application/json
{
 {
     "errorCode": 400,
     "errorMessage": "There is already same courier exists!"
 }
}
```     

### courier/move 
Update courier location
<br>

- http://localhost:8081/courier/move

**input like:**
```json
POST
Content-type: application/json
{
 {
    "courierId": "31697d51-20e4-4343-bf56-00d15e9af6c2",
    "lat": 40.986106,
    "lng": 28.1161293
 }
}
``` 

**output like:**
```json
POST
Content-type: application/json
{
 {
    "id": "f9f748ef-5b2a-4029-ac88-5e19701eb4d7",
    "personalNumber": "123124ABC1231",
    "code": "Migros12",
    "lat": 40.986106,
    "lng": 28.1161293,
    "totalDistance": 83937.22139716914
 }
}
```     

### courier/{courierId}/courier-log 
To list selected courier all movements.
<br>

- http://localhost:8081/courier/{courierId}/courier-log

**output like:**
```json
GET
Content-type: application/json
{
 [
     {
         "lastMovingTime": "2021-10-03T01:24:04.439",
         "storeName": "Beylikdüzü 5M Migros"
     },
     {
         "lastMovingTime": "2021-10-03T01:16:15.037",
         "storeName": "Novada MMM Migros"
     }
 ]
}
```     

### courier/code/{courierCode}
If you want to learn all information of courier by code, use this api.
<br>

- http://localhost:8081/courier/code/Migros12
<br>

**output like:**
```json
GET
Content-type: application/json
{
 {
     "id": "31697d51-20e4-4343-bf56-00d15e9af6c2",
     "personalNumber": "12312342ABC1231",
     "code": "Migros12",
     "lat": 41.0066851,
     "lng": 28.6552262,
     "totalDistance": 38748.68039493758
 }
}
```     

