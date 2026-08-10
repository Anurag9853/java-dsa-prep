package basics;

class Number{
    int x = 10;

}
public class Without_static {
    public static void main(String[] args){

        Number value = new Number();
        System.out.println(value.x);
    }
}
