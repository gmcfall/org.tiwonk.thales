package org.tiwonk.util.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * A widget which displays a status message.  This is typically used
 * to display error messages, or a "Loading..." message.
 * This widget is injected into a document and then used multiple times.
 * When it is not currently displaying a message, the widget is hidden
 * (technically, it has CSS display="none").
 * 
 * @author gmcfall
 *
 */
public interface StatusMessageView extends IsWidget {
  
  /**
   * Ensure that this StatusMessageView has been added to the current
   * HTML document.  It is safe to call this method multiple times;
   * the StatusMessageView will be added to the document only once.
   */
  void ensureInjected();
  
  /**
   * Display the given message.  After a certain amount of time, the
   * message will dissolve.  The amount of time that the message
   * remains on the page is configured by the underlying implementation.
   */
  void show(String message);
  
  /**
   * Explicitly hide the currently displayed message (if any).
   */
  void hide();

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