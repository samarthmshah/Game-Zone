package Controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUploadController
 */
@WebServlet("/FileUploadController")
public class FileUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private boolean isMultipart;
    private String folderPath;
    private int maxFileSize = 1000 * 1024;
    private int maxMemSize = 1000 * 1024;
    private File file ;
    private String fileName = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploadController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		throw new ServletException("GET method used with " + getClass( ).getName( )+": POST method required.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String absPath = getServletContext().getRealPath(request.getServletPath());
		folderPath = absPath.substring(0, absPath.lastIndexOf(File.separator)) + "/Seller_Buyer/images/posters";
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");

		if (!isMultipart) return;
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File(folderPath + File.separator + "large_images"));
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);
		try {
			// Parse the request to get file items.
			List<FileItem> fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator<FileItem> itr = fileItems.iterator();

			while(itr.hasNext()){
				FileItem fi = itr.next();
				if(! fi.isFormField()){
					// Get the uploaded file parameters
		            String file_name = fi.getName();
		            
		            // Write the file
		            if( file_name.lastIndexOf(File.separator) >= 0 ){
		            	String pathToStoreImg = folderPath + File.separator + file_name.substring( file_name.lastIndexOf(File.separator));
		               file = new File(pathToStoreImg) ;
		            }
		            else{
		            	String pathToStoreImg = folderPath + File.separator + file_name.substring(file_name.lastIndexOf(File.separator)+1);
		            	System.out.println("(ELSE PART) Full Path to store image "+pathToStoreImg);
		            	file = new File(pathToStoreImg) ;
		            }
		            fi.write(file);
		            System.out.println("(FILEUPLOADCONTROLLER) File has been uploaded successfully!!");
		            fileName = file_name;
				}
			}
		} 
		catch (FileUploadException fue) {
			fue.printStackTrace();
			System.err.println("Retry.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		if(fileName != null){
    		session.setAttribute("fileName", fileName);
		}
		else{
			session.setAttribute("fileName", "no_image_available.png");
		}
		session.setAttribute("isUploaded", "The Image has been uploaded successfully");
		response.sendRedirect(request.getContextPath()+"/Seller_Buyer/seller_addGame.jsp");
	}
};