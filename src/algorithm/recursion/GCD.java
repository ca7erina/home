public class GCD {
  public static void main(String[] args) {
  		
  }
  
  public static void gcd(int a,int b){
    if(a==0||b==0){
        return a+b;
    }
    return gcd(b,a%b);
  }
  /**
   * gcd(6, 4)
   * tryDivisor(6, 4, 4)
  *   tryDivisor(6, 4, 3)
   * tryDivisor(6, 4, 2)
   * */
  int tryDivisor(int m, int n, int g) {
   if (((m % g) == 0) && ((n % g) == 0))
      return g;
   else
      return tryDivisor(m, n, g - 1);
}

}
