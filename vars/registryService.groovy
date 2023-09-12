import devops.cicd.tools.Ssh

def call() {
    dockerNode(image: 'ngochung1809/registry-service:0.0.1') {
        def ssh = new Ssh()
        def serverIP = "34.126.122.163"
        def applicationDir = "/home/ubuntu/registry-service"
        stage("Pull Code") {
            ssh.executeCommand(serverIP, executeDir(applicationDir, "git pull"), "ubuntu")
        }
        stage("Install Dependencies") {
            ssh.executeCommand(serverIP, executeDir(applicationDir, "mvn install"), "ubuntu")
        }
        
        stage("Restart application") {
            ssh.executeCommand(serverIP, executeDir(applicationDir, "pm2 restart api"), "ubuntu")
        }
    }
}

def executeDir(dir, command) {
    return "'cd ${dir} && ${command}'"
}
