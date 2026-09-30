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

## 📍 Latest Checkpoint

**Checkpoint:** Integration Testing với MySQL + Testcontainers  
**Status:** 🟢 Completed  
**Testing:** 26/26 PASS  
**Next:** Swagger / OpenAPI

---

## ⏭️ Kế hoạch tiếp theo

### Swagger / OpenAPI
- Tích hợp Swagger/OpenAPI
- Document REST API
- Document Request/Response DTO
- Document HTTP status codes và validation errors
- Sử dụng Swagger UI để test API

### Sau Swagger
- Spring Security / Authentication
- Authorization
- Database Migration
- Docker hoàn thiện
- Backend features nâng cao