package partOne.pageObject16.tests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class FirstTest extends BaseTest{

    @Test
    public void DBTest() {

    authorizationPage.goToAuthorizationPage();
    authorizationPage.fillInLogin("Dtest");
    authorizationPage.fillInPassword("Dtest");
    authorizationPage.clickLoginButton();

    Assert.assertTrue("Заметка не отображается", mainPage.notesIsDisplayed());
    }

}
