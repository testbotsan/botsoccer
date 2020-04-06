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

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication {
    private final Logger log = LoggerFactory.getLogger(EchoApplication.class);
    
    Boolean ch = true;
    int randomValue;
    String Code = "aaa";
    String Awn = "aaa";
    String Qur = "aaa";
    
    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        log.info("event: " + event);
        final String originalMessageText = event.getMessage().getText();
        /*Random random = new Random();
        if(ch == true){
            switch(originalMessageText){
                case "ランダム":
                ch = false;
                Qur = "ランダム";
                    randomValue = random.nextInt(20);
                    if(randomValue == 0){
                    
                        return new TextMessage("2018年ロシアワールドカップ初戦はロシア対どこ？");
                    }
                    if(randomValue == 1){
                    
                        return new TextMessage("現在の日本代表の世界ランキングは何位でしょう？ \n ※「～位」と答えてください");
                    }
                    if(randomValue == 2){
                        return new TextMessage("ワールドカップは何年おきに開催されるでしょう？ \n ※「～年」と答えてください");
                    }
                    if(randomValue == 3){
                        return new TextMessage("欧州サッカー連盟（UEFA）の主催で毎年9月から翌年の5月にかけて行われる、クラブチームによるサッカーの大陸選手権大会の事をアルファベット2文字で何という？");
                    }
                    if(randomValue == 4){
                        return new TextMessage("サッカー専門誌「フランス・フットボール」が創設したヨーロッパの年間最優秀選手に贈られる賞のことを何という？");
                    }
                    if(randomValue == 5){
                        return new TextMessage("オリンピックでは何歳以下の選手が出場できるでしょう \n ※「～歳」と答えてください");
                    }
                    if(randomValue == 6){
                        return new TextMessage("サッカーは1チーム何人でおこなう？ \n ※「～人」と答えてください");
                    }
                    if(randomValue == 7){
                        return new TextMessage("試合中危険なプレイをした選手に警告を出す時に使用するカードは何というカードでしょう？");
                    }
                    if(randomValue == 8){
    
                        return new TextMessage("別の場所で映像を見ながらフィールドの審判員をサポートする審判員をアルファベット3文字で何という？");
                    }
                    if(randomValue == 9){
    
                        return new TextMessage("試合時間は前半と後半合せて何分ある？ \n ※「～分」と答えてください");
                    }
                    if(randomValue == 10){
    
                        return new TextMessage("選手交代はオリンピックや通常の試合だと何人まで可能でしょう？ \n ※「～人」と答えてください");
                    }
                    if(randomValue == 11){
    
                        return new TextMessage("ファールなどで試合が中断した際、ボールを決められた所において試合を再開させるプレーを何プレーという？");
                    }
                    if(randomValue == 12){
    
                        return new TextMessage("試合中にけがや交代に消費した時間を前後半立った後に追加する制度を何タイムという？");
                    }
                    if(randomValue == 13){
    
                        return new TextMessage("ファウルのあった場所にボールを置き、自由に蹴ることができるルールを何キックという？");
                    }
                    if(randomValue == 14){
                        return new TextMessage("18歳の若さでスペインの強豪レアルマドリードへ移籍した日本人選手は？" );
                    }
                    if(randomValue == 15){
                        return new TextMessage("鹿児島出身のJリーガであり「コロコロPK」で有名な選手は？" );
                    }
                    if(randomValue == 16){
                        return new TextMessage("現在53歳を迎え今もなお「キング・カズ」の愛称でJリーグで活躍する選手は？" );
                    }
                    if(randomValue == 17){
                        return new TextMessage("実質的なカンボジアの代表を勤め現在東京オリンピック出場を目指している選手は？" );
                    }
                    if(randomValue == 18){
                        return new TextMessage("今シーズンのチャンピオンズ・リーグで欧州王者リバプールに素晴らしいパフォーマンスを見せ、そのリバプールへと移籍した日本人FWはだれ？");
                    }
                    if(randomValue == 19){
                        return new TextMessage("日本代表のゲームメーカーとしてチームを指揮する司令塔の役目を担い、スペインで活躍する日本人MFは？");
                    }
                    if(randomValue == 20){
                        return new TextMessage("「BIG3」のフレーズで有名な3人組の一人で、イングランド・プレミアリーグにて奇跡のリーグ優勝経験を持つ選手は？");
                    }
                break;

                case "基本編":
                ch = false;
                Qur = "基本編";
                    randomValue = random.nextInt(7);
                    if(randomValue == 0){
                        return new TextMessage("サッカーは1チーム何人でおこなう？ \n ※「～人」と答えてください");
                    }
                    if(randomValue == 1){
                        return new TextMessage("試合中危険なプレイをした選手に警告を出す時に使用するカードは何というカードでしょう？");
                    }
                    if(randomValue == 2){
    
                        return new TextMessage("別の場所で映像を見ながらフィールドの審判員をサポートする審判員をアルファベット3文字で何という？");
                    }
                    if(randomValue == 3){
    
                        return new TextMessage("試合時間は前半と後半合せて何分ある？ \n ※「～分」と答えてください");
                    }
                    if(randomValue == 4){
    
                        return new TextMessage("選手交代はオリンピックや通常の試合だと何人まで可能でしょう？ \n ※「～人」と答えてください");
                    }
                    if(randomValue == 5){
    
                        return new TextMessage("ファールなどで試合が中断した際、ボールを決められた所において試合を再開させるプレーを何プレーという？");
                    }
                    if(randomValue == 6){
    
                        return new TextMessage("試合中にけがや交代に消費した時間を前後半立った後に追加する制度を何タイムという？");
                    }
                    if(randomValue == 7){
    
                        return new TextMessage("ファウルのあった場所にボールを置き、自由に蹴ることができるルールを何キックという？");
                    }
                    
                break;

                case "選手編":
                ch = false;
                Qur = "選手編";
                    randomValue = random.nextInt(6);
                        if(randomValue == 0){
                            return new TextMessage("18歳の若さでスペインの強豪レアルマドリードへ移籍した日本人選手は？" );
                        }
                        if(randomValue == 1){
                            return new TextMessage("鹿児島出身のJリーガであり「コロコロPK」で有名な選手は？" );
                        }
                        if(randomValue == 2){
                            return new TextMessage("現在53歳を迎え今もなお「キング・カズ」の愛称でJリーグで活躍する選手は？" );
                        }
                        if(randomValue == 3){
                            return new TextMessage("実質的なカンボジアの代表を勤め現在東京オリンピック出場を目指している選手は？" );
                        }
                        if(randomValue == 4){
                            return new TextMessage("今シーズンのチャンピオンズ・リーグで欧州王者リバプールに素晴らしいパフォーマンスを見せ、そのリバプールへと移籍した日本人FWはだれ？");
                        }
                        if(randomValue == 5){
                            return new TextMessage("日本代表のゲームメーカーとしてチームを指揮する司令塔の役目を担い、スペインで活躍する日本人MFは？");
                        }
                        if(randomValue == 6){
                            return new TextMessage("「BIG3」のフレーズで有名な3人組の一人で、イングランド・プレミアリーグにて奇跡のリーグ優勝経験を持つ選手は？");
                        }
                break;

                default:
                break; 
            }
        }

        if(ch == false){
            switch(Qur){
                case "ランダム":
                ch = true;
                    if(randomValue == 0){
                        if(originalMessageText.equals("サウジアラビア")){
                            ch = true;
                            return new TextMessage("正解! \n ロシアワールドカップ記念すべき第1戦はロシア対サウジアラビアの試合が行われ「5-0」でロシアが勝利しました");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「ロシア」です");
                        }
                    }
                    if(randomValue == 1){
                        if(originalMessageText.equals("28位")){
                            ch = true;
                            return new TextMessage("正解! \n 日本は現在「28位」でアジア地域では1位となっています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「28位」です");
                        }
                    }
                    if(randomValue == 2){
                        if(originalMessageText.equals("4年")){
                            ch = true;
                            return new TextMessage("正解! \n 次回は2022年カタールでの開催が予定されています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「4年」です");
                        }
                    }
                    if(randomValue == 3){
                        String text = originalMessageText.toUpperCase();
                        if(text.equals("CL")){
                            ch = true;
                            return new TextMessage("正解! \n アジアではACLと呼ばれる大陸選手権大会が1～11月にかけて行われます");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「CL」です");
                        }
                    }
                    if(randomValue == 4){
                        if(originalMessageText.equals("バロンドール")){
                            ch = true;
                            return new TextMessage("正解! \n 現在バロンドールを多く獲得している選手はリオネル・メッシ選手の「6回」となっています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「バロンドール」です");
                        }
                    }
                    if(randomValue == 5){
                        if(originalMessageText.equals("23歳")){
                            ch = true;
                            return new TextMessage("正解! \n 特別枠として23歳以上の選手も出場できる「オーバーエイジ」枠が3選手まで適用できます");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「23歳」です");
                        }
                    }
                    if(randomValue == 6){
                        if(originalMessageText.equals("11人")){
                            ch = true;
                            return new TextMessage("正解! \n サッカーは１チーム11人で行います");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「11人です」");
                        }
                    }

                    if(randomValue == 7){
                        ch = true;
                        if(originalMessageText.equals("イエローカード")){
                            ch = true;
                            return new TextMessage("正解! \n 1試合に2枚のイエローカードを貰うとその試合から退場処分を受けます");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「イエローカードです」");
                        }
                    }
                    
                    if(randomValue == 8){
                        ch = true;
                        String text = originalMessageText.toUpperCase();
                        if(text.equals("VAR")){
                            ch = true;
                            return new TextMessage("正解! \n 日本のJリーグでは2020年シリーズ全試合にVARの導入が決定しています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「VAR」です");
                        }
                    }
                    
                    if(randomValue == 9){
                        ch = true;
                        if(originalMessageText.equals("90分")){
                            ch = true;
                            return new TextMessage("正解! \n サッカーは前半後半合せて90分行われます");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「90分」です");
                        }
                    }
                   
                    if(randomValue == 10){
                        ch = true;
                        if(originalMessageText.equals("3人")){
                            ch = true;
                            return new TextMessage("正解! \n 前半後半合わせて3人まで可能です");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「3人」です");
                        }   
                    }
                    
                    if(randomValue == 11){
                        ch = true;
                        if(originalMessageText.equals("セットプレー")){
                            ch = true;
                            return new TextMessage("正解! \n セットプレーには「フリーキック」「コーナーキック」「ペナルティキック」があります");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「セットプレー」です");
                        }   
                    } 
                   
                    if(randomValue == 12){
                        ch = true;
                        if(originalMessageText.equals("アディショナルタイム")){
                            ch = true;
                            return new TextMessage("正解! \n 大半は前半1～3分,後半3～5分が多いです");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「セットプレー」です");
                        }   
                    } 
                   
                    if(randomValue == 13){
                        ch = true;
                        if(originalMessageText.equals("フリーキック")){
                            ch = true;
                            return new TextMessage("正解! \n Jリーグでは中村俊介選手がフリーキックの名手として有名です");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「フリーキック」です");
                        }   
                    }
                    if(randomValue == 14){
                        if(originalMessageText.equals("久保建英")){
                            ch = true;
                            return new TextMessage("正解! \n 現在久保建英選手はスペインの「RCDマジョルカ」へとレンタル移籍しています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「久保建英」選手です");
                        }
                    }
                    if(randomValue == 15){
                        if(originalMessageText.equals("遠藤保仁")){
                            ch = true;
                            return new TextMessage("正解! \n 近年ではコロコロPKの詳細が知れ渡り普通にPKをすることが多いです");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「遠藤保仁」選手です");
                        }
                    }
                    if(randomValue == 16){
                        if(originalMessageText.equals("三浦知良")){
                            ch = true;
                            return new TextMessage("正解! \n 世界各国のメディアからは「ラストサムライ」だと賞賛しています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「三浦知良」選手です");
                        }
                    }
                    if(randomValue == 17){
                        if(originalMessageText.equals("本田圭佑")){
                            ch = true;
                            return new TextMessage("正解! \n 現在はオーバーエイジ枠での出場を目指しブラジルのボタフォゴFRでプレーしています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解");
                        }
                    }
                    if(randomValue == 18){
                        if(originalMessageText.equals("南野拓実")){
                            ch = true;
                            return new TextMessage("正解! \n 現在の日本代表に欠かせないプレイヤーの一人になっています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「南野拓実」選手です");
                        }
                    }
                    if(randomValue == 19){
                        if(originalMessageText.equals("柴崎岳")){
                            ch = true;
                            return new TextMessage("正解! \n 現在はスペイン2部のデポルティーボ・ラ・コルーニャでプレイし1部復帰を目指しています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「柴崎岳」選手です");
                        }
                    }
                    if(randomValue == 20){
                        if(originalMessageText.equals("岡崎慎司")){
                            ch = true;
                            return new TextMessage("正解! \n 本人は利き足は「頭」と宣言しており頭でのシュート成功率は高いものを誇っています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「岡崎慎司」選手です");
                        }
                    } 
                    break;

                    case "基本編":
                    if(randomValue == 0){
                        if(originalMessageText.equals("11人")){
                            ch = true;
                            return new TextMessage("正解! \n サッカーは１チーム11人で行います");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「11人です」");
                        }
                    }

                    if(randomValue == 1){
                        ch = true;
                        if(originalMessageText.equals("イエローカード")){
                            ch = true;
                            return new TextMessage("正解! \n 1試合に2枚のイエローカードを貰うとその試合から退場処分を受けます");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「イエローカードです」");
                        }
                    }
                    
                    if(randomValue == 2){
                        ch = true;
                        String text = originalMessageText.toUpperCase();
                        if(text.equals("VAR")){
                            ch = true;
                            return new TextMessage("正解! \n 日本のJリーグでは2020年シリーズ全試合にVARの導入が決定しています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「VAR」です");
                        }
                    }
                    
                    if(randomValue == 3){
                        ch = true;
                        if(originalMessageText.equals("90分")){
                            ch = true;
                            return new TextMessage("正解! \n サッカーは前半後半合せて90分行われます");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「90分」です");
                        }
                    }
                   
                    if(randomValue == 4){
                        ch = true;
                        if(originalMessageText.equals("3人")){
                            ch = true;
                            return new TextMessage("正解! \n 前半後半合わせて3人まで可能です");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「3人」です");
                        }   
                    }
                    
                    if(randomValue == 5){
                        ch = true;
                        if(originalMessageText.equals("セットプレー")){
                            ch = true;
                            return new TextMessage("正解! \n セットプレーには「フリーキック」「コーナーキック」「ペナルティキック」があります");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「セットプレー」です");
                        }   
                    } 
                   
                    if(randomValue == 6){
                        ch = true;
                        if(originalMessageText.equals("アディショナルタイム")){
                            ch = true;
                            return new TextMessage("正解! \n 大半は前半1～3分,後半3～5分が多いです");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「アディショナルタイム」です");
                        }   
                    } 
                   
                    if(randomValue == 7){
                        ch = true;
                        if(originalMessageText.equals("フリーキック")){
                            ch = true;
                            return new TextMessage("正解! \n Jリーグでは中村俊介選手がフリーキックの名手として有名です");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「フリーキック」です");
                        }   
                    } 
                    break;

                    case "選手編":
                    ch = true;
                    if(randomValue == 0){
                        if(originalMessageText.equals("久保建英")){
                            ch = true;
                            return new TextMessage("正解! \n 現在久保建英選手はスペインの「RCDマジョルカ」へとレンタル移籍しています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「久保建英」選手です");
                        }
                    }
                    if(randomValue == 1){
                        if(originalMessageText.equals("遠藤保仁")){
                            ch = true;
                            return new TextMessage("正解! \n 近年ではコロコロPKの詳細が知れ渡り普通にPKをすることが多いです");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「遠藤保仁」選手です");
                        }
                    }
                    if(randomValue == 2){
                        if(originalMessageText.equals("三浦知良")){
                            ch = true;
                            return new TextMessage("正解! \n 世界各国のメディアからは「ラストサムライ」だと賞賛しています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解! \n 正解は「三浦知良」選手です");
                        }
                    }
                    if(randomValue == 3){
                        if(originalMessageText.equals("本田圭佑")){
                            ch = true;
                            return new TextMessage("正解! \n 現在はオーバーエイジ枠での出場を目指しブラジルのボタフォゴFRでプレーしています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解");
                        }
                    }
                    if(randomValue == 4){
                        if(originalMessageText.equals("南野拓実")){
                            ch = true;
                            return new TextMessage("正解! \n 現在の日本代表に欠かせないプレイヤーの一人になっています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「南野拓実」選手です");
                        }
                    }
                    if(randomValue == 5){
                        if(originalMessageText.equals("柴崎岳")){
                            ch = true;
                            return new TextMessage("正解! \n 現在はスペイン2部のデポルティーボ・ラ・コルーニャでプレイし1部復帰を目指しています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「柴崎岳」選手です");
                        }
                    }
                    if(randomValue == 6){
                        if(originalMessageText.equals("岡崎慎司")){
                            ch = true;
                            return new TextMessage("正解! \n 本人は利き足は「頭」と宣言しており頭でのシュート成功率は高いものを誇っています");
                        }else{
                            ch = true;
                            return new TextMessage("不正解 \n 正解は「岡崎慎司」選手です");
                        }
                    }
                    break;
            }
        }
        return new TextMessage("ランダム、基本編、選手編のいずれか1つを発言してください");*/
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String col = null;

        String url = "jdbc:postgresql://localhost:5432/botsoccer";
        String user = "postgres";
        String password = "password";

        try{

            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
                String sql = "SELECT * from ramdom";
                rset = stmt.executeQuery(sql);
    
                //SELECT結果の受け取り
                while(rset.next()){
                     col = rset.getString(1);
                }

                rset.close();
                stmt.close();
                conn.close();

        }catch(SQLException e){

            return new TextMessage("SQL接続でエラーが出ました");
        }
            String answer = col;
            return new TextMessage("DBの中身は : " + answer); 

    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
    /*public void DBsetUp(){

        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String col = null;

        String url = "jdbc:postgresql://localhost:5432/botso";
        String user = "postgres";
        String password = "password";

        try{

            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
                String sql = "SELECT * from ramdom";
                rset = stmt.executeQuery(sql);
    
                //SELECT結果の受け取り
                while(rset.next()){
                     col = rset.getString(1);
                }

                rset.close();
                stmt.close();
                conn.close();

                return new TextMessage(col);

        }catch(SQLException e){

            return new TextMessage(e);

        }
    }*/
}