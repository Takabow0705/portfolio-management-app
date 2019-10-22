FROM amazoncorretto:11

WORKDIR /usr/app

RUN yum update -y && yum upgrade -y \
    && yum intall git curl \
    && git clone https://github.com/Takabow0705/web-app.git \
    && cd web-app \
    && ./gradlew --full-stacktrace -q build

CMD [ "java", "-jar", "-Dspring.profiles.active=prod", "build/libs/portfolio-management-app-1.0-SNAPSHOT.jar" ]

