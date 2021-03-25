node {
  stage('Clone the Git') {
    git 'https://github.com/shazforiot/GOL.git'
  }
  stage('SonarQube analysis') {
    def scannerHome = tool 'sonarqube';
    withSonarQubeEnv('sonarqube') {
      sh "${scannerHome}/bin/sonar-scanner \
      -Dsonar.organization=marcosvinirocha \
      -Dsonar.projectKey=testes-algar \
      -Dsonar.sources=. \
      -Dsonar.host.url=https://sonarcloud.io
    }
  }
}