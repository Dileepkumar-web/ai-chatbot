new Gemini AI Chatbot (Spring Boot + Thymeleaf)

A web-based chatbot built with Spring Boot, Thymeleaf, and Google Gemini API.  
The application allows users to interact with Google's generative AI model through a clean browser-based interface.
.
---

## Features
- Responsive chatbot UI with a simple form-based interface.
- Integration with Google Gemini API (`gemini-1.5-flash-latest`).
- MVC architecture for clean separation of concerns.
- API key stored securely in `application.properties` (excluded from GitHub via `.gitignore`).
- Works on any system with Java and Maven installed.

---

## Project Structure
booming/
├── src/main/java/aii/example/booming/
│ ├── controller/ChatController.java # Handles form submission and routing
│ ├── service/GeminiService.java # Communicates with Gemini API
│ └── BoomingApplication.java # Main Spring Boot entry point
├── src/main/resources/
│ ├── templates/chat.html # Chatbot UI (Thymeleaf)
│ ├── static/images/chatt.jpg # Bot logo/image
│ └── application.properties # Local configuration
├── pom.xml # Maven dependencies
└── README.md # Project documentation

yaml
Copy
Edit

---

## Requirements
- Java 17 or newer
- Maven 3.8 or newer
- Google Gemini API key
- Internet connection

---

## Getting a Google Gemini API Key
1. Go to [Google AI Studio](https://aistudio.google.com/) and log in.
2. Enable the Generative Language API in [Google Cloud Console](https://console.cloud.google.com/apis/library).
3. Create an API key in [Google Cloud Credentials](https://console.cloud.google.com/apis/credentials).
4. Copy the API key for use in the project.

---

## Setup and Run

### 1. Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/gemini-ai-chatbot.git
cd gemini-ai-chatbot
2. Configure the API key
Edit src/main/resources/application.properties:



Copy
Edit
http://localhost:8080
ai.
