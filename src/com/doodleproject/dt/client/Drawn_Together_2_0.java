package com.doodleproject.dt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Drawn_Together_2_0 implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	HorizontalPanel firstLayout = new HorizontalPanel();
	TabSet tablePanel = new TabSet();
	TabSet loginPanel = new TabSet();
	VerticalPanel loginLayout = new VerticalPanel();
	Tab tableTab = new Tab("Eventi");
	Tab loginTab = new Tab("Login");
	Label userNameText = new Label("Username:");
	TextBox userName = new TextBox();
	Label passwordText = new Label("Password:");
	PasswordTextBox password = new PasswordTextBox();
	Button loginButton = new Button("Conferma");
	
	public void onModuleLoad() {
		firstLayout.setSpacing(20);
		tablePanel.addTab(tableTab);
		loginPanel.addTab(loginTab);
		setLoginTab(loginPanel);
		
		firstLayout.add(tablePanel);
		firstLayout.add(loginPanel);
		RootPanel.get().add(firstLayout);
	}
	
	void setLoginTab(TabSet loginPanel){
		loginLayout.add(userNameText);
		loginLayout.add(userName);
		loginLayout.add(passwordText);
		loginLayout.add(password);
		loginLayout.add(loginButton);
		loginPanel.add(loginLayout);
	}
}