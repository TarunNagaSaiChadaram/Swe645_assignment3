pipeline {
    agent any
    environment {
        REPO_NAME = "tarunchadaram/stusurvey-app"
        TAG = "${env.BUILD_ID}"
    }
    stages {
        stage("Checkout Code") {
            steps {
                script {
                    checkout scm
                }
            }
        }
        stage("Build and Push Docker Image") {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        sh "echo \$DOCKERHUB_PASSWORD | docker login -u \$DOCKERHUB_USERNAME --password-stdin"
                    }
                    // Build image with both versioned and latest tags
                    sh "docker build -t $REPO_NAME:$TAG -t $REPO_NAME:latest ."
                    
                    // Push both tags
                    sh "docker push $REPO_NAME:$TAG"
                    sh "docker push $REPO_NAME:latest"
                }
            }
        }
        stage("Deploy to Rancher Kubernetes Cluster") {
            steps {
                script {
                    sh "sed -i 's|IMAGE_NAME|$REPO_NAME:$TAG|g' deployment.yaml"
                    withCredentials([file(credentialsId: 'kubernetes', variable: 'KUBECONFIG')]) {
                        sh "kubectl --kubeconfig=$KUBECONFIG apply -f deployment.yaml"
                        sh "kubectl --kubeconfig=$KUBECONFIG apply -f service.yaml"
                    }
                }
            }
        }
    }
}
