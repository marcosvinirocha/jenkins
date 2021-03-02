pipeline {
    agent { docker { image "maven:3.3.3" } }
    parameters {
        booleanParam(name: "RC", defaultValue: false, description: "Is this a Release Candidate?")
        }
        environment {
        VERSION = "0.1.0"        
        VERSION_RC = "rc.2"
    }
    stages {
        stage("Checkout Git") {
            steps {
                git "https://github.com/ragsns/hello-world-spring-boot"
            }
        }
        stage("Build"){
            environment {
                VERSION_SUFFIX = getVersionSuffix()
            }
            steps{
                sh "mvn -B -DskipTests clean package"
            }
        }
        stage("Testing"){
            steps{
                sh "mvn test"
                junit "test-results.xml"
                
            }
            
        }
        stage(deploy) {
            when {
                expression { return params.RC }
            } 
            steps{
                echo "this is deploy of aplication"
            }
        }

    }
}

String getVersionSuffix() {
    if (params.RC) {
        return env.VERSION_RC
    } else {
        return env.VERSION_RC + "+ci." + env.BUILD_NUMBER
    }
}
