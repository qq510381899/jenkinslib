package org.devops
 
def GetCode(srcType,branchName,gitHttpURL,credentialsId){
 
    if (srcType == "git"){
        println("下载代码 --> 分支： ${branchName}")
        checkout([$class: 'GitSCM', branches: [[name: "${branchName}"]],
                    extensions: [], 
                    userRemoteConfigs: [[credentialsId: "${credentialsId}", 
                                        url: "${gitHttpURL}"]]])
    }
 
}
