package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<Human, List<String>> phoneBook;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
    }

    public void addPhone(Human human, String phone) {
        List<String> phones = phoneBook.get(human);

        if (phones == null) {
            phones = new ArrayList<>();
            phoneBook.put(human, phones);
        }

        phones.add(phone);
    }

    public void removePhone(Human human, String phone) {
        List<String> phones = phoneBook.get(human);
        if (phones != null) {
            phones.remove(phone);
        }
    }

    public List<String> getPhones(Human human) {
        List<String> phones = phoneBook.get(human);
        if (phones == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(phones);
    }

    public Human findHumanByPhone(String phone) {
        for (Map.Entry<Human, List<String>> entry : phoneBook.entrySet()) {
            List<String> phones = entry.getValue();

            if (phones.contains(phone)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Map<Human, List<String>> findBySurnameStr(String str) {
        Map<Human, List<String>> result = new HashMap<>();

        for (Map.Entry<Human, List<String>> entry : phoneBook.entrySet()) {
            Human human = entry.getKey();
            if (human.getSurname().startsWith(str)) {
                result.put(human, new ArrayList<>(entry.getValue()));
            }
        }
        return result;
    }

}

