# **Banking Project**

A simple banking system with the following features:

1. **Deposit**
2. **Withdraw**
3. **Print Balance**
4. **Transfer Funds**
5. **Exit**

---

## **Project Setup**

The project was initialized using [Spring Initializr](https://start.spring.io) by creating a zip file and uploading it to the **banking app** folder.

---

## **Dependencies:**

- **Spring Boot Starters:**
  - `spring-boot-starter-data-jdbc`
  - `spring-boot-starter-web`
  - `spring-boot-starter-test`
  - `spring-boot-maven-plugin`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-thymeleaf`

- **Database:**
  - `postgresql database` (changed from `H2 database`)

- **For Logging:**
  - `org.slf4j` maybe later change to `log4j`
  - `ch.qos.logback`
 
- **For Swagger:**
  - `springdoc-openapi-ui`

- **Extra:**
  - `hibernate-validator`
  - `jakarta.persistence-api`

---

## **To Do:**

- Set up database different than H2
- Implement Swagger for front-end API documentation
+ Implement **GET** and **POST** requests
- Automate tests using **Selenium WebDriver**
- JUnit tests

---

### **Installation:**

1. Clone the repository:
   ```bash
   git clone https://github.com/aliva2/Banking.git
