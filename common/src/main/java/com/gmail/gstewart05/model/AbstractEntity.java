package com.gmail.gstewart05.model;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
@Data
@NoArgsConstructor
public abstract class AbstractEntity
{
	@Id
	@Column( nullable = false )
	@GeneratedValue( generator = "uuid" )
	@GenericGenerator( name = "uuid", strategy = "uuid2" )
	String id;

	protected AbstractEntity( String id )
	{
		setId( id );
	}
}