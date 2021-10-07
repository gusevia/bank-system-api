
Приложение имитирует работу API физического лица банка:

1. Выпуск новой карты по счету
2. Просмотр списка карт клиента
3. Просмотр списка карт по счету
4. Проверка баланса счета

Примеры запросов


// Rest-api URLs exampes

//Accounts

// Баланс счета
GET http://localhost:8080/rest/v1/accounts/1/balance
Accept: application/json
###

// Баланс счета при неправильном accountId= 0
GET http://localhost:8080/rest/v1/accounts/0/balance
Accept: application/json
###
// Баланс счета при несуществующем accountId= 55
GET http://localhost:8080/rest/v1/accounts/55/balance
Accept: application/json
###

// Внесение средств на счет с accountId = 5
PUT http://localhost:8080/rest/v1/accounts/5
Content-Type: application/json

{
"amount": 50000
}
###

// Внесение средств на  несуществующий счет с accountId = 5
PUT http://localhost:8080/rest/v1/accounts/5
Content-Type: application/json

{
"amount": 25000
}
###

//Cards

// Список карт по счету accountId =1
GET http://localhost:8080/rest/v1/accounts/1/cards
Accept: application/json
###

// Список карт по счету, в котором не карт
GET http://localhost:8080/rest/v1/accounts/2/cards
Accept: application/json
###

// Список  карт пользователя
GET http://localhost:8080/rest/v1/cards
Accept: application/json
###

// новая карта по счету
POST http://localhost:8080/rest/v1/accounts/1/cards
Content-Type: application/json

{}
###

// новая карта по несуществующему счету
POST http://localhost:8080/rest/v1/accounts/100/cards
Content-Type: application/json

11111



{}
#### bank-system-api
