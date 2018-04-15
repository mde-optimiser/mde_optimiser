package uk.ac.kcl.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

import uk.ac.kcl.client.Content;
import uk.ac.kcl.client.constants.PageConstants;

public class HelpPage extends Content {

	private static HelpPageUiBinder uiBinder = GWT.create(HelpPageUiBinder.class);

	interface HelpPageUiBinder extends UiBinder<Widget, HelpPage> {
	}

	public HelpPage() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public HelpPage(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public String getToken() {
		return PageConstants.HELP_TOKEN;
	}

	@Override
	public String getWindowTitle() {
		return PageConstants.HELP_TITLE;
	}

}
