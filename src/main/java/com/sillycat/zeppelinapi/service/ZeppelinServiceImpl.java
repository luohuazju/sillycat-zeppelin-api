package com.sillycat.zeppelinapi.service;

import java.util.List;

import org.apache.zeppelin.client.ClientConfig;
import org.apache.zeppelin.client.NoteResult;
import org.apache.zeppelin.client.ParagraphResult;
import org.apache.zeppelin.client.ZeppelinClient;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@Setter
public class ZeppelinServiceImpl implements ZeppelinService{
	
	private static final int EXECUTION_TIMEOUT = 30 * 60 * 1000;
	
	private String server;
	
	private String username;
	
	private String password;
	
	public ZeppelinClient getClient() {
		ClientConfig clientConfig = new ClientConfig(this.getServer());
		log.info("start to init the Zeppelin client with host = " + this.getServer());
		ZeppelinClient zClient = null;
		try {
			zClient = new ZeppelinClient(clientConfig);
			String zeppelinVersion = zClient.getVersion();
			log.info("zeppelin version record as " + zeppelinVersion);
		} catch (Exception e) {
			log.error("Get Zeppelin Client error:" + e.getMessage());
		}
		return zClient;
	}
	
	public void login(ZeppelinClient client) {
		try {
			log.info("start to login to server with username " + username);
			client.login(this.getUsername(), this.getPassword());
			log.info("zeppelin login successful.");
		} catch (Exception e) {
			log.error("Zeppelin login failed!");
		}
	}
	
	public void executeNote(String noteId, ZeppelinClient client) {
		NoteResult noteResult;
		try {
			log.info("Zeppelin execute noteId = " + noteId);
			noteResult = client.executeNote(noteId);
			client.waitUntilNoteFinished(noteId, EXECUTION_TIMEOUT);
			log.info("Zeppelin successfully get the result:");
			if (noteResult != null) {
				List<ParagraphResult> results = noteResult.getParagraphResultList();
				if (results != null && !results.isEmpty()) {
					for (ParagraphResult result: results) {
						log.info("result for " + result.getParagraphId() + "-------------------");
						log.info(result.getResultInText());
						log.info("-------------------------------------------------------------");
					}
				}
			}
		} catch (Exception e) {
			log.error("Zeppelin execute Note failed with error:" + e.getMessage());
		}
	}

}
