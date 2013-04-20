<?php

/**
 * saFormsElement filter form base class.
 *
 * @package    authority
 * @subpackage filter
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormFilterGeneratedTemplate.php 29570 2010-05-21 14:49:47Z Kris.Wallsmith $
 */
abstract class BasesaFormsElementFormFilter extends BaseFormFilterDoctrine
{
  public function setup()
  {
    $this->setWidgets(array(
      'form_name'     => new sfWidgetFormFilterInput(),
      'element_name'  => new sfWidgetFormFilterInput(),
      'element_key'   => new sfWidgetFormFilterInput(),
      'element_value' => new sfWidgetFormFilterInput(),
      'element_type'  => new sfWidgetFormFilterInput(),
      'sort'          => new sfWidgetFormFilterInput(),
      'created_at'    => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
      'updated_at'    => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
    ));

    $this->setValidators(array(
      'form_name'     => new sfValidatorPass(array('required' => false)),
      'element_name'  => new sfValidatorPass(array('required' => false)),
      'element_key'   => new sfValidatorPass(array('required' => false)),
      'element_value' => new sfValidatorPass(array('required' => false)),
      'element_type'  => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'sort'          => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'created_at'    => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 00:00:00')), 'to_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 23:59:59')))),
      'updated_at'    => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 00:00:00')), 'to_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 23:59:59')))),
    ));

    $this->widgetSchema->setNameFormat('sa_forms_element_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    $this->setupInheritance();

    parent::setup();
  }

  public function getModelName()
  {
    return 'saFormsElement';
  }

  public function getFields()
  {
    return array(
      'form_id'       => 'Number',
      'form_name'     => 'Text',
      'element_name'  => 'Text',
      'element_key'   => 'Text',
      'element_value' => 'Text',
      'element_type'  => 'Number',
      'sort'          => 'Number',
      'created_at'    => 'Date',
      'updated_at'    => 'Date',
    );
  }
}
