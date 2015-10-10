package com.threeheadedmonkey.teepee.web;

import com.threeheadedmonkey.teepee.entity.Item;
import com.threeheadedmonkey.teepee.exception.ResourceNotFoundException;
import com.threeheadedmonkey.teepee.respository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Web controller for the Application
 */
@Controller
public class ApplicationController {

    private final static Logger log = LoggerFactory.getLogger(ApplicationController.class);

    @Inject
    private ItemRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String get(@RequestParam(required = false) String key) {

        if (StringUtils.hasText(key)) {
            log.debug("Redirecting to {}", key);
            return "redirect:/" + key;
        }

        log.debug("Loading index page");
        return "index";
    }

    /*
        @RequestMapping(method=RequestMethod.POST)
        public String create(@Valid Account account, BindingResult result) {
            if (result.hasErrors()) {
                return "account/createForm";
            }
            this.accounts.put(account.assignId(), account);
            return "redirect:/account/" + account.getId();
        }
     */
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public String getTasks(@PathVariable String uuid, Model model) {

        log.debug("Loading tasks for UUID {}", uuid);
        Collection<Item> items = this.repository.get(uuid);
        if (items == null) {
            throw new ResourceNotFoundException(uuid);
        }

        DailyTasksDecorator dailyTasks = new DailyTasksDecorator(items);

        model.addAttribute("key", uuid);
        model.addAttribute("tasks", dailyTasks);
        return "tasks";
    }

}
