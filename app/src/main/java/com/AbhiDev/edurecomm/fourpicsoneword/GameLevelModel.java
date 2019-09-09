package com.AbhiDev.edurecomm.fourpicsoneword;

/**
 * Created by sharda on 23/07/17.
 */

public class GameLevelModel {
    int images[];   // array resource IDs of 4 images
    String correctAnswer;   //correct word that explains the 4 pictures
    String characters; //set of 12 characters to display as options

    public GameLevelModel(int image1, int image2, int image3, int image4, String correctAnswer, String characters){
        this.images = new int[]{image1, image2, image3, image4};
        this.correctAnswer = correctAnswer;
        this.characters = characters;
    }

    public int[] getImages() {
        return images;
    }

    public void setImages(int[] images) {
        this.images = images;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }
}
