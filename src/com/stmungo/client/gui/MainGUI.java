package com.stmungo.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
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
import com.stmungo.client.service.ServiceClientImp;

	public class MainGUI extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	private TextArea textInputScr;
	private TextArea textInputStM;
	private Button buttonStM;
	private Button buttonScr;
	private Button buttonVOne;
	private Button buttonVTwo;
	private Button buttonVThree;
	private Button buttonVFour;
	private Button buttonVFive;
	private Button globalOne;
	private Button globalTwo;
	private Button globalThree;
	private Button globalFour;
	private Button globalFive;
	private Label labelVOne;
	private Label labelVTwo;
	private Label labelVThree;
	private Label labelVFour;
	private Label labelVFive;
	private Label globalP;
	private Label localP;
	private Label main;
	private Label role;
	private Label protocol;
	private Label choice;
	private Label typestate;
	private TextArea textOutputOne;
	private TextArea textOutputTwo;
	private TextArea textOutputThree;
	private TextArea textOutputFour;
	private TextArea textOutputFive;
	private ServiceClientImp serviceImp;
	
	
	public MainGUI(ServiceClientImp serviceImp){	
	this.serviceImp = serviceImp;
	initWidget(this.vPanel);
	this.vPanel.setSpacing(1);

    this.globalP = new Label("Global Protocol");

    this.textInputScr = new TextArea();
    this.textInputScr.setPixelSize(600, 250);
    
    this.globalOne = new Button("");
    globalOne.setVisible(false);
    globalOne.addClickHandler(new ButtonScrProjOneClickHandler());

    this.globalTwo = new Button("");
    globalTwo.setVisible(false);
    globalTwo.addClickHandler(new ButtonScrProjTwoClickHandler());

    this.globalThree = new Button("");
    globalThree.setVisible(false);
    globalThree.addClickHandler(new ButtonScrProjThreeClickHandler());

    this.globalFour = new Button("");
    globalFour.setVisible(false);
    globalFour.addClickHandler(new ButtonScrProjFourClickHandler());

    this.globalFive = new Button("");
    globalFive.setVisible(false);
    globalFive.addClickHandler(new ButtonScrProjFiveClickHandler());

    
    
    String scrButton = ("Translate");
    this.buttonScr = new Button(scrButton);
    buttonScr.addClickHandler(new ButtonScrClickHandler());
	
    this.localP = new Label("Local Protocol");
    this.textInputStM = new TextArea();
    this.textInputStM.setPixelSize(600, 250);
    
    String stMButton = ("Translate");
    this.buttonStM = new Button(stMButton);
    buttonStM.addClickHandler(new ButtonStMClickHandler());
    
    this.main = new Label("Typestate Main Method");
    this.textOutputOne = new TextArea();
    this.textOutputOne.setPixelSize(600, 500);
    
    String validateOne = ("Validate");
    this.buttonVOne = new Button(validateOne);
    buttonVOne.addClickHandler(new ButtonValidatorM());
    
    this.labelVOne = new Label();
    
    this.role = new Label("Typestate Role Method");
    this.textOutputTwo = new TextArea();
    this.textOutputTwo.setPixelSize(600, 500);
    
    String validateTwo = ("Validate");
    this.buttonVTwo = new Button(validateTwo);
    buttonVTwo.addClickHandler(new ButtonValidatorR());
    
    this.labelVTwo = new Label();
    this.protocol = new Label("Typestate Protocol");
    this.textOutputThree = new TextArea();
    this.textOutputThree.setPixelSize(600, 500);

    String validateThree = ("Validate");
    this.buttonVThree = new Button(validateThree);
    buttonVThree.addClickHandler(new ButtonValidatorP());
    
    this.labelVThree = new Label();
    this.choice = new Label("Typestate Choices");
    this.textOutputFour = new TextArea();
    this.textOutputFour.setPixelSize(600, 250);
    
    String validateFour = ("Validate");
    this.buttonVFour = new Button(validateFour);
    buttonVFour.addClickHandler(new ButtonValidatorC());
    
    this.labelVFour = new Label();
    this.typestate = new Label("Typestate");
    this.textOutputFive = new TextArea();
    this.textOutputFive.setPixelSize(600, 250);
    
    String validateFive = ("Validate");
    this.buttonVFive = new Button(validateFive);
    buttonVFive.addClickHandler(new ButtonValidatorT());
    
    this.labelVFive = new Label();
    
    //vPanel.add(globalP);
	//vPanel.add(buttonScr);
	//vPanel.add(textInputScr);
	//vPanel.add(globalOne);
	//vPanel.add(globalTwo);
	//vPanel.add(globalThree);
	//vPanel.add(globalFour);
	//vPanel.add(globalFive);
	vPanel.add(localP);
	vPanel.add(buttonStM);
	vPanel.add(textInputStM);
	vPanel.add(main);
	vPanel.add(buttonVOne);
	vPanel.add(labelVOne);
	vPanel.add(textOutputOne);
	vPanel.add(role);
	vPanel.add(buttonVTwo);
	vPanel.add(labelVTwo);
	vPanel.add(textOutputTwo);
	vPanel.add(protocol);
	vPanel.add(buttonVThree);
	vPanel.add(labelVThree);
	vPanel.add(textOutputThree);
	vPanel.add(choice);
	vPanel.add(buttonVFour);
	vPanel.add(labelVFour);
	vPanel.add(textOutputFour);
	vPanel.add(typestate);
	vPanel.add(buttonVFive);
	vPanel.add(labelVFive);
	vPanel.add(textOutputFive);

}
	
	
	public void updaterScrRole(TranslatorScribbleRole ouputResult) {
		String[] split = ouputResult.getText().split("\\s+");
		int i =0;
		if(i< split.length){
		globalOne.setText(split[i]);
		globalOne.setVisible(true);
		i++;
		}
		if(i< split.length){
		globalTwo.setText(split[i]);
		globalTwo.setVisible(true);
		i++;
		}
		if(i< split.length){
		globalThree.setText(split[i]);
		globalThree.setVisible(true);
		i++;
		}
		if(i< split.length){
		globalFour.setText(split[i]);
		globalFour.setVisible(true);
		i++;
		}
		if(i< split.length){
		globalFive.setText(split[i]);
		globalFive.setVisible(true);
		}
	}
	
	public void updaterScr(TranslatorScribble ouputResult) {
		this.textInputStM.setText(ouputResult.getText());
	}
	
	public void updaterMain(TranslatorMain ouputResult) {
		this.textOutputOne.setText(ouputResult.getText());
	}
	public void updaterVMain(ValidatorMain ouputResult) {
		this.labelVOne.setText(ouputResult.getText());
	}
	public void updaterRole(TranslatorRole ouputResult) {
		this.textOutputTwo.setText(ouputResult.getText());
	}
	public void updaterVRole(ValidatorRole ouputResult) {
		this.labelVTwo.setText(ouputResult.getText());
	}
	public void updaterProtocol(TranslatorProtocol ouputResult) {
		this.textOutputThree.setText(ouputResult.getText());
	}
	public void updaterVProtocol(ValidatorProtocol ouputResult) {
		this.labelVThree.setText(ouputResult.getText());
	}
	public void updaterChoice(TranslatorChoice ouputResult) {
		this.textOutputFour.setText(ouputResult.getText());
	}
	public void updaterVChoice(ValidatorChoice ouputResult) {
		this.labelVFour.setText(ouputResult.getText());
	}
	public void updaterTypestate(TranslatorTypestate ouputResult) {
		this.textOutputFive.setText(ouputResult.getText());
	}
	public void updaterVTypestate(ValidatorTypestate ouputResult) {
		this.labelVFive.setText(ouputResult.getText());
	}


private class ButtonStMClickHandler implements ClickHandler{
@Override
public void onClick(ClickEvent event) {
	String stMInput = textInputStM.getText();
	serviceImp.getTranslatorMain(stMInput);
	serviceImp.getTranslatorRole(stMInput);
	serviceImp.getTranslatorProtocol(stMInput);
	serviceImp.getTranslatorChoice(stMInput);
	serviceImp.getTranslatorTypestate(stMInput);
	labelVOne.setText(null);
	labelVTwo.setText(null);
	labelVThree.setText(null);
	labelVFour.setText(null);
	labelVFive.setText(null);	
}
}
private class ButtonValidatorM implements ClickHandler{
@Override
public void onClick(ClickEvent event) {
	serviceImp.getValidatorMain(textOutputOne.getText());
}
}
private class ButtonValidatorR implements ClickHandler{
@Override
public void onClick(ClickEvent event) {
	serviceImp.getValidatorRole(textOutputTwo.getText());
}
}
private class ButtonValidatorP implements ClickHandler{
@Override
public void onClick(ClickEvent event) {
	serviceImp.getValidatorProtocol(textOutputThree.getText());
}
}
private class ButtonValidatorC implements ClickHandler{
@Override
public void onClick(ClickEvent event) {
	serviceImp.getValidatorChoice(textOutputFour.getText());
}
}
private class ButtonValidatorT implements ClickHandler{
@Override
public void onClick(ClickEvent event) {
	serviceImp.getValidatorTypestate(textOutputFive.getText());
}
}
private class ButtonScrClickHandler implements ClickHandler{
@Override
public void onClick(ClickEvent event) {
	String scrInput = textInputScr.getText();
	serviceImp.getTranslatorScribbleRole(scrInput);
	labelVOne.setText(null);
	labelVTwo.setText(null);
	labelVThree.setText(null);
	labelVFour.setText(null);
	labelVFive.setText(null);
}
}

private class ButtonScrProjOneClickHandler implements ClickHandler{

@Override
public void onClick(ClickEvent event) {
	String scrInput = globalOne.getText();
	serviceImp.getTranslatorScribble(scrInput);
}
}
private class ButtonScrProjTwoClickHandler implements ClickHandler{
@Override
public void onClick(ClickEvent event) {
	String scrInput = globalTwo.getText();
	serviceImp.getTranslatorScribble(scrInput);
}
}
private class ButtonScrProjThreeClickHandler implements ClickHandler{
@Override
public void onClick(ClickEvent event) {
	String scrInput = globalThree.getText();
	serviceImp.getTranslatorScribble(scrInput);
}
}
private class ButtonScrProjFourClickHandler implements ClickHandler{
@Override
public void onClick(ClickEvent event) {
	String scrInput = globalFour.getText();
	serviceImp.getTranslatorScribble(scrInput);
}
}
private class ButtonScrProjFiveClickHandler implements ClickHandler{
@Override
public void onClick(ClickEvent event) {
	String scrInput = globalFive.getText();
	serviceImp.getTranslatorScribble(scrInput);
}
}
}
