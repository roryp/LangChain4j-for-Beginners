#!/bin/bash
set -e

# Install Maven (only in Linux environments with sudo)
if command -v sudo &> /dev/null; then
    echo "Installing Maven..."
    sudo apt-get update
    sudo apt-get install -y maven
else
    echo "Skipping Maven installation (sudo not available)"
fi

# Configure Git - must be done BEFORE any file operations
git config --global core.autocrlf input
git config --global core.eol lf
git config --global core.fileMode false

# Make all shell scripts executable
find . -name "*.sh" -type f -exec chmod +x {} + 2>/dev/null || true

echo "Dev container setup complete!"
