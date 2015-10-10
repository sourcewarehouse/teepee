package com.threeheadedmonkey.teepee.web;

import com.threeheadedmonkey.teepee.entity.Item;
import com.threeheadedmonkey.teepee.entity.ParentItem;
import com.threeheadedmonkey.teepee.entity.Task;
import com.threeheadedmonkey.teepee.entity.TaskComparator;

import java.util.Collection;
import java.util.TreeSet;

/**
 * Decorate tasks to provide getters for today and tomorrow
 */
public class DailyTasksDecorator {

    private final Collection<Task> today;
    private final Collection<Task> tomorrow;
    private final Collection<Task> overdue;
    private final Collection<Task> regularTasks;

    public DailyTasksDecorator(Collection<Item> items) {
        this.today = new TreeSet<Task>(new TaskComparator());
        this.tomorrow = new TreeSet<Task>(new TaskComparator());
        this.overdue = new TreeSet<Task>(new TaskComparator());
        this.regularTasks = new TreeSet<Task>(new TaskComparator());

        if (items != null && !items.isEmpty()) {
            categorise(items);
        }
    }

    /**
     * @return the tasks which are overdue
     */
    public boolean hasOverdue() {
        return !overdue.isEmpty();
    }

    public Collection<Task> getOverdue() {
        return overdue;
    }

    /**
     * @return the tasks which are due today
     */
    public boolean hasToday() {
        return !today.isEmpty();
    }

    public Collection<Task> getToday() {
        return today;
    }

    public boolean hasTomorrow() {
        return !tomorrow.isEmpty();
    }

    public Collection<Task> getTomorrow() {
        return tomorrow;
    }

    public boolean hasRegularTasks() {
        return !regularTasks.isEmpty();
    }

    public Collection<Task> getRegularTasks() {
        return regularTasks;
    }

    private void categorise(Collection<Item> items) {
        // loop over all items and child items to group into buckets
        for (Item item : items) {
            if (item instanceof ParentItem) {
                ParentItem pi = (ParentItem) item;
                if (pi.hasItems()) {
                    categorise(pi.getItems());
                }
            }
            if (item instanceof Task) {
                categorise((Task)item);
            }
        }
    }

    private void categorise(Task task) {
        if (task.isDone()) {
            return;
        }
        if (task.isOverdue()) {
            this.overdue.add(task);
        } else if (task.isDueToday()) {
            this.today.add(task);
        } else if (task.isDueTomorrow()) {
            this.tomorrow.add(task);
        } else {
            this.regularTasks.add(task);
        }

    }

}
