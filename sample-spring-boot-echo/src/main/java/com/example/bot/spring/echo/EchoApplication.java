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

import java.util.Random;

import javax.naming.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication {
    private final Logger log = LoggerFactory.getLogger(EchoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event/*,int num*/) {
        log.info("event: " + event);
        final String originalMessageText = event.getMessage().getText();
        String x = "asas";
        /*switch(originalMessageText){
            case "ランダム" :
            //RandomNumber();
            x = "asasas";
            break;

            case "国別" :
            //RandomNumber();
            x = "asasasasas";
            break;

            default :
            x = "asasasasasasasasa";
            break;
        }*/
        /*x = Integer.toString(num);
        if(x == null){
            return new TextMessage("ランダムか国別を指定してください");
        }*/
        
        return new TextMessage("生成された乱数は :" + x);
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }

    private void RandomNumber(){
        Random num_Ran = new Random();
        int randomValue = num_Ran.nextInt(9); 
        //return randomValue;
    }

    private void DBsetUp(){
        
    }
}
