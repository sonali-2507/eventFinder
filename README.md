# Getting Started

### Event finder
For further reference, please consider the following sections:
Software required:
* Java 17 or higher
* Maven 3.6.3 or higher
* MySQL 8.0.26 or higher
* Postman

### Added dependencies
* Spring Boot DevTools
* Spring Web
* Spring Data JPA
* MySQL Driver
* Spring boot starter test
* Lombok
* Spring configuration processor

### Guides
The following guides illustrate how to use some features concretely:

* [Github repository link](https://github.com/sonali-2507/eventFinder/tree/main/location)
* Download csv file from provided document link(https://drive.google.com/drive/folders/1V5acaXj0cuI9bItZRe5ZNCn9cbqrJYwM)
* Import csv file to MYSQL database
* Set up the database in application.properties file


### End points
* POST localhost:8080/events/find
request body:
```json
{
  "query":"2024-03-15",
  "pageNumber":0,
  "pageSize":10,
  "sortValues":[{"sortField": "date","sortType":"ASC"}
  ]
}
```
* POST localhost:8080/location/save
request body:
```json
{

  "event_name":"Holi6",
  "city_name":"nasik1",
  "date":"2024-03-26",
  "time":"10:30:00",
  "latitude":38.33354302,
  "longitude":157.9579286

}
```
* GET localhost:8080/location
request body:
```json
{
  "latitude":40.7128,
  "longitude":-74.0060,
  "date":"2024-03-15"
}
```
### Challenge faced
* I have faced challenges in writing queries based on date intervals because of a conflict between the Java date library and SQL date library.
* I have faced a challenge in mapping the fetch data from weather API & distance API to Java object.
* I have faced the challenge of mapping the page fetch from the database to the response object page.
### Achievements
* Reduced response time from 19s to 5s by following approaches.
* Created index on date column of table location_info 
* Applied pagination and sorting.


