import java.util.*;

class Student {
    private String id, name;
    private int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() { return id; }
    public int getMarks() { return marks; }
    public String getRole() { return "Undergrad"; }

    @Override
    public String toString() {
        return id + "-" + name + " (" + getMarks() + ") " + getRole();
    }
}

class GraduateStudent extends Student {
    private String area;

    public GraduateStudent(String id, String name, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }

    @Override
    public String getRole() {
        return "Grad (" + area + ")";
    }
}

class HonoursStudent extends Student {
    private int bonusMarks;

    public HonoursStudent(String id, String name, int marks, int bonusMarks) {
        super(id, name, marks);
        this.bonusMarks = bonusMarks;
    }

    @Override
    public int getMarks() {
        return super.getMarks() + bonusMarks;
    }

    @Override
    public String getRole() {
        return "Honours (+ bonus " + bonusMarks + ")";
    }
}

class Repository<T> {
    private Map<String, T> data = new HashMap<>();

    public void save(String id, T obj) { data.put(id, obj); }
    public T find(String id) { return data.get(id); }
    public void delete(String id) { data.remove(id); }
}

public class Main {

    public static Student getTopper(List<Student> list) {
        return list.stream()
                .max(Comparator.comparingInt(Student::getMarks))
                .orElse(null);
    }

    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        list.add(new Student("S1", "Ayush", 88));
        list.add(new Student("S2", "Shubham", 89));
        list.add(new Student("S3", "Tarak", 78));
        list.add(new GraduateStudent("S4", "Ansh", 88, "MBA"));
        list.add(new HonoursStudent("S5", "Yogesh", 85, 10));

        Repository<Student> repo = new Repository<>();
        for (Student s : list) repo.save(s.getId(), s);

        System.out.println("ALL STUDENTS:");
        list.forEach(System.out::println);

        System.out.println("\nTOPPER:");
        Student topper = getTopper(list);
        System.out.println(topper != null ? topper : "No students found");

        System.out.println("\nLOOKUP S2:");
        Student s = repo.find("S2");
        System.out.println(s != null ? s : "not found");

        Iterator<Student> it = list.iterator();
        while (it.hasNext()) {
            Student st = it.next();
            if (st.getMarks() < 80) {
                it.remove();
                repo.delete(st.getId());
            }
        }

        System.out.println("\nAFTER REMOVAL (<80 removed):");
        list.forEach(System.out::println);

        System.out.println("\nREPO lookup S1:");
        System.out.println(repo.find("S1"));
    }
}
