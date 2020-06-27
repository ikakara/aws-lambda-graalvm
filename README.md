# aws-lambda-graalvm

Tests to compare aws-lambda supported languages (micronaut, nodejs, python) for memory use and performance

**Use cases to be tested**

* /echo/{text}                    - baseline
* /find-primes-below/{number}     - computational work
* /food-trivia-random/{apiKey}    - goto spoonacular.com to get an apiKey
                                    I'm not affiliated with these folks in anyway,
                                    just needed a simple api to test http client

**Technologies Used:**

* AWS Lambda                      - serverless functions <https://docs.aws.amazon.com/lambda/index.html>
* GraalVm v20.1.0                 - compile to native code <https://www.graalvm.org/docs/release-notes/20_0/>
* Docker (installation required)  - needed to run graalvm in amazonlinux environment
  * Toolbox                       - <https://github.com/docker/toolbox/releases>
  * Desktop                       - <https://docs.docker.com/release-notes/>
* VSCode (optional)               - 2 very useful extensions: Docker (view container, images) and AWS Explorer (view/invoke lambda functions)
  * Download                      - <https://code.visualstudio.com/download>
* AWS SAMCLI (optional)           - test lambda functions locally
  * Installation                  - <https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install.html>
* AWS CLI                         - used to upload lambda functions to AWS
  * Installation (required)       - <https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html>
* Gradle                          - build tool for micronaut, nodejs and python

**Other Installation:**

* Micronaut v2.0.0                - <https://micronaut.io/download.html>
  * OpenJDK 14                    - <https://developers.redhat.com/products/openjdk/download>
* nodejs v12.16.3                 - <https://nodejs.org/en/download/>
* python v3.8                     - this is installed w/ conda, or Visual Studio
  * Anaconda Install              - <https://docs.anaconda.com/anaconda/install/>

**Installation Instructions**

The only installation that wasn't straight forward, was Docker.  So good luck ... kidding.  I'll help as much as I can.
I'm using Windows Home, so I chose Docker Toolbox (versus Docker Desktop).

**Docker Toolbox Installation Notes**

* Download Docker Toolbox (for Windows, Mac or Linux) - https://docs.docker.com/toolbox/ 
* Check make sure your cpu has virtualization enabled -- THIS IS VERY IMPORTANT
* After installation, check to be sure Docker is working
  * Run Oracle VM VirtualBox (Console App)
  * Run Docker Quickstart Terminal -- wait for VirtualBox to finish (takes a minute or two)
  * In the docker terminal: $docker run hello-world -
    * if problems, reset the default machine in the Console App OR run the following commands in the terminal
      * $ docker-machine rm default
      * $ docker-machine create --driver virtualbox default
    * Check VS Code (install Docker extension), you should see the docker containers, images, etc
* Update the VM settings for the Virtual Box that you're using (default)
  * Open Oracle VM Virtual Box
  * Close (if running) your VM - I use the default
    * on windows, right click on VM (default) and select "Close" (power off)
  * Click settings
    * System tab -
      * increase memory to at least 4Gb - if you are unable to change settings, it's because your VM is still running
      * increase cpu to at least 4 (or half system total)
    * Display tab - increase your video memory (I'm using 32mb - anything more than 8mb)
    * Storage tab - I've spent many hours trying to increase disk.vmdk - the boot2docker doesn't like it (won't let you connect)
    * Shared folders - mount any drives that you need.  Docker needs access to the paths for java, micronaut, etc

I found this thread to be very helpful: <https://github.com/docker/for-win/issues/204>

**Handy Docker commands**

$ docker-machine ip  --- you'll need this if you want to run the app in docker, and test in the browser http://machineip:8088/

**Micronaut Build**

I'm planning on update the build.gradle files so that you can manage all the builds from the root.  
I haven't done it yet, so for now, you'll have to build in the project folders.

* Run the Oracle VM - start the VM (default) - I start it headless
* Run the Docker Quickstart Terminal
* Wait for it to load ...
* cd to aws-lamda-graalvm/micronaut folder
* for brand new installation

```
./gradlew assemble (docker env)
./docker-build.sh  (docker env)
mkdir build

```

After code changes, I like to test/debug locally (local env) 

```
./gradlew test  

```

**Build and create a folder.zip and upload to Amazon (docker env)**

```
$./docker-build.sh
$docker run --rm --entrypoint cat micronaut  /home/application/function.zip > build/function.zip
$aws lambda create-function \
  --function-name micronaut \
  --zip-file fileb://build/function.zip \
  --handler function.handler \
  --runtime provided \
  --role [enter your aws-lambda-role]

```

For updates:

```
$aws lambda update-function-code \
  --function-name micronaut \
  --zip-file fileb://build/function.zip

```

There's also a deploy.sh script that does the above

Use the following to invoke the lammbda function

```
aws lambda invoke --function-name micronaut --payload ''

```

Use the following payloads:

```
{
  "resource": "/echo/blah",
  "path": "/echo/blah",
  "httpMethod": "GET"
}
```

```
{
  "resource": "/find-primes-below/999",
  "path": "/find-primes-below/999",
  "httpMethod": "GET"
}
```

```
{
  "resource": "/food-trivia-random/{enter your apiKey}",
  "path": "/food-trivia-random/{enter your apiKey}",
  "httpMethod": "GET"
}
```

I'll update the doc once I wire this to AWS API-Gateway (REST API) and/or AWS AppSync (GraphQL)

I'll update when I add the nodejs and python tests
