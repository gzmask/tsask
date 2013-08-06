<?php

require_once dirname(__FILE__).'/../lib/ordersGeneratorConfiguration.class.php';
require_once dirname(__FILE__).'/../lib/ordersGeneratorHelper.class.php';

/**
 * orders actions.
 *
 * @package    authority
 * @subpackage orders
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class ordersActions extends autoOrdersActions
{

  /*
  public function executeIndex(sfWebRequest $request)
  {
    parent::executeIndex($request);
    $this->sa_orders->setQuery(Doctrine_Core::getTable('sa_orders')->getListeByState('Published'));
  }*/


  public function executeView(sfWebRequest $request)
  {
    $this->sa_orders = $this->getRoute()->getObject();
    $this->form = $this->configuration->getForm($this->sa_orders);  
    $this->order_content=$this->sa_orders['order_content'];	
    $this->form_name=$this->sa_orders['form_name'];	
  }   

  public function postExecute()
  {
		$this->getResponse()->setSlot('tab_orders', '_on');  
  }   
}
