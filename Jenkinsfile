pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage('Clonar repositorio') {
            steps {
                echo 'Repositorio clonado'
            }
        }
        stage('Compilar') {
            steps {
                dir('cinema') {
                    sh 'mvn clean compile -DskipTests'
                }
            }
        }
        stage('Pruebas') {
            steps {
                dir('cinema') {
                    sh 'mvn test -DskipTests'
                }
            }
        }
        stage('Validar autenticacion') {
            steps {
                echo 'ERROR: Falla critica en modulo de autenticacion'
                error('Error intencional en autenticacion - feature/auth-error')
            }
        }
    }
    post {
        success {
            mail to: 'p.laurav2@gmail.com',
            subject: 'Pipeline EXITOSO',
            body: 'La compilacion y pruebas finalizaron correctamente.'
        }
        failure {
            mail to: 'p.laurav2@gmail.com',
            subject: 'Pipeline FALLO',
            body: 'La compilacion o pruebas presentaron errores.'
        }
    }
}