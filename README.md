# BiddingSystem

Infrastructure Requirements : 

Maven 3.6 - Build the project and run tests
Java 11 - Run the Service
cURL - Test the service


Techstack chosen and reasons : 

1. The project was created in mind that it can be deployed without any external dependencies. 
2. Springboot framework with web starter kit used for easy setup of REST APIs. Testcases are written in JUNIT 5
3. JPA with H2 database is used to keep the data (Auction and User Store) in memory so doesn't require mySQL setup. The solution is expandable and can easily incorporate changes to use an actual database storage. 


Steps to build and run the project -
1. mvn install -> this should generate an executable jar file in the target folder

2. java -jar target/BiddingSystem-0.0.1-SNAPSHOT.jar --> Run the service on localhost:8080
  a. This will create 100 random bids with varying minimum bid amount
  b. 2 users will be created with UserId, Token pairs as - (1,"abc") and (2, "xyz")

3. Ideally for authentication of the bidding endpoint, another service which provides OAuth2 tokens after user onboarding and authorization. For simplicity sakes, I validate the users by checking if the userId, UserToken pair is already in the system.

4. To see the running auctions, use 
```curl localhost:8080/auction``` This gives 20 auctions. To go to more pages of auctions or to specify the size of list, use 'page' and 'size'
```localhost:8080/auction?page=0&size=5```

This is how the response would be - 
{"content":[{"id":1,"minimumBasePrice":100,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":2,"minimumBasePrice":1000,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":3,"minimumBasePrice":600,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":4,"minimumBasePrice":800,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":5,"minimumBasePrice":200,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":6,"minimumBasePrice":300,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":7,"minimumBasePrice":100,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":8,"minimumBasePrice":700,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":9,"minimumBasePrice":700,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":10,"minimumBasePrice":1000,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":11,"minimumBasePrice":700,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":12,"minimumBasePrice":200,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":13,"minimumBasePrice":400,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":14,"minimumBasePrice":1000,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":15,"minimumBasePrice":700,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":16,"minimumBasePrice":100,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":17,"minimumBasePrice":900,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":18,"minimumBasePrice":400,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":19,"minimumBasePrice":600,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0},{"id":20,"minimumBasePrice":800,"highestBidPlaced":0,"stepRate":100,"isRunning":"RUNNING","version":0}],"pageable":{"sort":{"unsorted":true,"sorted":false,"empty":true},"offset":0,"pageNumber":0,"pageSize":20,"paged":true,"unpaged":false},"totalPages":5,"totalElements":100,"last":false,"size":20,"number":0,"sort":{"unsorted":true,"sorted":false,"empty":true},"numberOfElements":20,"first":true,"empty":false}


5. To bid on the auction, you need to provide a valid userId, userToken in headers along with itemCode (as part of resource url) and bid amount.

```curl -i -H "userid: 101" -H "usertoken: abc" localhost:8080/auction/1/bid -d bidAmount=900```

Response : 

HTTP/1.1 201 
Content-Type: text/plain;charset=UTF-8
Content-Length: 15
Date: Fri, 11 Sep 2020 10:58:27 GMT

Bid is Accepted

AuthenticatorService will give 401 if the headers are not present.

6. Conflicts are handled using Optimistic Locking. If there's a race condition, competing bid is rejected.

7. Wavefront dashboard to monitor - https://wavefront.surf/dashboards/integration-spring-boot#_v01(g:(d:7200,ls:!t,s:1599834594,w:'2h'),l:(integration-spring-boot-4-3-2:(d:43200,s:1599798562)),p:(application:(d:Label,f:TAG_KEY,k:application,l:Application,m:(Label:'',Label_S:unnamed_application),q:'ts(%22process.uptime%22%20and%20source=%22$%7Bsource%7D%22)',s:Label_S,tbr:''),filter:(h:t,v:'%20and%20source=%22$%7Bsource%7D%22%20and%20application=%22$%7Bapplication%7D%22%20and%20service=%22$%7Bservice%7D%22'),service:(d:Label,f:TAG_KEY,k:service,l:Service,m:(Label:'',Label_S:unnamed_service),q:'ts(%22process.uptime%22%20AND%20source=%22$%7Bsource%7D%22%20AND%20application=%22$%7Bapplication%7D%22)',s:Label_S,tbr:''),source:(d:Label,f:SOURCE,k:'',l:Source,m:(Label:mrinals-macbook-pro.local),q:'ts(%22process.uptime%22)',tbr:'')))

