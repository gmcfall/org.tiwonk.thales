package org.tiwonk.util.server.api;

import java.util.Date;
import java.util.Random;

import org.tiwonk.util.shared.RandomGenerator;

/**
 * A random number generator suitable for use on the server-side.
 * This implementation is backed by java.util.Random.
 * 
 * @author gmcfall
 *
 */
public class ServerRandomGenerator implements RandomGenerator {
  
  private Random random;
  
  

  public ServerRandomGenerator(Random random) {
    this.random = random;
  }
  
  public ServerRandomGenerator() {    
    this(new Random(new Date().getTime()));
  }

  @Override
  public int nextInt() {
    return random.nextInt();
  }

  @Override
  public int nextInt(int limit) {
    return random.nextInt(limit);
  }

  @Override
  public double nextDouble() {
    return random.nextDouble();
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

  @Override
  public String nextUid() {
     return nextString(32);
  }

}
