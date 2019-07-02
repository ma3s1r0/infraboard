package com.joeunins.infraboard.board.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.joeunins.infraboard.board.domain.BoardMapper;

@Controller
public class BoardController {

	@Autowired
	private BoardMapper boardMapper;

	@RequestMapping(value = "/infraboard/board", method = { RequestMethod.GET, RequestMethod.POST })
	private String index(@RequestParam Map<String, Object> allRequestParams, Model model, HttpSession sess,
			RedirectAttributes rttr) throws Exception {

		return "board/board";
	}

	@RequestMapping(value = "/infraboard/board/write", method = { RequestMethod.GET, RequestMethod.POST })
	private String write(@RequestParam Map<String, Object> allRequestParams, Model model, HttpSession sess,
			RedirectAttributes rttr) throws Exception {

		return "board/write";
	}

	@RequestMapping(value = "/infraboard/board/image_attatch", method = { RequestMethod.GET, RequestMethod.POST })
	private String image_attatch(@RequestParam Map<String, Object> allRequestParams, Model model, HttpSession sess,
			RedirectAttributes rttr) throws Exception {

		return "board/image_attatch";
	}


	// 단일 파일 업로드 Ajax
	@RequestMapping(value = "/infraboard/board/singleUploadImageAjax", method = RequestMethod.POST)
	public @ResponseBody HashMap singleUploadImageAjax(@RequestParam("Filedata") MultipartFile multipartFile, HttpSession httpSession) {

	    HashMap fileInfo = new HashMap(); // CallBack할 때 이미지 정보를 담을 Map

	    // 업로드 파일이 존재하면
	    if(multipartFile != null && !(multipartFile.getOriginalFilename().equals(""))) {

	        // 확장자 제한
	        String originalName = multipartFile.getOriginalFilename(); // 실제 파일명
	        String originalNameExtension = originalName.substring(originalName.lastIndexOf(".") + 1).toLowerCase(); // 실제파일 확장자 (소문자변경)
	        if( !( (originalNameExtension.equals("jpg")) || (originalNameExtension.equals("gif")) || (originalNameExtension.equals("png")) || (originalNameExtension.equals("bmp")) ) ){
	            fileInfo.put("result", -1); // 허용 확장자가 아닐 경우
	            return fileInfo;
	        }

	        // 파일크기제한 (1MB)
	        long filesize = multipartFile.getSize(); // 파일크기
	        long limitFileSize = 1*1024*1024; // 1MB
	        if(limitFileSize < filesize){ // 제한보다 파일크기가 클 경우
	            fileInfo.put("result", -2);
	            return fileInfo;
	        }

	        // 저장경로
	        String defaultPath = httpSession.getServletContext().getRealPath("/"); // 서버기본경로 (프로젝트 폴더 아님)
	        String path = defaultPath + File.separator + "upload" + File.separator + "board" + File.separator + "images" + File.separator + "";

	        // 저장경로 처리
	        File file = new File(path);
	        if(!file.exists()) { // 디렉토리 존재하지 않을경우 디렉토리 생성
	            file.mkdirs();
	        }

	        // 파일 저장명 처리 (20150702091941-fd8-db619e6040d5.확장자)
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        String today= formatter.format(new Date());
	        String modifyName = today + "-" + UUID.randomUUID().toString().substring(20) + "." + originalNameExtension;

	        // Multipart 처리
	        try {
	            // 서버에 파일 저장 (쓰기)
	            multipartFile.transferTo(new File(path + modifyName));

	            // 로그
	            System.out.println("** upload 정보 **");
	            System.out.println("** path : " + path + " **");
	            System.out.println("** originalName : " + originalName + " **");
	            System.out.println("** modifyName : " + modifyName + " **");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("이미지파일업로드 실패 - singleUploadImageAjax");
	        }

	        // CallBack - Map에 담기
	        String imageurl = httpSession.getServletContext().getContextPath() + "/upload/board/images/" + modifyName;    // separator와는 다름!
	        fileInfo.put("imageurl", imageurl);     // 상대파일경로(사이즈변환이나 변형된 파일)
	        fileInfo.put("filename", modifyName);   // 파일명
	        fileInfo.put("filesize", filesize);     // 파일사이즈
	        fileInfo.put("imagealign", "C");        // 이미지정렬(C:center)
	        fileInfo.put("originalurl", imageurl);  // 실제파일경로
	        fileInfo.put("thumburl", imageurl);     // 썸네일파일경로(사이즈변환이나 변형된 파일)

	        fileInfo.put("result", 1);                // -1, -2를 제외한 아무거나 싣어도 됨
	    }

	    return fileInfo;    // @ResponseBody 어노테이션을 사용하여 Map을 JSON형태로 반환
	}
	
	@RequestMapping(value = "/infraboard/board/file_attatch", method = { RequestMethod.GET, RequestMethod.POST })
	private String file_attatch(@RequestParam Map<String, Object> allRequestParams, Model model, HttpSession sess,
			RedirectAttributes rttr) throws Exception {

		return "board/file_attatch";
	}
	
	// 단일 파일 업로드 Ajax
	@RequestMapping(value = "/infraboard/board/singleUploadFileAjax", method = RequestMethod.POST)
	public @ResponseBody HashMap singleUploadFileAjax(@RequestParam("Filedata") MultipartFile multipartFile, HttpSession httpSession) {

	    HashMap fileInfo = new HashMap(); // CallBack할 때 파일 정보를 담을 Map

	    // 업로드 파일이 존재하면
	    if(multipartFile != null && !(multipartFile.getOriginalFilename().equals(""))) {

	        // 파일크기제한 (5MB)
	        long filesize = multipartFile.getSize(); // 파일크기
	        long limitFileSize = 10*1024*1024; // 10MB
	        if(limitFileSize < filesize){ // 제한보다 파일크기가 클 경우
	            fileInfo.put("result", -1);
	            return fileInfo;
	        }

	        // 저장경로
	        String defaultPath = httpSession.getServletContext().getRealPath("/"); // 서버기본경로 (프로젝트 폴더 아님)
	        String path = defaultPath + File.separator + "upload" + File.separator + "board" + File.separator + "images" + File.separator + "";

	        // 저장경로 처리
	        File file = new File(path);
	        if(!file.exists()) { // 디렉토리 존재하지 않을경우 디렉토리 생성
	            file.mkdirs();
	        }

	        // 파일 저장명 처리 (20150702091941-파일명)
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	        String today= formatter.format(new Date());
	        String originalName = multipartFile.getOriginalFilename(); // 파일이름
	        String modifyName = today + "-" + originalName; 

	        // Multipart 처리
	        try {
	            // 서버에 파일 저장 (쓰기)
	            multipartFile.transferTo(new File(path + modifyName));

	            // 로그
	            System.out.println("** upload 정보 **");
	            System.out.println("** path : " + path + " **");
	            System.out.println("** originalName : " + originalName + " **");
	            System.out.println("** modifyName : " + modifyName + " **");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("파일업로드 실패 - singleUploadFileAjax");
	        }

	        // mime
	        String fileMime = multipartFile.getContentType();

	        // CallBack - Map에 담기
	        String attachurl = httpSession.getServletContext().getContextPath() + "/upload/board/files/" + modifyName; // separator와는 다름!
	        fileInfo.put("attachurl", attachurl); // 상대파일경로(사이즈변환이나 변형된 파일)
	        fileInfo.put("filemime", fileMime); // mime
	        fileInfo.put("filename", modifyName); // 파일명
	        fileInfo.put("filesize", filesize); // 파일사이즈
	        fileInfo.put("result", 1); // -1을 제외한 아무거나 싣어도 됨
	    }

	    return fileInfo;    // @ResponseBody 어노테이션을 사용하여 Map을 JSON형태로 반환
	}

	@RequestMapping(value = "/infraboard/board/post", method = { RequestMethod.GET, RequestMethod.POST })
	private String board_post(@RequestParam Map<String, Object> allRequestParams, @RequestParam(value="image_attatch") List<String> imgPathList,
			@RequestParam(value="file_attatch") List<String> filePathList, Model model, HttpSession sess,
			RedirectAttributes rttr) throws Exception {

		String title 	= allRequestParams.get("title").toString();
		String content 	= allRequestParams.get("content").toString();
		
		HashMap<String,Object> _hm = new HashMap();
		_hm.put("title",title);
		_hm.put("content",content);
		_hm.put("img_attatch",imgPathList);
		_hm.put("file_attatch",filePathList);
		_hm.put("reg_id",sess.getAttribute("LOGIN_ID").toString());
		_hm.put("reg_name",sess.getAttribute("LOGIN_NAME").toString());
		_hm.put("dept_team",sess.getAttribute("LOGIN_DEPT_TEAM").toString());
		

		return "redirect:/infraboard/board";
	}
}
