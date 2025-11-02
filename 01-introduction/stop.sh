#!/bin/bash

# Stop script for 01-introduction module

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PORT=8080
MODULE_NAME="01-introduction"
JAR_NAME="introduction-1.0.0.jar"

echo "Stopping $MODULE_NAME..."

# Function to stop application on specific port
stop_on_port() {
    local port=$1
    
    if command -v lsof &> /dev/null; then
        # Use lsof if available (Mac/Linux)
        local pids=$(lsof -ti:$port 2>/dev/null)
        if [ ! -z "$pids" ]; then
            echo "Stopping processes on port $port (PIDs: $pids)"
            kill -9 $pids 2>/dev/null
            echo "✓ Stopped processes on port $port"
            return 0
        fi
    else
        # Use netstat for Windows Git Bash
        local pids=$(netstat -ano | grep ":$port" | awk '{print $5}' | sort -u)
        if [ ! -z "$pids" ]; then
            echo "Stopping processes on port $port"
            for pid in $pids; do
                taskkill //F //PID $pid 2>/dev/null
            done
            echo "✓ Stopped processes on port $port"
            return 0
        fi
    fi
    
    echo "No process found running on port $port"
    return 1
}

# Stop by port
stopped_by_port=false
if stop_on_port $PORT; then
    stopped_by_port=true
fi

# Also try to kill by JAR name
if pkill -f "$JAR_NAME" 2>/dev/null; then
    echo "✓ Stopped $JAR_NAME process"
    stopped_by_port=true
fi

if [ "$stopped_by_port" = false ]; then
    echo "No running instance of $MODULE_NAME found"
else
    echo "$MODULE_NAME stopped successfully"
fi
