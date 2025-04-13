pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub') // Jenkins Credentials ID
        IMAGE_NAME = 'tarunchadaram/stusurvey-app'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/TarunNagaSaiChadaram/Swe645_assignment3.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    def imageTag = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                        def image = docker.build("${IMAGE_NAME}:${imageTag}")
                        image.push()
                        image.push('latest')
                    }
                }
            }
        }

        stage('Deploy to Rancher Kubernetes Cluster') {
            when {
                expression { currentBuild.currentResult == 'SUCCESS' }
            }
            steps {
                echo 'Deploy step goes here. Skipping for now.'
                // Example:
                // sh 'kubectl apply -f k8s/deployment.yaml'
            }
        }
    }

    post {
        failure {
            echo 'Pipeline failed!'
        }
        success {
            echo 'Pipeline succeeded!'
        }
    }
}
