Jovan Djurovic 01-18
Web i Mobilne Aplikacije Zavrsni Projekat

Pokretanje frontenda:
cd   {projekat}/src/ui 
Npm install 
Npm run serve

Pokretanje backenda
cd   {projekat}
mvn clean install
java -jar target/users-0.0.1-SNAPSHOT.jar

Pristup frontendu: http://localhost:3000
Pristup backendu (bazi): http://localhost:8080/h2-console (username: "sa" password: "password")