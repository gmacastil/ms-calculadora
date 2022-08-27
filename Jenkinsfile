pipeline {

    agent { 
        label 'master'
    } 

    tools {
        maven 'maven386'
    }
    
    environment {
        AMBIENTE=setEnv()
	GITURL_TEST = "https://github.com/oacarrillo/SAP.git"
	BRANCH_TEST = "main"
	COLL_SAP="newman/SAP.postman_collection.json"
	ENV_SAP="newman/SAP.postman_environment.json"
        TEST=false
    }

    stages {
        
        stage('Build & Deploy Local'){
            when {
		    expression { ${AMBIENTE} == 'local'}
            }
            agent { 
                label 'sap-server'
            }
	    steps{
		script{
		    // Desplegar en local
			sh "echo ${AMBIENTE}"
			sh "echo $AMBIENTE"
			sh "echo $BRANCH_NAME"
			sh "echo ${BRANCH_NAME}"
			sh "echo ${env.BRANCH_NAME}"
			
		    sh "echo local -----------------------"
		}
	    }
	}

        stage('Clone Repo Test'){
            steps{
                git url: GITURL_TEST, credentialsId: 'github-user', branch: BRANCH_TEST
	    }
	}
	    
        stage('Build & Deploy SAP'){
	    when {
		    expression { ${AMBIENTE} != 'local'}
            }
            agent { 
                label 'newman-slave'
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
		    expression { ${TEST} }
            }
            agent { 
                label 'newman-slave'
            }
	    steps {
        	build job: 'SAPtest'
	    }
	}
    }
}

def setEnv() {
	if ("${env.BRANCH_NAME}".contains('dev') ) {
		return "dev"
	} else if ("${env.BRANCH_NAME}".contains('main')){
		return "main"
	} 
}

