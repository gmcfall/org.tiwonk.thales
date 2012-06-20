package org.tiwonk.util.client.api;

import org.tiwonk.util.shared.RandomGenerator;

import com.google.gwt.user.client.Random;

public class GwtRandomGenerator implements RandomGenerator {

  
  public GwtRandomGenerator() { 
  }
  
  @Override
  public int nextInt() {
    return Random.nextInt();
  }

  @Override
  public double nextDouble() {
    return Random.nextDouble();
  }

  @Override
  public String nextUid() {
    return nextString(32);
  }


  @Override
  public int nextInt(int limit) {
    int result = (int)(nextDouble() * (limit+1));
    if (result > limit) {
      result = limit;
    }
    return result;
  }

  @Override
  public String nextString(int length) {
    StringBuilder builder = new StringBuilder(length);
    
    while (builder.length() < length) {
      builder.append(Integer.toHexString(nextInt()));
    }

    String value = builder.toString();
    return value.substring(0, length);
  }

}
