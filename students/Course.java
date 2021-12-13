package students;

public class Course {
    private int id;
    private String title;
    private int semester;

    static int courses_num = 0;

    public Course() {
        this.id = ++courses_num;
        this.title = "Unknown";
        this.semester = -1;
    }

    public Course(String title) {
        this.id = ++courses_num;
        this.title = title;
        this.semester = -1;
    }

    public Course(int semester) {
        this.id = ++courses_num;
        this.title = "Unknown";
        this.semester = semester;
    }

    public Course(String title, int semester) {
        this.id = ++courses_num;
        this.title = title;
        this.semester = semester;
    }
    
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String new_title) {
        this.title = new_title;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int new_semester) {
        this.semester = new_semester;
    }

    @Override
    public String toString() {
        return "course " + id + ": " + title + ", semester " + semester;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        Course other_course = (Course) other;
        return  this.id == other_course.id &&
                this.title == other_course.title &&
                this.semester == other_course.semester;
    }

    @Override
    public int hashCode() {
        
        final int KEY = 52;
        
        // simple multiplication...
        return KEY * getId();
    }
}
