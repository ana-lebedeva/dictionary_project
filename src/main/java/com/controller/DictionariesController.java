package com.controller;

import com.Entry;
import dictionaries.DictionaryBehavior;
import enums.CharRange;
import enums.DictionaryStructure;
import enums.Message;
import enums.Status;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class DictionariesController {
    private DictionaryBehavior dictionary;
    private DictionaryStructure activeDictionary = null;
    @Inject
    public DictionariesController(DictionaryBehavior dictionary) {
        this.dictionary = dictionary;
    }

    @RequestMapping({"/", "/dictionaries"})
    public String getDictionaries( ModelMap model) {
        model.addAttribute("dictionaries", dictionary.getDictionaries());
        model.addAttribute("dictionaryButtonActive", false);
        return "dictionaries";
    }

    @RequestMapping(value="/dictionaries/{name}", method=RequestMethod.GET)
    public String getDictionary(@PathVariable("name") String name, ModelMap model){
        setActiveDictionary(name);
        model.addAttribute("dictionaries", dictionary.getDictionaries());
        model.addAttribute("activeDictionary", activeDictionary);
        model.addAttribute("dictionary", dictionary);
        model.addAttribute("dictionaryButtonActive", true);
        return "dictionaries";
    }

    private void setActiveDictionary(String name){
        DictionaryStructure[] dictionaries = dictionary.getDictionaries();
        for (DictionaryStructure dictionaryS : dictionaries){
            if (dictionaryS.getName().equals(name)){
                dictionary.setActiveDictionary(dictionaryS);
                activeDictionary = dictionaryS;
            }
        }
    }

    @RequestMapping(value="/dictionaries/delete",
            method=RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<String> deleteEntryFromDictionaties (@RequestParam String key){
        splitRequestAndDeleteEntry(key);
        return dictionary.getAllEntries();
    }

    @RequestMapping(value="/entry/delete",
            method=RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    List<String> deleteEntryFromEntry (@RequestBody Entry entry){
        List<String> response = new ArrayList<>();
        Status status = dictionary.deleteEntry(entry.getKey(), entry.getValue());
        response.add(returnMessage(status));
        String[] entries = getEntriesForKey(entry.getKey());
        if (entries != null && entries.length != 0)
        response.addAll(Arrays.asList(entries));
        return response;
    }

    private Status splitRequestAndDeleteEntry (String key){
        String[] entry = key.split("-");
        return dictionary.deleteEntry(entry[0].trim(), entry[1].trim());
    }

    @RequestMapping(value = "/dictionaries/search",
            method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    public @ResponseBody String searchEntry (@RequestParam String key){
        String result = dictionary.getValue(key);
        if (result.equals(""))
            return Status.NOT_ENTRY.getDescription();
        else
            return result;
    }

    @RequestMapping(value = "/dictionaries/searchAll",
            method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    public @ResponseBody String searchEntryInAll (@RequestParam String key){
        String nameActiveDictionary = activeDictionary.getName();
        DictionaryStructure[] dictionaries = dictionary.getDictionaries();
        String result = "";
        String entry;
        for (DictionaryStructure dictionaryS : dictionaries){
            dictionary.setActiveDictionary(dictionaryS);
            entry = dictionary.getValue(key);
            if (entry != null) {
                result+= entry;
            }
        }
        setActiveDictionary(nameActiveDictionary);
        return result;
    }

    @RequestMapping(value="/entry/{key}", method=RequestMethod.GET)
    public String getEntry(@PathVariable("key") String key, ModelMap model){
        model.addAttribute("key", key);
        model.addAttribute("entries", getEntriesForKey(key));
        return "entry";
    }

    private String[] getEntriesForKey(String key){
        String entries = dictionary.getValue(key);
        String[] entriesArr = null;
        if (!entries.equals("")) {
            entriesArr = entries.split("\n");
            for (int i = 0; i < entriesArr.length; i++) {
                String temp = entriesArr[i].split("-")[1];
                entriesArr[i] = temp.trim();
            }
        }
        return entriesArr;
    }

    @RequestMapping(value="/entry/add", method=RequestMethod.POST, produces = {"application/json"})
    public @ResponseBody
    List<String> addEntry (@RequestBody Entry entry){
        List<String> response = new ArrayList<>();
        Status status = dictionary.validateAndAddEntry(entry.getKey(), entry.getValue());
        response.add(returnMessage(status));
        String[] entries = getEntriesForKey(entry.getKey());
        if (entries != null)
            response.addAll(Arrays.asList(entries));
        return response;
    }

    private String returnMessage(Status status) {
        String result = status.getDescription() + "\r\n";
        System.out.println(status.getDescription());
        if (status == Status.INVALID_LENGTH){
            result = Message.LENGTH.getDescription() + "\r\n"
                    + status.getLength();
        }
        if (status == Status.OUT_OF_BOUNDS){
            result = Message.ALPHABETS.getDescription() + "\r\n"
                    + status.getFieldError() + "\r\n";
            for(CharRange range: status.getCharRanges())
                result += range.getName() + " ";
        }
        return result;
    }
}
