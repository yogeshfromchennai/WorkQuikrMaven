package com.hexaware.workquikr.console.action;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hexaware.framework.config.ConfigHandler;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.delegate.CatalogDelegate;
import com.hexaware.workquikr.console.service.notification.Notifier;


public class UploadFile extends HttpServlet {

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 5000000 * 1024;
	private int maxMemSize = 400000 * 1024;
	private File file ;
	private String ioFileName=null;
	private String ioVersion="";
	private String adapterPath;
	String flag=null;
	final static int BUFFER = 2048;
	private Logger log=LogFactory.getLogger(this.getClass());
	private CatalogDelegate catalogDelegate;
	int androidAvailable=0;
	int iosAvailable=0;
	int blackberryAvailable=0;

	public void init( ){
		// Get the file location where it would be stored.
		log.info("upload init called");
		filePath = ConfigHandler.getInstance().getSystemProperty("server.deploy.location");
		adapterPath=ConfigHandler.getInstance().getSystemProperty("server.deploy.adapter.location");
		log.info("Server deploy location "+filePath);       

		// Creation of WorkQuikr-Download Folder 
		boolean f = new File(filePath+"WorkQuikr-Download").mkdirs();
		log.info("WorkQuikr-Download Folder Created");

		//Get the path to store Adapter Files

	}
	public void doPost(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, java.io.IOException {
		log.info("******************* Do post Starts*************************");

		catalogDelegate=new CatalogDelegate();
		// Check that we have a file upload request
		isMultipart = ServletFileUpload.isMultipartContent(request);
		log.info("isMultipart "+isMultipart);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter( );

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File("c:\\temp"));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax( maxFileSize );

		try{ 
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);
			log.info("fileItems : " + fileItems.size());
			// Process the uploaded file items
			Iterator i = fileItems.iterator();
			while ( i.hasNext () ) 
			{
				FileItem fi = (FileItem)i.next();
				log.info("FieldName " + fi.getFieldName() );
				if ( !fi.isFormField () )	
				{

					// Get the uploaded file parameters         
					String fileName = fi.getName();

					log.info("File name is "+fileName);
					log.info("Filepath is"+filePath);
					String toLower=fileName.toLowerCase();            
					// Check the type of the file 
					if(toLower.contains("war")){    	
						// Write the file
						if( fileName.lastIndexOf("\\") >= 0 ){
							file = new File( filePath + 
									fileName.substring( fileName.lastIndexOf("\\")-1)) ;
						}else{
							file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
						}

						fi.write(file) ;

						try {
							BufferedOutputStream dest = null;
							FileInputStream fis = new FileInputStream(file);
							ZipInputStream zis = new  ZipInputStream(new BufferedInputStream(fis));
							ZipEntry entry;
							String trim=fi.getName();
							String temp=trim.replaceAll(".war", "");
							String inter=temp.replaceAll("-web","");
							String result=inter.replaceAll("[0-9]", ""); 
							String trimmed=result.replaceAll("[.]", "");
							log.info("App Name from url "+trimmed);
							while((entry = zis.getNextEntry()) != null) {
								int count;
								byte data[] = new byte[BUFFER];

								String pathForSecureDownload=filePath+"WorkQuikr-Download/"+trimmed+"/";
								// write the files to the disk

								if(entry.getName().contains("repo/")){

									if(entry.isDirectory()){
										if(entry.getName().equalsIgnoreCase("repo/")){
											boolean f = new File(filePath+"WorkQuikr-Download/"+trimmed).mkdirs();
											log.info("folder Created");

										}
										else{
											boolean f = new File(pathForSecureDownload+entry.getName().replaceAll("repo/", "")).mkdirs();
											if(entry.getName().contains("Android")){
												androidAvailable=androidAvailable+1;
											}
											else if(entry.getName().contains("iOS")){
												iosAvailable=iosAvailable+1;
											}
											else if(entry.getName().contains("BB")){
												blackberryAvailable=blackberryAvailable+1;
											}	
										}
									}
									else{

										log.info("inside file block");

										FileOutputStream fos = new FileOutputStream(pathForSecureDownload+entry.getName().replaceAll("repo/", ""));
										dest = new BufferedOutputStream(fos, BUFFER);
										while ((count = zis.read(data, 0, BUFFER))!= -1) {
											dest.write(data, 0, count);
										}

										// dest.flush();
										// dest.close();

									}

								}

							}
							// zis.close();
						} catch(Exception e) {
							e.printStackTrace();
						}

						request.setAttribute("msg", file.getName()+" is Deployed Successfully");            
						ioFileName=file.getName();
						insertDatabase();
						log.info("File successfully uploaded. Name : "+ ioFileName); 


					}
					if(toLower.contains(".xml")){
						log.info("Adapter path"+adapterPath);
						// Write the file
						if( fileName.lastIndexOf("\\") >= 0 ){
							file = new File( adapterPath + 
									fileName.substring( fileName.lastIndexOf("\\")-1)) ;
						}else{
							file = new File( adapterPath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
						}

						fi.write(file) ;
						request.setAttribute("message", file.getName()+" is Deployed Successfully");            
						ioFileName=file.getName();
						log.info("File successfully uploaded. Name : "+ ioFileName);
						ioFileName=ioFileName.replace(".xml","");
						String adapterType="";
						String adapFileName=ioFileName.toLowerCase();
						if(adapFileName.contains("sql")){
							adapterType="Sql";
						}
						if(adapFileName.contains("xml")){
							adapterType="Xml";
						}
						if(adapFileName.contains("ws")){
							adapterType="WebService";
						}
						if(adapFileName.contains("java")){
							adapterType="Java";
						}

						float version=1;
						Object[] parameters={ioFileName,fileName,new Date(),adapterType,version};
						//db.insertAdapterDeployment(parameters);
						catalogDelegate.insertAdapter(parameters);
						log.info("Adapter Inserted into db");
						log.info("Adapter Deployed and Inserted in to Database Sucessfully");                
					}

				}
				else{
					//        	log.info("getName " +  fi.getName());
					log.info("getString " +  fi.getString());  
					flag=fi.getString();
					if(flag.equalsIgnoreCase("true")){
						// Enable Auto Push
						Notifier notifier = new Notifier();
						String appName=ioFileName.substring(0, ioFileName.length() - 3);
						notifier.sendNotification(appName.replace("-web", ""));
						log.info("The Message has been pushed to notification server..");
					}
					else{
						log.info("Push Not Enabled");
					}
					//log.error("File item is not form field"); 
				}


			}


			RequestDispatcher rD=request.getRequestDispatcher("Home");
			rD.forward(request, response);
		}catch(Exception ex) {
			log.error(ex);
		}
		log.info("******************* Do post Ends*************************");
	}

	public void doGet(HttpServletRequest request, 
			HttpServletResponse response)
					throws ServletException, java.io.IOException {
		log.info("Redirected to post from get ");
		doPost(request, response);
	} 
	public void insertDatabase(){
		ioFileName=ioFileName.replace(".war","");	  	 
		ioVersion=ioFileName.substring(ioFileName.length() - 3,ioFileName.length());
		log.info("App Version"+ioVersion);
		String appName=ioFileName.substring(0, ioFileName.length() - 3);
		Object[] parameters={appName,ioFileName,ioVersion,new Date()};
		log.info("Appname "+appName);

		catalogDelegate.insertApplication(parameters);
		if(androidAvailable>0){
			insertEnvironment(appName, "android", "true", "true");
			androidAvailable=0;
		}
		if(iosAvailable>0){
			insertEnvironment(appName, "ios", "true", "true");
			iosAvailable=0;
		}
		if(blackberryAvailable>0){
			insertEnvironment(appName, "blackberry", "true", "true");
			blackberryAvailable=0;
		}

		log.info("Deployed and inserted in database"); 	 
	}
	public void insertEnvironment(String appName, String deviceType, String notifyRequired, String notifyType){
		Object[] envParameters = {appName, deviceType, notifyRequired, notifyType};
		catalogDelegate.insertEnvironmentInfo(envParameters);
	}
}