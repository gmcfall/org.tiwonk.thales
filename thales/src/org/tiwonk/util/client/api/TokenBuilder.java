package org.tiwonk.util.client.api;

/**
 * A utility that can be used to build tokens.
 * @author gmcfall
 *
 */
public class TokenBuilder {

  static final String DELIM = ",";
  static final String ESCAPE_DELIM = "&#44;";
  static final String EQUALS = "=";
  static final String ESCAPE_EQUALS = "&#61;";
  
  private StringBuilder builder = new StringBuilder();
  
  public void append(String name, String value) {
    if (builder.length()>0) {
      builder.append(DELIM);
    }
    builder.append(name);
    builder.append(EQUALS);
    builder.append(escape(value));
  }
  
  private String escape(String value) {
    
    return value.replace(DELIM, ESCAPE_DELIM).replace(EQUALS, ESCAPE_EQUALS);
  }
  
  public void append(String name, boolean value) {
    if (value) {
      if (builder.length()>0) {
        builder.append(DELIM);
      }
      builder.append(name);
    }
  }
  
  public String toString() {
    return builder.toString();
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