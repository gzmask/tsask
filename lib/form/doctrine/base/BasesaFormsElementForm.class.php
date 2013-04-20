<?php

/**
 * saFormsElement form base class.
 *
 * @method saFormsElement getObject() Returns the current form's model object
 *
 * @package    authority
 * @subpackage form
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormGeneratedTemplate.php 29553 2010-05-20 14:33:00Z Kris.Wallsmith $
 */
abstract class BasesaFormsElementForm extends BaseFormDoctrine
{
  public function setup()
  {
    $this->setWidgets(array(
      'form_id'       => new sfWidgetFormInputHidden(),
      'form_name'     => new sfWidgetFormInputText(),
      'element_name'  => new sfWidgetFormInputText(),
      'element_key'   => new sfWidgetFormInputText(),
      'element_value' => new sfWidgetFormInputText(),
      'element_type'  => new sfWidgetFormInputText(),
      'sort'          => new sfWidgetFormInputText(),
      'created_at'    => new sfWidgetFormDateTime(),
      'updated_at'    => new sfWidgetFormDateTime(),
    ));

    $this->setValidators(array(
      'form_id'       => new sfValidatorChoice(array('choices' => array($this->getObject()->get('form_id')), 'empty_value' => $this->getObject()->get('form_id'), 'required' => false)),
      'form_name'     => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'element_name'  => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'element_key'   => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'element_value' => new sfValidatorString(array('max_length' => 255, 'required' => false)),
      'element_type'  => new sfValidatorInteger(array('required' => false)),
      'sort'          => new sfValidatorInteger(array('required' => false)),
      'created_at'    => new sfValidatorDateTime(),
      'updated_at'    => new sfValidatorDateTime(),
    ));

    $this->widgetSchema->setNameFormat('sa_forms_element[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    $this->setupInheritance();

    parent::setup();
  }

  public function getModelName()
  {
    return 'saFormsElement';
  }

}
