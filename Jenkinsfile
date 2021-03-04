pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
        stages{
        stage('SCM Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/tolgafiratoglu/spring-boot-junit-test-examples'
            }
        
        }
        stage("Build"){
            steps{
               sh 'mvn -Dmaven.test.failure.ignore=true  package' 
            }
            post{
                always{
                    junit "**/target/surefire-reports/*.xml"
                    archiveArtifacts "target/*.jar"
                }
                
            }
            
        }
        
    }
}
