package com.sample.maven.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.sample.maven.dto.FileDto;

public class FileUtil {
	
	public String fileUpload(String contextPath, String uploadPath, MultipartFile attcFile, String folderNm) throws Exception{
		String rtnVal = "";
		try{
			String attcFileNm = "";
			String attcFileOriNm = "";
			String attcFileOriExt = "";
			String storedFileName = "";
			
			SimpleDateFormat sdf = new SimpleDateFormat ( "yyMM", Locale.KOREA );
			Date currentDate = new Date();
			String dateFolderNm = sdf.format(currentDate);
			
			attcFileNm = UUID.randomUUID().toString().replaceAll("-", "");
			attcFileOriNm = attcFile.getOriginalFilename();
			attcFileOriExt = attcFileOriNm.substring(attcFileOriNm.lastIndexOf("."));
			storedFileName = attcFileNm + attcFileOriExt; 

			File file = new File(uploadPath + "/" + folderNm +  "/"  + dateFolderNm);
			if(!file.exists()){
				file.mkdirs();
			}
			
			attcFile.transferTo(new File(uploadPath + "/" + folderNm + "/" + dateFolderNm + "/" + storedFileName));
			rtnVal = storedFileName + "|" + attcFileOriNm + "|" + contextPath;
		}catch(Exception e){
			e.printStackTrace();
			rtnVal = "";
		}
		
		return rtnVal;
	}
	
	public int FileDownload(FileDto fileDto, String uploadPath, String folderNm, HttpServletRequest request, HttpServletResponse response){
		int resultData = 0;
		
		try{
			String storedFileName = fileDto.getAttcFileNm();
			String fileOriginName = fileDto.getAttcFileOriginNm();
			
			File file = new File(uploadPath + "/" + folderNm + "/" + storedFileName);

			ServletOutputStream outStream = null;
			FileInputStream inputStream = null;
			
			outStream = response.getOutputStream();
	        inputStream = new FileInputStream(file);
		    
		    response.setContentType("application/octet-stream");
		    response.setHeader("Content-Disposition", "attachment;filename=\""+ storedFileName + "\"");
		    
		    byte[] outByte = new byte[4096];

	        while(inputStream.read(outByte, 0, 4096) != -1)

	        {
	          outStream.write(outByte, 0, 4096);
	        }
		    
		    resultData = 1;
		    
		}catch(Exception e){
			e.printStackTrace();
			resultData = 0;
		}
		
		return resultData;
		
	}
	
	public String getBrowser(HttpServletRequest request) { 
		String header = request.getHeader("User-Agent"); 
		if (header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1) { 
			return "MSIE";
		}
		else if (header.indexOf("Chrome") > -1) {
			return "Chrome"; 
		}
		else if (header.indexOf("Opera") > -1) {
				return "Opera"; 
		}
		return "Firefox"; 
	}
	
	public String getDisposition(String filename, String browser) throws Exception { 
		String dispositionPrefix = "attachment;filename="; 
		String encodedFilename = null; 
		if (browser.equals("MSIE")) { 
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) { 
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) { 
			StringBuffer sb = new StringBuffer(); 
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') { 
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		}

		return dispositionPrefix + encodedFilename;
	}
	
	public void fileDelete(String uploadPath, String folderNm, String fileNm){
		File file = new File(uploadPath + "/" + folderNm + "/" + fileNm);
		
		boolean delRslt = false;
		if(file.exists() && file.isFile()){
			delRslt = file.delete();
		}

	}
	
}
