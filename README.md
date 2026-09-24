# Secure Banking Application

## Project Description

The Secure Banking Application is a console-based banking application developed in Java.

The application allows users to register and securely log in, manage their bank account, perform deposits and withdrawals, and view their transaction history.

The application uses text files to persist user credentials, account information, and transaction history. It also demonstrates object-oriented programming principles and secure coding practices.

## Student Details

**Name:** Jemima Rufaro Godzi  
**Registration Number:** H250830M  
**Institution:** Harare Institute of Technology (HIT)

## Technologies Used

- Java
- Java Object-Oriented Programming
- Text File Storage
- PBKDF2 with HMAC-SHA256 for password hashing

## Features

### 1. User Registration

Users can create a new account by providing a username and password.

The application checks whether the username already exists before creating a new user.

### 2. Secure User Authentication

Users can log in using their username and password.

Passwords are not stored as plain text. Passwords are securely hashed using PBKDF2 with HMAC-SHA256 and a randomly generated salt.

The application also limits failed login attempts to three attempts.

### 3. Account Management

Users can:

- Check their account balance
- Deposit money
- Withdraw money

The application prevents invalid transaction amounts and prevents users from withdrawing more money than their available balance.

### 4. Transaction History

The application records deposits and withdrawals and allows users to view their transaction history.

### 5. Data Persistence

The application stores data using text files instead of a database.

The following files are used:

- `users.txt` — stores usernames and hashed passwords
- `accounts.txt` — stores account balances
- `transactions.txt` — stores transaction history

Saved information is loaded when a user logs in again.

## Object-Oriented Programming Principles

The application demonstrates the four main object-oriented programming principles.

### Encapsulation

Sensitive and important data such as the account balance is kept private and accessed through methods.

### Inheritance

The `Account` class extends the `BankAccount` class.

```java
public class Account extends BankAccount