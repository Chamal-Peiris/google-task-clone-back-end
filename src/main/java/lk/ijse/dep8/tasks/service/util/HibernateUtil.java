package lk.ijse.dep8.tasks.service.util;

import lk.ijse.dep8.tasks.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.id.UUIDGenerator;

public class HibernateUtil {
   private static final SessionFactory sessionFactory = buildSessionFactory();

   private static SessionFactory buildSessionFactory() {

      String profile=System.getProperty("app.profiles.active","dev");
      StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
              .loadProperties(profile.equals("dev")?"application-dev.properties":"application-prod.properties")
              .build();

      Metadata metadata = new MetadataSources(standardRegistry).addAnnotatedClass(User.class)
              .getMetadataBuilder()
              .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
              .build();

      return metadata.getSessionFactoryBuilder()
              .build();
   }

   public static SessionFactory getSessionFactory() {
      return sessionFactory;
   }
}
