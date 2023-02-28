package com.alphabee.shortner.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("urlentity")
public class URLEntity {
  @Id
  private String shortURL;
  private String longURL;

  public URLEntity(){}

  public URLEntity(String shortURL,String longURL){
    this.shortURL=shortURL;
    this.longURL=longURL;
  }
  public String getShortURL() {
    return shortURL;
  }

  public void setShortURL(String shortURL) {
    this.shortURL = shortURL;
  }

  public String getLongURL() {
    return longURL;
  }

  public void setLongURL(String longURL) {
    this.longURL = longURL;
  }
}
