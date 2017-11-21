package com.java.pattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Command : 行为设计模式之一 命令模式
 *
 * 定义：将一个请求封装成一个对象，从而是你可用不同的请求对客户端参数化，
 *       对请求排队或记录请求日志，以及支持可撤销的操作。
 *  Command：命令，声明具体命令的抽象接口。
 *  ConcreteCommand：具体命令，接收者对象绑定与一个动作。
 *  Receiver：接收者，执行与请求相关的操作，具体实现对请求的业务处理。
 *  Invoker：调用者，负责调用命令对象执行请求，相关的方法叫做行动方法。
 * Created by Administrator on 2017/11/11.
 */
public class CommandPattern {
}

//播放对象
class Story{
    private String sName;
    private String sUrl;

    public Story(String sName,String sUrl){
        this.sName = sName;
        this.sUrl = sUrl;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }
}
//命令执行者（接收者)
class StoryPlayer{
    private int cursor = 0;//当前播放项
    private int pauseCursor = -1;//暂停播放项
    private List<Story> playList = new ArrayList<>();

    public void setPlayList(List<Story> list){
        this.playList = list;
        cursor = 0;
        System.out.println("更新播放列表......");
    }

    public void play(){
        cursor = 0;

    }
    public void play(int cursor){
        if(playList.size() == 0){
            System.out.println("当前播放列表为空，请先设置播放列表！");
        }else{
            if(pauseCursor == cursor){
                System.out.println("继续播放第" + pauseCursor +"个故事：<<" +playList.get(pauseCursor).getsName() +">>");
            }else{
                this.cursor = cursor;
                System.out.println("开始播放第" + cursor +"个故事:<<" + playList.get(cursor).getsName() +">>");
            }
        }
    }

    public void next(){
        cursor++;
        if(cursor == playList.size()){
            cursor = 0;
        }
        play(cursor);
    }

    public void pre(){
        cursor--;
        if(cursor < 0){
            cursor = playList.size() - 1;
        }
        play(cursor);
    }

    public void pause(){
        pauseCursor = cursor;
        System.out.println("暂停播放！");
    }
}
//抽象命令接口
interface Command{
    void execut();
}
//具体命令类
class SetListCommand implements Command{
    private StoryPlayer storyPlayer;
    private List<Story> mList = new ArrayList<>();
    public SetListCommand(StoryPlayer storyPlayer){
        this.storyPlayer = storyPlayer;
    }
    @Override
    public void execut() {
        storyPlayer.setPlayList(mList);
    }

    public void setPlayList(List<Story> list){
        this.mList = list;
    }
}
class PlayCommand implements Command{

    private StoryPlayer storyPlayer;
    public PlayCommand(StoryPlayer storyPlayer){
        this.storyPlayer = storyPlayer;
    }
    @Override
    public void execut() {
       storyPlayer.play();
    }
}

class PauseCommand implements Command{
    private StoryPlayer mPlayer;
    public PauseCommand(StoryPlayer storyPlayer){
        this.mPlayer = storyPlayer;
    }
    @Override
    public void execut() {
        mPlayer.pause();
    }
}
class NextCommand implements Command{
    private StoryPlayer storyPlayer;
    public NextCommand(StoryPlayer storyPlayer){
        this.storyPlayer = storyPlayer;
    }
    @Override
    public void execut() {
        storyPlayer.next();
    }
}
class PreCommand implements Command{
    private StoryPlayer storyPlayer;

    public PreCommand(StoryPlayer storyPlayer){
        this.storyPlayer = storyPlayer;
    }
    @Override
    public void execut() {
        storyPlayer.pre();
    }
}
//请求者类 (调用者类)
class Invoker{
    private SetListCommand setListCommand;
    private PlayCommand playCommand;
    private PauseCommand pauseCommand;
    private NextCommand nextCommand;
    private PreCommand preCommand;

    public void setSetListCommand(SetListCommand setListCommand) {
        this.setListCommand = setListCommand;
    }

    public void setPlayCommand(PlayCommand playCommand) {
        this.playCommand = playCommand;
    }

    public void setPauseCommand(PauseCommand pauseCommand) {
        this.pauseCommand = pauseCommand;
    }

    public void setNextCommand(NextCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    public void setPreCommand(PreCommand preCommand) {
        this.preCommand = preCommand;
    }

    public void setPlayList(List<Story> list){
        setListCommand.setPlayList(list);
        setListCommand.execut();
    }

    public void paly(){
        playCommand.execut();
    }
    public void pause(){
        pauseCommand.execut();
    }

    public void next(){
        nextCommand.execut();
    }

    public void pre(){
        preCommand.execut();
    }
}
