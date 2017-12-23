package com.gmail.gstewart05.deviceservice.valve.mq;

import com.gmail.gstewart05.dto.ValveDTO;
import com.gmail.gstewart05.deviceservice.valve.service.ValveChangeService;
import com.gmail.gstewart05.deviceservice.valve.service.ValveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
public class ValveReceiver
{
	@Autowired
	ValveChangeService theValveChangeService;

	@Autowired
	ValveService theValveService;

	@Transactional
	@RabbitListener( bindings = @QueueBinding(
			value = @Queue( autoDelete = "true", durable = "false" ),
			exchange = @Exchange( value = "amq.topic", type = "topic", durable = "true" ),
			key = "valve.v1.statechange"
	) )
	public void receiveChange( @Payload ValveDTO pValveDTO, @Header( AmqpHeaders.RECEIVED_ROUTING_KEY ) String pKey ) throws InterruptedException
	{
		theValveChangeService.handleNewChange( pValveDTO );
	}

	@Transactional
	@RabbitListener( bindings = @QueueBinding(
			value = @Queue( autoDelete = "true", durable = "false" ),
			exchange = @Exchange( value = "amq.topic", type = "topic", durable = "true" ),
			key = "valve.v1.setstate"
	) )
	public void receiveChangeRequest( @Payload ValveDTO pValveDTO, @Header( AmqpHeaders.RECEIVED_ROUTING_KEY ) String pKey ) throws InterruptedException
	{
		theValveService.handleChangeRequest( pValveDTO );
	}
}
