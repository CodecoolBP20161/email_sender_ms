package com.codecool.job_processor.jobs;

import com.codecool.configuration.ApplicationContextProvider;
import com.codecool.model.Email;
import com.codecool.service.EmailService;
import org.quartz.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class EmailSendingJob implements Job {

    private Email email;
    private String emailId;
    private JobDataMap dataMap;
    private EmailService emailService = ApplicationContextProvider.getBean("emailService",EmailService.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        emailId = dataMap.getString("emailId");
        email = emailService.findById(emailId);
        emailService.sendEmail(email);
    }
}
