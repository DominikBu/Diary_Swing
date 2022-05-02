import java.util.Locale;

import static java.lang.Math.round;

public class Student implements Comparable<Student> {
    String name;
    String sname;
    StudentCondition student_con;
    int year;
    double points;

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public String getSname() {
        return sname;
    }

    public StudentCondition getStudent_con() {
        return student_con;
    }

    public double getPoints() {
        return points;
    }

    public void setStudent_con(StudentCondition student_con) {
        this.student_con = student_con;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    Student()
    {
    }
    Student(String i, String n, StudentCondition ss, int ru, double ip) {
        name = i;
        sname = n;
        student_con = ss;
        year = ru;
        points = ip;
    }

    public void print()
    {
        System.out.println( "Student [stan: " + student_con.toString() + "] "+ name + " " + sname + " urodzony " + year + "r. otrzymał " + round(points) + "pkt.");
    }

    @Override
    public int compareTo(Student o) {
        int result = this.getSname().compareTo(o.getSname());
        return result;
    }
}
