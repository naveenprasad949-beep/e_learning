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
