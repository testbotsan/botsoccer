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

import java.sql.*;

import java.util.Random;

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
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: " + event);
        final String originalMessageText = event.getMessage().getText();

        String x;
        int randomValue;
    
        switch(originalMessageText){

            case "ランダム":
            case "国別":
            Random num_Ran = new Random();
            randomValue = num_Ran.nextInt(9);
            x = Integer.toString(randomValue);
            break;

            default:
            x = null;
            break;
        }
        
        if(x == null){

            return new TextMessage("ランダムか国別を指定してください"); 
            try{
                Connection conn =
            DriverManager.getConnection
            ("jdbc:postgresql://localhost:5432/botsoccer");
            // ステートメントを作成
            Statement stmt = conn.createStatement();
            // 問合せの実行
            ResultSet rset = stmt.executeQuery("select * From ram_number");
            // 問合せ結果の表示
            while ( rset.next() ) {
              // 列番号による指定
              System.out.println(rset.getInt(1) + "\t" + rset.getString(2));
                   }
            // 結果セットをクローズ
            rset.close();
            // ステートメントをクローズ
            stmt.close();
            // 接続をクローズ
            conn.close();
            }catch(Exception exception){
                return new TextMessage(exception);
            }
            
        }else{
            return new TextMessage("生成された乱数は :" + x);
        }
    }
    
    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}