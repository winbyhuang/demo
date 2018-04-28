package com.example.demo.common.handler;

import com.example.demo.common.domain.GameRequest;
import com.example.demo.common.domain.GameResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitHandler implements GameHandler {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void execute(GameRequest request, GameResponse response) {
		this.logger.error(request.readString());
		response.write("I am ok!");
	}
}
