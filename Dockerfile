FROM java:openjdk-7

MAINTAINER Jentsch <d.jentsch@fu-berlin.de>

ENV ACTIVATOR_VERSION 1.3.5

RUN cd /tmp && \
  wget http://downloads.typesafe.com/typesafe-activator/$ACTIVATOR_VERSION/typesafe-activator-$ACTIVATOR_VERSION.zip && \
  unzip typesafe-activator-$ACTIVATOR_VERSION.zip && \
  mkdir /opt/typesafe && \
  mv /tmp/activator-dist-$ACTIVATOR_VERSION /opt/typesafe/activator-$ACTIVATOR_VERSION && \
  ln -s /opt/typesafe/activator-$ACTIVATOR_VERSION/activator /usr/local/bin/activator && \
  rm /tmp/typesafe-activator-$ACTIVATOR_VERSION.zip

WORKDIR /opt/

RUN git clone https://jentsch@github.com/Jentsch/scaml-play-example.git /opt/scaml-play-example/

WORKDIR /opt/scaml-play-example/

# Downloads SBT
RUN activator scalaVersion
# Downloads dependencies
RUN activator compile
RUN activator -Dactivator.timeout=2s ui

EXPOSE 8888 9000

CMD ["activator", "-Dactivator.timeout=8h", "-Dhttp.address=0.0.0.0", "ui"]

