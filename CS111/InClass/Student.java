/**
 * Created by Abhi on 12/4/14.
 */
public class Student {

    //define fields
    private double numcredits;
    private int gradYear;
    private String major;
    private String fname;
    private String lname;
    private int age;
    private String id;
    private Course[] schedule;
    private int numCources;

    //constructor
    public Student(String fname, String lname, String id){

        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.major = "undeclared";
        this.age = 17;
        this.gradYear = 2018;
        this.numcredits = 0;
    }

    public Student(String fname, String lname, String id, String major, double credits){
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.major = major;
        this.age = 17;
        this.gradYear = 2018;
        this.numcredits = credits;
    }

    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        if(age > 0)
            this.age = age;
    }

    public boolean addCource(Course c){
        if(numCources >= schedule.length)
            return false;
        if(c.isFull())
            return false;
        for(int i=0; i < numCources; i++){
            if(c.equals(schedule[i]))
                return false;
        }
        schedule[numCources] = c;
        numCources++;
        numcredits = c.getNumCredits() + numcredits;
        c.register(this);
        return true;
    }

    public String toString(){
        return lname + " " + fname + " " + id;
    }
}
