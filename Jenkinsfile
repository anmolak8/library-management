pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/anmolak8/library-management'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Deploy') {
            steps {
                sh 'ansible-playbook ansible/playbooks/deploy.yml'
            }
        }
    }
post {
    always {
        cleanWs()  // Clean workspace after build
    }
    // Remove or comment out the slackSend blocks
}
