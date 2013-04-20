<?php

/**
 * saOrders form base class.
 *
 * @method saOrders getObject() Returns the current form's model object
 *
 * @package    authority
 * @subpackage form
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormGeneratedTemplate.php 29553 2010-05-20 14:33:00Z Kris.Wallsmith $
 */
abstract class BasesaOrdersForm extends BaseFormDoctrine
{
  public function setup()
  {
    $this->setWidgets(array(
      'id'              => new sfWidgetFormInputHidden(),
      'form_id'         => new sfWidgetFormInputText(),
      'user_id'         => new sfWidgetFormInputText(),
      'form_name'       => new sfWidgetFormInputText(),
      'user_name'       => new sfWidgetFormInputText(),
      'total_cost_paid' => new sfWidgetFormInputText(),
      'date_completed'  => new sfWidgetFormDateTime(),
      'order_content'   => new sfWidgetFormTextarea(),
      'created_at'      => new sfWidgetFormDateTime(),
      'updated_at'      => new sfWidgetFormDateTime(),
    ));

    $this->setValidators(array(
      'id'              => new sfValidatorChoice(array('choices' => array($this->getObject()->get('id')), 'empty_value' => $this->getObject()->get('id'), 'required' => false)),
      'form_id'         => new sfValidatorInteger(array('required' => false)),
      'user_id'         => new sfValidatorInteger(array('required' => false)),
      'form_name'       => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'user_name'       => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'total_cost_paid' => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'date_completed'  => new sfValidatorDateTime(array('required' => false)),
      'order_content'   => new sfValidatorString(array('required' => false)),
      'created_at'      => new sfValidatorDateTime(),
      'updated_at'      => new sfValidatorDateTime(),
    ));

    $this->widgetSchema->setNameFormat('sa_orders[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    $this->setupInheritance();

    parent::setup();
  }

  public function getModelName()
  {
    return 'saOrders';
  }

}
