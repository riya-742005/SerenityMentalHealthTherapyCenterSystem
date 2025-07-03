# **🧠 Serenity Mental Health Therapy Center System**

A desktop-based JavaFX application designed to manage login and registration for a mental health therapy center. Built with Java, JavaFX for the UI, Hibernate ORM for database interactions, and MySQL as the backend.

**🎯  Preview** 

![Welcome)](https://github.com/user-attachments/assets/9de704a8-3c93-43e2-8f31-720accda1386)

![Login](https://github.com/user-attachments/assets/44d35538-f588-47c6-b533-dbd6221173f5)

![SignUp](https://github.com/user-attachments/assets/143b6552-9a21-4100-a260-1d35b4971567)

![Dashboard](https://github.com/user-attachments/assets/ee8546e5-c192-4a63-9ce1-f375652558c3)

![Therapist](https://github.com/user-attachments/assets/7534823f-72c8-49a2-bacd-a0a773c1bb1a)

![Registration](https://github.com/user-attachments/assets/66a543e4-85d4-4305-b76e-9a7d457ddf8e)

![Booking](https://github.com/user-attachments/assets/5bdaef11-84aa-4318-8236-3c4c2c3bed63)

![Payment](https://github.com/user-attachments/assets/2d48193d-ec6e-4298-8454-45477f2dea0f)

![Patient](https://github.com/user-attachments/assets/4d902767-2645-4c67-bd14-a143a5f4ac4a)


**✨ Features**
***🧑‍⚕️ Authentication***
Login Functionality with secure credential validation
Signup/Register Page to create a new user profile
Toggle password visibility in login form
Redirects between login and signup pages

***📁 Tech Stack***
Layer	Technology
UI	JavaFX (FXML, SceneBuilder)
Backend Logic	Java (OOP, MVC Pattern)
ORM	Hibernate (JPA)
Database	MySQL
Build Tool	Maven or Gradle (your choice)

***🗂️ Project Structure***
pgsql
Copy
Edit
src/
├── main/
│   ├── java/
│   │   ├── controller/
│   │   │   ├── LoginPageController.java
│   │   │   ├── SignupController.java
│   │   ├── model/
│   │   │   └── User.java
│   │   ├── util/
│   │   │   └── HibernateUtil.java
│   │   └── Main.java
│   ├── resources/
│   │   ├── fxml/
│   │   │   ├── login.fxml
│   │   │   ├── signup.fxml
│   │   ├── css/
│   │   │   └── style.css
│   │   └── images/
│   │       └── (any icons, backgrounds)
│   └── hibernate.cfg.xml

***🛠️ Installation & Setup***
**1. Clone the Repository**
bash
Copy
Edit
git clone https://github.com/your-username/serenity-mental-health.git
cd serenity-mental-health
**2. Configure Database**
Open MySQL Workbench

Run:

sql
Copy
Edit
CREATE DATABASE serenity_mental_health_therapy_center;

**3. Check hibernate.cfg.xml**
xml
Copy
Edit
<property name="hibernate.connection.url">
    jdbc:mysql://localhost:3306/serenity_mental_health_therapy_center?createDatabaseIfNotExist=true
</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">your_password_here</property>

**4. Run the App**
Open in IntelliJ/Eclipse

Run Main.java

App launches with Login → SignUp → Dashboard

***🧾 Scripts & Commands***
Command	Description
mvn clean install	Build the project
Main.java	Launch the application

***📋 Signup Page Details***
Field	Type
Full Name	TextField
Username	Unique
Password	Encrypted
Confirm Pass	Validation

Users are saved in MySQL via Hibernate ORM.

***🧠 Authentication Flow***
Login

User enters credentials

Hibernate verifies against DB

If valid, redirect to dashboard
Signup
User fills form
Stored securely via Hibernate
Can now login with those details

***🎨 UI Design***
Modern JavaFX UI
Responsive layout
Input focus, hover animations
Custom CSS: /resources/css/style.css

***🔐 Security Considerations***
Password masking & toggling
Hibernate PreparedStatements to avoid SQL Injection
Input field validation for username/password length

**📦 How to Push to GitHub**
1. Initialize Git (if not already)
bash
Copy
Edit
git init
2. Add and Commit
bash
Copy
Edit
git add .
git commit -m "Initial commit - JavaFX Hibernate app"
3. Create GitHub Repo & Link
bash
Copy
Edit
git remote add origin https://github.com/your-username/serenity-mental-health.git
git branch -M main
git push -u origin main

**🚀 Deployment**
Since it’s a desktop app:
Build .jar or .exe for distribution using:
javapackager or Launch4j
Host source code on GitHub for others to clone and run

**🤝 Contributing**
Fork the repository
Create your feature branch: git checkout -b feature/signup
Commit your changes: git commit -m 'Add signup logic'
Push and open a pull request

**📄 License**
This project is licensed under the MIT License

**🆘 Support**
If you run into issues:
Ensure MySQL is running on port 3306
Confirm Hibernate config matches your DB
Validate scene redirection logic
Or open an issue on this repo.
