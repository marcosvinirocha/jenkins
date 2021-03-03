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
                git branch: 'main', url: 'https://github.com/SillasVinicius/myMediaListBackend'
            }
        
        }
        stage("Build"){
            steps{
               sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            
        }
        stage("Test"){
            steps{
                sh 'mvn test'
            }
            post{
                success{
                    junit "**/target/surefire-reports/TEST-*.xml"
                    archiveArtifacts "target/*.jar"
                }
                
            }
        }
    }
}
