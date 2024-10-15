package in.dhirajrajput.cache;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        int[] arr = { 2, 3, 4, 5 };

        
        Optional<Integer> sencond = Arrays.stream(arr)
                .boxed()
                .sorted((a, b) -> b - a)
                .skip(1)
                .findFirst();

        System.out.println(sencond.get());

        // sum of even numbers
        Integer sum = Arrays.stream(arr)
                .boxed()
                .filter(a -> (a % 2 == 0))
                .reduce((a, b) -> a + b).get();
        System.out.println(sum);

        List<String> list = Arrays.asList("cat", "dog", "cow");
        // convert toUpperCase
        String s = list.stream()
                .map(name -> name.toUpperCase())
                .collect(Collectors.joining(", "));

        System.out.println(s);

        // second largest sallary
        List<Employee> employees = Arrays.asList(
                new Employee("Rahul", 120000),
                new Employee("Rohit", 300000),
                new Employee("Sohan", 1000)
            );

        List<Employee> employees2 = employees.stream()
                .sorted(Comparator.comparingInt(Employee::getSallary).reversed())
                .skip(1)
                .collect(Collectors.toList());

        System.out.println(employees2.get(0).getName());
    }

    public static class Employee {

        private String name;

        private Integer sallary;

        public Employee(String name, Integer sallary) {
            this.name = name;
            this.sallary = sallary;
        }

        public String getName() {
            return name;
        }

        public Integer getSallary() {
            return sallary;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return super.toString();
        }
    }
}
