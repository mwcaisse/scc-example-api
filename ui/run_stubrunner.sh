#!/bin/bash


# If stubrunner jar doesn't exist, fetch it
if [ ! -f ./stubrunner.jar ]; then
  wget https://repo1.maven.org/maven2/org/springframework/cloud/spring-cloud-contract-stub-runner-boot/3.0.0/spring-cloud-contract-stub-runner-boot-3.0.0.jar -O stubrunner.jar
fi

java -jar \
  -Dstubrunner.generateStubs=true \
  -Dstubrunner.repositoryRoot=git://git@github.com:mwcaisse/scc-example-api-ui.git \
  -Dstubrunner.stubsMode=REMOTE \
  -Dstubrunner.ids=com.mwcaisse.samples:recipe-api:+:8950 \
  ./stubrunner.jar