package com.threeheadedmonkey.teepee.entity;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task extends ParentItem {

    private final List<Tag> tags;
    private boolean done;
    private Date doneDate;
    private boolean overdue;
    private Date dueDate;
    private boolean dueToday;
    private boolean dueTomorrow;

    public Task(String content) {
        super(content);
        this.tags = new ArrayList<Tag>();
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public boolean hasTags() {
        return tags != null && !tags.isEmpty();
    }

    public void setDone() {
        this.done = true;
    }

    public boolean isDone() {
        return done;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setOverdue() {
        this.overdue = true;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        dueToday = false;
        dueTomorrow = false;
        if (hasDueDate()) {
            DateTime today = new DateTime();
            if (today.toDate().equals(dueDate)) {
                dueToday = true;
            } else if (today.plusDays(1).toDate().equals(dueDate)) {
                dueTomorrow = true;
            }
        }
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean hasDueDate() {
        return dueDate != null;
    }

    public boolean isDueToday() {
        return dueToday;
    }

    public boolean isDueTomorrow() {
        return dueTomorrow;
    }

    public ItemType getType() {
        return ItemType.TASK;
    }
}
