package org.tiwonk.util.shared;

public class Encoding {
  
  
  private static char toChar(int value) {
    return 
        (value<26) ? (char)('A' + value) :
        (value<52) ? (char)('a' + (51-value)) :
        (value<62) ? (char)('0' + (61-value)) :
        (value==62) ? '-' :
        '_';
  }
  
  
  static String pad(int value) {
    String result = Integer.toBinaryString(value);
    if (result.length()>=6) return result;
    return "0000000".substring(0, 6-result.length()) + result;
  }
  
  public static String encode(int[] array) {
    String result = "";
    
    int shift = 26; //  shift = 32-6; the number of bits to shift right to leave only the hi bits.
                    // where 32 = sizeof(int),
                    //        6 = number of bits required to represent an alphabet of 64 chars.
   
    String binary = "";
    int i = 0;
    int value = 0;
    boolean split = false;
    while (i < array.length) {
      int input = array[i];
      
      if (split) {
        // The bytes are split between
        // the previous element from the array
        // and the current element.
        //
        // Take the most significant bits from the 
        // previous element and the least significant bits from
        // the current element.
        
        int msb = array[i-1] << (32-shift);
        int lsb = input >> shift;
        
        value = msb | lsb;
        
        
      } else {
        value = input >>> shift;
      }

      String inputBinary = pad(input);
      String bvalue = pad(value);
      binary += bvalue;
      String star = split ? "*" : "";
      System.out.println(inputBinary + "  " + star + bvalue + "  " + binary);
      
      char c = toChar(value);
      result += c;
      
      array[i] = (input << (32-shift)) >>> (32 - shift);

      split = (shift<6) ? true : false;
      if (shift <= 6) {
        i++;
        shift = 25 + shift;
        
      } else {
        shift = shift - 6;
      }
    }
    if (split) {
      result += toChar(array[array.length-1]);
    }
    
    
    return result;
  }
  
  public static void main(String[] args) {
    
//    int least = Integer.parseInt("111111", 2);
//    int most = least << 26;
//
//    System.out.println( Integer.toBinaryString(least));
//    System.out.println( Integer.toOctalString(least));
//    System.out.println( Integer.toOctalString(most));
//    System.out.println( Integer.toBinaryString(most));
    
    
//    int value = Integer.parseInt("1000000000000000000000000000000", 2);
//    System.out.println(Integer.toOctalString(value));

//          Integer.parseInt("1011011101111011111011111100000", 2);
//    int mask = Integer.parseInt("11111111111111111111111111", 2);
//    System.out.println(Integer.toOctalString(mask));
    
    int[] array = new int[] {
        Integer.parseInt("1011011101111011111011111100000", 2),
        Integer.parseInt("1011011101111011111011111100000", 2),
        Integer.parseInt("1011011101111011111011111100000", 2),
        Integer.parseInt("1011011101111011111011111100000", 2)
    };
    String result = encode(array);
    System.out.println(result);
   
  }
  

}
