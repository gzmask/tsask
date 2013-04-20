<?php

/**
 * saOrders filter form base class.
 *
 * @package    authority
 * @subpackage filter
 * @author     Your name here
 * @version    SVN: $Id: sfDoctrineFormFilterGeneratedTemplate.php 29570 2010-05-21 14:49:47Z Kris.Wallsmith $
 */
abstract class BasesaOrdersFormFilter extends BaseFormFilterDoctrine
{
  public function setup()
  {
    $this->setWidgets(array(
      'form_id'         => new sfWidgetFormFilterInput(),
      'user_id'         => new sfWidgetFormFilterInput(),
      'form_name'       => new sfWidgetFormFilterInput(),
      'user_name'       => new sfWidgetFormFilterInput(),
      'total_cost_paid' => new sfWidgetFormFilterInput(),
      'date_completed'  => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate())),
      'order_content'   => new sfWidgetFormFilterInput(),
      'created_at'      => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
      'updated_at'      => new sfWidgetFormFilterDate(array('from_date' => new sfWidgetFormDate(), 'to_date' => new sfWidgetFormDate(), 'with_empty' => false)),
    ));

    $this->setValidators(array(
      'form_id'         => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'user_id'         => new sfValidatorSchemaFilter('text', new sfValidatorInteger(array('required' => false))),
      'form_name'       => new sfValidatorPass(array('required' => false)),
      'user_name'       => new sfValidatorPass(array('required' => false)),
      'total_cost_paid' => new sfValidatorPass(array('required' => false)),
      'date_completed'  => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 00:00:00')), 'to_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 23:59:59')))),
      'order_content'   => new sfValidatorPass(array('required' => false)),
      'created_at'      => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 00:00:00')), 'to_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 23:59:59')))),
      'updated_at'      => new sfValidatorDateRange(array('required' => false, 'from_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 00:00:00')), 'to_date' => new sfValidatorDateTime(array('required' => false, 'datetime_output' => 'Y-m-d 23:59:59')))),
    ));

    $this->widgetSchema->setNameFormat('sa_orders_filters[%s]');

    $this->errorSchema = new sfValidatorErrorSchema($this->validatorSchema);

    $this->setupInheritance();

    parent::setup();
  }

  public function getModelName()
  {
    return 'saOrders';
  }

  public function getFields()
  {
    return array(
      'id'              => 'Number',
      'form_id'         => 'Number',
      'user_id'         => 'Number',
      'form_name'       => 'Text',
      'user_name'       => 'Text',
      'total_cost_paid' => 'Text',
      'date_completed'  => 'Date',
      'order_content'   => 'Text',
      'created_at'      => 'Date',
      'updated_at'      => 'Date',
    );
  }
}
