package org.example;

import java.util.*;

public class ListDemo {
    public List<Human> findSameSurnamesPeople(List<Human> list, Human human) {
        List<Human> listNameSakesPeople = new ArrayList<>();

        for (Human h : list) {
            if (human.getSurname().equals(h.getSurname()) && !human.equals(h)) {
                listNameSakesPeople.add(h);
            }
        }

        return listNameSakesPeople;
    }

    public List<Human> copyWithoutHuman(List<Human> list, Human human) {
        List<Human> newList = new ArrayList<>();

        for (Human h : list) {
            if (!h.equals(human)) {
                newList.add(h);
            }
        }
        return newList;
    }

    public List<Set<Integer>> setWithoutInterceptions(List<Set<Integer>> listOfSets, Set<Integer> requiredSet) {
        List<Set<Integer>> result = new ArrayList<>();

        for (Set<Integer> set : listOfSets) {
            boolean intersects = false;
            for (Integer num : set) {
                if (requiredSet.contains(num)) {
                    intersects = true;
                    break;
                }
            }
            if (!intersects) {
                result.add(set);
            }
        }

        return result;
    }

    public Set<Human> getPeopleWithMaxAge(List<? extends Human> list) {
        int maxAge = 0;
        Set<Human> peopleWithMaxAge = new HashSet<>();

        for (Human human : list) {
            if (human.getAge() > maxAge) {
                maxAge = human.getAge();
            }
        }

        for (Human human : list) {
            if (human.getAge() == maxAge) {
                peopleWithMaxAge.add(human);
            }
        }

        return peopleWithMaxAge;
    }

    public List<Human> sortListByFullName(Set<? extends Human> humanSet) {
        Comparator<Human> comparator = Comparator
                .comparing(Human::getSurname)
                .thenComparing(Human::getName)
                .thenComparing(Human::getPatronymic);

        TreeSet<Human> sortedSet = new TreeSet<>(comparator);
        sortedSet.addAll(humanSet);

        return new ArrayList<>(sortedSet);
    }

    public Set<Human> getPeopleByIds(Map<Integer, Human> idToHumanMap, Set<Integer> ids) {
        Set<Human> resultSet = new HashSet<>();

        for (Integer id : ids) {
            if (idToHumanMap.containsKey(id)) {
                resultSet.add(idToHumanMap.get(id));
            }
        }

        return resultSet;
    }

    public List<Integer> getAdultIds(Map<Integer, Human> idToHumanMap, Set<Integer> ids) {
        List<Integer> resultList = new ArrayList<>();

        for (Integer id : ids) {
            if (idToHumanMap.containsKey(id) && idToHumanMap.get(id).getAge() >= 18) {
                resultList.add(id);
            }
        }

        return resultList;
    }

    public Map<Integer, Integer> createAgeMap(Map<Integer, Human> idToHumanMap, Set<Integer> ids) {
        Map<Integer, Integer> resultMap = new HashMap<>();

        for (Integer id : ids) {
            if (idToHumanMap.containsKey(id)) {
                resultMap.put(id, idToHumanMap.get(id).getAge());
            }
        }

        return resultMap;
    }

    public Map<Integer, List<Human>> listPeopleByAge(Set<Human> setPeople) {
        Map<Integer, List<Human>> resultMap = new HashMap<>();

        for (Human h : setPeople) {
            int age = h.getAge();

            if (!resultMap.containsKey(age)) {
                resultMap.put(age, new ArrayList<>());
            }

            resultMap.get(age).add(h);
        }

        return resultMap;
    }

    public Map<Integer, Map<Character, List<Human>>> listPeopleByAgeAndFirstLetter(Set<Human> setPeople) {
        Map<Integer, List<Human>> ageMap = listPeopleByAge(setPeople);
        Map<Integer, Map<Character, List<Human>>> resultMap = new HashMap<>();

        Comparator<Human> comparator = Comparator
                .comparing(Human::getSurname)
                .thenComparing(Human::getName)
                .thenComparing(Human::getPatronymic)
                .reversed();


        for (Map.Entry<Integer, List<Human>> entry : ageMap.entrySet()) {
            List<Human> peopleWithSameAge = entry.getValue();
            Map<Character, List<Human>> charMap = new HashMap<>();

            for (Human h : peopleWithSameAge) {
                char firstLetter = h.getSurname().charAt(0);
                if (!charMap.containsKey(firstLetter)) {
                    charMap.put(firstLetter, new ArrayList<>());
                }
                charMap.get(firstLetter).add(h);
            }

            for (List<Human> list : charMap.values()) {
                list.sort(comparator);
            }

            resultMap.put(entry.getKey(), charMap);
        }

        return resultMap;
    }
}






