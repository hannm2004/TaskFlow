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
- **Spring Security Baseline:** 🟢 Completed

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
- TaskIntegrationTest: 12/12 PASS *(updated after Security Checkpoint 01)*
- H2 in-memory database
- Kiểm tra Controller → Service → Repository → Database
- Integration tests chạy qua Spring Security Filter Chain
- @WithMockUser được dùng cho authenticated test cases
- Kiểm tra unauthenticated Task API trả 401
- Kiểm tra Health endpoint public
- Swagger/OpenAPI vẫn hoạt động

### Integration Testing — MySQL + Testcontainers
🟢 Completed
- MySQLIntegrationTest: 12/12 PASS *(updated after Security Checkpoint 01)*
- MySQL 8.4 (`mysql:8.4`)
- Testcontainers
- Database test chạy trong container riêng
- Không ảnh hưởng MySQL development

### Testing Summary (sau Security Checkpoint 01)
**Total: 36/36 tests PASS**
- AppTest: 1/1 PASS
- TaskControllerTest: 5/5 PASS
- TaskServiceTest: 6/6 PASS
- TaskIntegrationTest: 12/12 PASS
- MySQLIntegrationTest: 12/12 PASS
- Failures: 0
- Errors: 0
- Skipped: 0
- Build: BUILD SUCCESS
- H2 integration: PASS
- Testcontainers MySQL 8.4: PASS

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

## 🔐 Security Checkpoint 01 — Spring Security Baseline
🟢 Completed

**Architect:** APPROVED  
**Backend Dev:** PASS  
**QA:** PASS  
**Reviewer:** APPROVED FOR GIT CHECKPOINT

### Những gì đã thực hiện:
- Thêm `spring-boot-starter-security`
- Thêm `spring-security-test`
- Tạo `SecurityConfig`
- REST API chạy stateless
- CSRF disabled
- formLogin disabled
- httpBasic disabled
- Custom `AuthenticationEntryPoint` trả HTTP 401
- `/api/health` public
- `/v3/api-docs/**` public
- `/swagger-ui/**` public
- `/swagger-ui.html` public
- `/api/tasks/**` yêu cầu authentication
- `anyRequest` authenticated
- Integration tests đã chạy qua Spring Security Filter Chain
- `@WithMockUser` được dùng cho authenticated test cases
- Kiểm tra unauthenticated Task API trả 401
- Kiểm tra Health endpoint public
- Swagger/OpenAPI vẫn hoạt động

### Testing (Security Checkpoint 01):
- AppTest: 1/1 PASS
- TaskControllerTest: 5/5 PASS
- TaskServiceTest: 6/6 PASS
- TaskIntegrationTest: 12/12 PASS
- MySQLIntegrationTest: 12/12 PASS
- **Tổng: 36/36 PASS**
- Failures: 0 | Errors: 0 | Skipped: 0
- BUILD SUCCESS

---

## 📍 Latest Checkpoint

**Checkpoint:** Security Checkpoint 01 — Spring Security Baseline  
**Status:** 🟢 Completed  
**Reviewer:** APPROVED FOR GIT CHECKPOINT  
**Testing:** 36/36 PASS  
**Next:** Security Checkpoint 02 — User Entity & Authentication Foundation

---

## ⏭️ Kế hoạch tiếp theo

### Security Checkpoint 02 — User Entity & Authentication Foundation
*(Chưa bắt đầu — kế hoạch)*
- Thiết kế `UserEntity`
- `UserRepository`
- `PasswordEncoder` / BCrypt
- Chuẩn bị User Registration
- Chuẩn bị Login
- Authentication foundation
- *(Sau đó mới tiến tới JWT)*

### Sau Security Checkpoint 02
- JWT Authentication
- Authorization / Role-based access
- Database Migration
- Docker hoàn thiện
- Backend features nâng cao