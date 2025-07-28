package com.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.mapping.MetadataSource;

import com.model.Account;



public class HibarnetUtil {
private static StandardServiceRegistry registry;
private static SessionFactory sf;

public static SessionFactory getSessionFactory()
{
	
	if(sf==null)
	{
		Map<String, Object> m=new HashMap();
		m.put(Environment.DRIVER,"com.mysql.jdbc.Driver");
		m.put(Environment.URL,"jdbc:mysql://localhost:3306/bank_system");
		m.put(Environment.USER,"root");
		m.put(Environment.PASS,"Geeta@1234");
		m.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5InnoDBDialect");
		m.put(Environment.HBM2DDL_AUTO,"update");
		m.put(Environment.SHOW_SQL,"true");
		
		registry=new StandardServiceRegistryBuilder().applySettings(m).build();
		MetadataSources mds=new MetadataSources(registry).addAnnotatedClass(Account.class);
		Metadata md=mds.getMetadataBuilder().build();
		sf=md.getSessionFactoryBuilder().build();
	}
	return sf;
}
}
