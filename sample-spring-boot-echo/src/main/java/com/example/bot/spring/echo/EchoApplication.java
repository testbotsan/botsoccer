/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Method;
import java.util.Random;

import javax.naming.Context;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;






@SpringBootApplication
@LineMessageHandler
public class EchoApplication {
    private final Logger log = LoggerFactory.getLogger(EchoApplication.class);
    
    int randomValue;
    boolean ch = true;
    String User;
    String text;
    
    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event,String ge) {
        log.info("event: " + event);
        final String originalMessageText = event.getMessage().getText();
        text = originalMessageText;
        if(ch == true){
            Genre();
        }
        return new TextMessage(ge + "");
    }
    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

    public String Genre(){

        ch = false;
        String user = text;
        String genre ="初期";

        if(User.equals("ランダム")){

            genre = "ランダムから問題を出題します";

        }else if(User.equals("国別")){

            genre = "国別から問題を出題します";

        }else if(User.equals("ルール別")){

            genre = "ルール別から問題を出題します";

        }else{

            genre = "「ランダム」「国別」「ルール別」から選んで発言してください";
        }
        Quiz();
        return genre;
    }

    public String Quiz(){
        Random random = new Random();
        randomValue = random.nextInt(1);
        String quiz = "first";
        
        switch(randomValue){
            case 0:
            quiz = "日本";
            break;

            case 1:
            quiz = "韓国";
            break;
        }
        Awnser();
        return quiz;
    }

    public String Awnser(){

        String Awnser = text;
        String q_STR = "first";

        switch(randomValue){
            case 0 :
            if(Awnser.equals("日本")){
                q_STR = "正解";
            }else{
                q_STR = "残念";
            }
            break;

            case 1 :
            if(Awnser.equals("韓国")){
                q_STR = "正解";
            }else{
                q_STR = "残念";
            }
            break;
        }
        ch = true;
        return q_STR;
    }
}