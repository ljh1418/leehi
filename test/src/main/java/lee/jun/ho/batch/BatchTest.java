package lee.jun.ho.batch;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BatchTest{
	
	@Scheduled(cron="*/100 * * * * *")
	public void jobMethod() throws Exception {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("스케줄 실행 : " + dateFormat.format(calendar.getTime()));
	}
}
