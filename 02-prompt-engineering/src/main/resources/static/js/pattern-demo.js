async function callPattern(endpoint, requestBody) {
    const responseDiv = document.getElementById('response');
    const loadingDiv = document.getElementById('loading');
    const submitBtn = document.getElementById('submit-btn');
    
    try {
        // Show loading state
        loadingDiv.style.display = 'block';
        responseDiv.innerHTML = '';
        submitBtn.disabled = true;
        
        // Call API
        const response = await fetch(`/api/gpt5/${endpoint}`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(requestBody)
        });
        
        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }
        
        const data = await response.json();
        
        // Display response
        displayResponse(data.result || data.response || JSON.stringify(data));
        
    } catch (error) {
        displayError('Failed to get response: ' + error.message);
    } finally {
        loadingDiv.style.display = 'none';
        submitBtn.disabled = false;
    }
}

function displayResponse(text) {
    const responseDiv = document.getElementById('response');
    const escapedText = escapeHtml(text);
    responseDiv.innerHTML = `
        <div class="response-card">
            <div class="response-header">
                <span class="response-label">AI Response</span>
                <button onclick="copyToClipboard(\`${escapedText}\`)" class="btn-copy">📋 Copy</button>
            </div>
            <div class="response-body">
                <pre>${escapedText}</pre>
            </div>
            <div class="response-footer">
                <small>Generated at ${new Date().toLocaleTimeString()}</small>
            </div>
        </div>
    `;
}

function displayError(message) {
    const responseDiv = document.getElementById('response');
    responseDiv.innerHTML = `
        <div class="error-message">
            <strong>Error:</strong> ${escapeHtml(message)}
        </div>
    `;
}

function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

function copyToClipboard(text) {
    // Unescape HTML entities first
    const textarea = document.createElement('textarea');
    textarea.innerHTML = text;
    const plainText = textarea.value;
    
    navigator.clipboard.writeText(plainText).then(() => {
        alert('Copied to clipboard!');
    }).catch(err => {
        console.error('Failed to copy:', err);
    });
}

function setExample(text) {
    const input = document.querySelector('textarea, input[type="text"]');
    if (input) {
        input.value = text;
        input.focus();
    }
}
