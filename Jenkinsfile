//-Done By,Samanvitha Matta,G01252738-->
//<!--Akshaya Reddy Dundigalla,G01482843-->
//<!--Tarun Naga Sai Chadaram,G01445928-->

pipeline {
    agent any  // Run this pipeline on any available Jenkins agent

    environment {
        // Define environment variables
        IMAGE_NAME = "tarunchadaram/stusurvey-app"  // Docker image name on Docker Hub
        TAG = "${env.BUILD_ID}"  // Use the Jenkins build ID as the image tag
    }

    stages {
        // Stage 1: Checkout source code from the configured SCM (e.g., GitHub)
        stage("Checkout Code") {
            steps {
                script {
                    checkout scm  // Uses Jenkinsfile's linked SCM repo
                }
            }
        }

        // Stage 2: Build Docker image and push it to Docker Hub
        stage("Build and Push Docker Image") {
            steps {
                script {
                    // Use Docker Hub credentials securely stored in Jenkins
                    withCredentials([usernamePassword(
                        credentialsId: 'dockerhub',  // Credential ID from Jenkins
                        usernameVariable: 'DOCKERHUB_USERNAME',
                        passwordVariable: 'DOCKERHUB_PASSWORD'
                    )]) {
                        // Log in to Docker Hub
                        sh "echo \$DOCKERHUB_PASSWORD | docker login -u \$DOCKERHUB_USERNAME --password-stdin"
                    }

                    // Build Docker image with the current build ID as the tag
                    sh "docker build -t $IMAGE_NAME:$TAG ."

                    // Push the tagged image to Docker Hub
                    sh "docker push $IMAGE_NAME:$TAG"

                    // Also tag and push as 'latest'
                    sh "docker tag $IMAGE_NAME:$TAG $IMAGE_NAME:latest"
                    sh "docker push $IMAGE_NAME:latest"
                }
            }
        }

        // Stage 3: Deploy the application to a Kubernetes cluster managed by Rancher
        stage("Deploy to Rancher Kubernetes Cluster") {
            steps {
                script {
                    // Replace placeholder IMAGE_NAME in deployment.yaml with actual image:tag
                    sh "sed -i 's|IMAGE_NAME|$IMAGE_NAME:$TAG|g' deployment.yaml"

                    // Use KUBECONFIG credentials stored securely in Jenkins
                    withCredentials([file(
                        credentialsId: 'kubernetes',  // This is the Kubeconfig file stored in Jenkins
                        variable: 'KUBECONFIG'
                    )]) {
                        // Apply deployment and service YAML to the Kubernetes cluster
                        sh "kubectl --kubeconfig=$KUBECONFIG apply -f deployment.yaml"
                        sh "kubectl --kubeconfig=$KUBECONFIG apply -f service.yaml"
                    }
                }
            }
        }
    }
}
