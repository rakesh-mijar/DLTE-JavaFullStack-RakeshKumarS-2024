function fetchAccountDetails() {
    // Retrieve customer ID from input field
    let customerId = parseInt(document.getElementById('customerId').value);

    // Array of customer accounts
    let customerAccounts = [
        {
            accountId: 101,
            accountNumber: 987654321,
            customerId: 1,
            accountType: "Savings",
            accountStatus: "Active",
            accountBalance: 5000.00
        },
        {
            accountId: 102,
            accountNumber: 123456789,
            customerId: 2,
            accountType: "Salary",
            accountStatus: "Inactive",
            accountBalance: 10000.00
        },
        {
            accountId: 103,
            accountNumber: 908765785,
            customerId: 1,
            accountType: "Salary",
            accountStatus: "Inactive",
            accountBalance: 10000.00
        },
    ];

    // Filter customer accounts based on the provided customer ID and active status
    let activeAccounts = customerAccounts.filter(account => account.customerId === customerId && account.accountStatus === 'Active');

    // Display account details in separate cards if found
    let accountDetailsContainer = document.getElementById('accountDetailsContainer');
    accountDetailsContainer.innerHTML = '';

    if (activeAccounts.length > 0) {
        activeAccounts.forEach(account => {
            let accountCard = document.createElement('div');
            accountCard.className = 'account-box';
            accountCard.innerHTML = `
                <h3 class="account-details-title">Account Details</h3>
                <p><strong>Account ID:</strong> ${account.accountId}</p>
                <p><strong>Account Number:</strong> ${account.accountNumber}</p>
                <p><strong>Customer ID:</strong> ${account.customerId}</p>
                <p><strong>Account Type:</strong> ${account.accountType}</p>
                <p><strong>Account Status:</strong> ${account.accountStatus}</p>
                <p><strong>Account Balance:</strong> ${account.accountBalance}</p>
            `;
            // Added click event to show detailed view in modal
            accountCard.addEventListener('click', function() {
                showDetailedView(account);
            });
            accountDetailsContainer.appendChild(accountCard);
        });
    } else {
        alert('No active accounts found for the provided customer ID.');
    }
}

