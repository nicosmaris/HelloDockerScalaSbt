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
            sh "docker-compose down -v || true"
          }
      }
    }
}

