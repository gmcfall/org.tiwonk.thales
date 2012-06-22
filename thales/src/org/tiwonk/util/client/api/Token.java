package org.tiwonk.util.client.api;

import java.util.HashMap;
import java.util.Map;

public class Token {
  private Map<String, String> map = new HashMap<String, String>();
  private static final String TRUE = "true";
  
  public Token(String token) {
    String[] lineList = token.split(TokenBuilder.DELIM);
    for (int i=0; i<lineList.length; i++) {
      String[] data = lineList[i].split(TokenBuilder.EQUALS);
      if (data.length==1) {
        map.put(data[0], TRUE);
      } else {
        map.put(data[0], unescape(data[1]));
      }
    }
  }
  
  private String unescape(String value) {
    return value
        .replace(TokenBuilder.ESCAPE_DELIM, TokenBuilder.DELIM)
        .replace(TokenBuilder.ESCAPE_EQUALS, TokenBuilder.EQUALS);
  }
  
  public String get(String key) {
    return map.get(key);
  }
  
  public boolean getBoolean(String key) {
    return TRUE.equals(get(key));
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