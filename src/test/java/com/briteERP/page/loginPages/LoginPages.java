package com.briteERP.page.loginPages;

import com.briteERP.utilities.BrowserFactory;
import com.briteERP.utilities.ConfigurationReader;
import com.briteERP.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPages {

   // private WebDriver driver = Driver.getDriver();
   // private WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigurationReader.getProperty("explicitwait")));

    // public class  void LoginPage{
        BriteLogin login = new BriteLogin();
        String email  = ConfigurationReader.getProperty("eventscrmmanagerEmail");
        String password = ConfigurationReader.getProperty("eventscrmmanagerPassword");


}
