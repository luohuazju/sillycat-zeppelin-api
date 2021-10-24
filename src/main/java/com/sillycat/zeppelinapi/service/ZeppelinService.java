package com.sillycat.zeppelinapi.service;

import org.apache.zeppelin.client.ZeppelinClient;

public interface ZeppelinService {

	public ZeppelinClient getClient();

	public void login(ZeppelinClient client);

	public void executeNote(String noteId, ZeppelinClient client);

}
