/**
 * Created by Abhi on 12/4/14.
 */
public class Course {
    private String courseName;
    private String courseID;
    private boolean open;
    private double numCredits;
    private int capacity;
    private Course[] schedule;
    private int numCources;
    int numStudents;


    public Course(String courseName,String courseID, double credits){
        this.courseName = courseName;
        this.courseID = courseID;
        this.numCredits = credits;
        this.open = true;
        this.capacity = 100;
        schedule = new Course[20];
    }


    public void setCourseName(String name){
        this.courseName = name;
    }
    public String getCourseName(){
        return this.courseName;
    }
    public void setCourseID(String id){
        this.courseID = id;
    }
    public String getCourseID(){
        return courseID;
    }

    public void setNumStudents(int students){
        this.numStudents = students;
    }
    public int getNumStudents(){
        return numStudents;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public int getCapacity(){
        return capacity;
    }

    public boolean isFull(){
        if(this.numStudents >= this.capacity){
            return true;
        }
        return false;
    }

    public boolean equals(Course o){
        if(this.courseName.equals(o.courseName))
            return true;
        return false;
    }

    public void setNumCredits(double credits){
        this.numCredits = credits;
    }
    public double getNumCredits(){
        return numCredits;
    }


    public boolean register(Student c){
        numStudents++;
    }





}
