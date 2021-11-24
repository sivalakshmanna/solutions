//declarative pipeline
pipeline{
    agentany
    parameters{
        string(name: 'SOURCE_CODE', defaltValue: '',description: 'clone source code')
        string(name: 'BRANCH_NAME', defaltValue: '', description: 'git branch name')
    }
    stages{
        stage("clone the code"){
            steps{
                println "clonig code from github"
            }
        }
        stage("build the code"){
            steps{
                println "build the code"
                sh "mvn clean package"
                sh "ls -l target"
            }
        }
        stage("uploading artifacts to s3"){
            steps{
                println "upload artifacts to s3 bucket"
                sh "echo $BUILD_NUMBER"
                sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://sivabandela/${BRANCH}/${BUILD_NUMBER}/"
            }
        }
    }
}