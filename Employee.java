import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of employees-");
        int N = sc.nextInt();
        List<emp> list = new ArrayList<>();
        sc.nextLine();
        for (int i = 0; i < N; i++) {
            System.out.println("Enter Employee_id, Employee_name and Salary of Employee with space in between.");
            String s = sc.nextLine();
            String[] arrs = s.split(" ");
            String emp_name = arrs[1];
            int emp_id = Integer.parseInt(arrs[0]);
            int salary = Integer.parseInt(arrs[2]);
            list.add(new emp(emp_id, emp_name, salary));
        }

        for (emp emp : list) {
            double tax;
            int sal = emp.salary;
            if (sal <= 50000)
                tax = 0;
            else if (sal <= 60000)
                tax = .1 * (sal - 50000);
            else if (sal <= 150000)
                tax = .2 * (sal - 60000) + 1000;
            else
                tax = .3 * (sal - 150000) + 1000 + 18000;
            System.out.println("Employee with ID:- " + emp.emp_id + " and Name:- " + emp.emp_name + " having Salary = "
                    + emp.salary + " have to pay Income Tax = " + tax);
        }

    }
}

class emp {
    String emp_name;
    int emp_id;
    int salary;

    public emp(int emp_id, String emp_name, int salary) {
        this.emp_name = emp_name;
        this.emp_id = emp_id;
        this.salary = salary;
    }

}