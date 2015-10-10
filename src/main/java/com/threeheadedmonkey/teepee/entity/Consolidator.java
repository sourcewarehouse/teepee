package com.threeheadedmonkey.teepee.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * Consolidate a list of flat items into a project/task/note hierarchy
 */
public class Consolidator {

    private final Collection<Item> items;


    /**
     * Construct Consolidator with provided items
     *
     * @param items the collection of items to consolidate
     */
    public Consolidator(Collection<Item> items) {
        this.items = items;
    }

    /**
     * Consolidate a collection of flat items into a tree of items where Tasks fall under Projects and so on
     *
     * @return the tree of items
     */
    public Collection<Item> consolidate() {

        // We will be returning a new list
        List<Item> consolidatedItems = new ArrayList<Item>();

        Stack<ParentItem> levelStack = new Stack<ParentItem>();
        levelStack.push(new RootParentItem(consolidatedItems));

        for (Item item : items) {
            addItemToParent(levelStack, item);
        }

        return consolidatedItems;
    }

    private void addItemToParent(Stack<ParentItem> parentStack, Item item) {
        int parentLevel = parentStack.peek().getLevel();
        int itemLevel = item.getLevel();

        if (parentLevel == itemLevel - 1) {
            parentStack.peek().addItem(item);
            if (item instanceof ParentItem) {
                parentStack.push((ParentItem) item);
            }
            return;
        }
        if (item instanceof Note && parentLevel == itemLevel) {
            parentStack.peek().addItem(item);
            return;
        }

        parentStack.pop();
        addItemToParent(parentStack, item);
    }

    private class RootParentItem extends ParentItem {

        private final List<Item> items;

        public RootParentItem(List<Item> items) {
            super("root");
            this.items = items;
        }

        @Override
        public int getLevel() {
            return -1;
        }

        @Override
        public void addItem(Item item) {
            this.items.add(item);
        }

    }
}