//declarative pipeline
pipeline{
    agent any
    parameters{
        string(name: 'BRANCH_NAME', defaultValue: '', description: 'git branch name')
        string(name: 'url', defaultValue: '', description: 'download url')
    }
    stages{
        stage("clone the code"){
            steps{
                println "clonig code from  the other github"
                      
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