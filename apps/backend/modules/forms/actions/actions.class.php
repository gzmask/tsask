<?php

require_once dirname(__FILE__).'/../lib/formsGeneratorConfiguration.class.php';
require_once dirname(__FILE__).'/../lib/formsGeneratorHelper.class.php';

/**
 * forms actions.
 *
 * @package    authority
 * @subpackage forms
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class formsActions extends autoFormsActions
{
  public function executeNew(sfWebRequest $request)
  {
  }

  public function executeSave(sfWebRequest $request)
  {
    $this->form_name=$this->getRequestParameter('form_name');
    $this->form_content=$this->getRequestParameter('form_content');
    $this->form_published=$this->getRequestParameter('form_published');
    $my_form=new saForms();
    $my_form['form_name']=$this->form_name;
    $my_form['form_content']=$this->form_content;
    $my_form['form_published']=$this->form_published;
    $my_form->save();
    $this->logMessage('$this->form_content  '.$this->form_content);
    $this->logMessage('$this->form_published  '.$this->form_published);
    $this->forward('forms','index');
  }

  public function executeEdit(sfWebRequest $request)
  {
    $this->sa_forms = $this->getRoute()->getObject();
    $this->form = $this->configuration->getForm($this->sa_forms);
    $this->form_name=$this->sa_forms['form_name'];
    $this->form_content=$this->sa_forms['form_content'];
    $this->form_published=$this->sa_forms['form_published'];
  }

  public function executeUpdate(sfWebRequest $request)
  {
    $this->form_name=$this->getRequestParameter('form_name');
    $this->form_content=$this->getRequestParameter('form_content');
    $this->form_published=$this->getRequestParameter('form_published');
    $this->sa_forms = $this->getRoute()->getObject();
    $this->form = $this->configuration->getForm($this->sa_forms);
	$this->sa_forms['form_name']=$this->form_name;
	$this->sa_forms['form_content']=$this->form_content;
	$this->sa_forms['form_published']=$this->form_published;
	$this->sa_forms->save();
    $this->setTemplate('edit');
  }

  public function executePreview(sfWebRequest $request)
  {
	$redirectUrl='/forms/show/id/'.$this->getRequestParameter('id');
	$this->redirect($redirectUrl);
  }

  public function postExecute()
  {
		$this->getResponse()->setSlot('tab_forms', '_on');
  }
}
