public class DataGenerator {
    ClassContainer group;
    DataGenerator()
    {
        Student s = new Student("Dominik", "Szkolny", StudentCondition.CHORY, 1999, 10.3);
        Student a = new Student("Jan", "Kowalski", StudentCondition.CHORY, 2000, 44.3);
        Student b = new Student("Dominik", "Noak", StudentCondition.CHORY, 1988, 60.3);
        Student c = new Student("Marta", "Tara", StudentCondition.NIEOBECNY, 1999, 34.2);
        Student d = new Student("Marysia", "Hgo", StudentCondition.NIEOBECNY, 2005, 76.3);
        Student e = new Student("Amelia", "Lego", StudentCondition.NIEOBECNY, 1999, 55.2);

        Class cl = new Class("1B", 6);

        cl.addStudent(a);
        cl.addStudent(b);
        cl.addStudent(c);
        cl.addStudent(d);
        cl.addStudent(s);
        cl.addStudent(e);

        s = new Student("Dominik", "Szkolny", StudentCondition.CHORY, 1999, 10.3);
        a = new Student("Jan", "Kowalski", StudentCondition.CHORY, 2000, 44.3);
        b = new Student("Dominik", "Nowak", StudentCondition.CHORY, 1978, 6.3);
        c = new Student("Marta", "Tra", StudentCondition.NIEOBECNY, 1999, 34.2);
        d = new Student("Marysia", "Hugo", StudentCondition.NIEOBECNY, 2000, 76.3);
        e = new Student("Amelia", "Lego", StudentCondition.NIEOBECNY, 1999, 55.2);

        Class cl1 = new Class("1A", 7);

        cl1.addStudent(a);
        cl1.addStudent(b);
        cl1.addStudent(c);
        cl1.addStudent(d);
        cl1.addStudent(s);
        cl1.addStudent(e);

        s = new Student("Dominik", "Slny", StudentCondition.CHORY, 1999, 10.3);
        a = new Student("Jan", "Kowalki", StudentCondition.CHORY, 2000, 46.3);
        b = new Student("Dominik", "Nowak", StudentCondition.CHORY, 1998, 60.3);
        c = new Student("Marta", "Tara", StudentCondition.NIEOBECNY, 1999, 34.2);
        d = new Student("Marysia", "Hgo", StudentCondition.NIEOBECNY, 2000, 76.3);
        e = new Student("Amelia", "Lego", StudentCondition.NIEOBECNY, 1999, 5.2);

        Class cl2 = new Class("2A", 10);

        cl2.addStudent(a);
        cl2.addStudent(b);
        cl2.addStudent(c);
        cl2.addStudent(d);
        cl2.addStudent(s);
        cl2.addStudent(e);

        group = new ClassContainer();

        group.setClass(cl);
        group.setClass(cl1);
        group.setClass(cl2);
    }

    public ClassContainer getGroup() {
        return group;
    }
}
