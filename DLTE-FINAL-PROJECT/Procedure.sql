create or replace PROCEDURE close_account_service(
    p_account_id NUMBER,
--    p_account_profile OUT NUMBER,
    p_account_number OUT NUMBER,
    p_customer_id OUT NUMBER,
    p_account_type OUT VARCHAR2,
    p_account_status OUT VARCHAR2,
    p_account_balance OUT NUMBER,
    p_result OUT VARCHAR2
)
AS
    v_customer_id NUMBER;
    v_customer_status VARCHAR2(255);
BEGIN
    -- Check if the account exists and fetch its details
    SELECT ACCOUNT_NUMBER, CUSTOMER_ID, ACCOUNT_TYPE, ACCOUNT_STATUS, ACCOUNT_BALANCE
    INTO p_account_number, p_customer_id, p_account_type, p_account_status, p_account_balance
    FROM MYBANK_APP_ACCOUNT
    WHERE ACCOUNT_ID = p_account_id;

    -- Check if the account is already inactive
    IF p_account_status = 'Inactive' THEN
        p_result := 'SQLERR-001';--'Account is already inactive.';
        RETURN;
    END IF;

    -- Check if the customer exists
    SELECT CUSTOMER_ID INTO v_customer_id FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = p_customer_id;
    IF v_customer_id IS NULL THEN
        p_result := 'SQLERR-002';--'Customer not found.';
        RETURN;
    END IF;

    -- Check if the customer is active
    SELECT CUSTOMER_STATUS INTO v_customer_status FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = p_customer_id;
    IF v_customer_status != 'Active' THEN
        p_result := 'SQLERR-003';--'Customer is not active. Cannot close account.';
        RETURN;
    END IF;

    -- Close the account (set status as inactive)
    UPDATE MYBANK_APP_ACCOUNT
    SET ACCOUNT_STATUS = 'Inactive'
    WHERE ACCOUNT_ID = p_account_id;

    -- Fetch the updated account profile
    --p_account_profile := p_account_id;

    -- Set the result message
    p_result := 'SQLSUCESS';--'Account successfully closed.';
    p_account_status:='Inactive';
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_result := 'SQLERR-004';--'Account or Customer not found.';
    WHEN OTHERS THEN
        p_result := 'SQLERR-005 ' || SQLERRM;
END;
