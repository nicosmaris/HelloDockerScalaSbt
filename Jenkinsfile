pipeline {
    agent { label 'docker' }
    stages {
        stage('Build job') {
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
            sh "docker-compose stop || true"
          }
      }
    }
}

