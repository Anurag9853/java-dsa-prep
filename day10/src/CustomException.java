

public class CustomException {

    int marks;

    class InvalidMarksException extends Exception{

        public InvalidMarksException(String message){
            super(message);
        }

    }

    void getmarks(int marks) throws InvalidMarksException{

        if(marks < 0 || marks >100){
            throw new InvalidMarksException("Invalid marks");
        }

    }

    public static void main(String[] args) {

        CustomException c = new CustomException();
        try{
            c.getmarks(105);
        }catch (InvalidMarksException e){
            System.out.println("Invalid marks");
        }

    }


}
