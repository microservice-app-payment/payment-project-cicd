# devops-project-cicd

## Jenkins Plugin requirements
- Docker Plugin: https://plugins.jenkins.io/docker-plugin/

## Remote server config
- On remote server (apllication server). Comment these line:
- **from**
```shell
# If not running interactively, don't do anything
case $- in
    *i*) ;;
      *) return;;
esac
```
- **to**

```shell
# If not running interactively, don't do anything
#case $- in
#    *i*) ;;
#      *) return;;
#esac
```


## Setup manual

1. Generate SSH key and add to remote(application server)
```shell
# generate ssh
ssh-keygen
# copy ssh key to remote server
ssh-copy-id <user>@<server_ip>
```
2. Upload private key to Jenkins credential

- On Jenkins UI `Dashboard` >  `Manage Jenkins` >  `Credentials` >  `System` > `Global credentials (unrestricted)`
- Click on `Add Credentials`
- Upload file `~/.ssh/id_rsa` to Jenkins credential

![credentials.png](images%2Fcredentials.png)
3. Config Jenkins share library
- On Jenkins UI `Dashboard` >  `Manage Jenkins` > `System` > `Global Pipeline Libraries`
- Add Share library as follows:

![share-lib.png](images%2Fshare-lib.png)
4. After that we can use share library in Jenkins file as follows:
```groovy
@Library("cicd")_
pipelineFileName() // file name in "vars" folder 
```
example:
```groovy
@Library("cicd")_
frontend() // file name in "vars" folder 
```
