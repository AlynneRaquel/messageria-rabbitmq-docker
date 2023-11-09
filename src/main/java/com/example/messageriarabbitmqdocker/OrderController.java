package com.example.messageriarabbitmqdocker;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/orders")
public class OrderController {
	
	@Autowired
	private OrderRepository orders;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostMapping
	public Order create(@RequestBody Order order) {
		orders.save(order);
		String routingKey = "orders.v1.order-created"; //fila usada
		//Message message = new Message(order.getId().toString().getBytes()); //mensagem passada a fila
		//rabbitTemplate.send(routingKey, message ); //enviando as mensagens para a fila
		//rabbitTemplate.convertAndSend(routingKey, order.getId() ); //uma opção para converter no envio e recebimento
		OrderCreatedEvent event = new OrderCreatedEvent(order.getId(), order.getValue());
		rabbitTemplate.convertAndSend(routingKey, event ); 
		return order;
	}
	
	

}
