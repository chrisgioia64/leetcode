package base.training;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LambdaExpression {

    public static class Person {
        private int age;
        private String name;

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }

        private void print() {
            System.out.println(age + " " + name);
        }
    }

    public interface CheckPerson {

        public boolean checkPerson(Person p);
    }

    public static void printPersonsOlder(List<Person> persons, int age) {
        for (Person person : persons) {
            if (person.age > age) {
                person.print();
            }
        }
    }

    public static void printPersons(List<Person> persons, CheckPerson check) {
        for (Person person : persons) {
            if (check.checkPerson(person)) {
                person.print();
            }
        }
        System.out.println("-------");
    }

    public static void personsFilter(List<Person> persons,
                                     Predicate<Person> filter,
                                     Consumer<Person> consumer) {
        for (Person person : persons) {
            if (filter.test(person)) {
                consumer.accept(person);
            }
        }
        System.out.println("-----");
    }

    public static void personsFilter2(List<Person> persons,
                                     Predicate<Person> filter,
                                     Function<Person, String> function) {
        for (Person person : persons) {
            if (filter.test(person)) {
                String s = function.apply(person);
                System.out.println(s);
            }
        }
        System.out.println("------");
    }

    public static void main(String[] args) {
        List<Person> persons = new LinkedList<>();
        persons.add(new Person(5, "Mary"));
        persons.add(new Person(26, "Sergio"));
        persons.add(new Person(64, "John"));
        persons.add(new Person(59, "Marc"));
        persons.add(new Person(67, "Joseph"));
        persons.add(new Person(29, "Chris"));
        persons.add(new Person(17, "Emilia"));

//        printPersonsOlder(persons, 60);

        // anonymous class
        printPersons(persons, new CheckPerson() {
            @Override
            public boolean checkPerson(Person p) {
                return p.age < 30;
            }
        });

        // lambda expression
        printPersons(persons, (Person p) -> p.age >= 20 && p.age <= 30);

        personsFilter(persons,
                p -> p.age >= 20 && p.age <= 30,
                p -> System.out.println(p.name));

        personsFilter2(persons,
                p -> p.age >= 20 && p.age <= 30,
                p -> p.name);

        ToDoubleFunction<Person> toDouble = new ToDoubleFunction<Person>() {
            @Override
            public double applyAsDouble(Person value) {
                return value.age;
            }
        };
        Collector<Person, ?, Double> ageAverage = Collectors.averagingDouble(toDouble);
        ageAverage = Collectors.averagingDouble((Person p) -> p.age);
        double age = persons.stream().collect(ageAverage);
        System.out.println(age);

        System.out.println("-----");
        Collections.sort(persons, (x, y) -> {
            if (x.age == 29) {
                return -1;
            } else {
                return x.age - y.age;
            }
        });
        for (Person person : persons) {
            person.print();
        }
    }

}
