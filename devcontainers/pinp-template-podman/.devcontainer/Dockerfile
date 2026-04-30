FROM mcr.microsoft.com/devcontainers/base:jammy

# Update and install podman, fuse-overlayfs, and rootless requirements
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install --no-install-recommends \
    podman \
    fuse-overlayfs \
    slirp4netns \
    uidmap \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Configure subuid/subgid for vscode user (devcontainer default)
RUN usermod --add-subuids 10000-65535 --add-subgids 10000-65535 vscode

# Create configuration directories
RUN mkdir -p /etc/containers

# Copy proper configuration files to prevent newline corruption from echo commands
COPY storage.conf /etc/containers/storage.conf
COPY containers.conf /etc/containers/containers.conf
