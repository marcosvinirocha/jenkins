FROM ubuntu:20.04
USER root
RUN apt-get update
RUN curl -s "https://get.sdkman.io" | bash
RUN source "$HOME/.sdkman/bin/sdkman-init.sh"
RUN sdk install java 11.0.10-librca
RUN sudo apt-get install ssh -y
RUN sudo systemctl ssh start
RUN sudo systemctl ssh enable
