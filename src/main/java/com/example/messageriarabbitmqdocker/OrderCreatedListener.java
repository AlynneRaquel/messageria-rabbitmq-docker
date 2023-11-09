package com.example.messageriarabbitmqdocker;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {
	
	@RabbitListener(queues = "orders.v1.order-created")
	public void onOrderCreated(OrderCreatedEvent event) {
		System.out.println("Id recebido " + event.getId());
		System.out.println("Valor recebido " + event.getValue());
		
	}

}
