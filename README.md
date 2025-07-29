CICD - flow
![Screenshot 2024-07-11 at 5 23 56 PM](https://github.com/basahota/aws-cicd/assets/25712816/342e97bf-5fbe-490f-bf76-b5bdd33ce415)

# spring-procedure-function

![Screenshot 2025-01-18 at 2 40 52 PM](https://github.com/user-attachments/assets/1a7681e6-2077-464a-a706-45c9ec253c23)

update_stock:
--------------
```
CREATE PROCEDURE javatechie.update_stock(
	IN productId INT,
    IN quantity INT
    )
BEGIN
	UPDATE product
    SET stockQuantity = stockQuantity - quantity
    WHERE id = productId;
END
```
get_total_price:
----------------
```
CREATE FUNCTION javatechie.get_total_price(productId INT)
RETURNS DECIMAL(10,2)
DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE total DECIMAL(10,2);
    SELECT SUM(price * stockQuantity) INTO total
    FROM Product
    WHERE id = productId;

    RETURN total;
END
```
### Why Use Stored Procedures and Functions?

Complex Logic: If you have complicated SQL operations — like multiple joins or conditional logic — stored procedures can help organize your code.

Performance: Stored procedures sometimes run faster because the database can optimize and cache them.

Security: You can permit to run a stored procedure without allowing direct access to the underlying tables.

Reusability: If many applications or parts of your code need the same SQL logic, you can store it in a procedure or function and call it whenever you want

# 🔁 Transaction Propagation in Spring Boot

## 📘 Overview

**Transaction Propagation** defines how transactional methods behave when called within an existing transaction. It controls whether a method joins an existing transaction or starts a new one.

---

## 🧩 Propagation Types in Spring

| Propagation Type     | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| `REQUIRED`           | Joins the current transaction if it exists; creates a new one otherwise. **(Default)** |
| `REQUIRES_NEW`       | Suspends the current transaction and always starts a new one.               |
| `NESTED`             | Executes within a nested transaction using savepoints (requires JDBC support). |
| `SUPPORTS`           | Joins the current transaction if it exists; otherwise runs non-transactionally. |
| `NOT_SUPPORTED`      | Always runs non-transactionally, suspending any existing transaction.       |
| `NEVER`              | Must run outside of a transaction. Throws exception if a transaction exists. |
| `MANDATORY`          | Must run inside an existing transaction. Throws exception if none exists.   |

---

## ✅ Usage Example

```java
@Service
public class PaymentService {

    @Transactional(propagation = Propagation.REQUIRED)
    public void processPayment() {
        // Joins or starts a transaction
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateAuditLog() {
        // Always runs in a new transaction
    }
}
