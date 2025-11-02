#!/bin/bash
set -e

# Install Maven
sudo apt-get update
sudo apt-get install -y maven

# Configure Git
git config --global core.autocrlf input
git config --global core.eol lf

# Make all shell scripts executable (ignore permission errors)
find . -name "*.sh" -exec chmod +x {} + 2>/dev/null || true

echo "Dev container setup complete!"
