# Instalación de WSL2 con Ubuntu 24.04 en Windows con docker engine
---

## 1. Instalación de la Distro (Desde Windows)

Abre **PowerShell** como administrador y ejecuta el comando para instalar la versión LTS específica:

```powershell
wsl --install -d Ubuntu-24.04
```

Una vez terminada la descarga, se abrirá automáticamente la terminal de Ubuntu.

---

## 2. Configuración del Usuario y Sistema

Dentro de la terminal de Ubuntu, sigue estos pasos iniciales:

1.  **Crear Usuario:** Introduce tu nombre de usuario y una contraseña segura (no verás caracteres mientras escribes la clave).
2.  **Actualizar el sistema:** Asegúrate de que todo esté al día:
    ```bash
    sudo apt update && sudo apt upgrade -y
    sudo apt install -y ca-certificates curl gnupg lsb-release
    ```

---

## 3. Instalación de Docker Engine

Instalaremos la versión oficial de Docker para asegurar compatibilidad total con **Dev Containers**.

### A. Preparar Repositorios
```bash
# Añadir llave GPG oficial
sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg

# Configurar el repositorio oficial
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```

### B. Instalar el Motor
```bash
sudo apt update
sudo apt install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

---

## 4. Configuración de Permisos (Post-Instalación)

Para que Docker funcione sin `sudo` en cada comando (necesario para VS Code):

1.  **Añadir tu usuario al grupo:**
    ```bash
    sudo usermod -aG docker $USER
    ```
2.  **Reiniciar WSL:** Cierra la terminal de Ubuntu y, en **PowerShell**, ejecuta:
    ```powershell
    wsl --terminate Ubuntu-24.04
    ```
    Vuelve a abrir Ubuntu para que los cambios surtan efecto.

---

## 5. Configuración de Alias en `.bashrc`

Ahora añadiremos el acceso rápido para iniciar el servicio manualmente, manteniendo el control sobre el consumo de recursos.

1.  **Abrir el archivo de configuración:**
    ```bash
    nano ~/.bashrc
    ```
2.  **Añadir los alias al final del archivo:**
    Pega estas líneas (puedes incluir el de parada y estado para mayor control):
    ```bash
    # Gestión rápida de Docker Engine
    alias stdocker='sudo service docker start'
    alias spdocker='sudo service docker stop'
    alias ssdocker='sudo service docker status'
    ```
3.  **Guardar y salir:** Presiona `Ctrl + O`, luego `Enter` y finalmente `Ctrl + X`.
4.  **Aplicar cambios:**
    ```bash
    source ~/.bashrc
    ```

---

## 6. Verificación Final

Ahora puedes usar tu nuevo alias para arrancar el motor y probar que todo esté correcto:

| Acción | Comando | Resultado esperado |
| :--- | :--- | :--- |
| **Iniciar Motor** | `stdocker` | *Starting Docker: docker.* |
| **Verificar Versión** | `docker version` | Detalles del motor 27.x o superior |
| **Prueba de Fuego** | `docker run hello-world` | Mensaje "Hello from Docker!" |
