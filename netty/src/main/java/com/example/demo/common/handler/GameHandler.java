package com.example.demo.common.handler;

import com.example.demo.common.domain.GameRequest;
import com.example.demo.common.domain.GameResponse;


public abstract interface GameHandler
{
  public abstract void execute(GameRequest paramGameRequest, GameResponse paramGameResponse);
}
