FROM alpine

ADD https://releases.hashicorp.com/consul-template/0.15.0/consul-template_0.15.0_linux_amd64.zip /tmp

RUN unzip -q -d /usr/local/bin /tmp/consul-template_0.15.0_linux_amd64.zip \
  && rm -f /tmp/consul-template_0.15.0_linux_amd64.zip \

CMD /consul-template
