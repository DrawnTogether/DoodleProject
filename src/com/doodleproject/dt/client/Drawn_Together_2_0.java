package com.doodleproject.dt.client;

import com.doodleproject.dt.shared.User;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Drawn_Together_2_0 implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	User utente;
	Button logout = new Button("Logout");
	HorizontalPanel firstLayout = new HorizontalPanel();
	TabPanel tablePanel = new TabPanel();
	TabPanel loginPanel = new TabPanel();
	VerticalPanel loginLayout = new VerticalPanel();
	Label userNameText = new Label("Username:");
	TextBox userName = new TextBox();
	Label passwordText = new Label("Password:");
	PasswordTextBox password = new PasswordTextBox();
	Button loginButton = new Button("Conferma");
	HTML registrationLogin = new HTML("<u>Register</u>");
	DialogBox registrationBox = new DialogBox();
	///////REGISTRAZIONE
	TextBox usernameRegistration = new TextBox();
	TextBox nameRegistration = new TextBox();
	PasswordTextBox passwordRegistration = new PasswordTextBox();
	PasswordTextBox passwordConfirmRegistration = new PasswordTextBox();
	TextBox mailRegistration = new TextBox();
	Button confirmRegistration = new Button("Register");
	Button closeRegistration = new Button("Close");
	public void onModuleLoad() {

		firstLayout.setSpacing(20);
		setLoginTab(loginPanel);
		firstLayout.add(tablePanel);
		if(Cookies.getCookie("session") == null ){
			firstLayout.add(loginPanel);
		}
		else {
			RootPanel.get().add(new Label("Bentornato "+Cookies.getCookie("session")));
			RootPanel.get().add(logout);
		}
		RootPanel.get().add(firstLayout);
		logout.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Cookies.removeCookie("session");
				Window.Location.reload();

			}
		});
	}

	void setLoginTab(TabPanel loginPanel){
		loginPanel.setAnimationEnabled(true);
		loginLayout.setSpacing(15);
		loginLayout.add(userNameText);
		loginLayout.add(userName);
		loginLayout.add(passwordText);
		loginLayout.add(password);
		loginLayout.add(loginButton);
		loginLayout.add(registrationLogin);
		loginPanel.add(loginLayout,"Login");
		loginPanel.setWidth("80%");
		loginPanel.selectTab(0);
		loginButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				effettuaLogin();

			}
		});
		registrationLogin.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				createDialogRegistration();
			}
		});
	}

	void createDialogRegistration(){
		registrationBox = new DialogBox();
		VerticalPanel layoutDialog = new VerticalPanel();
		layoutDialog.add(new HTML("Name:"));
		layoutDialog.add(nameRegistration);
		layoutDialog.add(new HTML("Username:"));
		layoutDialog.add(usernameRegistration);
		layoutDialog.add(new HTML("Password:"));
		layoutDialog.add(passwordRegistration);
		layoutDialog.add(new HTML("Confirm Password:"));
		layoutDialog.add(passwordConfirmRegistration);
		layoutDialog.add(new HTML("Mail:"));
		layoutDialog.add(mailRegistration);
		HorizontalPanel doubleButton = new HorizontalPanel();
		doubleButton.setSpacing(10);
		doubleButton.add(confirmRegistration);
		doubleButton.add(closeRegistration);
		layoutDialog.add(doubleButton);
		registrationBox.add(layoutDialog);
		registrationBox.center();
		confirmRegistration.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				effettuaRegistrazione();
			}
		});
		closeRegistration.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				registrationBox.hide();
			}
		});
	}

	void effettuaRegistrazione(){
		greetingService.registration(new User(nameRegistration.getText(),usernameRegistration.getText(),passwordRegistration.getText(),mailRegistration.getText()), new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				Window.alert(result ? "Registrato" : "Username in uso!");
				return;
			}
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Errore connessione remota");
				return;

			}
		});
	}
	void effettuaLogin() {
		try {
			greetingService.login(new User("",userName.getText(),password.getText(),""), new AsyncCallback<Boolean>() {

				@Override
				public void onSuccess(Boolean result) {
					Window.alert(result ? "Loggato": "Username e/o Password errati!");
					if(result)utente = new User("",userName.getText(),password.getText(),"");
					Cookies.setCookie("session", utente.getUsername());
					Window.Location.reload();
					return;		
				}
				@Override
				public void onFailure(Throwable caught) {
					return;

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error for login in Server Side!");
		}
	}
}