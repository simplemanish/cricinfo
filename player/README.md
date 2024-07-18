# Steps to install and make changes

1. clone repository
```
	 git clone https://github.com/simplemanish/cricinfo.git
```
2. goto the player folder and execute command 
```
	 mvn install -DskipTests
```
3. execute command to run the application
```
	 java -jar target\player-0.0.1-SNAPSHOT.jar
```
4. Application will get started at port 8080, you can access endpoints
	* Create Player:
```
	 http://localhost:8080/Api/v1/player
```
	* Get Player Details:
```
	 http://localhost:8080/Api/v1/player/1
```
	* Update Player Details:
```	
	http://localhost:8080/Api/v1/player/1
```
	* Delete Player:
```
	 http://localhost:8080/Api/v1/player/1
```
