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