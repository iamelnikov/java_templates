package spring.mongo.connector.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.mongo.connector.domain.file.FileWrapper;
import spring.mongo.connector.service.FileDAO;


@RestController
public class FileUploadController {

	
	@Autowired
	private FileDAO fileDao;
	
	@RequestMapping(value = "/spring.mongo/v1/uploadFile", method = RequestMethod.POST)
	public @ResponseBody String postUploadVideo( 
												@RequestBody(required = true) FileWrapper wrapper 
												/*@RequestParam("fileContent") MultipartFile fileContent*/){
		if (!wrapper.isInputStreamNullOrEmpty()){
			try {
					fileDao.storeMediaFile(wrapper);
					return "File Successfully Uploaded";
				}
			catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
		}
		else
			return "File is empty. Nothing to upload";
		
	}
}
