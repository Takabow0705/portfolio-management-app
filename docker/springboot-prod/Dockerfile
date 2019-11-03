FROM amazoncorretto:11

WORKDIR /usr/app

RUN yum update -y && yum upgrade -y \
    && yum install git -y \
    && git clone https://github.com/Takabow0705/web-app.git \
    && cd web-app \
    && ./gradlew --full-stacktrace -q build \
    && chmod 777 ./build/libs/portfolio-management-app-0.1.0-SNAPSHOT.jar

WORKDIR /usr/app/deploy

RUN ln -nfs /usr/app/web-app/build/libs/portfolio-management-app-0.1.0-SNAPSHOT.jar app
RUN ln -nfs /usr/app/web-app/gradlew gradlew

CMD [ "bash" ]

