# CI/CD Deployment of Full-Stack Application using Jenkins, Docker & Kubernetes on AWS

A complete DevOps CI/CD pipeline for deploying a full-stack application (Backend + Frontend) with automated build, test, and deployment processes.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Step 1: Fork GitHub Repository](#step-1-fork-the-github-repository)
- [Step 2: Create AWS RDS](#step-2-create-aws-rds-database-setup)
- [Step 3: Create Amazon EKS Cluster](#step-3-create-amazon-eks-cluster)
- [Step 4: Install Required Tools on EC2](#step-4-install-required-tools-on-ec2)
- [Step 5: Configure Jenkins](#step-5-configure-jenkins)
- [Step 6: Configure Credentials in Jenkins](#step-6-configure-credentials-in-jenkins)
- [Step 7: Create Backend Jenkins Pipeline](#step-7-create-backend-jenkins-pipeline)
- [Step 8: Update Frontend Environment](#step-8-update-frontend-env-file)
- [Step 9: Create Frontend Jenkins Pipeline](#step-9-create-frontend-jenkins-pipeline)
- [Step 10: Access Your Application](#step-10-access-application)
- [Troubleshooting](#troubleshooting)

---

## 🎯 Overview

This project demonstrates a production-ready DevOps CI/CD pipeline that automates the entire deployment lifecycle of a full-stack application.

**Key Features:**
- Automated builds triggered by code changes
- Containerized microservices architecture
- Kubernetes orchestration for high availability
- Secure credential management
- Cloud-native infrastructure

---

## 🏗️ Architecture Overview

```
Developer → GitHub (Forked Repo)
                ↓
            Jenkins CI/CD
                ↓
          Docker Build & Push
                ↓
          Kubernetes Deployment
                ↓
           LoadBalancer URL
                ↓
             Live Application
```

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| 🐳 Docker | Application containerization |
| ☁️ AWS RDS | Managed database service |
| 🔁 Jenkins | CI/CD automation server |
| 📦 Docker Hub | Container image registry |
| ☸️ Kubernetes | Container orchestration |
| 🌐 LoadBalancer | External traffic routing |
| 🔧 AWS EKS | Managed Kubernetes service |

---

## 📚 Prerequisites

Before starting, ensure you have:

- ✅ AWS Account with appropriate permissions
- ✅ EC2 Instance (Ubuntu 22.04, t2.medium or larger)
- ✅ Kubernetes cluster (EKS or self-managed)
- ✅ Docker Hub account
- ✅ GitHub account
- ✅ Basic knowledge of:
  - Docker and containerization
  - Kubernetes concepts
  - Jenkins pipelines
  - Git workflows

---

## 🔷 STEP 1: Fork the GitHub Repository

### Why Fork?

Forking allows you to:
- Modify `application.properties`
- Update `.env` files
- Customize `Jenkinsfile`
- Change Docker image names
- Track your own changes

### How to Fork

1. Go to the original repository
2. Click **Fork** button (top right)
3. Select your GitHub account
4. Wait for fork to complete

**Result:** You now have your own copy of the repository at:
```
https://github.com/YOUR_USERNAME/repository-name
```

---

## 🔷 STEP 2: Create AWS RDS (Database Setup)

### Configuration

Follow the AWS RDS setup documentation to create:

**📖 Reference:** 👉 [RDS Setup Documentation](https://github.com/pranavmisal1002/EasyCrud-Docker#-phase-1-database-setup-amazon-rds--mariadb)

**Security Group:**
- Allow port `3306` (MySQL) or `5432` (PostgreSQL)
- Source: EKS cluster security group


---

## 🔷 STEP 3: Create Amazon EKS Cluster

### Why EKS?

- Managed Kubernetes service
- Automatic updates and patches
- High availability
- Integration with AWS services

### Setup Steps

Follow the EKS setup documentation to create your cluster.

**📖 Reference:** 👉 [AWS EKS Setup Documentation](https://github.com/pranavmisal1002/AWS-EKS-Setup)
---

## 🔷 STEP 4: Install Required Tools on EC2

### Launch EC2 Instance

**Configuration:**
- AMI: Ubuntu 22.04
- Instance Type: c7i-flex.large
- Storage: 30 GB
- Security Group:
  - SSH (22)
  - Jenkins (8081)
  - HTTP (80)
  - HTTPS (443)

### Connect to EC2

```bash
ssh -i your-key.pem ubuntu@<EC2_PUBLIC_IP>
```

---

### Install Docker

```bash
sudo apt update
sudo apt install docker.io -y
sudo systemctl enable docker
sudo systemctl start docker
```

**Verify Docker:**

```bash
docker --version
```


---

### Install AWS CLI

```bash
sudo snap install aws-cli --classic
aws --version
```

**Expected output:**
```
aws-cli/1.x.x Python/3.x.x Linux/5.x.x
```

---

### Install Jenkins

**Install Java:**

```bash
sudo apt update
sudo apt install openjdk-17-jdk -y
java -version
```

**Add Jenkins repository:**

```bash
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee \
  /usr/share/keyrings/jenkins-keyring.asc > /dev/null

echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
```

**Install Jenkins:**

```bash
sudo apt update
sudo apt install jenkins -y
```

**Start Jenkins:**

```bash
sudo systemctl start jenkins
sudo systemctl enable jenkins
```


**Now add Jenkins to docker group:**

```bash
sudo usermod -aG docker jenkins
sudo systemctl restart jenkins
```

---

## 🔷 STEP 5: Configure Jenkins

### Change Jenkins Port to 8081

**Why?** Port 8080 is commonly used by other applications.

**Edit Jenkins configuration:**

```bash
sudo nano /etc/default/jenkins
```

**Find and change:**

```
HTTP_PORT=8080
```

**To:**

```
HTTP_PORT=8081
```

**Reload and restart:**

```bash
sudo systemctl daemon-reload
sudo systemctl restart jenkins
```

---

### Access Jenkins

**Open in browser:**

```
http://<EC2_PUBLIC_IP>:8081
```

**Get initial admin password:**

```bash
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
```

**Setup Steps:**
1. Paste the admin password
2. Click **Install suggested plugins**
3. Create your admin user
4. Configure Jenkins URL
5. Start using Jenkins

---

### Install Required Plugins

**Navigate to:**
```
Manage Jenkins → Plugins → Available Plugins
```

**Install these plugins:**

1. **AWS Credentials Plugin**
   - Enables AWS credential management
   - Required for EKS deployment

2. **Pipeline Stage View Plugin**
   - Visual pipeline execution
   - Better monitoring


**After installation:**
- Click **Restart Jenkins when no jobs are running**

---

## 🔷 STEP 6: Configure Credentials in Jenkins

### Navigate to Credentials

```
Manage Jenkins → Credentials → System → Global credentials → Add Credentials
```

---

### 1️⃣ AWS Credentials

**Add AWS credentials for EKS access:**

| Field | Value |
|-------|-------|
| Kind | AWS Credentials |
| ID | `aws` |
| Access Key ID | Your AWS Access Key |
| Secret Access Key | Your AWS Secret Key |
| Description | AWS EKS Credentials |

> ⚠️ **CRITICAL:** Use ID: `aws` (exact match)

**How to get AWS credentials:**

1. Go to AWS Console
2. Navigate to IAM → Users → Your User
3. Security credentials → Create access key
4. Download and save securely

---

### 2️⃣ Docker Hub Credentials

**Add Docker Hub credentials for image push:**

| Field | Value |
|-------|-------|
| Kind | Username with password |
| Scope | Global |
| Username | Your Docker Hub username |
| Password | Your Docker Hub password |
| ID | `Docker` |
| Description | Docker Hub Credentials |

> ⚠️ **CRITICAL:** Use ID: `Docker` (exact match with capital D)

**Why these exact IDs?**

The Jenkinsfile references these credentials by ID:
```groovy
withCredentials([usernamePassword(credentialsId: 'Docker', ...)])
withAWS(credentials: 'aws', region: 'us-east-1') { ... }
```

If you use different IDs, you must update the Jenkinsfile.

---

## 🔷 STEP 7: Create Backend Jenkins Pipeline

### Create Pipeline Job

1. Click **New Item**
2. Enter name: `backend-pipeline`
3. Select **Pipeline**
4. Click **OK**

---

### Configure Pipeline

**In the Pipeline section:**

1. **Definition:** Pipeline script from SCM
2. **SCM:** Git
3. **Repository URL:** Your forked GitHub repository URL
   ```
   https://github.com/YOUR_USERNAME/your-repo.git
   ```
4. **Branch:** `*/main` or `*/master`
5. **Script Path:** `backend/Jenkinsfile`

**Or paste Jenkinsfile directly:**

```groovy
pipeline{
    agent any
    environment{
        image_name_backend = "pranavmisal1002/easy-crud-backend"
        tag = "v1"
    }

    stages{
        stage('git pull'){
            steps{
            git branch: 'main', url: 'https://github.com/pranavmisal1002/EasyCRUD-Updated.git'
            }
        }
        stage('Build Backend'){
            steps{
                dir('backend'){
                sh ''' docker rmi -f ${image_name_backend}:${tag} || true
                        docker build --no-cache -t ${image_name_backend}:${tag} .
                       docker images'''
                }
            }
        }
        stage('Push Image Hub'){
            steps{
                withCredentials([usernamePassword(credentialsId: 'Docker', passwordVariable: 'Docker_Password', usernameVariable: 'Docker_user')]) {
                sh '''echo Logging into DockerHub...
                      echo \$Docker_Password | docker login -u \$Docker_user --password-stdin
                      docker push ${image_name_backend}:${tag} 
                      docker logout '''
                }
            }  
        }
        stage('Configure Kube') {
            steps {
                withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                    sh '''
                        aws eks update-kubeconfig --region eu-north-1 --name EKS-cluster
                    '''
                }
            }
        }
        stage('K8s Deployment'){
            steps{
                dir('kubernetes'){
                    withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                        sh ''' kubectl apply -f backend-deployment.yml'''
                    }
                }
            }
        }
        stage('View pod & service'){
            steps{
                withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                sh ''' kubectl get pods -o wide
                       echo ########################################################
                       kubectl get svc backend-svc'''
                }
            }
        }                  
    }
}
```

---

### Modify Jenkinsfile

**Replace the following:**

1. **GitHub URL:**
   ```groovy
   url: 'https://github.com/YOUR_USERNAME/your-repo.git'
   ```

2. **Docker Image Name:**
   ```groovy
   image_name_backend = "yourdockerhubid/backend"
   ```

3. **EKS Cluster Name & Region:**
   ```groovy
   aws eks update-kubeconfig --name YOUR_CLUSTER_NAME --region YOUR_REGION
   ```

---

### Update Kubernetes Deployment YAML

**Edit:** `backend/k8s/backend-deployment.yaml`

**Change image name:**

```yaml

        image: yourdockerhubid/backend:latest  # ← Change this
     
```

---

### Run Backend Pipeline

1. Click **Build Now**
2. Watch the pipeline stages execute:
   - ✅ Clone Repository
   - ✅ Build Docker Image
   - ✅ Push to Docker Hub
   - ✅ Deploy to Kubernetes

**Monitor progress:**
- Click on build number (#1, #2, etc.)
- Click **Console Output** to see logs

---

### Get Backend LoadBalancer URL

After successful deployment:

```bash
kubectl get svc
```

**Expected output:**

```
NAME               TYPE           EXTERNAL-IP                                     PORT(S)
backend-service    LoadBalancer   abc123-xyz.us-east-1.elb.amazonaws.com         8080:30001/TCP
```

**Copy the EXTERNAL-IP:** This is your backend LoadBalancer URL.

Example:
```
http://abc123-xyz.us-east-1.elb.amazonaws.com:8080
```

---

## 🔷 STEP 8: Update Frontend .env File

### Why This Step?

The frontend needs to know where the backend API is located.

### Edit .env File

**In your GitHub repository, edit:** `frontend/.env`

```env
REACT_APP_API_URL=http://<BACKEND_LB_URL>:8080
```

**Example:**

```env
REACT_APP_API_URL=http://abc123-xyz.us-east-1.elb.amazonaws.com:8080
```

> ⚠️ **CRITICAL:** You must use the backend LoadBalancer URL, not localhost or EC2 IP!

### Commit and Push

```bash
git add frontend/.env
git commit -m "Update backend URL in frontend"
git push origin main
```

---

## 🔷 STEP 9: Create Frontend Jenkins Pipeline

### Create Pipeline Job

1. Click **New Item**
2. Enter name: `frontend-pipeline`
3. Select **Pipeline**
4. Click **OK**

---

### Configure Frontend Pipeline

**Paste frontend Jenkinsfile:**

```groovy
pipeline{
    agent any
    environment{
        image_name_frontend = "pranavmisal1002/easy-crud-frontend"
        tag = "v1"
    }
    stages{
        stage('git pull'){
            steps{
              git branch: 'main', url: 'https://github.com/pranavmisal1002/EasyCRUD-Updated.git'  
            
            }
        }
        stage('Build Frontend'){
            steps{
                dir('frontend'){
                sh '''docker rmi -f ${image_name_frontend}:${tag} || true
                        docker build --no-cache -t ${image_name_frontend}:${tag} .
                       docker images '''
                }
            }
        }
        stage('Push Image Hub'){
            steps{
                withCredentials([usernamePassword(credentialsId: 'Docker', passwordVariable: 'Docker_Password', usernameVariable: 'Docker_user')]) {
                sh '''echo Logging into DockerHub...
                      echo \$Docker_Password | docker login -u \$Docker_user --password-stdin
                      docker push ${image_name_frontend}:${tag} 
                      docker logout '''
                }
            }
        }
        stage('Configure Kube') {
            steps {
                withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                    sh '''
                        aws eks update-kubeconfig --region eu-north-1 --name EKS-cluster
                    '''
                }
            }
        }
        stage('K8s Deployment'){
            steps{
                dir('kubernetes'){
                    withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                        sh ''' kubectl apply -f frontend-deployment.yml'''
                    }
                }
            }
        }
        stage('View pod & service'){
            steps{
                withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                sh ''' kubectl get pods -o wide
                       echo ########################################################
                       kubectl get svc frontend-svc'''
                }
            }
        } 
    }
}
```

---

### Update Frontend Configuration

**1. Change Docker Image Name:**

```groovy
image_name_frontend = "yourdockerhubid/frontend"
```

**2. Edit Kubernetes Deployment:** `frontend/k8s/frontend-deployment.yaml`

```yaml

        image: yourdockerhubid/frontend:latest  # ← Change this

```

---

### Run Frontend Pipeline

1. Click **Build Now**
2. Monitor stages:
   - ✅ Clone Repository
   - ✅ Build Docker Image
   - ✅ Push to Docker Hub
   - ✅ Deploy to Kubernetes

---

## 🔷 STEP 10: Access Application

### Get Frontend LoadBalancer URL

```bash
kubectl get svc
```

**Expected output:**

```
NAME                TYPE           EXTERNAL-IP                                     PORT(S)
backend-service     LoadBalancer   abc123-backend.elb.amazonaws.com               8080:30001/TCP
frontend-service    LoadBalancer   xyz789-frontend.elb.amazonaws.com              80:30002/TCP
```

### Access Your Application

**Open in browser:**

```
http://xyz789-frontend.elb.amazonaws.com
```

🎉 **Your application is now live!**

---

## 🏆 What This Project Demonstrates

✔️ **CI/CD Pipeline** - Automated build, test, and deployment  
✔️ **Docker Containerization** - Application packaging and portability  
✔️ **Kubernetes Orchestration** - Container management and scaling  
✔️ **Secure Credential Management** - Jenkins credentials integration  
✔️ **Cloud Database Integration** - AWS RDS connectivity  
✔️ **Production-Ready DevOps Workflow** - Industry best practices  
✔️ **Infrastructure as Code** - Kubernetes YAML configurations  
✔️ **Microservices Architecture** - Separate backend and frontend services  

---

## 🔧 Troubleshooting

### Jenkins Build Fails

**Check Jenkins logs:**

```bash
sudo tail -f /var/lib/jenkins/jenkins.log
```

**Common issues:**

1. **Docker permission denied**
   ```bash
   sudo usermod -aG docker jenkins
   sudo systemctl restart jenkins
   ```

2. **Git authentication failed**
   - Verify repository URL
   - Check if repository is public or add GitHub credentials

3. **AWS credentials invalid**
   - Verify AWS credentials in Jenkins
   - Check IAM permissions

---

### Docker Push Fails

**Error:** `denied: requested access to the resource is denied`

**Solutions:**

1. **Login to Docker Hub:**
   ```bash
   docker login
   ```

2. **Verify credentials in Jenkins**
   - Check username and password
   - Ensure ID is exactly `Docker`

3. **Check image name format:**
   ```
   dockerhubusername/imagename:tag
   ```

---

### Kubernetes Deployment Fails

**Check pod status:**

```bash
kubectl get pods
kubectl describe pod <pod-name>
kubectl logs <pod-name>
```

**Common issues:**

1. **ImagePullBackOff**
   - Verify Docker Hub image exists
   - Check image name in deployment.yaml
   - Ensure image is public or add imagePullSecrets

2. **CrashLoopBackOff**
   - Check application logs
   - Verify environment variables
   - Check database connectivity

3. **Pending pods**
   - Check node resources
   - Verify node group is running

---

### LoadBalancer Pending

**Check service:**

```bash
kubectl describe svc backend-service
kubectl describe svc frontend-service
```

**Possible causes:**

1. **EKS LoadBalancer controller not installed**
   ```bash
   kubectl get deployment -n kube-system aws-load-balancer-controller
   ```

2. **Insufficient permissions**
   - Check IAM roles for service account

3. **Subnet configuration**
   - Verify public subnets are tagged correctly

---

### Application Not Accessible

**Check all components:**

```bash
# Check pods
kubectl get pods

# Check services
kubectl get svc

# Check deployments
kubectl get deployments

# Check events
kubectl get events --sort-by='.lastTimestamp'
```

**Verify:**

1. Backend is running and accessible
2. Frontend has correct backend URL in .env
3. LoadBalancer DNS is resolving
4. Security groups allow traffic
5. Database is accessible from pods

---

## 📚 Useful Commands

### Jenkins

```bash
# Restart Jenkins
sudo systemctl restart jenkins

# Check Jenkins status
sudo systemctl status jenkins

# View Jenkins logs
sudo tail -f /var/lib/jenkins/jenkins.log
```

### Docker

```bash
# List images
docker images

# Remove image
docker rmi <image-name>

# View running containers
docker ps

# View all containers
docker ps -a
```

### Kubernetes

```bash
# Get all resources
kubectl get all

# Describe resource
kubectl describe <resource-type> <resource-name>

# View logs
kubectl logs <pod-name>

# Delete resource
kubectl delete <resource-type> <resource-name>

# Apply configuration
kubectl apply -f <filename.yaml>
```


---

## 📝 Notes

- This setup uses LoadBalancer service type (costs apply)
- For production, consider using Ingress controller
- Implement HTTPS using cert-manager and Let's Encrypt
- Set up monitoring with Prometheus and Grafana
- Implement logging with ELK or AWS CloudWatch
- Use AWS Secrets Manager for sensitive data

---

## 🤝 Contributing

Feel free to submit issues or pull requests to improve this guide.

---

**Happy Deploying! 🚀**
