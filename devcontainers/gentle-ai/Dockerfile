# 1. Base Image
FROM mcr.microsoft.com/devcontainers/go:1.25-bookworm

ENV DEBIAN_FRONTEND=noninteractive

# 2. System Dependencies (Cleaned spaces and added build-essential for Go tools)
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    vim \
    tmux \
    git \
    iputils-ping \
    dnsutils \
    npm \
    build-essential \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

RUN apt-get update && apt-get install -y apt-transport-https ca-certificates gnupg && \
    echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] https://packages.cloud.google.com/apt cloud-sdk main" | tee -a /etc/apt/sources.list.d/google-cloud-sdk.list && \
    curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | gpg --dearmor -o /usr/share/keyrings/cloud.google.gpg && \
    apt-get update && apt-get install -y google-cloud-cli && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# 3. Install Global Tools
# We install opencode-ai via npm and gentle-ai via Go
RUN npm i -g opencode-ai

# Switch to vscode user to ensure 'go install' and config files have right permissions
USER vscode

RUN go install github.com/gentleman-programming/gentle-ai/cmd/gentle-ai@latest

# 4. Configuration (Using the vscode user context)
RUN mkdir -p /home/vscode/.config/opencode/ && \
    echo '{\n\
  "$schema": "https://opencode.ai/config.json",\n\
  "plugin": ["opencode-gemini-auth@latest"],\n\
  "provider": {\n\
    "google": {\n\
      "options": {\n\
        "projectId": "your-specific-project-id"\n\
      }\n\
    }\n\
  }\n\
}' > /home/vscode/.config/opencode/opencode.json

# 5. Initialization
# Since we installed gentle-ai into ~/go/bin, we call it via its path if not yet in $PATH
RUN gentle-ai install \
    --agent opencode \
    --persona gentleman \
    --component engram,sdd,skills,context7,persona,permissions \
    --skill go-testing,skill-creator 
    