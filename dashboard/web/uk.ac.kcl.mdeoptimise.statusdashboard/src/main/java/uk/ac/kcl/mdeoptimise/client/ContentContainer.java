package uk.ac.kcl.mdeoptimise.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import uk.ac.kcl.mdeoptimise.client.constants.PageConstants;

public class ContentContainer implements ValueChangeHandler<String> {
	
	private static ContentContainer myInstance = new ContentContainer();
    public static synchronized ContentContainer getInstance() {
        return myInstance;
    }
    
    private ContentContainer() {
        History.addValueChangeHandler(this);
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = (String) event.getValue();
        this.setContentByToken(token);
    }

    public void setContentByToken(String token) {
        if (token.equals(PageConstants.EXPERIMENT_TOKEN)) {
            // Do nothing. Do not change the content, because last seen experiment is unknown.
        } else if (token.equals(PageConstants.HOME_TOKEN) || token.equals(PageConstants.WORKER_TOKEN)) {
            // Reload the home page
        	Window.Location.reload();
        } else {
            Window.alert("Error in parsing history token: '" + token + "'");
        }
    }

    public void setContent(Content content) {
        RootPanel contentRoot = RootPanel.get("content");
        contentRoot.clear();
        History.newItem(content.getToken(), false);
        contentRoot.add(content);
        Window.setTitle(content.getWindowTitle());
        Window.scrollTo(0, 0);
    }
}