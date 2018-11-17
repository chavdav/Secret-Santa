package com.chavdav;

import java.util.*;

import static java.lang.Math.random;

public class SecretSantaGenerator
{

    public static void main( String[] args )
    {
        List<Person> persons = new ArrayList<Person>();

        Scanner sc = new Scanner(System.in);

        boolean more = true;
        while(more) {
            more = addPerson(persons, sc.nextLine());
        }

        List<Person> originalPersons = new ArrayList<Person>(persons);

        randomize(persons);
        System.out.println(persons.toString());
        Map<Person, Person> assignments = validateAndAdjust(originalPersons, persons);
        System.out.println(assignments.toString());
    }

    private static Map<Person, Person> validateAndAdjust(List<Person> originalPersons, List<Person> persons) {
        Iterator<Person> iter = persons.iterator();
        for( int i = 0;iter.hasNext(); i++) {
            if(validate(persons, i, i+1)) {
                continue;
            }
        }

        return null;
    }

    private static boolean validate(List<Person> persons, int p1, int p2) {
        Person a = persons.get(p1);
        Person b = persons.get(p2);

        for(String group : a.groups) {
            if(b.groups.contains(group)) {
                return false;
            }
        }
        return true;
    }

    private static void randomize(List<Person> persons) {
        for( int i = 0; i<persons.size(); i++) {
            swap(persons, i, (int) Math.floor(random()* persons.size()));
        }
    }

    private static void swap(List<Person> persons, int p1, int p2) {
        Person temp = persons.get(p1);
        Person temp2=persons.get(p2);
        persons.remove(p1);
        persons.add(p1, temp2);
        persons.remove(p2);
        persons.add(p2, temp);
    }

    static Map<String, Person> assignSantas() {
        Map<String, Person> santas = new HashMap<String, Person>();
        return null;
    }

    static boolean addPerson(List<Person> persons, String person) {
        if("".equals(person)) {
            return false;
        }

        String[] personData = person.split("\\s+-\\s+", 2);

        String name = personData[0];

        String[] groups = personData[1].split("\\s+", 0);

        persons.add(new Person(name, groups));
        return true;
    }

    static class Person {
        final String name;
        final Set<String> groups;

        Person(String name, String... groups) {
            this.name = name;

            Set<String> groupset = new HashSet<String>();
            for(String group : groupset) {
                groupset.add(group);
            }

            this.groups = Collections.unmodifiableSet(groupset);
        }
    }
}
