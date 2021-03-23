pipeline{
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }

    stages{
        stage('Checkout SCM ') {
            steps {
               checkout scm
            }
        }

        stage("Build") {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true  package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploying app to production"
            }
        }
    }
}