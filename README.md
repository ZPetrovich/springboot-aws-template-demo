## 🔁 About This Demo Version

This is a simplified version of the full Starter Kit. It's designed to show the overall structure and Terraform-based deployment — with minimal code and one working endpoint.

<p align="center">
  <video
    src="https://github.com/user-attachments/assets/3c2aab5a-700d-4d05-85d2-d0ef06a2cc6a"
    controls
    playsinline
    muted
    width="720">
    Your browser doesn’t support embedded videos.
    <a href="https://github.com/user-attachments/assets/3c2aab5a-700d-4d05-85d2-d0ef06a2cc6a">Download video</a>.
  </video>
</p>

📺 **[Watch the demo on YouTube](https://www.youtube.com/watch?v=agKj_un5PZg)**

---

## 🚀 Quick Start

### Prerequisites

- **Java 17+** and **Maven 3.8+**
- **[AWS CLI](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)** configured with deployment permissions
- **[Terraform 1.0+](https://developer.hashicorp.com/terraform/downloads)** (for manual deployment)
- **Git** and **GitHub account** (if testing automation)
- Create AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY env variables (for manual deployment)
- Ensure the default configuration values in the .env file meet your requirements

### 🧪 Run the Demo

```bash
git clone https://github.com/ZPetrovich/springboot-aws-template-demo
cd springboot-aws-template-demo
chmod a+x deploy.sh
./deploy.sh
```

Once deployed, test the endpoint in your Browser:

```bash
https://<YOUR_AWS_GATEWAY_ID>.execute-api.<YOUR_AWS_REGION>.amazonaws.com/prod/api/items/YOUR_KEYWORD
```

## ✨ What's Included (Demo)

- 🟢 Simple Spring Boot app with 1 working endpoint
- 🟢 Infrastructure-as-Code (Terraform): Lambda, API Gateway
- 🟢 1-click deploy script (`deploy.sh`)

## 🚫 Not Included (Demo Limitation)

- DynamoDB Storage, Lambda, CI/CD, CloudWatch
- Postman collections and Swagger
- GitHub Actions CI/CD workflow
- Multi-environment support
- 🧰 Production-ready deployment structure

---
📌 This project is for learning purposes.
It’s intentionally kept small and focused so you can understand how AWS Lambda + API Gateway + Java work together.

Got questions or want to go further?
Open an issue or send a message — I’ll be happy to help 🙌
