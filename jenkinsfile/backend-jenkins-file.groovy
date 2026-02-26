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
                       kubectl get svc'''
                }
            }
        }
        stage('view backend LB URL'){
            steps{
                withCredentials([aws(accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'aws', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY')]) {
                sh ''' echo Copy This LB URL and paste in frontend .env
                    kubectl get svc backend-svc'''
                }
            }
        }                  
    }
}
