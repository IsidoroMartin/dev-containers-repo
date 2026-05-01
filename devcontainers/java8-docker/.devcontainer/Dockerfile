FROM mcr.microsoft.com/devcontainers/java:8

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    git \
    maven \
    && apt-get clean && rm -rf /var/lib/apt/lists/*
