#!/bin/bash

# Stop all LangChain4j applications

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

echo "Stopping all LangChain4j applications..."

# Function to stop applications on a specific port
stop_on_port() {
    local port=$1
    local module=$2
    local stopped=false
    
    if command -v lsof &> /dev/null; then
        # Use lsof if available (Mac/Linux)
        local pids=$(lsof -ti:$port 2>/dev/null)
        if [ ! -z "$pids" ]; then
            echo "Stopping $module on port $port (PIDs: $pids)"
            kill -15 $pids 2>/dev/null || kill -9 $pids 2>/dev/null
            sleep 1
            # Verify stopped
            if ! lsof -ti:$port &>/dev/null; then
                echo "✓ Successfully stopped $module"
                stopped=true
            else
                echo "Warning: $module may still be running on port $port"
            fi
        fi
    else
        # Use netstat for Windows Git Bash
        local pids=$(netstat -ano | grep "LISTENING" | grep -w ":$port" | awk '{print $5}' | sort -u)
        if [ ! -z "$pids" ]; then
            echo "Stopping $module on port $port"
            for pid in $pids; do
                if [[ "$pid" =~ ^[0-9]+$ ]]; then
                    if taskkill //F //PID $pid 2>/dev/null; then
                        stopped=true
                    fi
                fi
            done
            if [ "$stopped" = true ]; then
                echo "✓ Successfully stopped $module"
            fi
        fi
    fi
    
    if [ "$stopped" = false ]; then
        echo "No process found for $module on port $port"
    fi
    
    return 0
}

# Stop all modules
stop_on_port 8080 "01-introduction"
stop_on_port 8083 "02-prompt-engineering"
stop_on_port 8081 "03-rag"
stop_on_port 8084 "04-tools"

# Also try to find and kill any Java processes running our JARs (as fallback)
if pkill -f "introduction-1.0.0.jar" 2>/dev/null; then
    echo "✓ Stopped introduction-1.0.0.jar process"
fi
if pkill -f "prompt-engineering-1.0.0.jar" 2>/dev/null; then
    echo "✓ Stopped prompt-engineering-1.0.0.jar process"
fi
if pkill -f "rag-1.0.0.jar" 2>/dev/null; then
    echo "✓ Stopped rag-1.0.0.jar process"
fi
if pkill -f "tools-1.0.0.jar" 2>/dev/null; then
    echo "✓ Stopped tools-1.0.0.jar process"
fi

echo ""
echo "All applications stopped."

