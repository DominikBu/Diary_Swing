import java.util.*;

import static java.lang.Math.abs;

public class Class {
    String group_name;
    List<Student> studentlist;
    int max_student;

    public List<Student> getStudentlist() {
        return studentlist;
    }

    public void setStudentlist(List<Student> studentlist) {
        this.studentlist = studentlist;
    }

    Class(String ng, int ms)
    {
        group_name = ng;
        max_student = ms;
        studentlist = new ArrayList<>(max_student);
    }

    public void remove(Student s) throws MyException
    {

        if(!studentlist.remove(s))
            throw new MyException("Nie można usunąć wybranego studenta.");
    }

    public void addStudent(Student other)throws MyExceptionArth
    {
        for(Student s : studentlist){
            if(s.getName().equals(other.getName()))
            {
                //System.out.println("Student o podanym imieniu już istnieje");
                break;
            }
        }

        if(studentlist.size() == max_student)
            throw new MyExceptionArth("Brak miejsca w klasie");

        studentlist.add(other);

        studentlist.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.compareTo(o2);
            }
        });
    }

    public void addPoints(Student s, double pkt)
    {
        s.points += pkt;
    }

    public void getStudent(Student s)
    {
        if(s.getPoints() <= 0 )
            studentlist.remove(s);
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public void setMax_student(int max_student) {
        this.max_student = max_student;
    }

    public void changeCondition(Student s, StudentCondition con)
    {
        s.setStudent_con(con);
    }

    public void removePoints(Student s, double pkt)
    {
        s.points -= pkt;
        getStudent(s);
    }

    public Student search(String surname)
    {
        Student s = new Student();
        s.setSname(surname);
        int result = Collections.binarySearch(studentlist, s , new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.compareTo(o2);
            }
        });

        return studentlist.get(result);
    }

    public List<Student> searchPartial(String str)
    {
        List<Student> l = new LinkedList<>();
        for(Student s : studentlist){
            if(s.getSname().contains(str) || s.getName().contains(str))
            {
                l.add(s);
            }
        }
        return l;
    }

    public int countByCondition(StudentCondition sc)
    {
        int i=0;
        for(Student s : studentlist){
            if(s.getStudent_con() == sc)
                i++;
        }
        return i;
    }

    public void summary()
    {
        System.out.println();
        for(Student s : studentlist){
            s.print();
        }
        System.out.println();
    }

    public List<Student> sortByName()
    {
        List<Student> l = new LinkedList<>();

        for(Student s : studentlist){
            l.add(s);
        }


        Collections.sort(l, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return l;
    }

    public List<Student> sortByPoints()
    {
        List<Student> l = new LinkedList<>();

        for(Student s : studentlist){
            l.add(s);
        }

        Collections.sort(l, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getPoints(), o2.getPoints());
            }
        });

        return l;
    }

    public int getMax_student() {
        return max_student;
    }

    public String getGroup_name() {
        return group_name;
    }

    public Student max()
    {
        Student s1 = Collections.max(studentlist, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getPoints(), o2.getPoints());
            }
        });
        return s1;
    }

}
