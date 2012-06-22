package org.tiwonk.util.shared;

/**
 * An interface for creating random numbers.
 * This interface provides an abstraction so that application code
 * can use an appropriate implementation depending on whether it is
 * running server-side or client-side.
 * 
 * @author gmcfall
 *
 */
public interface RandomGenerator {
  
  /**
   * Returns the next random integer.
   */
  public int nextInt();
  
  /**
   * Returns the next random integer in the range
   * [0, limit].
   */
  public int nextInt(int limit);
  
  /**
   * Returns the next random double in the range [0, 1]
   */
  public double nextDouble();
  
  
  /**
   * Returns a random string of the specified length
   * drawn from an alphabet consisting of hex characters
   *  (0, 1, 2, 3, 4, 5, 6, 7, 8, 9, a, b, c, d, e, f).
   * Thus, the number of random bits in the resulting string is
   * 16*length.
   */
  public String nextString(int length);
  
  /**
   * A convenience method equivalent to <code>nextString(32)</code>
   * which is suitable for use as a randomly defined unique identifier.
   * It contains 128 random bits.  The probability of a collision with
   * another randomly generator unique identifier is negligible.
   */
  public String nextUid();

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