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
                body: 'La compilacion y pruebas finalizaron correctamente.'
            telegramSend(message: 'Pipeline EXITOSO - Cinema API', chatId: 8517969379)
            discordSend(
                description: 'Pipeline EXITOSO - Cinema API',
                webhookURL: 'https://discord.com/api/webhooks/1509064273383391302/Hfc-NY31btaRzVS8f5mAaW8JLSYbBIZnXYVuJIj_zbfQwYHrisa8_xLfI7Zve_1h0u3O'
            )
        }
        failure {
            mail to: 'p.laurav2@gmail.com',
                subject: 'Pipeline FALLO',
                body: 'La compilacion o pruebas presentaron errores.'
            telegramSend(message: 'Pipeline FALLO - Cinema API', chatId: 8517969379)
            discordSend(
                description: 'Pipeline FALLO - Cinema API',
                webhookURL: 'https://discord.com/api/webhooks/1509064273383391302/Hfc-NY31btaRzVS8f5mAaW8JLSYbBIZnXYVuJIj_zbfQwYHrisa8_xLfI7Zve_1h0u3O'
            )
        }
    }
}