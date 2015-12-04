public class GCD {
  public static void main(String[] args) {
  		
  }
  
  public static void gcd(int a,int b){
    if(a==0||b==0){
        return a+b;
    }
    return gcd(b,a%b);
  }
  

}
