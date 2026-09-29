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
- **Postman:** Đã test thành công tất cả các endpoint:
  - `GET /api/tasks` (GET all)
  - `GET /api/tasks/{id}` (GET by ID)
  - `POST /api/tasks` (Create)
  - `PUT /api/tasks/{id}` (Update)
  - `DELETE /api/tasks/{id}` (Delete)
  - Case trả về HTTP 404 khi không tìm thấy task
---
## ⏭️ Kế hoạch tiếp theo (Next Steps)
- **Unit Testing:** JUnit 5 + Mockito
  - Test `TaskService.getTaskById()`:
    - Case tìm thấy task (Task exists)
    - Case không tìm thấy task -> ném `TaskNotFoundException` (Task not found)