// vars/testPipeline.groovy
// Shared library step that runs a simple test pipeline for a Roboshop component

def call(Map configMap) {
    /*
      configMap is expected to contain keys like:
      - project   (e.g. "roboshop")
      - component (e.g. "catalogue")
    */

    pipeline {
        // Choose which Jenkins agent (node) will run this pipeline
        agent {
            node {
                // This label must match a configured Jenkins node in your Jenkins master
                label 'roboshop'
            }
        }

        stages {
            stage('Testing') {
                steps {
                    script {
                        // Run a shell command on the agent and print values from configMap
                        sh """
                            echo "project  : ${configMap.project}"
                            echo "component: ${configMap.component}"
                        """
                    }
                }
            }
        }

        // Post actions run after all stages are done
        post {
            // Runs whether pipeline is success, failure, or aborted
            always {
                echo 'I will always say Hello again!'
            }

            // Runs only if the pipeline completed successfully
            success {
                echo 'pipeline success'
            }

            // Runs only if the pipeline failed
            failure {
                echo 'pipeline failure'
            }
        }
    }
}