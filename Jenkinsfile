def AGENTE = 'none'
def AMBIENTE = 'none'
def TEST = false;

node('master') {
  stage('Agent'){
    if ("${${env.gitlabSourceBranch}".contains('dev')) {
	    AMBIENTE = "local"
        AGENTE = "sap-server"
	} else if ("${env.gitlabSourceBranch}".contains('main')){
	    AMBIENTE = "sap"
        AGENTE = "newman-slave"
	} 
  }
}


pipeline {

	agent {
		label "${AGENTE}"
	}

	tools {
        maven 'maven386'
    }
    
    environment {
		GITURL_TEST = "https://github.com/oacarrillo/SAP.git"
		BRANCH_TEST = "main"
		COLL_SAP="newman/SAP.postman_collection.json"
		ENV_SAP="newman/SAP.postman_environment.json"
	}

    stages {
        
        stage('Clone Repo Test'){
            steps{
                git url: GITURL_TEST, credentialsId: 'github-user', branch: BRANCH_TEST
	        }
	    }
	    
	    stage('Build & Deploy Local'){
	        when {
                expression { AMBIENTE == 'local'}
            }
	        steps{
                script{
                    // Desplegar en local
                    sh "echo local"
                }
	        }
	    }
	    
	    stage('Build & Deploy SAP'){
	        when {
                expression { AMBIENTE != 'local'}
            }
	        steps{
                script{
                    //sh "newman run ${COLL_SAP} -e ${ENV_SAP} --folder devops --env-var \"buildName=auto-${BUILD_NUMBER}\" "
                    sh "echo sap"
                }
	        }
	    }
	    
	    stage ('Testing') {
            when {
                expression { TEST }
            }
		    steps {
               build job: 'SAPtest'
		    }
		}
    }
}
