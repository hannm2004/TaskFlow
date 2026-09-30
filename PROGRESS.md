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
Đã test thành công tất cả các endpoint:
- GET /api/tasks
- GET /api/tasks/{id}
- POST /api/tasks
- PUT /api/tasks/{id}
- DELETE /api/tasks/{id}
- Case HTTP 404 khi không tìm thấy task

### Unit Testing — JUnit 5 + Mockito
🟢 Completed

#### TaskServiceTest — 6/6 PASS
- getTaskById() — Task exists
- getTaskById() — Task not found → TaskNotFoundException
- createTask()
- updateTask()
- deleteTask() — Task exists
- deleteTask() — Task not found → TaskNotFoundException

#### TaskControllerTest — 5/5 PASS
- GET /api/tasks
- GET /api/tasks/{id}
- POST /api/tasks
- PUT /api/tasks/{id}
- DELETE /api/tasks/{id}

#### AppTest — 1/1 PASS

Total: 12/12 tests PASS
Failures: 0
Errors: 0
Skipped: 0

---

## ⏭️ Kế hoạch tiếp theo (Next Steps)

- Integration Testing
  - Kiểm tra sự kết hợp giữa Controller → Service → Repository → MySQL
  - Phân biệt rõ Integration Test với Unit Test
  - Xác nhận API hoạt động với database trong môi trường test
  - Chưa triển khai Integration Test ở thời điểm hiện tại