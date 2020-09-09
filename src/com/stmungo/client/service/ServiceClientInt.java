package com.stmungo.client.service;


public interface ServiceClientInt {
	void getTranslatorScribbleRole(String textScr);
	void getTranslatorScribble(String textScr);
	void getTranslatorMain(String text);
	void getValidatorMain(String vText);
	void getTranslatorRole(String text);
	void getValidatorRole(String vText);
	void getTranslatorProtocol(String text);
	void getValidatorProtocol(String vText);
	void getTranslatorChoice(String text);
	void getValidatorChoice(String vText);
	void getTranslatorTypestate(String text);
	void getValidatorTypestate(String vText);

}
