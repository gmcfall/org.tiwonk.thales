package org.tiwonk.util.client.ui.impl;

import org.tiwonk.util.client.ui.StatusMessageView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class StatusMessageImpl extends Composite implements StatusMessageView {

  private static final int DISPLAY_DURATION = 5000;
  private static final int FADE_DURATION = 3000;
  private static final int FADE_RATE = 75; // ms per opacity setting
  private static final double DELTA_OPACITY = ((double)FADE_RATE)/FADE_DURATION;
  
  private static StatusMessageImplUiBinder uiBinder = GWT
      .create(StatusMessageImplUiBinder.class);

  interface StatusMessageImplUiBinder extends
      UiBinder<Widget, StatusMessageImpl> {
  }

  @UiField HTMLPanel panel;
  @UiField Label label;
  
  private Timer timer;
  
  public StatusMessageImpl() {
    initWidget(uiBinder.createAndBindUi(this));
    panel.getElement().getStyle().setDisplay(Display.NONE);
  }

  @Override
  public void display(String message) {
    label.setText(message);
    label.getElement().getStyle().setOpacity(1);
    panel.getElement().getStyle().setDisplay(Display.BLOCK);
    if (timer != null) {
      timer.cancel();
    }
    timer = new DissolveTimer();
    timer.schedule(DISPLAY_DURATION);
  
    
  }

  @Override
  public void hide() {
    if (timer != null) {
      timer.cancel();
    }

    panel.getElement().getStyle().setDisplay(Display.NONE);
    
  }
  
  class DissolveTimer extends Timer {
    double opacity = 1;
    
    @Override
    public void run() {
      opacity -= DELTA_OPACITY;
      if (opacity <= 0) {
        hide();
      } else {
        label.getElement().getStyle().setOpacity(opacity);
        schedule(FADE_RATE);
      }
      
    }
    
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