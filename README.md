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

---

//Expectations

* Clean code structure using service-repository pattern
* Consistent API responses using `ApiResponse` wrapper
* Error handling through custom exceptions (e.g., `ResourceNotFoundException`)



//API Endpoints

 User Endpoints

| Method | Endpoint | Description         |
| ------ | -------- | ------------------- |
| POST   | `/users` | Register a new user |

 Cart Endpoints

| Method | Endpoint                | Description                |
| ------ | ----------------------- | -------------------------- |
| GET    | `/carts/{cartId}`       | Retrieve a cart by ID      |
| DELETE | `/carts/{cartId}`       | Clear and delete a cart    |
| GET    | `/carts/{cartId}/total` | Get total amount of a cart |

 Cart Items Endpoints

| Method | Endpoint                                      | Description                             |
| ------ | --------------------------------------------- | --------------------------------------- |
| POST   | `/cartItems/add?cartId=&productId=&quantity=` | Add item to cart (creates cart if null) |
| DELETE | `/cartItems/{cartId}/{itemId}`                | Remove item from cart                   |
| PUT    | `/cartItems/{cartId}/{itemId}?quantity=`      | Update quantity of a cart item          |



