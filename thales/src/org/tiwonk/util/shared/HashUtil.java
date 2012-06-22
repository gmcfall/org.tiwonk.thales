package org.tiwonk.util.shared;


/**
*
*  Secure Hash Algorithm (SHA1)
*  
*  Adapted from
*  http://www.webtoolkit.info/javascript-sha1.html
*
**/
public class HashUtil {

  static private int rotate_left(int n, int s) {
    int t4 = ( n<<s ) | (n>>>(32-s));
    return t4;
  }
  
  static private String cvt_hex(int val) {
    String str="";
    int i;
    int v;
 
    for( i=7; i>=0; i-- ) {
      v = (val>>>(i*4))&0x0f;
      str += Integer.toString(v, 16);
    }
    return str;
  };
 
  static private String Utf8Encode(String string) {
    string = string.replace("\\r\\n", "\n");
    String utftext = "";
 
    for (int n = 0; n < string.length(); n++) {
 
      char c = string.charAt(n);
 
      if (c < 128) {
        utftext += c;
      }
      else if((c > 127) && (c < 2048)) {
        utftext += ((c >> 6) | 192);
        utftext += ((c & 63) | 128);
      }
      else {
        utftext += ((c >> 12) | 224);
        utftext += (((c >> 6) & 63) | 128);
        utftext += ((c & 63) | 128);
      }
 
    }
 
    return utftext;
  };
  
  private static class IntArray {
    int initialSize;
    int[] array;
    int size=0;
    int maxSize;
    
    private IntArray(int initialSize) {
      this.initialSize = initialSize;
      array = new int[initialSize];  
      maxSize = 10*initialSize;
    }
    private int size() {
      return size;
    }
    private void push(int value) {
      if (size==array.length){
        int newSize = size + initialSize;
        if (newSize >= maxSize) {
          throw new RuntimeException("buffer overflow");
        }
        int[] newArray = new int[newSize];
        for (int i=0; i<size; i++) {
          newArray[i] = array[i];
        }
        array = newArray;
      }
      array[size++] = value;
    }
    
  }
  
  static public String SHA1(String msg) {
   
    int blockstart;
    int i, j;
    int msg_len = msg.length();
    int[] W = new int[80];
    int H0 = 0x67452301;
    int H1 = 0xEFCDAB89;
    int H2 = 0x98BADCFE;
    int H3 = 0x10325476;
    int H4 = 0xC3D2E1F0;
    int A, B, C, D, E;
    int temp;
   
    msg = Utf8Encode(msg);
   
   
    IntArray word_array = new IntArray(msg_len);
    
    for( i=0; i<msg_len-3; i+=4 ) {
      j = msg.charAt(i)<<24 | msg.charAt(i+1)<<16 |
      msg.charAt(i+2)<<8 | msg.charAt(i+3);
      word_array.push(j);
    }
   
    switch( msg_len % 4 ) {
      case 0:
        i = 0x080000000;
      break;
      case 1:
        i = msg.charAt(msg_len-1)<<24 | 0x0800000;
      break;
   
      case 2:
        i = msg.charAt(msg_len-2)<<24 | msg.charAt(msg_len-1)<<16 | 0x08000;
      break;
   
      case 3:
        i = msg.charAt(msg_len-3)<<24 | msg.charAt(msg_len-2)<<16 | msg.charAt(msg_len-1)<<8  | 0x80;
      break;
    }
   
    word_array.push(i);
   
    while( (word_array.size() % 16) != 14 ) word_array.push(0);
   
    word_array.push(msg_len>>>29);
    word_array.push((msg_len<<3)&0x0ffffffff );
   
    int[] array = word_array.array;
    
    for ( blockstart=0; blockstart<word_array.size; blockstart+=16 ) {
   
      for( i=0; i<16; i++ ) W[i] = array[blockstart+i];
      for( i=16; i<=79; i++ ) W[i] = rotate_left(W[i-3] ^ W[i-8] ^ W[i-14] ^ W[i-16], 1);
   
      A = H0;
      B = H1;
      C = H2;
      D = H3;
      E = H4;
   
      for( i= 0; i<=19; i++ ) {
        temp = (rotate_left(A,5) + ((B&C) | (~B&D)) + E + W[i] + 0x5A827999) & 0x0ffffffff;
        E = D;
        D = C;
        C = rotate_left(B,30);
        B = A;
        A = temp;
      }
   
      for( i=20; i<=39; i++ ) {
        temp = (rotate_left(A,5) + (B ^ C ^ D) + E + W[i] + 0x6ED9EBA1) & 0x0ffffffff;
        E = D;
        D = C;
        C = rotate_left(B,30);
        B = A;
        A = temp;
      }
   
      for( i=40; i<=59; i++ ) {
        temp = (rotate_left(A,5) + ((B&C) | (B&D) | (C&D)) + E + W[i] + 0x8F1BBCDC) & 0x0ffffffff;
        E = D;
        D = C;
        C = rotate_left(B,30);
        B = A;
        A = temp;
      }
   
      for( i=60; i<=79; i++ ) {
        temp = (rotate_left(A,5) + (B ^ C ^ D) + E + W[i] + 0xCA62C1D6) & 0x0ffffffff;
        E = D;
        D = C;
        C = rotate_left(B,30);
        B = A;
        A = temp;
      }
   
      H0 = (H0 + A) & 0x0ffffffff;
      H1 = (H1 + B) & 0x0ffffffff;
      H2 = (H2 + C) & 0x0ffffffff;
      H3 = (H3 + D) & 0x0ffffffff;
      H4 = (H4 + E) & 0x0ffffffff;
   
    }
   
    String result = cvt_hex(H0) + cvt_hex(H1) + cvt_hex(H2) + cvt_hex(H3) + cvt_hex(H4);
   
    return result;
   
  }

}

/*******************************************************************************
 * Copyright 2012 Gregory McFall
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

