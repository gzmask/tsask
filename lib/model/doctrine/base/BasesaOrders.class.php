<?php

/**
 * BasesaOrders
 * 
 * This class has been auto-generated by the Doctrine ORM Framework
 * 
 * @property integer $form_id
 * @property integer $user_id
 * @property string $form_name
 * @property string $user_name
 * @property string $total_cost_paid
 * @property timestamp $date_completed
 * @property clob $order_content
 * 
 * @method integer   getFormId()          Returns the current record's "form_id" value
 * @method integer   getUserId()          Returns the current record's "user_id" value
 * @method string    getFormName()        Returns the current record's "form_name" value
 * @method string    getUserName()        Returns the current record's "user_name" value
 * @method string    getTotalCostPaid()   Returns the current record's "total_cost_paid" value
 * @method timestamp getDateCompleted()   Returns the current record's "date_completed" value
 * @method clob      getOrderContent()    Returns the current record's "order_content" value
 * @method saOrders  setFormId()          Sets the current record's "form_id" value
 * @method saOrders  setUserId()          Sets the current record's "user_id" value
 * @method saOrders  setFormName()        Sets the current record's "form_name" value
 * @method saOrders  setUserName()        Sets the current record's "user_name" value
 * @method saOrders  setTotalCostPaid()   Sets the current record's "total_cost_paid" value
 * @method saOrders  setDateCompleted()   Sets the current record's "date_completed" value
 * @method saOrders  setOrderContent()    Sets the current record's "order_content" value
 * 
 * @package    authority
 * @subpackage model
 * @author     Your name here
 * @version    SVN: $Id: Builder.php 7490 2010-03-29 19:53:27Z jwage $
 */
abstract class BasesaOrders extends sfDoctrineRecord
{
    public function setTableDefinition()
    {
        $this->setTableName('sa_orders');
        $this->hasColumn('form_id', 'integer', null, array(
             'type' => 'integer',
             ));
        $this->hasColumn('user_id', 'integer', null, array(
             'type' => 'integer',
             ));
        $this->hasColumn('form_name', 'string', 255, array(
             'type' => 'string',
             'length' => 255,
             ));
        $this->hasColumn('user_name', 'string', 255, array(
             'type' => 'string',
             'length' => 255,
             ));
        $this->hasColumn('total_cost_paid', 'string', 255, array(
             'type' => 'string',
             'length' => 255,
             ));
        $this->hasColumn('date_completed', 'timestamp', null, array(
             'type' => 'timestamp',
             ));
        $this->hasColumn('order_content', 'clob', null, array(
             'type' => 'clob',
             ));
    }

    public function setUp()
    {
        parent::setUp();
        $timestampable0 = new Doctrine_Template_Timestampable(array(
             ));
        $this->actAs($timestampable0);
    }
}