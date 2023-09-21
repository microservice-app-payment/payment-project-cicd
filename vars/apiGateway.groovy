import devops.cicd.tools.Ssh

def call() {
    dockerNode(image: 'docker:latest') {
        def ssh = new Ssh()
        def serverDeployIP = "34.126.122.163"
        def serverSonarqubeIP = "34.126.122.163"
        def applicationDir = "/home/ubuntu/registry-service"
        stage("Pull Code") {
            ssh.executeCommand(serverDeployIP, executeDir(applicationDir, "git pull"), "ubuntu")
        }

        stage("Install Dependencies") {
            ssh.executeCommand(serverDeployIP, executeDir(applicationDir, "mvn clean install"), "ubuntu")
        }

        stage("SonarQube Analysis") {
            withSonarQubeEnv('SONARQUBE_SERVER') {
                ssh.executeCommand(serverSonarqubeIP, executeDir(applicationDir, "mvn sonar:sonar"), "ubuntu")
            }
        }
    }
}

def executeDir(dir, command) {
    return "'cd ${dir} && ${command}'"
}
