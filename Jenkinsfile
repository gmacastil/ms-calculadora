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
		expression { AMBIENTE == 'local'}
            }
            agent { 
               label 'sap-server'
            }
	    steps{
		script{
		    sh "hostname"
		    sh "echo git pull"	    
		    sh "echo ./shutdown.sh"
	            sh "echo ant clean all"
		    sh "echo ./hybridserver.sh start"
		}
	    }
	}
	    
        stage('Build & Deploy SAP'){
	    when {
		    expression { AMBIENTE != 'local'}
            }
            agent { 
                label 'newman-slave'
            }
	    steps{
		
		git url: GITURL_TEST, credentialsId: 'github-user', branch: BRANCH_TEST
		
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
            agent { 
                label 'master' //winslave
            }
	    steps {
		sh "echo build job: 'SAPtest'"
	    }
	}
    }
}

def setEnv() {
	if ("${env.BRANCH_NAME}".contains('dev') ) {
		return "local"
	} else if ("${env.BRANCH_NAME}".contains('main')){
		return "prd"
	} 
}

