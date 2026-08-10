package basics;

public class Grade_system {
    public static void main(String[] args){

        int marks = 0;
        String grade = switch(marks/10){
            case 10,9 -> "A+";
            case 8 -> "A";
            case 7 -> "B+";
            case 6 -> "B";
            case 5 -> "C";
            case 4 -> "D";
            case 3,2,1,0 -> "Fail";
            default -> "Invalid";
        };

        System.out.println(grade);
    }
}
