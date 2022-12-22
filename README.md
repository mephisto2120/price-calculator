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