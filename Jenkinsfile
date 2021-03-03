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
               sh 'mvn -B -DskipTests package' 
            }
            
        }
        stage("Test"){
            steps{
                sh 'mvn test'
            }
            post{
                always{
                    junit "target/surefire-reports/*.xml"
                    echo "this is simple test"
                }
                
            }
        }
    }
}
