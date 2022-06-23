
MYSQL Database Setup:

1) Run the following commands in mySQL shell:

create database dbetes; 
create user 'springuser'@'%' identified by 'password';
grant all on dbetes.* to 'springuser'@'%';S

2) If done correctly the database should be able to connect to Spring Boot. Otherwise refer to:
https://spring.io/guides/gs/accessing-data-mysql/
for a detailed installation guide.

Get dependencies for Vue and Webapp:
Download Node.js from https://nodejs.org/en/

1)Open the Frontend folder in vs code
2)Open a terminal and write
npm i
)3 if propmted to fix vulnerabilities do so

Visual studio should ask if you want to install vue extensions if it doesn't code highlight won't 
but the rest should still work and you can install it yourself by installing volar from the extensions.

Next do eclipse steps

Eclipse:
1) First you need:
JDK 1.8 or later https://adoptopenjdk.net/
Maven 3.2+  
and Eclipse.

2) Import the project by selecting import in eclipse, then under general: choose (Existing projects into Workspace),
import then from \Diabetes-app-project\Backend\Diabietes-Manager.


2) Run application in eclipse

Finally open a terminal in the visual studio code frontend folder and write the following:
npm run serve

It should give you a link to the webapp.
