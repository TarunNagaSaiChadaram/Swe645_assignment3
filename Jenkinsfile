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
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
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
        success {
            echo '✅ Pipeline completed successfully.'
        }
        failure {
            echo '❌ Pipeline failed.'
        }
        always {
            cleanWs()
        }
    }
}
