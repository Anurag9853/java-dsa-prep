package basics;


import java.sql.SQLOutput;

public class switch_case {

    public static void main(String[] args){

        int day = 2;
        String dayName = switch(day){
            case 1-> "Monday";
            case 2-> "Tuesday";
            case 3-> "Wednesday";
            default -> "Invalid";

        };

        System.out.println(dayName);
    }

}
