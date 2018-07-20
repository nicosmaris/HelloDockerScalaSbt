pipeline {
    agent any
    stages {
        stage('Build job...') {
            steps {
              ansiColor('xterm') {
                sh './scripts/ci.sh'
              }
            }
        }
    }
    post {
      always {
          ansiColor('xterm') {
            sh "/var/jenkins_home/docker-compose stop || true"
          }
      }
    }
}

