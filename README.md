# Shared / Remaining Code

This folder contains project-wide code and functionality not assigned as one of the six PDF member modules. Payment Records are deliberately kept here, as requested. Authentication, security, shared base entity, global exception handling, demo data and the Spring Boot entry point are also shared.

**Important:** These are reference copies. The authoritative, fully integrated application is `LearnHub-IntelliJ-Project` at the ZIP root. Open that folder in IntelliJ to run the complete site and preserve the original UI.

- `entity/BaseEntity.java`
- `entity/Payment.java`
- `controller/PaymentController.java`
- `controller/AuthController.java`
- `repository/PaymentRepository.java`
- `service/PaymentService.java`
- `service/AuthService.java`
- `service/CrudService.java`
- `dto/PaymentDto.java`
- `dto/AuthDtos.java`
- `security/CurrentUser.java`
- `security/SecurityConfig.java`
- `exception/ApiExceptionHandler.java`
- `config/DemoData.java`
- `LearnHubApplication.java`
# Course Feedback / Reviews

The existing project stores feedback/support in Tickets (subject, message, status, reply). Map feedback comments and review handling here; do not rename endpoints without coordinating the team.
# Homework / Assignments

The existing project models lessons/assignment instructions with the Lesson module. Map this module to the assignment tasks described in the PDF.
# Course Enrollment

Create/view/search/update/drop course enrollments.
# Course Notes / Study Materials

Add, view/search, edit and delete course study materials/notes.
# Student Account Management + Home/Landing Page

Student registration, list/search, edit and delete. Home page/landing page ownership is also assigned to Member 1. The original UI files are copied unchanged for reference.

## Files in this member folder
These are reference copies of the corresponding files from the complete working IntelliJ project. Keep Java package names (`com.learnhub...`) unchanged when merging changes.

- `entity/Ticket.java`
- `controller/TicketController.java`
- `repository/TicketRepository.java`
- `service/TicketService.java`
- `dto/TicketDto.java`
- `entity/Lesson.java`
- `controller/LessonController.java`
- `repository/LessonRepository.java`
- `service/LessonService.java`
- `dto/LessonDto.java`
- `entity/Enrollment.java`
- `controller/EnrollmentController.java`
- `repository/EnrollmentRepository.java`
- `service/EnrollmentService.java`
- `dto/EnrollmentDto.java`
- `entity/Note.java`
- `controller/NoteController.java`
- `repository/NoteRepository.java`
- `service/NoteService.java`
- `dto/NoteDto.java`
- `entity/Customer.java`
- `controller/CustomerController.java`
- `repository/CustomerRepository.java`
- `service/CustomerService.java`
- `dto/CustomerDto.java`

## Home page UI files (original copies; unchanged)
- `home-page-ui/index.html`
- `home-page-ui/app.js`
- `home-page-ui/main.css`

## Git workflow
Create a branch such as `member-1-student-home`, commit only your work, then push and open a Pull Request. The full app remains integrated in `../..` at `LearnHub-IntelliJ-Project`; these folders are split/reference copies, not six separately runnable Spring Boot apps.
