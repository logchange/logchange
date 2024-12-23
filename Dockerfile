FROM alpine:3

LABEL org.opencontainers.image.authors='team@logchange.dev' \
      org.opencontainers.image.url='https://github.com/logchange/logchange' \
      org.opencontainers.image.documentation='https://github.com/logchange/logchange/blob/main/README.md' \
      org.opencontainers.image.source='https://github.com/logchange/logchange' \
      org.opencontainers.image.vendor='The logchange Community' \
      org.opencontainers.image.licenses='Apache-2.0'

# Download artifact from GH Actions
# or build with:
# mvn package -Pnative-static && cp /logchange-cli/logchange ./logchange-linuxx64
ADD logchange-linuxx64 /usr/local/bin/logchange

ARG WORKING_PATH="/code"
RUN mkdir $WORKING_PATH
WORKDIR $WORKING_PATH

CMD ["logchnage", "--help"]