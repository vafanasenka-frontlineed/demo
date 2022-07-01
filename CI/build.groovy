def build(String workspace, Map conf) {
	dir("${workspace}/${conf.repositories.web_allot_test_framework.target}" ) {
        String suffix = ''
		String release_version = conf.version + "-" + currentBuild.number
		sh """
			echo "Building ..."
			echo "Release version: $release_version"

                        mvn versions:set -DnewVersion=${release_version} --batch-mode
                        mvn clean compile test-compile --batch-mode
                        
			echo "Packing ..."
                        mvn clean package -DskipTests --batch-mode
		"""
	}
}
return this