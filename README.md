# 🛠️ Workforce Management Task API

A RESTful API built with Spring Boot to manage tasks assigned to staff members — including task creation, assignment, status updates, comments, filtering, and deletion.

---
All files can be seen in this path : "_workforcemgmt\workforcemgmt\src\main\java\com\railse\hiring\workforcemgmt"
## 📦 Features

- Create, update, and delete tasks  
- Assign tasks to staff  
- Update task status (ACTIVE, IN_PROGRESS, COMPLETED, CANCELLED)  
- Add comments  
- Filter by priority  
- Filter by date range  
- View all or specific tasks  

---

## ⚙️ Tech Stack

- Java 17+  
- Spring Boot  
- Maven  
- JPA (with in-memory DataStore)  
- Postman or `curl` for testing  

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/workforce-mgmt-api.git
cd workforce-mgmt-api
2. Build the Project
mvn clean install

3. Run the App
mvn spring-boot:run
Server starts at: http://localhost:8080

🛠️ API Endpoints
🔹 1. Create a Task
POST /tasks

{
  "title": "Prepare Sprint Report",
  "startDate": "2025-08-02",
  "dueDate": "2025-08-05",
  "priority": "LOW",
  "staffId": "staff002"
}
🔹 2. Get All Tasks
GET /tasks

🔹 3. Get Task by ID
GET /tasks/{id}

🔹 4. Assign Task to Another Staff
POST /tasks/{id}/assign/{staffId}

🔹 5. Update Task Status
POST /tasks/{id}/status?value=IN_PROGRESS

Status options: ACTIVE, IN_PROGRESS, COMPLETED, CANCELLED

🔹 6. Complete a Task
POST /tasks/{id}/complete

🔹 7. Add Comment
POST /tasks/{id}/comments?text=Finished%20ahead%20of%20schedule

🔹 8. Filter by Priority
GET /tasks/priority?value=HIGH

🔹 9. Filter by Date Range
GET /tasks/range?from=2025-08-01&to=2025-08-10

🔹 10. Delete a Task
DELETE /tasks/{id}

✅ Example Workflow
Test this sequence using Postman or curl.

# 1. Create a Task
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Integrate Payment Gateway","startDate":"2025-08-03","dueDate":"2025-08-10","priority":"MEDIUM","staffId":"staff003"}'

# 2. Get All Tasks
curl http://localhost:8080/tasks

# 3. Update Status
curl -X POST "http://localhost:8080/tasks/<TASK_ID>/status?value=IN_PROGRESS"

# 4. Complete Task
curl -X POST http://localhost:8080/tasks/<TASK_ID>/complete

# 5. Add Comment
curl -X POST "http://localhost:8080/tasks/<TASK_ID>/comments?text=Great%20job"

# 6. Filter by Priority
curl "http://localhost:8080/tasks/priority?value=MEDIUM"
🧪 Sample Data to Use in Postman
json
Copy code
{
  "title": "Redesign Login Page",
  "startDate": "2025-08-02",
  "dueDate": "2025-08-08",
  "priority": "HIGH",
  "staffId": "staff007"
}
📁 Project Structure

src/
├── controller/
│   └── TaskController.java
├── service/
│   └── TaskService.java
├── dto/
│   └── CreateTaskRequest.java
│   └── TaskDto.java
├── model/
│   └── Task.java
│   └── Comment.java
│   └── Activity.java
├── util/
│   └── TaskStatus.java
└── WorkforceMgmtApplication.java
👨‍💻 Author
Ronit — HackX Winner | AI/ML Engineer | Full Stack Developer

📜 License
This project is open-source and free to use under the MIT license.






