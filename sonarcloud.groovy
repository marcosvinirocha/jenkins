node {
  stage('Clone the Git') {
    git 'https://github.com/shazforiot/GOL.git'
  }
  stage('SonarQube analysis') {
    def scannerHome = tool 'sonarqube';
    withSonarQubeEnv('sonarqube') {
      sh "${scannerHome}/bin/sonar-scanner \
      -Dsonar.organization=marcosvinirocha \
      -Dsonar.login=8c5822fabad7e04443398a11c8869eed0157a8d4 \
      -Dsonar.projectKey=testes-algar \
      -Dsonar.sources=. \
      -Dsonar.exclusions=vendor/**,resources/**,**/*.java \
      -Dsonar.host.url=https://sonarcloud.io"
    }
  }
}