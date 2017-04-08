package com.ciro;

import com.ciro.view.ProyectoView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EjemploApplication extends AbstractJavaFxApplicationSupport{

	public static void main(String[] args) {
		launchApp(EjemploApplication.class, ProyectoView.class,args);
	}
}
