import devops.cicd.tools.Ssh

def call() {
    def ssh = new Ssh()
    def serverDeployIP = "34.126.122.163"
    def serverSonarqubeIP = "34.126.122.163"
    def applicationDir = "/home/ubuntu/registry-service"
    stage("Pull Code") {
        sh 'echo "Hello world"'
    }
}

def executeDir(dir, command) {
    return "'cd ${dir} && ${command}'"
}
