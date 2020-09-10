# BiddingSystem

Project created using STS starter tools

localhost:8080/auction?page=0&size=5&sort=id params to control pagination

(base) mrinalgrover@Mrinals-MacBook-Pro BiddingSystem % curl -i localhost:8080/auction/1/bid -d bidAmount=220
HTTP/1.1 201 
Content-Type: text/plain;charset=UTF-8
Content-Length: 15
Date: Thu, 10 Sep 2020 18:59:29 GMT

Bid is Accepted%                                                                                                                                       (base) mrinalgrover@Mrinals-MacBook-Pro BiddingSystem % curl -i localhost:8080/auction/101/bid -d bidAmount=220
HTTP/1.1 406 
Content-Type: text/plain;charset=UTF-8
Content-Length: 15
Date: Thu, 10 Sep 2020 18:59:37 GMT

Bid is Rejected%   
