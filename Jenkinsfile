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
    }
    post {
        success {
            mail to: 'p.laurav2@gmail.com',
            subject: 'Pipeline EXITOSO',
            body: 'La compilación y pruebas finalizaron correctamente.'
        }
        failure {
            mail to: 'p.laurav2@gmail.com',
            subject: 'Pipeline FALLÓ',
            body: 'La compilación o pruebas presentaron errores.'
        }
    }
}
