package com.github.spring.websocket.chatroom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

  @Autowired(required = false)
  private NewsMapper newsMapper;


  /**
   * 返回用户的未读消息
   *
   * @param userName
   * @return
   */
  public int getUnReadNews(String userName) {
    NewsExample example = new NewsExample();
    List<News> news = newsMapper.selectByExample(example);
    return news.size();
  }


}
