pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
    }
   environment {
	    VERSION = "1.0.${BUILD_NUMBER}-SNAPSHOT"

        SONAR_TOKEN = credentials('SONAR_TOKEN')

        NEXUS_USERNAME = credentials('NEXUS_USERNAME')
        NEXUS_PASSWORD = credentials('NEXUS_PASSWORD')

        DOCKERHUB_USERNAME = credentials('DOCKERHUB_USERNAME')
        DOCKERHUB_PASSWORD = credentials('DOCKERHUB_PASSWORD')
        DOCKER_REPOSITORY_NAME = 'zahrahlioui_tpfoyer'
        DOCKER_REPOSITORY_NAMESPACE = 'zahrahlioui'
        DOCKER_REPOSITORY = "${DOCKER_REPOSITORY_NAMESPACE}/${DOCKER_REPOSITORY_NAME}:${VERSION}"

        APP_IMAGE = "${DOCKER_REPOSITORY_NAME}:${VERSION}"
    }
    stages {
        stage('Clean') {
            steps {
                script {
                    sh 'mvn clean'
                }
            }
        }

        stage('Compile') {
            steps {
                script {
                    sh 'mvn compile -DskipTests'
                }
            }
        }

        stage('Sonar-Test') {
            steps {
                script {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'mvn test -DskipCompile'
                }
            }
        }

        stage('Package') {
            steps {
                script {
                    sh 'mvn package -DskipTests -DskipCompile'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed!'
        }
        success {
            echo 'Build was successful!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
