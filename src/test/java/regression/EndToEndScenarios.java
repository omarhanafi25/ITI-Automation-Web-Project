package regression;

import base.BaseTest;
import base.PropertiesLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.P01LoginScreen;
import pages.P02HomeScreen;
import pages.P03AdminScreen;

public class EndToEndScenarios extends BaseTest {
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
    public void TC_01validateUSerSearch() {
        login.checkHomeScreen();
        login.checkLoginSuccessfully(PropertiesLoader.readEnvFile("USERNAME"), PropertiesLoader.readEnvFile("PASSWORD"));
    }

    @Test
    public void TC_02validateSideBar() {
        home.checkSideBar();
    }

    @Test
    public void TC_03validateAdminNavigation() {
        home.navigateToAdmin();
    }

    @Test
    public void TC_04validateAdminScreen() {
        admin.SearchAdmin(PropertiesLoader.readEnvFile("USERNAME"));
    }

    @Test
    public void TC_05validateAdminCantBeDeleted() {
        admin.checkAdminDelete();
    }
}
