package by.htp.jd2.config;

import by.htp.jd2.command.CommandHelper;
import by.htp.jd2.dao.CarDAO;
import by.htp.jd2.dao.CrashDao;
import by.htp.jd2.dao.OrderDao;
import by.htp.jd2.dao.UserDao;
import by.htp.jd2.dao.impl.SQLCarDao;
import by.htp.jd2.dao.impl.SQLOrderDao;
import by.htp.jd2.dao.impl.SQLUserDao;
import by.htp.jd2.dao.impl.SqlCrashDao;
import by.htp.jd2.entity.Crash;
import by.htp.jd2.service.CarService;
import by.htp.jd2.service.CrashService;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.UserService;
import by.htp.jd2.service.impl.CarServiceImpl;
import by.htp.jd2.service.impl.CrashServiceImpl;
import by.htp.jd2.service.impl.OrderServiceImpl;
import by.htp.jd2.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@ComponentScan(basePackages = "by.htp.jd2" )
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserDao userDao() {
        return new SQLUserDao();
    }

    @Bean
    public CarService carService(){
        return new CarServiceImpl();
    }

    @Bean
    public CarDAO carDAO(){
        return new SQLCarDao();
    }

    @Bean
    public CrashService crashService(){
        return new CrashServiceImpl();
    }

    @Bean
    public CrashDao crashDao(){
        return new SqlCrashDao();
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl();
    }

    @Bean
    public OrderDao orderDao(){
        return new  SQLOrderDao();
    }

//    @Bean
//    public CommandHelper commandHelper() {
//        return new CommandHelper();
//    }

}
