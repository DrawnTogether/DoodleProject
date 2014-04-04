package com.doodleproject.dt.client;

import com.doodleproject.dt.shared.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	boolean registration(User user) throws IllegalArgumentException;
	boolean login(User user) throws Exception;
}