def call(Map configMap){
    pipeline {
        agent {
            node {
                label 'roboshop' 
            } 
        }
        
        stages {
            stage('testing') {
                steps {
                    script{
                        sh """
                            echo "project: ${configMap.project}"
                        """
                    }
                }
            }
        }

        // post build
        post { 
            always { 
                echo 'I will always say Hello again!'
            }
            success {
                echo "pipeline success"
            }
            failure {
                echo "pipeline failure"
            }
        }
    }
}