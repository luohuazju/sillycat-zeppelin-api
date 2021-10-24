package com.sillycat.zeppelinapi;

import org.apache.zeppelin.client.ZeppelinClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sillycat.zeppelinapi.service.ZeppelinService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutorApplication {

	private static ApplicationContext context;

	public static void main(String[] args) {
		log.info("start the application-------------");
		if (args == null || args.length != 1) {
			log.info("args error, usage is as follow:");
			log.info("bin/run.sh nodeId");
			return;
		}
		String noteId = args[0];
		log.info("executing noteId = " + noteId);
		context = new ClassPathXmlApplicationContext("main-application.xml");
		ZeppelinService zeppelinService = (ZeppelinService) context.getBean("zeppelinService");
		ZeppelinClient client = zeppelinService.getClient();
		zeppelinService.login(client);
		zeppelinService.executeNote(noteId, client);
		log.info("application finished--------------");
	}

}
