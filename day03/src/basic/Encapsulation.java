package basic;

//bundling of methods and data and controlling the access to the data using access modifiers
public class Encapsulation {

    private double balance;

    Encapsulation(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        if(amount>0){
            balance += amount;
        }
        else {
            System.out.println("Invalid amount");
        }


    }

    public void withDraw(double amount){
        if(amount > 0 && amount <= balance){
            balance -= amount;
        }
        else{
            System.out.println("Not enough balance");
        }
    }

    public static void main(String[] args) {

        Encapsulation e = new Encapsulation(5000);
        double b = e.getBalance();
        System.out.println("Current amount:" + b);
        e.deposit(1000);
        b = e.getBalance();
        System.out.println("Updated balance:" + b);
        e.withDraw(4000);
        b = e.getBalance();
        System.out.println("Updated balance after withdraw:" + b);
    }
}
