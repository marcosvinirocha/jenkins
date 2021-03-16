pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    parameters {
        choice(name: 'VERSION', choices: ['1.10', '1.2.0', '1.3.0'], description: '')
        booleanParam(name: 'executeTests', defaultValue: true, description: '')
        booleanParam(name: 'deploy', defaultValue: true, description: '')
    }
    stages {
        stage('SCM Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/spring-projects/spring-petclinic'
            }
        }
        stage("Build") {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true  package'
            }
        }

        stage('Test') {
            when {
                expression {
                    params.executeTests
                }
            }
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
            when {
                expression {
                    params.deploy
                }
            }
            steps {
                echo "Deploying app to production"
            }
        }

    }
}
