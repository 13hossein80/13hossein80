package com.mycompany;

import Peractice.Game;
import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 */
public class WicketApplication extends WebApplication {




    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }
}
