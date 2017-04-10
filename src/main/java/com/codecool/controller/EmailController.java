package com.codecool.controller;

import com.codecool.job_processor.JobProcessor;
import com.codecool.job_processor.jobs.EmailSendingJob;
import com.codecool.model.Email;
import com.codecool.service.ClientService;
import com.codecool.service.EmailService;
import org.json.simple.JSONObject;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.codecool.controller.ResponseEnum.ACCESS_DENIED;
import static com.codecool.controller.ResponseEnum.JOB_SAVED;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@RestController
@RequestMapping("/")
public class EmailController {

    private Email email;
    private JobDetail job;
    private Trigger trigger;
    private JSONObject json = new JSONObject();
    private static final String STATUS = "status_message";
    private static final String JOB_IDENTIFIER = "job_id";

    @Autowired
    JobProcessor jobProcessor;

    @Autowired
    ClientService clientService;

    @Autowired
    EmailService emailService;

    @RequestMapping
    public String status() {
        return "OK";
    }

    @RequestMapping(value = "/{clientApiKey}/email", method = RequestMethod.POST)
    public String emailRequest(@PathVariable("clientApiKey") String clientApiKey,
                               @RequestParam(value = "subject") String subject,
                               @RequestParam(value = "body") String body,
                               @RequestParam(value = "receiver") String receiver,
                               @RequestParam(value = "cc", required = false) String cc,
                               @RequestParam(value = "bcc", required = false) String bcc) {
        if (clientService.findByApiKey(clientApiKey) == null) {
            json.put(STATUS, ACCESS_DENIED.getStatus());
        } else {
            email = new Email();
            email.setClient(clientService.findByApiKey(clientApiKey));
            email.setSubject(subject);
            email.setBody(body);
            email.setReceiver(receiver);
            email.setCc(cc);
            email.setCc(bcc);
            emailService.save(email);

            job = newJob(EmailSendingJob.class)
                    .withIdentity("job1", "group1")
                    .usingJobData("emailId", email.getId())
                    .build();

            trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule())
                    .build();

            jobProcessor.process(job, trigger);
            json.put(STATUS, JOB_SAVED.getStatus());
            json.put(JOB_IDENTIFIER, email.getId());
        }
        return json.toString();
    }
}