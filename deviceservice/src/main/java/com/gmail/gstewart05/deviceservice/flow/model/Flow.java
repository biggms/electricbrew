package com.gmail.gstewart05.deviceservice.flow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gmail.gstewart05.dto.FlowDTO;
import com.gmail.gstewart05.model.DoubleSensor;
import com.gmail.gstewart05.model.NamedEntity;
import com.gmail.gstewart05.model.OnOffSensor;
import com.gmail.gstewart05.model.wrappers.DoubleSensorWrapper;
import com.gmail.gstewart05.model.wrappers.OnOffSensorWrapper;
import com.gmail.gstewart05.service.changelisteners.OnOffSensorListener;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.slf4j.Logger;

import javax.persistence.*;

@Entity
@Accessors( chain = true )
@NoArgsConstructor
@Data
@ToString( callSuper = true )
@Audited
@Slf4j
@EntityListeners( OnOffSensorListener.class )
public class Flow extends NamedEntity implements OnOffSensorWrapper< FlowDTO >
{
	@Embedded
	@Audited
	@JsonIgnore
	OnOffSensor onoffsensor;

	@PrePersist
	public void prePersist()
	{
		if( onoffsensor == null )
		{
			onoffsensor = new OnOffSensor();
		}
	}

	@Builder
	Flow( String id, String name )
	{
		super( id, name );
	}

	@Override
	public OnOffSensor getEmbeddedOnOff()
	{
		return onoffsensor;
	}

	@Override
	public Logger getLog()
	{
		return log;
	}

	@Override
	public Class< FlowDTO > getDTOClass()
	{
		return FlowDTO.class;
	}
}
