# 🖼️ Pinacoteca  
**A modern digital platform for managing and exploring a virtual art gallery!**

![Java](https://img.shields.io/badge/Java-17%2B-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend-success?logo=springboot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-Frontend-red?logo=angular&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Container-Docker-2496ED?logo=docker&logoColor=white)
![Node.js](https://img.shields.io/badge/Node.js-18%2B-brightgreen?logo=node.js&logoColor=white)
![Status](https://img.shields.io/badge/Status-Work%20In%20Progress-orange)
![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20Linux%20%7C%20macOS-blueviolet)

---

## 📖 Project Overview  

**Pinacoteca** is a comprehensive web platform designed to manage and explore an online art gallery.  
It allows visitors to browse exhibits, curators to manage artworks and artists, and administrators to oversee the entire system.  

The project is built with **Java (Spring Boot)** for the back-end, **Angular** for the front-end, and **PostgreSQL** as the database engine.

---

## 🎯 Main Features  

### 👤 Guest Users  
- Browse exhibitions and gallery rooms  
- View details of artworks and artists  

### 🔐 Registered Users  
- Access the gallery with a personalized experience  
- Save favorite artworks and exhibitions (feature under development)  

### 🧑‍🎨 Curators  
- Manage the content of their assigned gallery rooms  
- Add, edit, or remove **artworks** and **artists**  
- Update room descriptions and exhibit details  

### 🛡️ Administrators  
- Full control of the platform  
- Add, edit, or delete **artworks**, **artists**, and **rooms**  
- Manage and ban registered users  
- Assign or remove curator privileges  
- Oversee the entire gallery system  

---

## 🛠️ Technologies Used  

### **Back-End**  
- **Java + Spring Boot**  
- **PostgreSQL**  
- **JWT Authentication**  
- **Role-Based Authorization (Admin, Curator, User, Guest)**  
- **Custom Error Handling**  
- **OpenAPI** for RESTful documentation  
- **Entity Pagination** for optimized database queries  
- **Docker** for the email notification service  

### **Front-End**  
- **Angular**  
- **HTML, CSS**  
- **Bootstrap, Tailwind, Flowbite, Angular Material**  
- **Google Maps API** *(optional: for location-based exhibits)*  

---

## ⚙️ System Requirements  

- **Java 17+**  
- **Node.js 18+** and **npm**  
- **Angular CLI**  
- **PostgreSQL**  
- **Docker** (for the email service)  

---

## 🚀 Installation Guide   

### **1️⃣ Clone the Repository**  
```bash
git clone https://github.com/tuo-username/Pinacoteca.git
cd Pinacoteca
```

### **2️⃣ Configure the Database**
> ⚠️ Make sure PostgreSQL is installed on your system.
```bash
CREATE DATABASE pinacoteca
```
- Then update the application.properties file with your PostgreSQL credentials.

### **3️⃣ Configure Docker**
> ⚠️ Ensure Docker is installed and running on your system.
You can install MailDev from DockerHub or directly from its repository:
🔗 https://github.com/maildev/maildev

- Start Docker
- Launch the MailDev container
- Update the application.properties file with the container IP and listening port

### **4️⃣ Run the Back-End**

```bash
cd backend
./mwn spring-boot:run
```

### **5️⃣ Install and Run the Front-End**

```bash
cd frontend
npm install
ng serve
```
- The application will be available at 👉 http://localhost:4200/

---

## Developed with ❤️ by Passluk
