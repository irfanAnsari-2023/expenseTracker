# 📄 Product Requirements Document (PRD)

## Project Name
**Expense Tracker API**

## Author
**Irfan Ansari**

## Product Type
Backend REST API

## Version
1.0

## Status
Implemented (Backend Complete)

---

## 1. 📌 Overview

The **Expense Tracker API** is a backend service designed to help users record, manage, analyze, and retrieve personal expense data efficiently.

The system exposes **RESTful APIs** that support expense creation, modification, deletion, and advanced querying such as filtering by category, name, and date range, with pagination support for scalability.

The application is built using **Spring Boot** and follows a **clean layered architecture**, ensuring maintainability, extensibility, and production readiness.

---

## 2. 🎯 Goals & Objectives

### Primary Goals
- Provide a reliable and scalable backend for expense management
- Ensure data integrity and validation at API boundaries
- Offer developer-friendly APIs with proper documentation
- Handle failures gracefully with centralized error handling

### Secondary Goals
- Support analytics-ready data retrieval via filters and date ranges
- Enable easy frontend or mobile app integration
- Maintain clean separation of concerns for future enhancements

---

## 3. 👥 Target Users

| User Type | Description |
|--------|------------|
| End Users | Individuals tracking daily/monthly expenses |
| Frontend Developers | Consuming APIs for web/mobile applications |
| Backend Engineers | Extending APIs (authentication, reports, analytics) |

---

## 4. 🏗️ System Architecture

### High-Level Architecture
- Client (Web / Mobile / Postman)
- REST Controller Layer
- Service Layer (Business Logic)
- Repository Layer (JPA)
- Database (MySQL)


### Architectural Principles
- Layered architecture (Controller → Service → Repository)
- Stateless REST APIs
- Exception-driven error handling
- Validation at API boundaries

---

## 5. 🧱 Core Entities

### Expense Entity

| Field | Type | Description |
|----|----|----|
| id | Long | Unique expense identifier |
| name | String | Expense title |
| description | String | Optional description |
| amount | BigDecimal | Expense amount |
| category | String | Expense category (Food, Travel, etc.) |
| date | Date | Date of expense |
| createdAt | Timestamp | Auto-generated creation timestamp |
| updatedAt | Timestamp | Auto-updated modification timestamp |

---

## 6. 🔑 Functional Requirements

### 6.1 Expense Management (CRUD)

#### Create Expense
- Accept expense details via request body
- Validate mandatory fields
- Persist expense to database
- Return created expense

#### Read Expense
- Fetch all expenses with pagination
- Fetch expense by ID
- Return `404 NOT FOUND` if expense does not exist

#### Update Expense
- Update expense by ID
- Support partial updates
- Preserve unchanged fields

#### Delete Expense
- Delete expense by ID
- Delete all expenses (admin/cleanup use case)

---

### 6.2 Advanced Filtering & Search

| Feature | Description |
|------|------------|
| Filter by Category | Retrieve expenses for a specific category |
| Search by Name | Keyword-based search on expense name |
| Date Range Filter | Fetch expenses between start & end dates |
| Default Date Handling | Auto-handle missing date parameters |

---

### 6.3 Pagination Support
- All list endpoints support `Pageable`
- Prevents large dataset overload
- Enables scalable frontend integration

---

## 7. 🛡️ Validation Rules

Validation is enforced using **Jakarta Bean Validation**.

| Field | Rule |
|----|----|
| name | Required, 2–50 characters |
| amount | Required, non-null |
| category | Required, non-blank |
| date | Required |
| description | Maximum 200 characters |

Invalid requests return structured error responses.

---

## 8. ⚠️ Error Handling & Exceptions

### Centralized Exception Management
Implemented using `@ControllerAdvice`.

| Exception | HTTP Status | Description |
|---------|------------|------------|
| ExpenseNotFoundException | 404 | Expense not found |
| Validation Errors | 400 | Invalid request data |
| Type Mismatch | 400 | Invalid parameter type |
| Generic Exception | 500 | Internal server error |

### Error Response Format
```json
{
  "statusCode": 404,
  "msg": "Expense is not found for the id 10"
}

