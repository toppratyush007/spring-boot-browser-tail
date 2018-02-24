# spring-boot-browser-tail
Using Server Sent Events(SSE) this project allows you to tail logs in browser with customisations.


### Deployment

Clone the project and run the application
```
mvn spring-boot:run
```
*You can also customise filename from `.files/test.txt` and increase timeout from default `60s` in pom.xml.*

### Playing Around

After deployment, open up concurrent broswers/tabs
```
http://localhost:8080/logs
```

and run commands to insert content in log file
```
 cd files
 echo "xyz" >> test.txt 
```

Sample run looks like : 
![Image after sample run](https://i.imgur.com/KEnxZr4.png)
