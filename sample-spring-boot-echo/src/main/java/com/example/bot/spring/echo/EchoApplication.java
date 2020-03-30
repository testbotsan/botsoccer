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

import static java.util.Collections.singletonList;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.linecorp.bot.client.LineBlobClient;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.client.MessageContentResponse;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.DatetimePickerAction;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.BeaconEvent;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.MemberJoinedEvent;
import com.linecorp.bot.model.event.MemberLeftEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.AudioMessageContent;
import com.linecorp.bot.model.event.message.ContentProvider;
import com.linecorp.bot.model.event.message.FileMessageContent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.LocationMessageContent;
import com.linecorp.bot.model.event.message.StickerMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.event.message.VideoMessageContent;
import com.linecorp.bot.model.event.source.GroupSource;
import com.linecorp.bot.model.event.source.RoomSource;
import com.linecorp.bot.model.event.source.Source;
import com.linecorp.bot.model.message.AudioMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.ImagemapMessage;
import com.linecorp.bot.model.message.LocationMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.VideoMessage;
import com.linecorp.bot.model.message.imagemap.ImagemapArea;
import com.linecorp.bot.model.message.imagemap.ImagemapBaseSize;
import com.linecorp.bot.model.message.imagemap.ImagemapExternalLink;
import com.linecorp.bot.model.message.imagemap.ImagemapVideo;
import com.linecorp.bot.model.message.imagemap.MessageImagemapAction;
import com.linecorp.bot.model.message.imagemap.URIImagemapAction;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.message.template.ImageCarouselColumn;
import com.linecorp.bot.model.message.template.ImageCarouselTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication {

    @Autowired
    private LineMessagingClient lineMessagingClient;

    @Autowired
    private LineBlobClient lineBlobClient;

    private final Logger log = LoggerFactory.getLogger(EchoApplication.class);
    
    Boolean ch = true;
    int randomValue;
    String Code = "aaa";
    String Awn = "aaa";
    
    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: " + event);
        final String originalMessageText = event.getMessage().getText();
        Random random = new Random(0);
    
    
            switch(originalMessageText){
                case "ランダム":
                    randomValue = random.nextInt(15);
                    Code = originalMessageText;
                    if(randomValue == 0){
                        ch = false;
                        return new TextMessage("2018年に行われたロシアワールドにて行われた最初の試合はロシア対どこでしょう？");
                    }

                    if(randomValue == 1){
                        ch = false;
                        return new TextMessage("18歳の若さでスペインの名門レアル・マドリーへ移籍した選手は？");
                    }
                    break;

                case "ルール":
                    randomValue = random.nextInt(9);
                    Code = originalMessageText;
                    if(randomValue == 0){
                        ch = false;
                        return new TextMessage("サッカーは１チーム何人必要？ \n ※「～人」と答えてください");
                    }
                    if(randomValue == 1){
                        ch = false;
                        return new TextMessage("試合中危険なプレイをした選手に警告を出す時に使用するカードは何というカードでしょう？");
                    }
                    if(randomValue == 2){
                        ch = false;
                        return new TextMessage("別の場所で映像を見ながらフィールドの審判員をサポートする審判員をアルファベット3文字で何という？");
                    }
                    if(randomValue == 3){
                        ch = false;
                        return new TextMessage("試合時間は前半と後半合せて何分ある？ \n ※「～分」と答えてください");
                    }
                    if(randomValue == 4){
                        ch = false;
                        return new TextMessage("選手交代はオリンピックや通常の試合だと何人まで可能でしょう？ \n ※「～人」と答えてください");
                    }
                    if(randomValue == 5){
                        ch = false;
                        return new TextMessage("ファールなどで試合が中断した際、ボールを決められた所において試合を再開させるプレーを何プレーという？");
                    }
                    if(randomValue == 6){
                        ch = false;
                        return new TextMessage("試合中にけがや交代に消費した時間を前後半立った後に追加する制度を何タイムという？");
                    }
                    if(randomValue == 7){
                        ch = false;
                        return new TextMessage("ファウルのあった場所にボールを置き、自由に蹴ることができるルールを何キックという？");
                    }
                    break;

                case "選手":
                randomValue = random.nextInt(15);
                Code = originalMessageText;
                if(randomValue == 0){
                        ch = false;
                        return new TextMessage("18歳の若さでスペインの名門レアル・マドリーへ移籍した選手は？");
                    }
                    break;
            }
            if(ch == false){
                switch(Code){
                    case "ランダム":
                       if(randomValue == 0){
                            ch = true;
                            if(originalMessageText.equals("サウジアラビア")){
                                return new TextMessage("正解");
                            }else{
                                return new TextMessage("不正解");
                            }
                       }
                    case "ルール別":
                       if(randomValue == 0){
                            ch = true;
                            if(originalMessageText.equals("11人")){
                                return new TextMessage("正解! \n サッカーは1チーム11人で行います");
                            }else{
                                return new TextMessage("不正解! \n 正解は「11人」です");
                            }
                        }
                        if(randomValue == 1){
                            ch = true;
                            if(originalMessageText.equals("イエローカード")){
                                return new TextMessage("正解! \n 1試合に2枚のイエローカードを貰うとその試合から退場処分を受けます");
                            }else{
                                return new TextMessage("不正解! \n 正解は「イエローカードです」");
                            }
                        }
                        if(randomValue == 2){
                            ch = true;
                            String text = originalMessageText.toUpperCase();
                            if(text.equals("VAR")){
                                return new TextMessage("正解! \n 日本のJリーグでは2020年シリーズ全試合にVARの導入が決定しています");
                            }else{
                                return new TextMessage("不正解! \n 正解は「VAR」です");
                            }
                       }
                       if(randomValue == 3){
                        ch = true;
                            if(originalMessageText.equals("90分")){
                                return new TextMessage("正解! \n サッカーは前半後半合せて90分行われます");
                        }else{
                                return new TextMessage("不正解! \n 正解は「90分」です");
                        }
                   }
                        if(randomValue == 4){
                        ch = true;
                            if(originalMessageText.equals("3人")){
                                return new TextMessage("正解! \n 前半後半合わせて3人まで可能です");
                        }else{
                                return new TextMessage("不正解! \n 正解は「3人」です");
                        }   
                   }
                        if(randomValue == 5){
                        ch = true;
                            if(originalMessageText.equals("セットプレー")){
                                return new TextMessage("正解! \n セットプレーには「フリーキック」「コーナーキック」「ペナルティキック」があります");
                        }else{
                                return new TextMessage("不正解! \n 正解は「セットプレー」です");
                        }   
                    } 
                    if(randomValue == 6){
                        ch = true;
                            if(originalMessageText.equals("アディショナルタイム")){
                                return new TextMessage("正解! \n 大半は前半1～3分,後半3～5分が多いです");
                        }else{
                                return new TextMessage("不正解! \n 正解は「セットプレー」です");
                        }   
                    } 
                    if(randomValue == 7){
                        ch = true;
                            if(originalMessageText.equals("フリーキック")){
                                return new TextMessage("正解! \n Jリーグでは中村俊介選手がフリーキックの名手として有名です");
                        }else{
                                return new TextMessage("不正解! \n 正解は「フリーキック」です");
                        }   
                    } 
                    case "選手":
                    if(randomValue == 0){
                        ch = true;
                        if(originalMessageText.equals("11人")){
                            return new TextMessage("正解! \n サッカーは1チーム11人で行います");
                        }else{
                            return new TextMessage("不正解! \n 正解は「11人」です");
                        }
                    }
            }
        }
        return new TextMessage("「ランダム」「国別」「ルール別」から1つ選んで発言してください");
    }
    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}