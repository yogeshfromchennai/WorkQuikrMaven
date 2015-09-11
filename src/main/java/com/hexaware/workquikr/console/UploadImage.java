package com.hexaware.workquikr.console;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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

/**
 * Servlet implementation class UploadImage
 */
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 5000000 * 1024;
	   private int maxMemSize = 400000 * 1024;
	   private File file ;
	   private String ioFileName=null;
	   private String ioVersion="";
	   private String adapterPath;
		String flag=null;
	   private Logger log=LogFactory.getLogger(this.getClass());
	   public void init( ){
		      // Get the file location where it would be stored.
			  log.info("upload init called");
		      filePath = ConfigHandler.getInstance().getSystemProperty("sync.output.location");
		      adapterPath=ConfigHandler.getInstance().getSystemProperty("server.deploy.adapter.location");
		      log.info("Server deploy location--- "+filePath);       
		      
		      //Get the path to store Adapter Files
		      
		   }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("******************* Do post Starts*************************");
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
	            	  String fileName = fi.getName();
	            	  if( fileName.lastIndexOf("\\") >= 0 ){
	                      file = new File( filePath + 
	                      fileName.substring( fileName.lastIndexOf("\\")-1)) ;
	                   }else{
	                      file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	                   }
	                              
	                   fi.write(file) ;
	                   log.info("File successfully uploaded. Name : "+ ioFileName); 
	            	  
	              }
	          }
	    	  
	      }catch(Exception ex) {
	          log.error(ex);
	      }
	         log.info("******************* Do post Ends*************************");
	}

}
