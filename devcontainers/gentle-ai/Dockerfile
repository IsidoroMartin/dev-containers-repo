# Usamos el nombre completo del registro para evitar el error "image not known"
FROM mcr.microsoft.com/devcontainers/go:1.24-bookworm

# Evitamos que la instalación de paquetes se detenga
ENV DEBIAN_FRONTEND=noninteractive

# Añadir paquetes de Linux 
RUN apt-get update && apt-get install -y \
    curl \
    vim \
    tmux \
    git \
    iputils-ping \
    dnsutils \
    && npm i -g opencode-ai \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Comando para instalar kubectl 
# RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" \
#     && chmod +x kubectl \
#     && mv kubectl /usr/local/bin/