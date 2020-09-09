package com.stmungo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.stmungo.client.service.ServiceClientImp;

public class StMungoApp implements EntryPoint {

public void onModuleLoad() {
	ServiceClientImp clientImp = new ServiceClientImp(GWT.getModuleBaseURL() + "service");
	RootPanel.get().add(clientImp.getMainGUI());
}
}
