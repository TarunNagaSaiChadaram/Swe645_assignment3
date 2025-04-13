pipeline {
    agent any
    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Docker login using credentials stored in Jenkins
                    withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        sh '''
                            echo "$DOCKERHUB_PASSWORD" | docker login -u $DOCKERHUB_USERNAME --password-stdin
                        '''
                    }

                    // Build the Docker image
                    sh "docker build -t tarunchadaram/stusurvey-app:${env.BUILD_ID} ."
                }
            }
        }

        stage('Push Image to DockerHub') {
            steps {
                script {
                    // Push the built image to DockerHub
                    sh "docker push tarunchadaram/stusurvey-app:${env.BUILD_ID}"
                }
            }
        }

        stage('Deploy to Rancher Kubernetes Cluster') {
            steps {
                script {
                    // Update the image name in the deployment.yaml file with the newly built image
                    sh "sed -i 's|IMAGE_NAME|tarunchadaram/stusurvey-app:${env.BUILD_ID}|g' deployment.yaml"

                    // Use Kubernetes config stored in Jenkins credentials to deploy to the cluster
                    withCredentials([file(credentialsId: 'kubernetes', variable: 'KUBECONFIG')]) {
                        sh "kubectl --kubeconfig=$KUBECONFIG apply -f deployment.yaml"
                        sh "kubectl --kubeconfig=$KUBECONFIG apply -f service.yaml"
                    }
                }
            }
        }
    }

    post {
        failure {
            echo "❌ Deployment failed. Please check the logs."
        }

        success {
            echo "✅ Deployment completed successfully!"
        }
    }
}
