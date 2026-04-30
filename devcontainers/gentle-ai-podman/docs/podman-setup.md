# Developer Guide: DevContainers with Podman on Windows - Gentle AI

This repo is a collection of standardized dev environments. We use **Podman** as a lightweight, rootless alternative to Docker Desktop, combined with **VS Code DevContainers** to keep our local machines clean.

---

## 1. Setup the Backend (Podman)

Podman on Windows runs inside WSL2, but we don't need to bloat our system with extra Linux distros from the Store.

### The "No-Distro" WSL Setup
1. **Install WSL:** You only need the core subsystem.
   ```powershell
   wsl --install --no-distribution
   ```
2. **Initialize Podman:** This creates its own internal, lightweight Linux machine.
   ```powershell
   podman machine init
   ```
3. **Start the Engine:**
   ```powershell
   podman machine start
   ```

### Pro Tip: The Socket Bridge
To use CLI tools (like the `devcontainer` CLI) outside of VS Code, you need to point them to the Podman socket:
```bash
export DOCKER_HOST=unix://$(podman machine inspect --format '{{.ConnectionArgs.Default.Socket.Path}}')
```

---

## 2. VS Code Configuration

Copy-paste these into your **User Settings (`settings.json`)**. These settings are specifically tuned to stop Podman from fighting with the Windows filesystem (fixes those annoying UNC path and permission errors).

```json
{
    "geminicodeassist.project": "YOUR_GCP_PROJECT_ID",
    "dev.containers.dockerPath": "podman",
    "dev.containers.dockerComposePath": "podman-compose",
    "dev.containers.executeInWSL": false,
    "dev.containers.mountWaylandSocket": false,
    "dev.containers.mountX11Socket": false,
    "dev.containers.copyAttributes": "none",
    "redhat.telemetry.enabled": true
}
```
> **Why `copyAttributes: "none"`?** Windows and Linux handle file permissions differently. This setting prevents Podman from trying to sync them, which speeds up builds and avoids "Permission Denied" bugs on Windows.

---

## 3. How to Use (GUI Workflow)

1.  **Open a project:**
    ```bash
    cd devcontainers/java8
    code .
    ```
2.  **Launch:** When the "Reopen in Container" notification pops up, click it. 
3.  **Force a Rebuild:** If you change the `Dockerfile`, open the Command Palette (`Ctrl+Shift+P`) and run:  
    `Dev Containers: Rebuild Without Cache and Reopen in Container`.

---

## 4. Using the CLI (Optional)

If you prefer the terminal, install the `@devcontainers/cli` via npm. **Always run these from inside the project folder.**

- **Spin up:** `devcontainer up`
- **Run a command inside:** `devcontainer exec mvn clean install`
- **Check config:** `devcontainer read-configuration`

---

## 5. Useful Podman Commands

Keep your system tidy with these:
- **What's running?** `podman ps -a`
- **Manual Start/Stop:** `podman start <name>` | `podman stop <name>`
- **Delete machine:** `podman machine rm`
- **Nuke unused data:** `podman system prune -a --volumes`

---

## 6. Official Repos & Resources

### Standards & Features
- [Official DevContainer Features](https://github.com/devcontainers/features) (Pre-built tools you can add to your config).
- [Official Templates](https://github.com/devcontainers/templates) (Starting points for new projects).

### Images & Source
- [Microsoft DevContainer Images](https://github.com/devcontainers/images) (The source code for our base images).
- [Docker Hub (Microsoft)](https://hub.docker.com/r/microsoft/devcontainers) (Where the pre-built images live).

### Project Specifics
- [Gentle-AI CLI Repo](https://github.com/gentleman-programming/gentle-ai)
- [Podman Desktop](https://podman-desktop.io) (If you want a GUI for Podman).
