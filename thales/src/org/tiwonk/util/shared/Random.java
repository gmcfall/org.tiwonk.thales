package org.tiwonk.util.shared;

import org.tiwonk.util.client.api.GwtRandomGenerator;
import org.tiwonk.util.server.api.ServerRandomGenerator;

/**
 * A convenience class for accessing random values of various types.
 * This class provides an abstraction that works in GWT clients and
 * also on the server side.
 * <p>
 * Applications are strongly encouraged to set an underlying generator,
 * either {@link GwtRandomGenerator} or
 * {@link ServerRandomGenerator}, depending on whether the application is
 * running in the browser or server-side.
 * </p>
 * The default implementation uses {@link SimpleRNG}, which is suitable
 * for testing, but not an especially high quality random number generator.
 * 
 * @author gmcfall
 *
 */
public class Random {
  
  private static RandomGenerator generator;
  
  /**
   * Returns true if this api has been initialized with an underlying RandomGenerator.
   */
  public static boolean hasGenerator() {
    return generator != null;
  }
  
  /**
   * Returns the underlying RandomGenerator that will be used to generate
   * the random values that are available from this class. If an underlying
   * generator does not exist, a SimpleRNG generator will created and held
   * internally as a side-effect.
   */
  public static RandomGenerator getGenerator() {
    if (generator == null) {
      generator = new SimpleRNG();
    }
    return generator;
  }

  /**
   * Sets the underlying RandomGenerator that will be used to generate
   * the random values that are available from this class.
   */
  public static void setGenerator(RandomGenerator generator) {
    Random.generator = generator;
  }
  
  public static int nextInt() {
    return getGenerator().nextInt();
  }
  
  public static int nextInt(int limit) {
    return getGenerator().nextInt(limit);
  }
  
  public static double nextDouble() {
    return getGenerator().nextDouble();
  }
  
  public static String nextString(int length) {
    return getGenerator().nextString(length);
  }
  
  public static String nextUid() {
    return getGenerator().nextUid();
  }

}
