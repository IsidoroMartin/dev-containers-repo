# Microsoft MCR Docker Registry - https://mcr.microsoft.com
FROM mcr.microsoft.com/devcontainers/java:21

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    git \
    maven \
    && apt-get clean && rm -rf /var/lib/apt/lists/*
