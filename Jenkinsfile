#!groovy

@Library('jenkinslib') _  
def mytools = new org.devops.tools()
def gitcode = new org.devops.gitcode()
def build = new org.devops.build()

String branchName = "${env.branchName}"
String gitHttpURL = "${env.gitURL}"
String credentialsId = "${env.credentialsId}"
String buildTpye = "${env.buildTpye}"
String buildShell = "${env.buildShell}"


pipeline {
    agent any

    stages {
        stage("GetCode"){
            steps{
                script{
        
                    // 调用GetCode方法进行代码下载 
                    mytools.PrintMes("获取代码",'green')
                    gitcode.GetCode("git",branchName,gitURL,credentialsId)
                    }
                }
            }
        
       
        stage("Build"){
            steps{
                script{
                    build.Build(buildType,buildShell)
                    //stash includes: 'target/*.jar', name: 'app'
                    }
                }
            } 
        
       stage("BuildImage"){
            steps{
                script{
                    mytools.PrintMes("构建上传镜像","green")
                    env.serviceName = "${JOB_NAME}"
                    
                    def dockerFile = libraryResource 'Dockerfile'
                    writeFile file: "${workspace}/Dockerfile", text: dockerFile
                    sh "cat ${workspace}/Dockerfile"


                    withCredentials([usernamePassword(credentialsId: 'HARBOR_ACCOUNT', passwordVariable: 'password', usernameVariable: 'username')]) {
                           
                        env.dockerImage = "10.94.169.67/magictommy/${serviceName}:${branchName}"
                        sh """
                            docker login -u ${username} -p ${password}  10.94.169.67
                            docker build -t 10.94.169.67/magictommy/${serviceName}:${branchName} .
                            sleep 1
                            docker push 10.94.169.67/magictommy/${serviceName}:${branchName}
                            sleep 1

                        """
                        }
                    }
                }
            } 
        
    }
}
