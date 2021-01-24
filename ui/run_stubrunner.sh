#!/bin/bash

# Stubrunner download URL
# https://repo1.maven.org/maven2/org/springframework/cloud/spring-cloud-contract-stub-runner-boot/3.0.0/spring-cloud-contract-stub-runner-boot-3.0.0.jar

java -jar \
  -Dstubrunner.generateStubs=true \
  -Dstubrunner.repositoryRoot=git://git@github.com:mwcaisse/scc-example-api-ui.git \
  -Dstubrunner.stubsMode=REMOTE \
  -Dstubrunner.ids=com.mwcaisse.samples:recipe-api:+ \
  spring-cloud-contract-stub-runner-boot-3.0.0.jar