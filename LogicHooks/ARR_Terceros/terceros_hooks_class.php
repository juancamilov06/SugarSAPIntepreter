<?php

if (!defined('sugarEntry') || !sugarEntry) die('Not A Valid Entry Point');

class terceros_methods{	

	private $headers = array("Content-Type: application/json");

	//Se ejecuta luego de guardar una propiedad
    function afterSave($bean, $event, $arguments)
    {
		//Diferencia si es un update o un insert
		if(isset($arguments['isUpdate']) && $arguments['isUpdate'] == false){			
			$info = $this->mapBean($bean);    
			$request = new Request();
			$request = $request->setPostMethod()->setHeaders($this->headers)->setInformation($info)->setUrl("http://localhost:4567/api/tercero");        
			
			$client = new ClientWS($request);
			$response = $client->sendRequest();
		} else {
			$header = array("Content-Type: application/json");  
			$info = $this->mapBean($bean);      
			$request = new Request();
			$request = $request->setPUTMethod()->setHeaders($this->headers)->setInformation($info)->setUrl("http://localhost:4567/api/tercero");        
			
			$client = new ClientWS($request);
			$response = $client->sendRequest();
		}
    }
	
	//Se encarga de mappear el bean respecto a sus campos
	private function mapBean($bean){
		$fields = array_keys($bean->audit_enabled_fields);
		$finalArray = array('u_id' => $bean->id, 'id' => $bean->name);			
		foreach($fields as $key){			
			if($key != 'name'){ 
				$finalArray[$key] = $bean->$key;
			}
		}		
		return $finalArray;
	}
}

 
class ClientWS{
	 
    private $curlRequest;
	
	function __construct($request) {
        $this->curlRequest = curl_init(); 
        $this->setGlobalConfig($request);
	}

    public function setGlobalConfig($request){
		 
        $this->configURL($request->getURL());
        if(count($request->getHeaders() ) > 0)
        {
            $this->configHeaders($request->getHeaders());
        }

        $this->configDataAndMethod($request->getActualMethod(),$request->getSendInfo());
    }

    public function configDataAndMethod($method,$info){
        switch($method){
            case Request::$POST:
				curl_setopt($this->curlRequest, CURLOPT_POST, count($info));
				curl_setopt($this->curlRequest, CURLOPT_POSTFIELDS, $info);
				curl_setopt($this->curlRequest, CURLOPT_RETURNTRANSFER, true);
				
				break;
				
            case Request::$GET:
				break;
				
            case Request::$PUT:
				curl_setopt($this->curlRequest, CURLOPT_CUSTOMREQUEST, "PUT"); 
				curl_setopt($this->curlRequest, CURLOPT_FAILONERROR, true);                                                                    
				curl_setopt($this->curlRequest, CURLOPT_POSTFIELDS, $info);  
				curl_setopt($this->curlRequest, CURLOPT_RETURNTRANSFER, true);
				
				break;
        }
    }
    
	public function sendRequest() {
	   
		$response = curl_exec($this->curlRequest);
		curl_close($this->curlRequest);
		
		return $response;
	}

	private function configHeaders($headers) {
        curl_setopt($this->curlRequest, CURLOPT_HTTPHEADER, $headers);
	}

	private function configURL($urlEndpoint){        
        curl_setopt($this->curlRequest,CURLOPT_URL,$urlEndpoint);
	}

}
 
class Request{

    private $url;
	
    public static $POST = 1;
    public static $GET = 2;
    public static $PUT = 3;
	
    private $actualMethod;
    private $information;
    private $sendInfo ;
    private $headers = "";

    //Asignacion del Endpoint del WebService
    public function setUrl($urlEnd){
        $this->url = $urlEnd;
        return $this;
    }

    public function getUrl(){
        return $this->url;
    }

    //Se pasa la informacion de un "MAP" Array, el metodo se encarga de la serializacion de los objetos a json
    public function setInformation($information)
    {
        //Se recorre el objeto como Map Array y despues se buscan objetos que deban ser serializados
         foreach($information as $key=>&$value)
         {
            if($value instanceof SerialAble)
            {
                $value = $value->toJson();
            }
         }

         $this->sendInfo = json_encode($information);
         return $this;
    }

    //Metodo parao obtener la informacion de envio para el Webservice
    public function getSendInfo(){
        return $this->sendInfo;
    }


    //Metodo para configurar el metodo HTTP en POST
    public function setPostMethod()
    {
        $this->actualMethod = 1;
        return $this;
    }

    //Metodo para configurar el metodo HTTP en GET
    public function setGETMethod(){
        $this->actualMethod = 2;
        return $this;
    }

    //Metodo para configurar el metodo HTTP en PUT
    public function setPUTMethod(){
        $this->actualMethod = 3;
        return $this;
    }

    //Obtener el metodo actual por el cual se piensa hacer le request
    public function getActualMethod(){
        return $this->actualMethod;
    }

    //Se asignan las headers de un MAP array 
    public function setHeaders($head){
        $this->headers = $head;
        return $this;
    }
	
    public function getHeaders(){
       return $this->headers;
    }


}

?>