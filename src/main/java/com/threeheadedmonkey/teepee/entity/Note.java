package com.threeheadedmonkey.teepee.entity;


public class Note extends Item {

    public Note(String content) {
        super(content);
    }

    public ItemType getType() {
        return ItemType.NOTE;
    }

}
