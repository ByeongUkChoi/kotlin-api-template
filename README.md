# Kotlin api template
## 코틀린으로 스프링 프로젝트 만들기
### 개요
코틀린으로 간단한 api 서버를 만든다.  

---

### Spec
주문하기  
주문목록조회  
주문상세조회  

### API Document
#### 주문하기  
##### Request
```http request
POST /orders
X-USER-ID: cbw

{
    "productId": 1,
    "quantity": 2,
    "totalPrice": 2000
}
```
#### Response
```http request
HTTP/1.1 200 Ok
```
#### 주문목록조회  
#### Request
```http request
GET /orders
X-USER-ID: cbw
```
#### Response
```http request
HTTP/1.1 200 Ok
Content-Type: application/json

[
    {
        "id": 1,
        "productId": 1,
        "quantity": 2,
        "totalPrice": 2000
    },
    {
        "id": 2,
        "productId": 2,
        "quantity": 3,
        "totalPrice": 6000
    }
]
```

#### 주문상세조회  
#### Request
```http request
GET /orders/1
```
#### Response
```http request
HTTP/1.1 200 Ok
Content-Type: application/json

{
    "id": 1,
    "productId": 1,
    "quantity": 2,
    "totalPrice": 2000
}
```

### Run (with gradle)
#### test
```shell
./gradlew test
```
#### build
```shell
./gradlew build
```
#### run
```shell
./gradlew bootRun
```
