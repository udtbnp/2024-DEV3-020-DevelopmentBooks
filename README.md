# 2024-DEV3-020/DevelopmentBooks

## prerequies
use java 21 (21.0.2-open)
gradle 

## testing
./gradlew test

## run 
./gradlew bootRun

## webservice  
`curl -i -X POST -H "Content-Type:application/json" -d '{ "quantities":[ { "bookId":"book1", "quantity":2 }, { "bookId":"book2", "quantity":2 }, { "bookId":"book3", "quantity":2 }, { "bookId":"book4", "quantity":1 }, { "bookId":"book5", "quantity":1 } ]}' 'http://localhost:8080/books/submitBasket'`

bookIds to use : "bookId1", "bookId2", "bookId3", "bookId4", "bookId5"
anything else will not validate the basket.
If basket valid, amount is calculated.
