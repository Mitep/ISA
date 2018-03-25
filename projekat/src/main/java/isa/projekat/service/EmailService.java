package isa.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import isa.projekat.model.User;



@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	/*
	 * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
	 */
	@Autowired
	private Environment env;

	public void sendNotificaitionSync(User user) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
		Thread.sleep(100);
		System.out.println("Slanje emaila...");
		
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Verifikacija naloga");
		System.out.println("DAJ MI MAIL" + mail);
		mail.setText("Pozdrav " + user.getUserName() + ",\n\nhvala što pratiš ISA.");
		javaMailSender.send(mail);
		System.out.println(mail);
		System.out.println("Email poslat!");
	}

}
