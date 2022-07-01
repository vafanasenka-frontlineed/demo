def deploy(String workspace, Map conf) {
		
	dir("${workspace}/${conf.repositories.web_allot_test_framework.target}" ){
		sh """
			echo "Deploying ..."
                        mvn deploy -gs ${workspace}/settings.xml -DskipTests --batch-mode
		"""
	}
}
return this