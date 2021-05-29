#!/bin/bash

PROJECT_HOME=$(pwd)
DEFAULT_JVM_OPTS="-Duser.language=ja -Dfile.encoding=UTF-8"
BP_GRADLE_BUILD_ARGUMENTS="assemble -x test --build-cache --quiet ${DEFAULT_JVM_OPTS}"
ENV="${DEFAULT_JVM_OPTS} ${BP_GRADLE_BUILD_ARGUMENTS}"

# build project-web
pack build --builder=paketobuildpacks/builder:base web-app:0.0.1 --env BP_GRADLE_BUILT_ARTIFACT="project-web/build/libs/*.jar" --env BP_GRADLE_BUILD_ARGUMENTS="${BP_GRADLE_BUILD_ARGUMENTS}"

# build project-calculator
pack build --builder=paketobuildpacks/builder:base calculator:0.0.1 --env BP_GRADLE_BUILT_ARTIFACT="project-calculator/build/libs/*.jar" --env BP_GRADLE_BUILD_ARGUMENTS="${BP_GRADLE_BUILD_ARGUMENTS}"