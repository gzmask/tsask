<?php

/**
 * credit actions.
 *
 * @package    authority
 * @subpackage credit
 * @author     Your name here
 * @version    SVN: $Id: actions.class.php 23810 2009-11-12 11:07:44Z Kris.Wallsmith $
 */
class creditActions extends sfActions
{
 /**
  * Executes index action
  *
  * @param sfRequest $request A request object
  */
  public function executeIndex(sfWebRequest $request)
  {


	$request_string='';
	
	$requestType='requestType=BACKEND&';
	$merchant_id='merchant_id=257900000&';

	$trnCardOwner='trnCardOwner=Paul+Randal&';
	$trnCardNumber='trnCardNumber=5100000010001004&';
	$trnExpMonth='trnExpMonth=01&';
	$trnExpYear='trnExpYear=13&';

	$trnOrderNumber='trnOrderNumber=2232&';
	$trnAmount='trnAmount=1.00&';

	$ordEmailAddress='ordEmailAddress=251499275@qq.com&';
	$ordName='ordName=Paul+Randal&';
	$ordPhoneNumber='ordPhoneNumber=9999999&';
	$ordAddress1='ordAddress1=1045+Main+Street&';
	$ordAddress2='ordAddress2=&';
	$ordCity='ordCity=Vancouver&';
	$ordProvince='ordProvince=BC&';
	$ordPostalCode='ordPostalCode=V8R+1J6&';
	$ordCountry='ordCountry=CA';

	$request_string.=$requestType;
	$request_string.=$merchant_id;
	$request_string.=$trnCardOwner;
	$request_string.=$trnCardNumber;
	$request_string.=$trnExpMonth;
	$request_string.=$trnExpYear;
	$request_string.=$trnOrderNumber;
	$request_string.=$trnAmount;
	$request_string.=$ordEmailAddress;
	$request_string.=$ordName;
	$request_string.=$ordPhoneNumber;
	$request_string.=$ordAddress1;
	$request_string.=$ordAddress2;
	$request_string.=$ordCity;
	$request_string.=$ordProvince;
	$request_string.=$ordPostalCode;
	$request_string.=$ordCountry;

	// Initialize curl
	$ch = curl_init();
	// Get curl to POST
	curl_setopt( $ch, CURLOPT_POST, 1 );
	curl_setopt($ch, CURLOPT_SSL_VERIFYHOST,0);
	curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);
	// Instruct curl to suppress the output from Beanstream, and to directly
	// return the transfer instead. (Output will be stored in $txResult.)
	curl_setopt( $ch, CURLOPT_RETURNTRANSFER, 1 );
	// This is the location of the Beanstream payment gateway
	curl_setopt( $ch, CURLOPT_URL, "https://www.beanstream.com/scripts/process_transaction.asp" );
	// These are the transaction parameters that we will POST	
	curl_setopt( $ch, CURLOPT_POSTFIELDS,  $request_string);
	// Now POST the transaction. $txResult will contain Beanstream's response
	$this->txResult = str_replace("&","<br>",urldecode(curl_exec( $ch )));
	//echo "Result:<BR>";
	//echo $txResult;
	curl_close( $ch );  
    //$this->forward('default', 'module');
  }
}
