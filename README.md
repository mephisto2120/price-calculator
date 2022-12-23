# price-calculator
App GUI starts at: 
http://localhost:8080/swagger-ui/index.html

Adding a product:
curl -X 'POST' \
'http://localhost:8080/shop/v1/products' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"id": "abc-123",
"description": "desc",
"createdAt": "2022-12-22T18:39:30.354Z",
"createdBy": "mephisto2120",
"modifiedAt": "2022-12-22T18:39:30.354Z",
"modifiedBy": "mephisto2120",
"name": "anything",
"price": 20.00
}'


Getting product by id:
curl -X 'GET' \
'http://localhost:8080/shop/v1/products?uuid=abc-123' \
-H 'accept: application/json'



curl -X 'PUT' \
'http://localhost:8080/shop/v1/discount-policy/amount' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"id": "def-456",
"description": "default amount policy",
"createdAt": "2022-12-23T05:57:21.569Z",
"createdBy": "mephisto2120",
"modifiedAt": "2022-12-23T05:57:21.569Z",
"modifiedBy": "mephisto2120",
"discountMap": {
"5": 5,
"10": 20,
"50": 30
}
}'


Percentage based:
curl -X 'PUT' \
'http://localhost:8080/shop/v1/discount-policy/percentage' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"id": "default-policy",
"description": "default settings",
"createdAt": "2022-12-23T07:23:09.293Z",
"createdBy": "mephisto2120",
"modifiedAt": "2022-12-23T07:23:09.293Z",
"modifiedBy": "mephisto2120",
"discount": 20
}'


curl -X 'GET' \
'http://localhost:8080/shop/v1/discount-policy/amount' \
-H 'accept: application/json'


curl -X 'GET' \
'http://localhost:8080/shop/v1/price-calculator/calculate?productId=abc-123&amount=5' \
-H 'accept: application/json'