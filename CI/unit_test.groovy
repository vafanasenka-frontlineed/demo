def unit_test(String workspace, Map conf) {
	dir("${workspace}/${conf.repositories.web_allot_test_framework.target}" ) {
		sh """
			echo "Running Unit Tests ..."
                        mvn test 
		"""
	}
}
return this