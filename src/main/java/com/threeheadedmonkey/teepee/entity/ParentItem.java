package com.threeheadedmonkey.teepee.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * An Item which can hold child items
 */
public class ParentItem extends Item {

    private final List<Item> items;

    public ParentItem(String content) {
        super(content);
        this.items = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        if (item != null && item.hasContent()) {
            items.add(item);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean hasItems() {
        return items != null && !items.isEmpty();
    }

}
