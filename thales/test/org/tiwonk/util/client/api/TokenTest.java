package org.tiwonk.util.client.api;

import static org.junit.Assert.*;

import org.junit.Test;

public class TokenTest {

  @Test
  public void test() {
    TokenBuilder builder = new TokenBuilder();
    
    builder.append("name", "john");
    builder.append("species", "homo sapiens");
    builder.append("isAuthor", false);
    builder.append("isPublisher", true);
    builder.append("hasEquals", "roses=red");
    builder.append("hasComma", "one, two, three");
    
    String text = builder.toString();
    String expected =
        "name=john,species=homo sapiens,isPublisher,hasEquals=roses&#61;red,hasComma=one&#44; two&#44; three";
    
    assertEquals(expected, text);
    
    Token token = new Token(text);
    
    assertEquals("john", token.get("name"));
    assertEquals("homo sapiens", token.get("species"));
    assertEquals(false, token.getBoolean("isAuthor"));
    assertEquals(true, token.getBoolean("isPublisher"));
    assertEquals("roses=red", token.get("hasEquals"));
    assertEquals("one, two, three", token.get("hasComma"));
    
    
    System.out.println(text);
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
