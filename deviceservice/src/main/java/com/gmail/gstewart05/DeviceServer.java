package com.gmail.gstewart05;

import com.gmail.gstewart05.deviceservice.cooler.model.devices.Cooler;
import com.gmail.gstewart05.deviceservice.cooler.service.CoolerService;
import com.gmail.gstewart05.deviceservice.heater.model.devices.Heater;
import com.gmail.gstewart05.deviceservice.heater.service.HeaterService;
import com.gmail.gstewart05.deviceservice.temperature.model.devices.TemperatureProbe;
import com.gmail.gstewart05.deviceservice.temperature.service.TemperatureProbeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
@Slf4j
public class DeviceServer
{
	public static void main( String[] args )
	{
		SpringApplication.run( DeviceServer.class, args );
	}

	@Bean
	public CommandLineRunner demo( TemperatureProbeService pTemperatureProbeService, HeaterService pHeaterService, CoolerService pCoolerService )
	{
		return ( args ) ->
		{
			TemperatureProbe lTemperatureProbe = TemperatureProbe.builder().name( "Cold Water" ).mac( "28ff220b00150208" ).build();
			pTemperatureProbeService.save( lTemperatureProbe );

			lTemperatureProbe = TemperatureProbe.builder().name( "Warm Water" ).mac( "28ff6a02641403ed" ).build();
			pTemperatureProbeService.save( lTemperatureProbe );

			lTemperatureProbe = TemperatureProbe.builder().name( "Fermenter" ).mac( "28ff983d6414031a" ).build();
			pTemperatureProbeService.save( lTemperatureProbe );

			Heater lHeater = Heater.builder().name( "Warm Water" ).build();
			pHeaterService.save( lHeater );

			lHeater = Heater.builder().name( "Boiler 1" ).build();
			pHeaterService.save( lHeater );

			lHeater = Heater.builder().name( "Boiler 2" ).build();
			pHeaterService.save( lHeater );

			Cooler lCooler = Cooler.builder().name( "Freezer" ).build();
			pCoolerService.save( lCooler );
		};
	}
}