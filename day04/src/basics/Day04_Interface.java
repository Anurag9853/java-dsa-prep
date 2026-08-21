package basics;

 interface shape {

     double area();
 }


 class circle implements shape{

     double r;
     circle(double r){
         this.r = r;
     }
     @Override
     public double area(){
         return 3.14 *  r * r;
     }
 }

 class rectangle implements  shape{

     double l ;
     double b;
     rectangle(double l , double b){
         this.l = l;
         this.b=b;
     }
     @Override
     public  double area(){
         return l*b;
     }
 }

 public class Day04_Interface {
     public static void main(String[] args) {

         shape c = new circle(5.0);
         shape r = new rectangle(5.0,8.0);

         System.out.println(c.area());
         System.out.println(r.area());
     }
}
