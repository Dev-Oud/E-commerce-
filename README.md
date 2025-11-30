//Ecommerce Backend Service

This is a backend service for an eCommerce web application, built using Spring Boot. It provides REST APIs for user management, cart operations, product handling, and order processing.

//Features

* User Registration 
* Cart Management (Add, Remove, Update Items)
* Product Catalog Management
* Checkout & Order Processing
* Total Price Calculation
* Uses JPA + Hibernate for ORM
* RESTful API design

//Assumptions

* A user can only have one active cart at a time.
* Product stock, order history, and payment processing are to be implemented in the next phase.
* No authentication mechanism is implemented yet (to be added later).
* Carts and Users are loosely coupled for now, can be associated later.



//API Endpoints

 

 Cart Endpoints
                              
GET    `/carts/{cartId}`       
DELETE  `/carts/{cartId}`           
GET     `/carts/{cartId}/total`

 Cart Items Endpoints:
                                                                  
POST    `/cartItems/add?cartId=&productId=&quantity=`
DELETE  `/cartItems/{cartId}/{itemId}`                           |
PUT     `/cartItems/{cartId}/{itemId}?quantity=`     



