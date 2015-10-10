package com.threeheadedmonkey.teepee.respository;

import com.threeheadedmonkey.teepee.entity.Item;

import java.util.Collection;

/**
 * Store for a collection of Items
 */
public interface ItemRepository {

    /**
     * Get the collection of Items identity by the provided key
     *
     * @param key the Item collection ID
     * @return the collection of items
     */
    public Collection<Item> get(String key);

    /**
     * Store a collection of Items by the provided key
     *
     * @param key the ID of the collection of Items
     * @param items the collection of Items
     */
    public void put(String key, Collection<Item> items);
}
