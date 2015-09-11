
document.addEventListener("deviceready", readyFn, true);
var showAlert = true;
var dataSource;
var dataTarget;


//SQL Adapter Invoker
function invokeAdapter(args,param)
{
	var name=args.name;
	var procedure=args.procedure;
	var param=args.param;
	 
	
	if(param=="undefined")
	{
		param=null;
	}
	
	AjaxRequest.post(
			  {
				'url':"http://172.25.106.233:8080/WorkQuikr-console/SQLInvoker",
				'parameters':{ 'name':name, 'procedure':procedure,"param":param},  
			    'onSuccess':onResult,
			    'onError':onError
			  }
			); 
}
function readyFn() {
	var allInput = document.getElementsByTagName("input");

	for ( var i = 0; i < allInput.length; i++) {
		var dataTask = allInput[i].getAttribute('data-task');

		if (dataTask != null) {
			
			/*var dataSource = allInput[i].getAttribute('data-source');*/
			var id = allInput[i].getAttribute('id');
			var dataTimes = allInput[i].getAttribute('data-times');
			var event = allInput[i].getAttribute('data-event');
			
		/*	var dataTarget = allInput[i].getAttribute("data-target");*/
			switch (dataTask) {
			case 'notify-type-alert':
				{
				
				$("#" + id).live(event,function(){notifyAlert(this);});
				break;
				}
			case 'notify-type-confirm':{
				$("#" + id).live(event, function(){notifyConfirm(this);});
				break;
			}

			case 'notify-type-beep':
				$("#" + id).live(event,function(){notifyBeep(this);});
				break;

			case 'notify-type-vibrate':
				$("#" + id).live(event,function(){notifyVibrate(this);});
				break;
			case 'connection-type': {
				$("#" + id).live(event,function(){connectionType(this);});
				
				break;
			}
			case 'device-info': {
				
				$("#"+id).live(event,function(){deviceInfo(this);});
				break;
			}
			case 'current-acceleration': {
				
				//window.any = dataTarget;
				$("#" + id).live(event,function(){getCurrentAcceleration(this);});
				break;
			}
			case 'watch-acceleration': {
				break;
			}
			case 'contact-find':{
				MsgDisplay("inside contact find");
//				var dataTarget = allInput[i].getAttribute('data-target');
//				var dataSource = allInput[i].getAttribute('data-source');
//				//window.anyTarget = dataTarget;
//				//window.anySource = dataSource;
//				MsgDisplay("source"+dataSource);
//				MsgDisplay("target"+dataTarget);
				MsgDisplay(id);
				MsgDisplay(event);
				$("#"+id).live(event,function(){contactFindFunction(this);});
				
			break;
			}
			
			
			case 'currentLocation': {
				
			//	window.anyTarget = dataTarget;
				
				$("#" + id).live(event,function(){getCurrentLocation(this);});
									
									
				break;
			}
			case 'Image-Gallery': {
				
				$("#"+id).live(event,function(){gallery(this);});
				break;
			}
			
			case 'capture-image': {
				
				$("#"+id).live(event,function(){captureImage(this);});
				break;
			}
			
			case 'capture-multiple-upload-image' : {
				
				$("#"+id).live(event,function(){captureMultipleUploadImage(this);});
			break;
			}
			
			case 'capture-vedio': {
			
				$("#"+id).live(event,function(){captureVedio(this);});
				break;
			}
			
			case 'media-audio-play':{
			
				$("#"+id).live(event,function(){mediaAudioPlay(this);});
			break;
			}
			case 'media-audio-pause':{
				
				$("#"+id).live(event,function(){mediaAudioPause(this);});
			break;
			}
			
			case 'media-audio-stop':{
				
				$("#"+id).live(event,function(){mediaAudioStop(this);});
			break;
			}
			
			case 'compass-current-heading':{
			
				$("#"+id).live(event,function(){compassGetCurrentHeading(this);});
			break;
			}
			
			default:
			}
		}

	}

	
}

/*_______________________________Main Functions_______________________________*/

function captureVedio(component)
{
	var dataTarget=component.getAttribute("data-target");
	var dataLimit=component.getAttribute("data-limit");
	captureVedioImpl(dataTarget,dataLimit);
}

function compassGetCurrentHeading(component)
{
	var dataTarget = component.getAttribute("data-target");
	compassGetCurrentHeadingImpl(dataTarget);
}
function connectionType(component)
{
	var dataTarget = component.getAttribute("data-target");
	connectionTypeImpl(dataTarget);
	
	
}


function contactFindFunction (component) {
	MsgDisplay("contactFindFunction Called");
	dataSource=component.getAttribute("data-source");
	dataTarget=component.getAttribute("data-target");
	MsgDisplay("source and target"+dataSource+dataTarget);
		soure=document.getElementById(dataSource).value;
	MsgDisplay("source-y-val"+soure);
	var options = new ContactFindOptions();
	options.filter = soure;
	var fields = [ "displayName", "name",
			"phoneNumbers" ];
	contactFindImpl(options,fields,dataSource,dataTarget);
	
 
};




function getCurrentLocation(component) {
dataTarget = component.getAttribute("data-target");
	MsgDisplay(dataTarget);
	getCurrentLocationImpl(dataTarget);
	  };
	  

	  
	  
	 
	  
	  function getCurrentAcceleration(component)
	  {
MsgDisplay('inside main acc');
		dataTarget = component.getAttribute("data-target");
MsgDisplay('data target'+dataTarget);
		 getCurrentAccelerationImpl(dataTarget);
		  
	  }
	  
	  
	 
	  
	  function notifyVibrate(component)
	  {
		  var dataTimes=component.getAttribute("data-times");
			notifyVibrateImpl(dataTimes);
			}
	  
	 
	  
	  function notifyBeep(component)
	  {
		  var dataTimes=component.getAttribute("data-times");
		  notifyBeepImpl(dataTimes);
		  
	  }
	  
	 
	  function notifyAlert(component)
	  {
		  dataSource=component.getAttribute("data-source");
		  var dataCaption = component.getAttribute('data-caption');
			var dataTitle = component.getAttribute('data-title');
			var source = document.getElementById(dataSource).value;
		 notifyAlertimpl(source,dataTitle, dataCaption);
			
	  }
	  
	  function notifyConfirm(component)
	  {
		  dataSource=component.getAttribute("data-source");
		  var source = document.getElementById(dataSource).value;
			
			var dataTitle = component.getAttribute('data-title');
			
			var dataButtonCaption=component.getAttribute('data-button-captions');
			notifyConfirmimpl(source, dataTitle,dataButtonCaption);
			
		}

	  function gallery(component)
	  {
		  function PictureType(){};
		  PictureType.PHOTO_LIBRARY = 1;
			PictureType.CAMERA = 0;
	  	MsgDisplay("inside gallery image");
	  	dataTarget=component.getAttribute("data-target");
	  	MsgDisplay("target"+dataTarget);
	  	sourceType=PictureType.PHOTOLIBRARY;
	  	     var options = { quality: 50 };
	       
	  	   options["sourceType"] = navigator.camera.PictureSourceType.PHOTOLIBRARY;
	  	     if (sourceType != undefined) {
	      	 MsgDisplay('sourcer');
	      	 MsgDisplay('inside if');
	           
	       }
	       
	       MsgDisplay('options used');
	       // if no sourceType specified, the default is CAMERA 
	       GalleryImpl(options,dataTarget);
	  }
	  
function captureImage(component)
{
	function PictureSourceType(){};
	PictureSourceType.PHOTO_LIBRARY = 0;
	PictureSourceType.CAMERA = 1;
	MsgDisplay("inside capture image");
	dataTarget=component.getAttribute("data-target");
	MsgDisplay("target"+dataTarget);
	sourceType=PictureSourceType.CAMERA;
	     var options = { quality: 50 };
     if (sourceType != undefined) {
    	 MsgDisplay('sourcer');
    	 MsgDisplay('inside if');
          options["sourceType"] = sourceType;
     }
     
     MsgDisplay('options used');
     // if no sourceType specified, the default is CAMERA 
     captureImageImpl(options,dataTarget);
}
function captureMultipleUploadImage(component)
{
	MsgDisplay("inside capture image");
	dataURL=component.getAttribute("data-url");
	MsgDisplay("data url>>"+dataURL);
	dataLimit=component.getAttribute("data-limit");
	captureMultipleUploadImageImpl(dataURL,dataLimit);
}

function deviceInfo(component)
{
	var dataTarget=component.getAttribute("data-target");
	deviceInfoImpl(dataTarget);
	
}
function mediaAudioPlay(component){
	
	dataSource=component.getAttribute("data-source");
	var dataSourceValue=$("#"+dataSource).val();
	var dataAudioPosition=component.getAttribute("data-audio-position");
	mediaAudioPlayImpl(dataSourceValue,dataAudioPosition);
}

function mediaAudioStop(component){
	mediaAudioStopImpl(component);
}

function mediaAudioPause(component)
{
	mediaAudioPauseImpl();
}

	/*___________________________Implementation Layer___________________________*/

function captureVedioImpl(dataTarget,dataLimit)
{
	MsgDisplay("dataLimit"+dataLimit);
	MsgDisplay('dataTarget'+dataTarget);
	  navigator.device.capture.captureVideo(captureVedioSuccess, captureVedioError, {limit: dataLimit});
}

function compassGetCurrentHeadingImpl(dataTarget)
{
    navigator.compass.getCurrentHeading(oncompassGetCurrentHeadingSuccess, oncompassGetCurrentHeadingError);
}

function deviceInfoImpl(dataTarget)
{
	
        var target = document.getElementById(dataTarget);

        target.innerHTML = 'Device Name: '     + device.name     + '<br />' + 
                            'Device Platform: ' + device.platform + '<br />' + 
                            'Device UUID: '     + device.uuid     + '<br />' + 
                            'Device Version: '  + device.version  + '<br />';
    }





function mediaAudioStopImpl(component)
{
	if (my_media) {
        my_media.stop();
    }
    clearInterval(mediaTimer);
    mediaTimer = null;
}




function mediaAudioPauseImpl()
{
	 if (my_media) {
         my_media.pause();
     }
}


function mediaAudioPlayImpl(dataSourceValue,dataAudioPosition)
{
	  my_media = new Media(dataSourceValue, onMediaAudioPlaySuccess, onMediaAudioPlayError);

      // Play audio
      my_media.play();

      // Update my_media position every second
      if (mediaTimer == null) {
          mediaTimer = setInterval(function() {
              // get my_media position
              my_media.getCurrentPosition(
                  // success callback
                  function(position) {
                      if (position > -1) {
                          setMediaAudioPosition(dataAudioPosition,(position) + " sec");
                      }
                  },
                  // error callback
                  function(e) {
                      console.log("Error getting pos=" + e);
                      setAudioPosition("Error: " + e);
                  }
              );
          }, 1000);
      }
}

function captureMultipleUploadImageImpl(dataURL,dataLimit)
{
	MsgDisplay('dataURL'+dataURL);
	MsgDisplay('dataURL'+dataLimit);
	navigator.device.capture.captureImage(captureMultipleUploadImageSuccess, 

			captureMultipleUploadImageError, {limit: dataLimit});	
}

function captureImageImpl(options,dataTarget)
{
	MsgDisplay("inside impl");
	navigator.camera.getPicture(captureImage_Success,captureImage_Fail, options);
}

function GalleryImpl(options,dataTarget)
{
	MsgDisplay("inside impl");
	navigator.camera.getPicture(Gallery_Success,Gallery_Fail, options);
}

function contactFindImpl(options,fields,dataSource,dataTarget)
{
	  MsgDisplay("in impl"+dataSource+dataTarget);
	  navigator.contacts.find(fields,onSuccessContactFind,onError, options);
}

function getCurrentLocationImpl(dataTarget)
{
	  MsgDisplay("data target in impl");
	  navigator.geolocation.getCurrentPosition(onSuccessCurrentLocation, onErrorCurrentLocation);
}

function getCurrentAccelerationImpl(dataTarget)
{
	  navigator.accelerometer.getCurrentAcceleration(onSuccessCurrentAcceleration,onErrorCurrentAcceleration);

}

function notifyVibrateImpl(dataTimes)
{
		navigator.notification.vibrate(dataTimes);
}

function notifyBeepImpl(dataTimes)
{
	  navigator.notification.beep(dataTimes);
}

function notifyAlertimpl(source,dataTitle,dataCaption)
{
	  navigator.notification.alert(source,dataTitle, dataCaption);
}

function notifyConfirmimpl(source,dataTitle,dataButtonCaption)
{
	
	
		navigator.notification.confirm(source,ConfirmCallBack,dataTitle,dataButtonCaption);
		  
}

function connectionTypeImpl(dataTarget)
{
	var networkState =  navigator.network.connection.type;
		var states = {};
		states[Connection.UNKNOWN] = 'Unknown connection';
		states[Connection.ETHERNET] = 'Ethernet connection';
		states[Connection.WIFI] = 'WiFi connection';
		states[Connection.CELL_2G] = 'Cell 2G connection';
		states[Connection.CELL_3G] = 'Cell 3G connection';
		states[Connection.CELL_4G] = 'Cell 4G connection';
		states[Connection.NONE] = 'No network connection';
		document.getElementById(dataTarget).value = states[networkState];
		
	 
}

/*_______________________________Support Functions__________________________*/
						
						/*Capute Vedio*/

function captureVedioSuccess(mediaFiles)
{
	MsgDisplay("target"+dataTarget);
	 var i, len;
     for (i = 0, len = mediaFiles.length; i < len; i += 1) {
    	 alert("The Vedio has been captured - details :"+"Name of the file"+mediaFiles[i].name+"Please Check in the Path: "+mediaFiles[i].fullPath);
    	 /* var dynamicHTML="";
          dynamicHTML+="<video width="+'"'+320+'"'+" height="+'"'+240+'"'+" controls="+'"'+"controls"+'"'+">";
          dynamicHTML+="<source src=";
          dynamicHTML+='"'+mediaFiles[i].fullPath+'"';
          dynamicHTML+=" type="+'"'+'"'+"video/3gp"+'"'+"/>";
          dynamicHTML+="no Support";
          dynamicHTML+="</vedio>";
          MsgDisplay('dynamic'+dynamicHTML);document.getElementById("getstar").innerHTML=dynamicHTML;*/
       
     }       
     
}


function captureVedioError()
{
	alert("Error In capturing Vedio");
}
						/*Compass Heading*/
function oncompassGetCurrentHeadingSuccess()
{
	document.getElementById(dataTarget).value=heading.magneticHeading;
}
function oncompassGetCurrentHeadingError()
{
	document.getElementById(dataTarget).value="Error";
}

							/*Media Audio*/
function setMediaAudioPosition(dataAudioPosition,position) {
    document.getElementById(dataAudioPosition).innerHTML = position;
}
function onMediaAudioPlaySuccess()
{
	alert("Media Audio success");
}
function onMediaAudioPlayError()
{
	alert("failed Media Audio Play");
}

							/*Capture Multiple Image and Upload*/
function captureMultipleUploadImageSuccess(mediaFiles)
{
	 var i, len;
     for (i = 0, len = mediaFiles.length; i < len; i += 1) {
         uploadFile(mediaFiles[i],dataURL);
}
     alert('success');
}

function captureMultipleUploadImageError()
{
	alert("error in captureMultipleUploadImageError ");
}


function uploadFile(mediaFile,dataURL) {
MsgDisplay('inside Upload File');
MsgDisplay('insied upload - data url is '+dataURL);

var ft = new FileTransfer(),
        path = mediaFile.fullPath,
        name = mediaFile.name;
MsgDisplay('Path and name>>'+path+'>>>>'+name);
    ft.upload(path,dataURL,function(result) {
            MsgDisplay('Upload success: ' + result.responseCode);
            MsgDisplay(result.bytesSent + ' bytes sent');
        },
        function(error) {
            MsgDisplay('Error uploading file ' + path + ': ' + error.code);
        },
        { fileName: name },true);   
}

							/*Capture Image*/

function Gallery_Success(imageData)
{
	MsgDisplay('inside success');
	MsgDisplay('data Target>>'+dataTarget);
               
         var dynamicHTML="";
         dynamicHTML+="<img src=";
         dynamicHTML+='"'+imageData+'"';
         dynamicHTML+=" "+"style="+'"'+"width:200px;height:290px"+'"';
         dynamicHTML+="/>";
         
         document.getElementById(dataTarget).innerHTML=dynamicHTML;
   
}
function Gallery_Fail(message) {
	alert('get picture failed');
}
function captureImage_Success(imageData)
{
	MsgDisplay('inside success');
	MsgDisplay('data Target>>'+dataTarget);
               
         var dynamicHTML="";
         dynamicHTML+="<img src=";
         dynamicHTML+='"'+imageData+'"';
         dynamicHTML+=" "+"style="+'"'+"width:200px;height:290px"+'"';
         dynamicHTML+="/>";
         
         document.getElementById(dataTarget).innerHTML=dynamicHTML;
   
}

function captureImage_Fail(message) {
	alert('get picture failed');
}


							/*Contact Find*/
	 
function onSuccessContactFind(contacts) {
			MsgDisplay("inside on success");
			MsgDisplay(dataTarget);
			for ( var i = 0; i < contacts.length; i++) {
				MsgDisplay("inside success for loop");
				MsgDisplay("tength of phone number is "
						+ contacts[i].phoneNumbers.length);
				for ( var j = 0; j <= contacts[i].phoneNumbers.length; j++) {
					MsgDisplay("inside success phone number for loop");
					document.getElementById(dataTarget).value += contacts[i].phoneNumbers[j].value + '\n';
				}

			}
		} ;

		function onError() {
		MsgDisplay("error");
		};
	  
								/*Message Display fr development purpose*/
	  function MsgDisplay(message)
	  {
		  if(showAlert) {
			  alert(message);
		  }
	  }
	  
	  							/*Get Current Geo Location*/
	  
	  function onSuccessCurrentLocation(position) {
		  
		  var valueOfLocation='Latitude: '+ position.coords.latitude+ '\n'+
		     'Longitude: '          + position.coords.longitude             + '\n' +
		     'Altitude: '           + position.coords.altitude              + '\n' +
		     'Accuracy: '           + position.coords.accuracy              + '\n' +
		     'Altitude Accuracy: '  + position.coords.altitudeAccuracy      + '\n' +
		     'Heading: '            + position.coords.heading               + '\n' +
		     'Speed: '              + position.coords.speed                 + '\n' +
		     'Timestamp: '          + new Date(position.timestamp)          + '\n';
		  
		  document.getElementById(dataTarget).value=valueOfLocation;
	  }
	  
	  
	  function onErrorCurrentLocation()
	  {
		  MsgDisplay("onErrorCurrentLocation");
	  }
	  
	  							/*Get Current Acceleration*/
	
	  
	  function onSuccessCurrentAcceleration(acceleration)
	  {		var tmp = "X :["
						+ acceleration.x
						+ "] Y: ["
						+ acceleration.y
						+ "]";
				document.getElementById(dataTarget).value = tmp;
			
	  }
	  
	  function onErrorCurrentAcceleration()
	  {
		  
				MsgDisplay('Error In Accel');
	  }
