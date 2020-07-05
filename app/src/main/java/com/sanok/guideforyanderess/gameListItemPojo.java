package com.sanok.guideforyanderess;

public class gameListItemPojo {
    private int  gmListImgSrc ;
    private String gameName , gameRDate ;

    public gameListItemPojo(int gmListImgSrc, String gameName, String gameRDate) {
        this.gmListImgSrc = gmListImgSrc;
        this.gameName = gameName;
        this.gameRDate = gameRDate;
    }

    public int getGmListImgSrc() {
        return gmListImgSrc;
    }

    public String getGameName() {
        return gameName;
    }

    public String getGameRDate() {
        return gameRDate;
    }
}
