package devops.cicd.tools

import groovy.transform.Field

@Field SSH_KEY_PREPARED = false

def prepare(credential = "ssh-key") {
    if (SSH_KEY_PREPARED) {
        return
    }
    withCredentials([file(credentialsId: credential, variable: 'FILE')]) {
        def text = readFile(FILE)
        writeFile(file: 'ssh-key', text: text)
        sh "chmod 400 ssh-key"
    }
    SSH_KEY_PREPARED = true
}

def executeCommand(hostname, command, username = "ubuntu") {
    prepare()
    sh "ssh -i ssh-key -o StrictHostKeyChecking=no ${username}@${hostname} -- ${command}"
}
