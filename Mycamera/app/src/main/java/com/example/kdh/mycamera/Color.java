package com.example.kdh.mycamera;

/**
 * Created by kdh on 2015-03-08.
 */
public class Color {

    String cbaAlikeWord;
    int cbaTyp;
    int cbaSeq;
    RGB rgb1=null;
    RGB rgb2=null;
    RGB rgb3=null;
    RGB rgb4=null;

    Color(int cbaSeq, int cbaTyp,String cbaAlikeWord,RGB rgb1){
        this.cbaSeq=cbaSeq;
        this.cbaTyp=cbaTyp;
        this.cbaAlikeWord=cbaAlikeWord;
        this.rgb1=rgb1;

    }
    Color(int cbaSeq, int cbaTyp,String cbaAlikeWord,RGB rgb1,RGB rgb2){
        this.cbaSeq=cbaSeq;
        this.cbaTyp=cbaTyp;
        this.cbaAlikeWord=cbaAlikeWord;
        this.rgb1=rgb1;
        this.rgb2=rgb2;

    }
    Color(int cbaSeq, int cbaTyp,String cbaAlikeWord,RGB rgb1,RGB rgb2,RGB rgb3){
        this.cbaSeq=cbaSeq;
        this.cbaTyp=cbaTyp;
        this.cbaAlikeWord=cbaAlikeWord;
        this.rgb1=rgb1;
        this.rgb2=rgb2;
        this.rgb3=rgb3;

    }
    Color(int cbaSeq, int cbaTyp,String cbaAlikeWord,RGB rgb1,RGB rgb2,RGB rgb3,RGB rgb4){
        this.cbaSeq=cbaSeq;
        this.cbaTyp=cbaTyp;
        this.cbaAlikeWord=cbaAlikeWord;
        this.rgb1=rgb1;
        this.rgb2=rgb2;
        this.rgb3=rgb3;
        this.rgb4=rgb4;
    }

}
