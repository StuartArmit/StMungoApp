package com.stmungo.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.stmungo.client.gui.MainGUI;
import com.stmungo.client.model.TranslatorChoice;
import com.stmungo.client.model.TranslatorMain;
import com.stmungo.client.model.TranslatorProtocol;
import com.stmungo.client.model.TranslatorRole;
import com.stmungo.client.model.TranslatorTypestate;
import com.stmungo.client.model.ValidatorChoice;
import com.stmungo.client.model.ValidatorMain;
import com.stmungo.client.model.ValidatorProtocol;
import com.stmungo.client.model.ValidatorRole;
import com.stmungo.client.model.ValidatorTypestate;
import com.stmungo.client.model.TranslatorScribbleRole;
import com.stmungo.client.model.TranslatorScribble;


public class ServiceClientImp implements ServiceClientInt {

	private ServiceAsync service;
	private MainGUI maingui;

	public ServiceClientImp (String url) {
		this.service = GWT.create(Service.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		
		this.maingui = new MainGUI(this);
	}
	
	public MainGUI getMainGUI() {
		return this.maingui;
	}
	
	@Override
	public void getTranslatorScribbleRole(String textScr) {
		this.service.getTranslatorScribbleRole(textScr, new DefaultCallback());
	}
	
	@Override
	public void getTranslatorScribble(String textScr) {
		this.service.getTranslatorScribble(textScr, new DefaultCallback());
	}
	
	@Override
	public void getTranslatorMain(String text) {
		this.service.getTranslatorMain(text, new DefaultCallback());	
	}
	@Override
	public void getValidatorMain(String vText) {
		this.service.getValidatorMain(vText, new DefaultCallback());	
	}
	@Override
	public void getTranslatorRole(String text) {
		this.service.getTranslatorRole(text, new DefaultCallback());
	}
	@Override
	public void getValidatorRole(String vText) {
		this.service.getValidatorRole(vText, new DefaultCallback());	
	}
	@Override
	public void getTranslatorProtocol(String text) {
		this.service.getTranslatorProtocol(text, new DefaultCallback());
	}	
	@Override
	public void getValidatorProtocol(String vText) {
		this.service.getValidatorProtocol(vText, new DefaultCallback());	
	}
	@Override
	public void getTranslatorChoice(String text) {
		this.service.getTranslatorChoice(text, new DefaultCallback());
	}	
	@Override
	public void getValidatorChoice(String vText) {
		this.service.getValidatorChoice(vText, new DefaultCallback());	
	}
	@Override
	public void getTranslatorTypestate(String text) {
		this.service.getTranslatorTypestate(text, new DefaultCallback());
	}
	@Override
	public void getValidatorTypestate(String vText) {
		this.service.getValidatorTypestate(vText, new DefaultCallback());	
	}


	private class DefaultCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			System.out.println("An Error Has Occured");			
		}

		@Override
		public void onSuccess(Object result) {
			System.out.println("Response Received")	;
			
			if(result instanceof TranslatorScribbleRole) {
			TranslatorScribbleRole outputResult = (TranslatorScribbleRole) result;
			maingui.updaterScrRole(outputResult);
			}
			if(result instanceof TranslatorScribble) {
			TranslatorScribble outputResult = (TranslatorScribble) result;
			maingui.updaterScr(outputResult);
			}
			if(result instanceof TranslatorMain) {
			TranslatorMain outputResult = (TranslatorMain) result;
			maingui.updaterMain(outputResult);
			TranslatorRole outputResult1 = (TranslatorRole) result;
			maingui.updaterRole(outputResult1);

			}
			if(result instanceof ValidatorMain) {
			ValidatorMain outputResult = (ValidatorMain) result;
			maingui.updaterVMain(outputResult);
			}
			if(result instanceof TranslatorRole) {
			TranslatorRole outputResult = (TranslatorRole) result;
			maingui.updaterRole(outputResult);
			}
			if(result instanceof ValidatorRole) {
			ValidatorRole outputResult = (ValidatorRole) result;
			maingui.updaterVRole(outputResult);
			}
			if(result instanceof TranslatorProtocol) {
			TranslatorProtocol outputResult = (TranslatorProtocol) result;
			maingui.updaterProtocol(outputResult);
			}
			if(result instanceof ValidatorProtocol) {
			ValidatorProtocol outputResult = (ValidatorProtocol) result;
			maingui.updaterVProtocol(outputResult);
			}
			if(result instanceof TranslatorChoice) {
			TranslatorChoice outputResult = (TranslatorChoice) result;
			maingui.updaterChoice(outputResult);
			}
			if(result instanceof ValidatorChoice) {
			ValidatorChoice outputResult = (ValidatorChoice) result;
			maingui.updaterVChoice(outputResult);
			}
			if(result instanceof TranslatorTypestate) {
			TranslatorTypestate outputResult = (TranslatorTypestate) result;
			maingui.updaterTypestate(outputResult);
			}
			if(result instanceof ValidatorTypestate) {
			ValidatorTypestate outputResult = (ValidatorTypestate) result;
			maingui.updaterVTypestate(outputResult);
			}

			}
			
		}

}

