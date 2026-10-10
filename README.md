# 🌱 GrassTime

**Touch grass. Take a break. Do something real.**

GrassTime is an offline-first application that generates practical, screen-free challenges to help people step away from their devices and engage with the real world. Instead of opening another social media feed when you're bored, open GrassTime, get a small challenge, and go do it.

GrassTime uses a locally running, open-weight AI model to generate challenges. Your prompts stay on your device, and challenge generation does not depend on cloud AI APIs or an internet connection once the model and application are installed.

## ✨ Features

- **Local AI generation** — Generate challenges using Qwen 2.5 0.5B Instruct in GGUF format.
- **Works offline** — Run inference locally without cloud APIs or a network connection after setup.
- **Privacy-first** — Challenge prompts are processed locally by the model.
- **Real-world activities** — Encourages simple activities that get you moving, exploring, creating, or connecting with people.
- **Lightweight web interface** — Built with HTML, CSS, and vanilla JavaScript.
- **Java backend** — Uses Spring Boot to connect the frontend to local model inference.

## 🧠 How It Works

1. Open GrassTime in your browser.
2. Request a challenge.
3. The Spring Boot backend passes the request to the locally loaded Qwen model.
4. The model generates a practical, screen-free activity.
5. Put your device away and complete the challenge.

The model runs on your own machine; GrassTime does not need a hosted AI service to generate challenges.

## 🛠️ Tech Stack

| Component | Technology |
|---|---|
| Backend | Java, Spring Boot |
| Local inference | `java-llama.cpp` |
| Language model | Qwen 2.5 0.5B Instruct (GGUF) |
| Frontend | HTML5, CSS3, vanilla JavaScript |
| Build tool | Maven |

## 📁 Project Structure

```text
GrassTime/
├── backend/
│   ├── src/main/java/dev/LocalAI/LocalAI/
│   │   ├── LocalAiApplication.java
│   │   ├── controller/
│   │   │   └── ChallengeController.java
│   │   ├── service/
│   │   │   └── AIService.java
│   │   └── config/
│   │       └── WebConfig.java
│   └── pom.xml
└── frontend/
    ├── index.html
    ├── app.js
    ├── challenges.js
    └── styles.css
```

## ⚙️ Requirements

- Java JDK 17 or later
- Maven (or the included Maven Wrapper, if present)
- Python 3, for serving the frontend locally
- A compatible Qwen 2.5 0.5B Instruct GGUF model file
- Enough memory and disk space to load and run the model

> **Note:** Native inference libraries can have operating-system and architecture requirements. Confirm that your `java-llama.cpp` dependency supports your environment.

## 🚀 Getting Started

### 1. Get the project

Clone the repository and enter the project directory:

```bash
git clone <your-repository-url>
cd GrassTime
```

Replace `<your-repository-url>` with the URL of your Git repository.

### 2. Download the model

Download a compatible **Qwen 2.5 0.5B Instruct GGUF** model, such as the `Q4_K_M` quantization, from a trusted model repository on Hugging Face.

Store the model locally, for example:

```text
/home/your-username/ai-models/qwen/
└── qwen2.5-0.5b-instruct-q4_k_m.gguf
```

Model files are not included in this repository. Make sure you review the model's license and usage terms before redistributing it.

### 3. Configure the model path

Open `AIService.java` and set the model path to the location of your downloaded GGUF file:

```java
ModelParameters modelParameters = new ModelParameters()
        .setModel("/home/your-username/ai-models/qwen/qwen2.5-0.5b-instruct-q4_k_m.gguf")
        .setThreads(4);
```

Adjust the path, context size, and thread count to suit your system and the inference library's supported settings.

**Important:** The model path must point to an existing file on your machine. Do not commit large model weights or machine-specific paths to the repository.

### 4. Start the backend

From the project root:

```bash
cd backend
```

If the repository includes the Maven Wrapper, run:

```bash
./mvnw clean compile
./mvnw spring-boot:run
```

Otherwise, use an installed Maven version:

```bash
mvn clean compile
mvn spring-boot:run
```

The backend is expected to start at:

```text
http://localhost:8080
```

Keep this terminal open while using the application. If startup fails, check the model path, Java version, native-library compatibility, and backend logs.

### 5. Start the frontend

Open a second terminal and run:

```bash
cd GrassTime/frontend
python3 -m http.server 3000
```

Open the application at:

```text
http://localhost:3000
```

The frontend must be configured to send requests to the running backend. If you change the backend URL or port, update the corresponding frontend configuration.

> **CORS note:** The backend's CORS configuration must allow the frontend origin, such as `http://localhost:3000`. Opening `index.html` directly with a `file://` URL may behave differently across browsers, so serving it locally is recommended.

## 🔒 Privacy and Offline Use

GrassTime is designed to keep challenge generation local:

- Inference runs on the user's machine.
- No cloud AI API key is required for local inference.
- Challenge prompts do not need to be sent to a remote AI service.
- After installation and model setup, challenge generation can work without internet access.

The initial setup may require an internet connection to obtain the source code, dependencies, and model weights. Actual offline operation also depends on the application's dependencies and configuration; avoid adding analytics, remote fonts, external APIs, or other network-dependent resources if fully offline operation is a requirement.

## 🧪 Troubleshooting

**The backend cannot load the model**
- Check that the configured model path is correct.
- Verify that the model file has downloaded completely.
- Confirm that the model format is supported by the inference library.

**The backend fails during startup**
- Check your Java version with `java -version`.
- Review the Spring Boot logs.
- Verify that the native inference library supports your operating system and CPU architecture.

**The frontend cannot generate a challenge**
- Confirm that the backend is running on port `8080`.
- Check the browser console and backend logs.
- Verify the frontend API URL and CORS configuration.

**Generation is slow or runs out of memory**
- Close memory-intensive applications.
- Adjust the inference thread count and context size.
- Consider a quantized model that better fits your hardware.

## 🤝 Contributing

Contributions are welcome! You can help improve GrassTime by:

- Improving challenge quality and variety.
- Refining the prompt used for local generation.
- Improving accessibility and the user interface.
- Fixing bugs and improving offline reliability.
- Adding tests and documenting setup issues across platforms.

A useful contribution should improve the experience without undermining the project's privacy-first, offline-first goal.

## 📜 License

This project is intended to be distributed under the **MIT License**. See the repository's `LICENSE` file for the exact terms.

The Qwen model and other third-party dependencies are distributed under their own licenses. The project's MIT license does not replace those separate terms.
