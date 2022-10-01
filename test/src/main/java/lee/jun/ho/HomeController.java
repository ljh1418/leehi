package lee.jun.ho;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lee.jun.ho.mail.GFWSmtpHelp;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * 
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@GetMapping("management")
	public String management() {
		return "sample/management";
	}
	
	@GetMapping("technology")
	public String technology() {
		return "sample/technology";
	}
	
	@GetMapping("chart")
	public String doAll() {
		return "sample/chart";
	}
	
	@GetMapping("htmlFile.do")
	public void htmlFile() throws IOException {
		
		System.out.println("htmlFile!!!");
		
		//폴더경로
		Path dirPath = Paths.get("/data/docuextract/sample/20220727/synap/20220727.docx.files/");
		
        List<Path> result;
        
        Stream<Path> walk = Files.walk(dirPath);
        
        result = walk.filter(Files::isRegularFile)
        		     .filter(p -> p.getFileName().toString().toLowerCase().endsWith(".html"))
                     .collect(Collectors.toList());

        for (Path path : result) {
            System.out.println(path);
        }
		
	}
	
//	@GetMapping("mail.do")
//	public void mail() throws IOException {
//		GFWSmtpHelp mail = new GFWSmtpHelp();
//		mail.sendMail(null, null, mail)
//		mail.sendMail("subjectTest", "contentTest", "ljh1418@gmail.com", null, null);
//	}
	
}
