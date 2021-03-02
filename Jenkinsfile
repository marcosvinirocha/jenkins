pipeline {
    agent { docker { image 'maven:3.3.3' } }
    parameters {
        booleanParam(name: 'RC', defaultValue: false, description: 'Is this a Release Candidate?')
        }
        environment {
        VERSION = "0.1.0"        
        VERSION_RC = "rc.2"
    }
    stages {
        stage('git clone') {
            steps {
                git 'https://github.com/ragsns/hello-world-spring-boot'
            }
        }
        stage('Build'){
            environment {
                VERSION_SUFFIX = getVersionSuffix()
            }
            steps{
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Testing'){
            steps{
                echo 'This is a simple test and before running i can test the package.' 
            }
        }
        stage(deploy) {
            when {
                expression { return params.RC }
            } 
            steps{
                echo 'this is deploy of aplication'
            }
        }
    }
}

String getVersionSuffix() {
    if (params.RC) {
        return env.VERSION_RC
    } else {
        return env.VERSION_RC + '+ci.' + env.BUILD_NUMBER
    }
}
