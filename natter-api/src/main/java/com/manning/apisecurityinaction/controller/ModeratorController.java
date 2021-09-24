package com.manning.apisecurityinaction.controller;

import org.dalesbred.Database;
import org.json.JSONObject;

import spark.*;

public class ModeratorController {

  private final Database database;

  public ModeratorController(Database database) {
    this.database = database;
  }

  //Description of function and parameters is not added
  public JSONObject deletePost(Request request, Response response) {
    
    //spaceId should not be added in url and it can be added if it is encrypted
    var spaceId = Long.parseLong(request.params(":spaceId"));
    
    //msgId should not be added in url and it can be added if it is encrypted
    var msgId = Long.parseLong(request.params(":msgId"));
    //SQL Injection
    database.updateUnique("DELETE FROM messages " +
        "WHERE space_id = ? AND msg_id = ?", spaceId, msgId);
    response.status(200);
    return new JSONObject();
  }
}
