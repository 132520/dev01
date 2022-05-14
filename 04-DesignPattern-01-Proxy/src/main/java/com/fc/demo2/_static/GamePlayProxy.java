package com.fc.demo2._static;

//代理对象
public class GamePlayProxy implements GamePlay {
    private GamePlay gamePlayer;

    public GamePlayProxy(GamePlay gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    @Override
    public void login() {
        gamePlayer.login();
    }

    @Override
    public void killBoss() {
        gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        gamePlayer.upgrade();
    }


    private void luckDraw() {
        System.out.println("一个铭文碎片");
    }
}
