FROM java:8

# https://issues.apache.org/jira/browse/FLINK-6332
# avoid scala 2.12 until flink 1.6 https://issues.apache.org/jira/browse/FLINK-5005
ENV SCALA_VERSION 2.11.1
ENV MILL_VERSION 0.2.5

# Define working directory
WORKDIR /root

# Scala expects this file
RUN touch /usr/lib/jvm/java-8-openjdk-amd64/release

# Install Scala
## Piping curl directly in tar
RUN \
  curl -fsL https://downloads.typesafe.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz | tar xfz - -C /root/ && \
  echo >> /root/.bashrc && \
  echo "export PATH=~/scala-$SCALA_VERSION/bin:$PATH" >> /root/.bashrc

# Install mill
RUN \
  curl -L -o /usr/local/bin/mill https://github.com/lihaoyi/mill/releases/download/$MILL_VERSION/$MILL_VERSION && \
  chmod +x /usr/local/bin/mill && \
  touch build.sc && \
  mill -i resolve _ && \
  rm build.sc

RUN mkdir -p /workspace/out
RUN mkdir -p /input
WORKDIR /workspace

# Application folder
#ENV APP_HOME /app
#RUN mkdir $APP_HOME
#WORKDIR $APP_HOME

# Needed for local development
#ARG username
#ARG userid
#RUN useradd --no-create-home --user-group --shell /bin/bash --home-dir $APP_HOME --uid $userid $username
#RUN chown -R $username: $APP_HOME

#USER $username

