package com.stmungo.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.stmungo.client.model.TranslatorChoice;
import com.stmungo.client.model.TranslatorMain;
import com.stmungo.client.model.TranslatorProtocol;
import com.stmungo.client.model.TranslatorRole;
import com.stmungo.client.model.TranslatorScribble;
import com.stmungo.client.model.TranslatorScribbleRole;
import com.stmungo.client.model.TranslatorTypestate;
import com.stmungo.client.model.ValidatorChoice;
import com.stmungo.client.model.ValidatorMain;
import com.stmungo.client.model.ValidatorProtocol;
import com.stmungo.client.model.ValidatorRole;
import com.stmungo.client.model.ValidatorTypestate;

@RemoteServiceRelativePath("service")
public interface Service extends RemoteService{
	TranslatorScribbleRole getTranslatorScribbleRole(String textScr);
	TranslatorScribble getTranslatorScribble(String textScr);
	TranslatorMain getTranslatorMain(String text);
	ValidatorMain getValidatorMain(String vText);
	TranslatorRole getTranslatorRole(String text);
	ValidatorRole getValidatorRole(String vText);
	TranslatorProtocol getTranslatorProtocol(String text);
	ValidatorProtocol getValidatorProtocol(String vText);
	TranslatorChoice getTranslatorChoice(String text);
	ValidatorChoice getValidatorChoice(String vText);
	TranslatorTypestate getTranslatorTypestate(String text);
	ValidatorTypestate getValidatorTypestate(String vText);

}
