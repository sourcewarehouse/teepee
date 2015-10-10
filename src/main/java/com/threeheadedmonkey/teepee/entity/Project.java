package com.threeheadedmonkey.teepee.entity;

public class Project extends ParentItem {


    public Project(String content) {
        super(content);
    }

    public ItemType getType() {
        return ItemType.PROJECT;
    }

    public String toString() {
        return super.toString() + ":";
    }
}
