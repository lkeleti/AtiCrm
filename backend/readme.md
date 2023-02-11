## Minimal CRM system

## Leírás
Ez a CRM rendszer a partnerekkel történő kapcsolattartáshoz készül.

## Használata

### Partner

A `Partner` entitás a következő attribútumokkal rendelkezik:
* `id` (a partner egyedi azonosítója)
* `name` (a partner neve, nem lehet `blank` (üres), valamint `String` típusú, legfeljebb 255 karakter hosszú.)
* `phone` (a partner telefonszáma nem lehet `blank` (üres), valamint `String` típusú, legfeljebb 20 karakter hosszú.)
* `city` (a partner címe nem lehet `blank` (üres), valamint `String` típusú, legfeljebb 20 karakter hosszú.)
* `events` (a események listája `List<Event>` típusú és OneToMany kétirányú kapcsolatban áll az `Event` entitással.)

A következő végpontokon érjük el az entitást:

| HTTP metódus | Végpont               | Leírás                             |  HTTP Response státusz  |
|--------------|-----------------------|------------------------------------|:-----------------------:|
| GET          | `"/api/partner"`      | lekérdezi az összes partnert       |           202           |       
| GET          | `"/api/partner/{id}"` | lekérdez egy partnert `id` alapján |           202           |       
| POST         | `"/api/partner"`      | létrehoz egy partnert              |           201           |       
| PUT          | `"/api/partner/{id}"` | módosít egy partnert `id` alapján  |           202           |       
| DELETE       | `"/api/partner/{id}"` | `id` alapján kitöröl egy partnert  |           204           |       

A `Partner` entitás adatai az adatbázisban a `partners` táblában tárolódnak.

---

### Event - események
Az `Event` entitás a következő attribútumokkal rendelkezik:
* `id` (az eseméy egyedi azonosítója)
* `eventDate` (az esemény dátuma, nem lehet `nonNull` (null értékű), valamint `LocalDateTime` típusú.)
* `comment` (az esemény leírása.)
* `partner` (ManyToOne kétirányú kapcsolatban áll a `Partner` entitással.)


| HTTP metódus | Végpont             | Leírás                                             |  HTTP Response státusz  |
|--------------|---------------------|----------------------------------------------------|:-----------------------:|
| GET          | `"/api/event/{id}"` | lekérdez egy eseményt `id` alapján                 |           202           |          
| POST         | `"/api/event"`      | létrehoz egy eseményt és hozzárendeli a partnerhez |           201           |       
| PUT          | `"/api/event/{id}"` | módosít egy eseményt `id` alapján                  |           202           |       
| DELETE       | `"/api/event/{id}"` | `id` alapján kitöröl egy eseményt                  |           204           |       

Az `Event` entitás adatai az adatbázisban a `events` táblában tárolódnak.

---

## Technológiai részletek

Ez egy klasszikus háromrétegű webes alkalmazás, controller, service és repository
réteggel, entitásonként a rétegeknek megfelelően elnevezett osztályokkal. A megvalósítás
Java programnyelven, Spring Boot használatával történt. Az alkalmazás HTTP kéréseket
képes fogadni, ezt a RESTful webszolgáltatások segítségével valósítja meg.
Adattárolásra SQL alapú MariaDB adatbázist használ, melyben a táblákat Flyway hozza létre.
Az alkalmazás tesztelésére WebClient-tel implementált integrációs tesztek állnak rendelkezésre, a tesztek futtatásakor H2 adatbázis kerül felhasználásra, így az éles adatbázisban tárolt adatok biztonságban vannak a tesztek futtatása alatt is. 
Az adatbáziskezelés Spring Data JPA technológiával történik. A beérkező adatok validálását a
Spring Boot `spring-boot-starter-validation` modulja végzi,
A mellékelt `Dockerfile` segítségével az alkalmazásból Docker image készíthető.

---

## Swagger felület és Open API link

[Swagger UI](http://localhost:8080/swagger-ui.html)

[Open API](http://localhost:8080/v3/api-docs)

---

## MariaDb indítása Dockerben (fejlesztéshez)
`docker run -d -e MYSQL_DATABASE=aticrm -e MYSQL_USER=aticrm -e MYSQL_PASSWORD=aticrm -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -p 3306:3306 --name aticrm-mariadb mariadb`

---

## Virtuális hálózat létrehozás
`docker network create --driver bridge aticrm-net`

## MariaDb indítása Dockerben
`docker run -d -e MYSQL_DATABASE=aticrm -e MYSQL_USER=aticrm -e MYSQL_PASSWORD=aticrm -e MYSQL_ALLOW_EMPTY_PASSWORD=yes --network aticrm-net -p 3306:3306 --name aticrm-mariadb mariadb`

## Az alkalmazás buildelése
`mvnw clean package`

`docker build -t aticrmapp .`

## Az alkalmazás futtatása dockerben MariaDB-vel
`docker run -d -e SPRING_DATASOURCE_URL=jdbc:mariadb://aticrm-mariadb/aticrm --network aticrm-net -p 8080:8080 --name aticrmapp aticrmapp`