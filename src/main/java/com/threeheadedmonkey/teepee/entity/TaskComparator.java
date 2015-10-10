package com.threeheadedmonkey.teepee.entity;

import org.joda.time.DateTime;

import java.util.*;

/**
 * Sort Tasks by their due date then name
 */
public class TaskComparator implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        Date date1 = task1.getDueDate();
        if (date1 == null) {
            date1 = new DateTime().plusYears(100).toDate();
        }
        Date date2 = task2.getDueDate();
        if (date2 == null) {
            date2 = new DateTime().plusYears(100).toDate();
        }
        int result = date1.compareTo(date2);
        if (result != 0) {
            return result;
        }
        result = ((Integer)task1.getLevel()).compareTo(task2.getLevel());
        if (result != 0){
            return result;
        }
        result = task1.getContent().compareTo(task2.getContent());
        return result;
    }
}
