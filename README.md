# 🛒 Product Selling Platform - Spring Boot Backend

This backend API enables product selling functionality for two user types:
- **Admin**: Can manage products and view all customer checkouts.
- **Customer**: Can browse products, purchase them, and perform checkout.

---

![image](https://github.com/user-attachments/assets/62a6588a-dd76-4c5e-a443-dc9f3853c102)


## 🔐 Authentication (`/auth`)

### `POST /auth/register/admin`
- **Purpose:** Register a new Admin user.
- **Body:**
- 
{
  "username": "admin123",
  "password": "adminpass"
}
### POST /auth/register/customer
Purpose: Register a new Customer user.

Body:

json

{
  "username": "john_doe",
  "password": "123456"
}
### POST /auth/login
Purpose: Authenticate user and receive JWT token.

Body:

json
{
  "username": "john_doe",
  "password": "123456"
}
Response:

Login Successful!
Username: john_doe
Role: ROLE_CUSTOMER
Token: <JWT_TOKEN>


## 🛍️ Product Management (/products)
POST /products/create (Admin only)
Purpose: Add a new product.

Body:

json
{
  "name": "Headphones",
  "description": "Wireless Bluetooth",
  "price": 2500.0,
  "status": "AVAILABLE"
}
GET /products
Purpose: Get all available products.

Access: Public

### GET /products/{id}
Purpose: Fetch a product by its ID.

### PUT /products/update/{id} (Admin only)
Purpose: Update product details.

### DELETE /products/delete/{id} (Admin only)
Purpose: Delete a product.

### GET /products/checkout (Customer only)
Purpose: View current user's checkout summary with all purchases, total amount, and stored contact details.

Returns:

json
{
  "name": "John",
  "contactNumber": "9999999999",
  "address": "Pune",
  "totalAmount": 5000,
  "purchases": [ ... ]
}


## 🧾 Purchase APIs (/purchases)
### POST /purchases/{productId}/buy?quantity=2 (Customer only)
Purpose: Buy a product in desired quantity.

Returns: Purchase object with product and quantity.

### GET /purchases/my (Customer only)
Purpose: View all purchases by the logged-in customer.

## 📦 Checkout (/checkout)
### POST /checkout/submit (Customer only)
Purpose: Final checkout. Saves name, contact number, and address.

Body:

json
{
  "name": "John",
  "contactNumber": "9999999999",
  "address": "Baner, Pune"
}
Returns: "Checkout completed successfully ✅"

## 📊 Admin Dashboard (/admin)
### GET /admin/checkout-summary (Admin only)
Purpose: See list of all customers who submitted checkout with their purchases and total.

Returns:

json
{
  "name": "John",
  "contactNumber": "9999999999",
  "address": "Baner, Pune",
  "totalAmount": 5200,
  "purchases": [ ... ]
}


## ✅ Tech Stack
Spring Boot

Spring Security + JWT

Spring Data JPA

MySQL

Postman Tested



## 📁 Project Structure
arduino
src/
├── controller/
├── model/
├── repository/
├── service/
├── config/
└── util/


## 🔐 Security
Role-based access (ADMIN, CUSTOMER)

JWT secured routes

Public access: Register, Login, Get all products



## 🔗 Author
Created by Shree
A complete product selling backend platform with:

Secure APIs

Purchase & Checkout system

Role-based access

Admin dashboard integration

