
pipeline {
    agent any

    environment {
        REGISTRY_CREDENTIALS = 'dockerhub'  // Replace with your Jenkins credential ID
        DOCKER_IMAGE = 'tarunchadaram/stusurvey-app'
        IMAGE_TAG = "${env.BUILD_ID}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    def image = docker.build("${DOCKER_IMAGE}:${IMAGE_TAG}")
                    docker.withRegistry('https://index.docker.io/v1/', REGISTRY_CREDENTIALS) {
                        image.push()
                    }
                }
            }
        }

        stage('Deploy to Rancher Kubernetes Cluster') {
            steps {
                script {
                    // Example shell command to trigger kubectl apply
                    // Make sure your Jenkins agent has access to kubeconfig
                    sh '''
                    echo "Deploying to Rancher cluster..."
                    kubectl set image deployment/stusurvey stusurvey=${DOCKER_IMAGE}:${IMAGE_TAG} -n your-namespace
                    kubectl rollout status deployment/stusurvey -n your-namespace
                    '''
                }
            }
        }
    }

    post {
        failure {
            echo 'Pipeline failed!'
        }
        success {
            echo 'Pipeline completed successfully.'
        }
    }
}
