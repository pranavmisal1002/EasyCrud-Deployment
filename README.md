# EasyCRUD - Multi-Deployment DevOps Project

A comprehensive DevOps learning project demonstrating multiple deployment strategies for the same full-stack application.

---

## 🎯 Project Overview

This repository contains a complete **EasyCRUD Application** deployed using multiple DevOps approaches. The purpose is to practice and strengthen core **DevOps**, **Cloud**, and **Site Reliability Engineering (SRE)** skills by deploying the same application using different infrastructure and CI/CD strategies.

---

## 📋 Table of Contents

- [Objective](#objective)
- [Project Architecture](#project-architecture)
- [Deployment Approaches](#deployment-approaches-covered)
- [Skills Practiced](#skills-practiced)
- [Getting Started](#getting-started)
- [Repository Structure](#repository-structure)
- [Prerequisites](#prerequisites)
- [Choose Your Deployment Path](#choose-your-deployment-path)

---

## 🎯 Objective

This project demonstrates how to deploy a full-stack application using different DevOps methodologies:

- 🐳 **Docker-based deployment**
- ☸️ **Kubernetes-based deployment**
- 🔁 **Jenkins CI/CD pipeline deployment**
- ☁️ **AWS cloud infrastructure**

Each deployment approach is implemented separately with detailed documentation, allowing you to learn and compare different strategies.

---

## 🏗️ Project Architecture

### Application Stack

| Component | Technology |
|-----------|-----------|
| **Backend** | Spring Boot (Java) |
| **Frontend** | React.js |
| **Database** |  (AWS RDS)  |
| **Containerization** | Docker |
| **Orchestration** | Kubernetes (EKS) |
| **CI/CD** | Jenkins |
| **Cloud Provider** | AWS |

### High-Level Architecture

```
Frontend (React)
      ↓
Backend (Spring Boot)
      ↓
Database ( RDS )
```

---

## 📦 Deployment Approaches Covered

This repository includes three distinct deployment strategies, each demonstrating different DevOps practices and tools.

---

### 🐳 1️⃣ Docker-Based Deployment (EC2)

**What You'll Learn:**
- Backend and frontend containerization using Docker
- Multi-stage Docker builds for optimization
- Manual container lifecycle management
- Deployment on AWS EC2 instances
- Docker networking and port mapping
- Environment variable configuration



**📄 Detailed Guide:**

➡️ [Docker Deployment Documentation](https://github.com/pranavmisal1002/EasyCrud-Deployment/tree/main/Docker)



---

### ☸️ 2️⃣ Kubernetes Deployment (EKS)

**What You'll Learn:**
- Kubernetes cluster setup on Amazon EKS
- Creating Deployment and Service manifests
- LoadBalancer service exposure
- Scalable container orchestration
- Pod management and monitoring
- Service discovery and networking




**📄 Detailed Guide:**

➡️ [Kubernetes Deployment Documentation](https://github.com/pranavmisal1002/EasyCrud-Deployment/tree/main/kubernetes)



---

### 🔁 3️⃣ CI/CD Deployment Using Jenkins

**What You'll Learn:**
- Automated build and deploy pipeline
- Jenkins pipeline configuration
- GitHub integration for code triggers
- Docker image building and pushing
- Automated Kubernetes deployments
- Credential management in Jenkins

**Key Features:**
- ✅ Automated CI/CD pipeline
- ✅ GitHub webhook integration
- ✅ Docker image build automation
- ✅ Docker Hub push automation
- ✅ Kubernetes deployment automation
- ✅ Rollout update strategies

**Architecture:**
```
Developer
    ↓
GitHub Repository
    ↓
Jenkins CI/CD Pipeline
    ↓
Docker Build & Push
    ↓
Kubernetes Deployment
    ↓
Live Application (EKS)
```


**📄 Detailed Guide:**

➡️ [Jenkins CI/CD Documentation](https://github.com/pranavmisal1002/EasyCrud-Deployment/tree/main/jenkinsfile)



---

## 🛠️ Skills Practiced

This repository helps you build hands-on experience in:

### Docker & Containerization
- ✅ Docker image creation & optimization
- ✅ Multi-stage builds
- ✅ Dockerfile best practices
- ✅ Container networking
- ✅ Docker Compose (optional)

### Cloud Infrastructure (AWS)
- ✅ AWS EC2 setup and configuration
- ✅ Amazon RDS (MySQL) configuration
- ✅ MongoDB Atlas integration
- ✅ Security groups and networking
- ✅ IAM roles and permissions

### Kubernetes
- ✅ Kubernetes deployments
- ✅ Service configurations
- ✅ LoadBalancer services
- ✅ Pod management
- ✅ kubectl operations
- ✅ Amazon EKS cluster management

### CI/CD & Automation
- ✅ Jenkins pipeline automation
- ✅ GitHub integration
- ✅ Automated testing and deployment
- ✅ Credential management
- ✅ Rollout strategies

### DevOps Best Practices
- ✅ Infrastructure as Code
- ✅ Configuration management
- ✅ Version control
- ✅ Monitoring and logging
- ✅ Security hardening

---

## 🚀 Getting Started

### Prerequisites

Before starting any deployment approach, ensure you have:

**Required:**
- AWS Account with appropriate permissions
- GitHub account
- Basic command-line knowledge
- Understanding of networking concepts

**Tool-Specific:**

| Deployment Method | Required Tools |
|-------------------|---------------|
| **Docker** | Docker, AWS CLI, EC2 access |
| **Kubernetes** | kubectl, AWS CLI, EKS cluster |
| **Jenkins CI/CD** | Jenkins, Docker, kubectl, AWS CLI |

---

## 📁 Repository Structure



---

## 🎓 Choose Your Deployment Path

Select the deployment method that best fits your learning goals:

### 🐳 Start with Docker

**Best for:**
- Beginners to containerization
- Understanding Docker fundamentals
- Simple deployment scenarios
- Learning container basics

**Time Required:** 2-3 hours

**Complexity:** ⭐⭐☆☆☆

➡️ [Start Docker Deployment](https://github.com/pranavmisal1002/EasyCrud-Deployment/tree/main/Docker)

---

### ☸️ Progress to Kubernetes

**Best for:**
- Understanding orchestration
- Learning scalability concepts
- Production-grade deployments
- Container management at scale

**Time Required:** 4-6 hours

**Complexity:** ⭐⭐⭐⭐☆

➡️ [Start Kubernetes Deployment](https://github.com/pranavmisal1002/EasyCrud-Deployment/tree/main/kubernetes)

---

### 🔁 Master CI/CD with Jenkins

**Best for:**
- Automation enthusiasts
- Learning DevOps pipelines
- Continuous deployment
- Production workflows

**Time Required:** 6-8 hours

**Complexity:** ⭐⭐⭐⭐⭐

➡️ [Start Jenkins CI/CD Deployment](https://github.com/pranavmisal1002/EasyCrud-Deployment/tree/main/jenkinsfile)

---

## 📊 Comparison of Deployment Methods

| Feature | Docker (EC2) | Kubernetes (EKS) | Jenkins CI/CD |
|---------|-------------|------------------|---------------|
| **Automation** | Manual | Manual | Fully Automated |
| **Scalability** | Limited | High | High |
| **Complexity** | Low | Medium | High |
| **Learning Curve** | Easy | Moderate | Steep |
| **Production Ready** | Basic | Yes | Yes |
| **Cost** | Low | Medium | Medium-High |
| **Best For** | Learning | Production | Enterprise |

---

## 🎯 Learning Path Recommendation

### Beginner Path
1. Start with **Docker Deployment** (EC2)
2. Move to **Kubernetes Deployment** (EKS)
3. Advance to **Jenkins CI/CD**

### Experienced Path
1. Review **Docker Deployment** (Quick refresh)
2. Focus on **Kubernetes Deployment**
3. Implement **Jenkins CI/CD** pipeline

### Expert Path
1. Implement all three approaches
2. Compare and optimize each
3. Create hybrid solutions
4. Add monitoring and logging

---




---

## 📝 Project Workflow

### Standard Deployment Process

1. **Setup Infrastructure**
   - Create AWS resources (EC2, RDS, EKS)
   - Configure networking and security

2. **Prepare Application**
   - Update configuration files
   - Set environment variables

3. **Build & Deploy**
   - Build Docker images
   - Deploy to target platform

4. **Verify & Test**
   - Check application health
   - Test functionality

5. **Monitor & Maintain**
   - Monitor logs and metrics
   - Perform updates as needed

---

---

## 📚 Additional Resources

### Documentation
- [Docker Documentation](https://docs.docker.com/)
- [Kubernetes Documentation](https://kubernetes.io/docs/)
- [Jenkins Documentation](https://www.jenkins.io/doc/)
- [AWS Documentation](https://docs.aws.amazon.com/)

### Learning Resources
- [Docker Tutorial](https://www.docker.com/101-tutorial/)
- [Kubernetes Tutorial](https://kubernetes.io/docs/tutorials/)
- [Jenkins Tutorial](https://www.jenkins.io/doc/tutorials/)

### Community
- [Docker Community](https://www.docker.com/community/)
- [Kubernetes Community](https://kubernetes.io/community/)
- [AWS Community](https://aws.amazon.com/developer/community/)

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit issues or pull requests.

### How to Contribute

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

---

## 📄 License

This project is provided as-is for educational purposes.

---

## 🎓 Learning Outcomes

After completing this project, you will be able to:

✅ Containerize applications using Docker  
✅ Deploy applications on AWS EC2  
✅ Create and manage Kubernetes clusters  
✅ Write Kubernetes manifests  
✅ Build CI/CD pipelines with Jenkins  
✅ Manage cloud infrastructure on AWS  
✅ Implement DevOps best practices  
✅ Troubleshoot deployment issues  
✅ Scale applications effectively  
✅ Automate deployment workflows  

---

## 📞 Support

If you encounter any issues or have questions:

1. Check the specific deployment guide
2. Review troubleshooting sections
3. Open an issue on GitHub
4. Consult the documentation links

---

## 🌟 Project Highlights

### Why This Project Matters

This project demonstrates:

- **Practical DevOps Skills** - Real-world scenarios and solutions
- **Multiple Approaches** - Compare different deployment strategies
- **Best Practices** - Industry-standard implementations
- **Scalability** - From simple to complex deployments
- **Automation** - From manual to fully automated workflows

### What Makes It Unique

- ✨ Same application, multiple deployment methods
- ✨ Comprehensive documentation for each approach
- ✨ Step-by-step guides with explanations
- ✨ Troubleshooting sections for common issues
- ✨ Progressive learning path from basic to advanced

---



**Happy Learning and Deploying!** 🚀

---


