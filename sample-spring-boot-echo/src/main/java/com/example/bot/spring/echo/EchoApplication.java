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
    
    String[] User_ans;
    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: " + event);
        final String originalMessageText = event.getMessage().getText();

        String x ;
        String y ;
        String z ;
        
        boolean ch = true;
        switch(originalMessageText){

            case "ランダム":
            case "国別":
            case "ルール別":
            Quiz();
            break;

            default:
            x = null;
            break;
        }
        
        if(x == null){
            return new TextMessage("ランダム、国別、ルール別のいずれかを選択してください");
        }
        return new TextMessage(originalMessageText + "からクイズを出題します");
    }
    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

    public Message Quiz(String STR){
        Random random = new Random();
        int randomValue = random.nextInt(1);
        String awnser = STR;

        switch(randomValue){
            case 1:
                this.replyMessage(replyToken,"現在の日本のサッカー代表の順位は？");
            if(awnser.equals("28位")){
                return new TextMessage("正解");
            }else{
                return new TextMessage("不正解");
            }
            break;

            case 2:
            this.replyMessage(replyToken,"18歳という若さでスペインの強豪レアル・マドリーに移籍した人物は？");
            if(awnser.equals("久保建英")){
                return new TextMessage("正解");
            }else{
                return new TextMessage("不正解");
            }
            break;
        }
    }
}