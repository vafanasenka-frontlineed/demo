def remote = [:]
def DOCKER_REPO_HOST = "artifactory.rdlab.local"
def DOCKER_REPO_PATH = "automation-docker-dev-local"
def IMAGE_PATH = "allot/quality/infra/scheduler"
def IMAGE_NAME = "cre_scheduler"
def DOCKER_PULL_PATH = "$DOCKER_REPO_HOST/$DOCKER_REPO_PATH/$IMAGE_PATH/$IMAGE_NAME"
def CONTAINER_NAME = "cre_scheduler"

remote.name = 'cre-scheduler'
remote.host = '10.110.91.60'
remote.user = 'root'
remote.password = 'root12'
remote.allowAnyHosts = true



pipeline {
    agent any

    stages {
        stage('Stopping Running Containers') {
            steps {
                sshCommand remote: remote, command: "docker stop $CONTAINER_NAME | true"
                sshCommand remote: remote, command: "docker rm $CONTAINER_NAME | true"
            }
        }

        stage('Pulling Docker Image') {
            steps {
                sshCommand remote: remote, command: "docker pull $DOCKER_PULL_PATH:${env.DOCKER_TAG}"
            }
        }

        stage('Running Service') {
            steps {
                sshCommand remote: remote, command: "docker run -tdi -p 80:8088 --name $CONTAINER_NAME $DOCKER_PULL_PATH:${env.DOCKER_TAG}"
            }
        }
    }
}
