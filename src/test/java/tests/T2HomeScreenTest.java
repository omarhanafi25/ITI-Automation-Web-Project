package tests;

import base.BaseTest;
import base.PropertiesLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.P01LoginScreen;
import pages.P02HomeScreen;

public class T2HomeScreenTest extends BaseTest {
    P02HomeScreen home;
    P01LoginScreen login;

    @BeforeClass
    private void initiateObjects() {
        login = new P01LoginScreen(getDriver());
        home = new P02HomeScreen(getDriver());
    }

    @Test
    public void TC_001validateAddingAccount() {
        login.checkLoginSuccessfully(PropertiesLoader.readEnvFile("USERNAME"), PropertiesLoader.readEnvFile("PASSWORD"));
        home.checkSideBar();
        home.navigateToAdmin();
        home.clickAddButton();
        home.chooseUserole();
        home.chooseStatus();
        home.EmployeeName();
        home.writeUserName();
        home.setPassword();
        home.confirmPassword();
        home.clickSaveButton();
        home.checkAccountSaved();
        login.logOut();

    }

}
