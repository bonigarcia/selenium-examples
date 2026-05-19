package tests.account;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import tests.BaseTest;

public class TC001Test extends BaseTest {

    private static final String ACCOUNT_NAME = "Acme Corp - TCV Test";
    private static final String NEW_TCV_AMOUNT = "150000.00";
    private static final String EXPECTED_FORMATTED_TCV = "$150,000.00";

    @Test(description = "TC001 - Verify Account Manager can view and edit Total Contract Value (TCV) field")
    public void testVerifyAndEditTCVAmount() {
        AccountPage accountPage = new AccountPage(driver);

        // Step 1 & 2: Login and Navigate to Accounts Tab
        // Login handled by BaseTest setup
        accountPage.navigateToAccountsTab();

        // Step 3 & 4: Search for the Account and open its record page
        accountPage.searchAndSelectAccount(ACCOUNT_NAME);

        // Step 5: Verify that the 'TCV Amount' field is visible on the Account detail page.
        Assert.assertTrue(accountPage.isTCVAmountFieldVisible(), "Expected TCV Amount field to be visible on the Account detail page.");
        String initialTCV = accountPage.getTCVAmount();
        System.out.println("Initial TCV Amount: " + initialTCV);

        // Step 6: Click the 'Edit' button
        accountPage.clickEditButton();

        // Step 7 & 8: Locate the 'TCV Amount' field in the edit modal and enter a new value.
        accountPage.enterTCVAmount(NEW_TCV_AMOUNT);

        // Step 9: Click the 'Save' button at the bottom of the edit modal.
        accountPage.saveAccountEdit();

        // Step 10 & 11: Verify the Account record page reloads and TCV Amount displays updated value.
        // The waitForSpinner() in saveAccountEdit handles the reload wait.
        String updatedTCV = accountPage.getTCVAmount();
        System.out.println("Updated TCV Amount: " + updatedTCV);
        Assert.assertEquals(updatedTCV, EXPECTED_FORMATTED_TCV, "Expected TCV Amount to be updated to " + EXPECTED_FORMATTED_TCV + " after saving.");

        // Optional: Verify success message (if present and needed)
        // String successMessage = accountPage.getSuccessMessage();
        // Assert.assertTrue(successMessage.contains("Account \"" + ACCOUNT_NAME + "\" was saved."), "Success message not found or incorrect.");
    }
}
