# TaskFlow - Tiến độ dự án
- **Branch hiện tại:** `feature/spring-boot`
- **Database:** MySQL 8.4 (Docker)
---
## 📌 Trạng thái công việc
- **Spring Boot + JPA + REST:** 🟡 In Progress
- **CRUD API:** 🟢 Completed
- **TaskNotFoundException + Xử lý lỗi 404:** 🟢 Completed
- **DTO + Validation:** 🟢 Completed
- **TaskMapper:** 🟢 Completed *(Đã commit & push)*
- **Swagger / OpenAPI:** 🟢 Completed
---
## 🧪 Testing

### Postman
🟢 Completed
- GET /api/tasks
- GET /api/tasks/{id}
- POST /api/tasks
- PUT /api/tasks/{id}
- DELETE /api/tasks/{id}
- HTTP 404 khi Task không tồn tại
- HTTP 400 khi Validation không hợp lệ

### Unit Testing — JUnit 5 + Mockito
🟢 Completed
- AppTest: 1/1 PASS
- TaskServiceTest: 6/6 PASS
- TaskControllerTest: 5/5 PASS
- Subtotal: 12/12 PASS

### Integration Testing — H2
🟢 Completed
- TaskIntegrationTest: 7/7 PASS
- H2 in-memory database
- Kiểm tra Controller → Service → Repository → Database

### Integration Testing — MySQL + Testcontainers
🟢 Completed
- MySQLIntegrationTest: 7/7 PASS
- MySQL 8.4 (`mysql:8.4`)
- Testcontainers
- Database test chạy trong container riêng
- Không ảnh hưởng MySQL development

### Testing Summary
**Total: 26/26 tests PASS**
- Failures: 0
- Errors: 0
- Skipped: 0
- Build: BUILD SUCCESS

---

## 📖 Swagger / OpenAPI
🟢 Completed
- Đã tích hợp SpringDoc OpenAPI (`org.springdoc:springdoc-openapi-starter-webmvc-ui:3.1.1`)
- Đã tạo cấu hình: `src/main/java/com/taskflow/config/OpenApiConfig.java`
- Đã document REST API cho TaskController bằng OpenAPI annotations (`@Tag`, `@Operation`, `@ApiResponses`, `@Parameter`):
  - GET /api/tasks
  - GET /api/tasks/{id}
  - POST /api/tasks
  - PUT /api/tasks/{id}
  - DELETE /api/tasks/{id}
- Đã document Request/Response DTO bằng `@Schema`:
  - `TaskRequest`
  - `TaskResponse`
- Đã document HTTP status codes:
  - HTTP 200 (Success)
  - HTTP 400 Validation Error
  - HTTP 404 Task Not Found
- Swagger UI đã được cấu hình và có thể sử dụng để test API
- URLs:
  - Swagger UI: http://localhost:8080/swagger-ui/index.html
  - OpenAPI JSON: http://localhost:8080/v3/api-docs

---

## 📍 Latest Checkpoint

**Checkpoint:** Swagger / OpenAPI  
**Status:** 🟢 Completed  
**Testing:** 26/26 PASS  
**Next:** Spring Security / Authentication

---

## ⏭️ Kế hoạch tiếp theo

### Spring Security / Authentication
- Tìm hiểu Spring Security
- Authentication
- Password hashing
- Login
- JWT Authentication

### Sau Authentication
- Authorization
- Database Migration
- Docker hoàn thiện
- Backend features nâng cao