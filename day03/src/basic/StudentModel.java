package basic;

//Model a Student class (name, roll no, marks; constructor; display() method);
// create and display 3 objects

public class StudentModel {

    String name;
    int roll;
    int marks;

    StudentModel(String name , int roll , int marks){

        this.name = name;
        this.roll = roll;
        this.marks = marks;
    }

    void display(){
        System.out.println(name);
        System.out.println(roll);
        System.out.println(marks);
    }

    public static void main(String[] args) {

        StudentModel s1 = new StudentModel("anurag",101,87);
        StudentModel s2 = new StudentModel("ayush",102,83);
        StudentModel s3 = new StudentModel("aryan",103,85);

        s1.display();
        s2.display();
        s3.display();

    }




}
