package tests;

import base.BaseTest;
import base.PropertiesLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.P01LoginScreen;
import pages.P02HomeScreen;
import pages.P03AdminScreen;

public class T3AdminScreenTest extends BaseTest {
    P02HomeScreen home;
    P01LoginScreen login;
    P03AdminScreen admin;

    @BeforeClass
    private void initiateObjects() {
        login = new P01LoginScreen(getDriver());
        home = new P02HomeScreen(getDriver());
        admin = new P03AdminScreen(getDriver());
    }

    @Test
    public void TC_001validateUSerSearch() {
        login.checkLoginSuccessfully(PropertiesLoader.readEnvFile("USERNAME"), PropertiesLoader.readEnvFile("PASSWORD"));
        home.checkSideBar();
        home.navigateToAdmin();
        admin.SearchAdmin(PropertiesLoader.readEnvFile("USERNAME"));
    }

    @Test
    public void TC_002validateAdminCantBEDeleted() {
        admin.checkAdminDelete();
    }
}
