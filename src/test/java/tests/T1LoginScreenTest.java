package tests;

import base.BaseTest;
import base.PropertiesLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.P01LoginScreen;

public class T1LoginScreenTest extends BaseTest {
    P01LoginScreen login;

    @BeforeClass
    private void initiateObjects() {
        login = new P01LoginScreen(getDriver());
    }

    @Test
    public void TC_001validateWelcomeScreen() {
        login.checkHomeScreen();
        login.checkLoginSuccessfully(PropertiesLoader.readEnvFile("USERNAME"), PropertiesLoader.readEnvFile("PASSWORD"));
        login.logOut();
    }

    @Test
    public void TC_002invalidUsernameLogin() {
        login.checkHomeScreen();
        login.checkInvalidLogin("sdmin", PropertiesLoader.readEnvFile("PASSWORD"));
    }

    @Test
    public void TC_003invalidPasswordLogin() {
        login.checkHomeScreen();
        login.checkInvalidLogin(PropertiesLoader.readEnvFile("USERNAME"), "Admin123");
    }


}
