package three.AppThree;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class AppThreeApplication {

	private static final String QUEUE_NAME = "newSQS" +
			new Date().getTime();

	public static void main(String[] args) {
		SpringApplication.run(AppThreeApplication.class, args);


		MensajesColas colas = new MensajesColas();

		final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		boolean flag = true;
		List<String> messagelist = new ArrayList<>();
		while (flag) {
			ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest("https://sqs.us-east-1.amazonaws.com/159205842436/newSQS");
			receiveMessageRequest.setMaxNumberOfMessages(10);
			receiveMessageRequest.setWaitTimeSeconds(20);
			List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();

			for (Message message : messages) {
				System.out.println( message.getBody());
				//messagelist.add( message.getBody());


			}
			if (messages.size() == 0) {
				flag = false;
			}
		}
	}
}
