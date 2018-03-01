# spring-boot-browser-tail
Using Server Sent Events(SSE) this project allows you to tail logs in browser with customisations.


### Deployment

Clone the project and run the application
```
mvn spring-boot:run
```
*You can increase timeout from default `600s` in pom.xml by passing command line parameter -Dtimeout.interval.millis = `x`.*

### Playing Around

After deployment, open up concurrent broswers/tabs
```
http://localhost:8080/logs?name=./files/test.txt
```

and run commands to insert content in log file
```
 cd files
 echo "testing" >> test.txt 
```

Sample run looks like : 
* When everything is fine :
![Image after sample run](https://i.imgur.com/e5eryi1.png)

* When file can't be read :
![File read permissions issue](https://imgur.com/h7JaWK4.png)

* When file doesn't exist : 
![File existance issue](https://imgur.com/GqjZujS.png)

* When file is a directory :
![File directory issue](https://imgur.com/ylTucDS.png)

