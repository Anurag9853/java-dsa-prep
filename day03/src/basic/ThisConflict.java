package basic;

//Demonstrate this resolving a constructor parameter/field naming conflict

public class ThisConflict {

    String name = "Anurag" ;

    ThisConflict(String name){
        this.name = name;
    }

    void display(){
        System.out.println(name);
    }

    public static void main(String[] args) {

        ThisConflict t = new ThisConflict("Aryan");

        t.display();
    }



}
