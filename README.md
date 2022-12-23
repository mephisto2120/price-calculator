# price-calculator
In order to run application installed JDK 17 is required. 
Installing gradle is optional, because wrapper can be used.

For convenience I would suggest to install sdkman:
https://sdkman.io/

Application was tested on version 17.0.5-amzn.
In order to use it after installation tool only two commands are needed:
sdk install java 17.0.5-amzn
sdk use java 17.0.5-amzn

(or accept version as default after installation)

Application has configured OpenAPI and starts after typing in cmd line:

./gradlew clean build bootRun

For examples below there is assumption that curl is installed otherwise you can use API given by swagger UI.

App GUI starts at: 
http://localhost:8080/swagger-ui/index.html

You can add a product with:
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
"name": "Golden cornflakes"",
"price": 20.00
}'

Getting product by id:
curl -X 'GET' \
'http://localhost:8080/shop/v1/products?uuid=abc-123' \
-H 'accept: application/json'


Defining policies you can do as follows:

Amount based:
curl -X 'PUT' \
'http://localhost:8080/shop/v1/discount-policy/amount' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"id": "default-policy",
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

The default policy is policy with no discount, you can change it to amount policy as follows:

curl -X 'PUT' \
'http://localhost:8080/shop/v1/discount-policy/current' \
-H 'accept: application/json' \
-H 'Content-Type: application/json' \
-d '{
"id": "default-policy",
"description": "selected policy name",
"createdAt": "2022-12-23T08:18:53.793Z",
"createdBy": "mephisto2120",
"modifiedAt": "2022-12-23T08:18:53.793Z",
"modifiedBy": "mephisto2120",
"policyName": "amount"
}'


Calculating price is being realized with api as follows where productId is an id returned by service
curl -X 'POST' \
'http://localhost:8080/shop/v1/products' \ ....


curl -X 'GET' \
'http://localhost:8080/shop/v1/price-calculator/calculate?productId=abc-123&amount=5' \
-H 'accept: application/json'


Containerizing:
App is ready do be containerized. In order to do it you have to use Dockerfile.prod with docker.
(Please note that Dockerfile.prod is unusual name, because of Elastic BeansTalk deployment problem with using just Dockerfile)
docker build -f Dockerfile.prod -t mephisto2120/price-calculator .
docker push mephisto2120/price-calculator

Afterwards you can deploy it in AWS as Elastic BeansTalk with using file
Dockerrun.aws.json

The second possibility:
Create account on travis CI. Travis will deploy it if proper enviroment variables are defined for a job as:
$DOCKER_PASSWORD, $DOCKER_ID for accessing docker repository
$AWS_ACCESS_KEY, $AWS_SECRET_KEY for deploying to S3 service for using Elastic BeansTalk

The third possibility
In folder k8s there is config for kubernetes, kubectl has to be configured to have access to cluster or minikube is started locally:
kubectl apply -f k8s/price-calculator-prod.yaml
