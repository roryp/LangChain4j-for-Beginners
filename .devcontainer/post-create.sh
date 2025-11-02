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

# Normalize line endings for all shell scripts
echo "Normalizing line endings..."
find . -name "*.sh" -type f -exec dos2unix {} + 2>/dev/null || \
    find . -name "*.sh" -type f -exec sed -i 's/\r$//' {} + 2>/dev/null || true

# Make all shell scripts executable
find . -name "*.sh" -type f -exec chmod +x {} + 2>/dev/null || true

# Reset Git index to pick up line ending changes
git rm --cached -r . >/dev/null 2>&1 || true
git reset --hard >/dev/null 2>&1 || true

echo "Dev container setup complete!"
