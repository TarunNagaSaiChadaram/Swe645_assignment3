pipeline {
    agent any

    environment {
        IMAGE_NAME = "tarunchadaram/stusurvey-app"
        IMAGE_TAG = "${env.BUILD_ID}"
    }

    stages {
        stage("Checkout and Build Docker Image for Web App") {
            steps {
                script {
                    checkout scm

                    // Login to DockerHub using your credentials
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        sh "docker login -u $DOCKERHUB_USERNAME -p $DOCKERHUB_PASSWORD"
                    }

                    // Build Docker image directly from the root of the repository
                    sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                }
            }
        }

        stage("Push Image to DockerHub") {
            steps {
                script {
                    sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                }
            }
        }

        stage("Deploy to Rancher Kubernetes Cluster") {
            steps {
                script {
                    // Replace placeholder with actual image tag in deployment.yaml
                    sh "sed -i 's|IMAGE_NAME|${IMAGE_NAME}:${IMAGE_TAG}|g' deployment.yaml"

                    // Use Rancher kubeconfig credentials
                    withCredentials([file(credentialsId: 'kubernetes', variable: 'KUBECONFIG')]) {
                        sh "kubectl --kubeconfig=$KUBECONFIG apply -f deployment.yaml"
                        sh "kubectl --kubeconfig=$KUBECONFIG apply -f service.yaml"
                    }
                }
            }
        }
    }

    post {
        success {
            echo "✅ Deployment successful!"
        }
        failure {
            echo "❌ Deployment failed. Please check the logs."
        }
    }
}
