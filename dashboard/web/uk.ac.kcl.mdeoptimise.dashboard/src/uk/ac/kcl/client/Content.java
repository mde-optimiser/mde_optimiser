package uk.ac.kcl.client;

import com.google.gwt.user.client.ui.Composite;

public abstract class Content extends Composite {
    public abstract String getToken();  
    public abstract String getWindowTitle();
}