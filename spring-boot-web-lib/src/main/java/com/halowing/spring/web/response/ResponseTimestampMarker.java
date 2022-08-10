package com.halowing.spring.web.response;

import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.halowing.spring.web.dto.response.DefaultResponse;

public class ResponseTimestampMarker {
	
	private static final Logger log = LoggerFactory.getLogger(ResponseTimestampMarker.class);

//	@Around(value = "@within(org.springframework.web.bind.annotation.RestController) ")
	public Object setTimestamp(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Object result = null;
		
		LocalDateTime requestDateTime = LocalDateTime.now();
		
		try{
			result = joinPoint.proceed();
		}catch(Throwable e) {
			throw e;
		}finally {
			LocalDateTime responseTime = LocalDateTime.now();
			
			log.info("{} : {} ns",joinPoint.getSignature().toShortString(),(responseTime.getNano() - requestDateTime.getNano()));
			
			if(result instanceof DefaultResponse) {
				DefaultResponse response = (DefaultResponse) result;
				response.setRequestDateTime(requestDateTime);
				response.setResponseDateTime(responseTime);
			}
		}
		return result;
	}
}
