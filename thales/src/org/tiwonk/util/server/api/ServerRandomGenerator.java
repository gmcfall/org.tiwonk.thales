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
