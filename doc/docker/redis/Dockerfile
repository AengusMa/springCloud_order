# redis 容器
FROM ubuntu:16.04
LABEL maintainer="mlwjndx@163.com"
ENV REFRESHED_AT 2018-10-29

RUN apt-get -qq update && apt-get -qq install redis-server redis-tools

EXPOSE 6379

ENTRYPOINT ["/usr/bin/redis-server" ]
CMD []