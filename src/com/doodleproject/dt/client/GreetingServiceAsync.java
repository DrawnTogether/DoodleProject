package com.doodleproject.dt.client;

import com.doodleproject.dt.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void registration(User user, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
	void login(User user, AsyncCallback<Boolean> callback)
			throws Exception;
}
