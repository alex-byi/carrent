package by.htp.jd2.command;

import java.util.HashMap;
import java.util.Map;

import by.htp.jd2.command.impl.*;

/**
 * @author alexey
 */
public class CommandHelper {

    private static final CommandHelper instance = new CommandHelper();
    private Map<CommandName, Command> commands = new HashMap<>();

    public CommandHelper() {

        commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.LOG_OUT, new LogOutCommand());
        commands.put(CommandName.USER_PAGE, new UserPageCommand());
        commands.put(CommandName.ALL_USERS, new ControlUsersPageCommand());
        commands.put(CommandName.ADD_CAR, new AddCarCommand());
        commands.put(CommandName.ADD_CAR_PAGE, new AddCarPageCommand());
        commands.put(CommandName.DEL_CAR, new DelCarCommand());
        commands.put(CommandName.DEL_USER, new DelUserCommand());
        commands.put(CommandName.CONTROL_CAR_PAGE, new ControlCarPageCommand());
        commands.put(CommandName.ORDER_PAGE, new OrderPageCommand());
        commands.put(CommandName.CRASH_PAGE_ADMIN, new CrashPageAdminCommand());
        commands.put(CommandName.ORDER_PAGE_USER, new OrderPageUserCommand());
        commands.put(CommandName.ORDER_PAGE_STEP2_USER, new OrderPageStep2UserCommand());
        commands.put(CommandName.ORDER_PAGE_STEP3_USER, new OrderPageStep3UserCommand());
        commands.put(CommandName.USER_ORDERS_PAGE, new UserOrdersPageCommand());
        commands.put(CommandName.CONFIRM_ORDER, new ConfirmOrderCommand());
        commands.put(CommandName.PAY, new UserPayCommand());
        commands.put(CommandName.NEW_CRASH_ORDER_PAGE, new NewCrashBillPageCommand());
        commands.put(CommandName.ACTIVATE_CAR, new ActivateCarCommand());
        commands.put(CommandName.ACTIVATE_USER, new ActivateUserCommand());
        commands.put(CommandName.ADD_MONEY, new AddMoneyCommand());
        commands.put(CommandName.COMPLETE_ORDER, new OrderCompleteCommand());
        commands.put(CommandName.CANCEL_ORDER, new OrderCancelCommand());
        commands.put(CommandName.SET_LANGUAGE, new LanguageCommand());
        commands.put(CommandName.ADD_CRASH, new AddCrashCommand());
        commands.put(CommandName.USER_CRASH_PAGE, new UserCrashPageCommand());
        commands.put(CommandName.USER_CRASH_PAY, new UserCrashPayCommand());
        commands.put(CommandName.GET_TRANSMISSION_CARS, new GetTransmissionCarCommand());
        commands.put(CommandName.GET_FUEL_CARS, new GetFuelCarsCommand());
        commands.put(CommandName.USER_ADD_FUNDS_PAGE, new UserAddCashCommand());
        commands.put(CommandName.USER_ADD_MONEY, new AddMoneyUserCommand());

    }

    public static CommandHelper getInstance() {

        return instance;
    }

    public Command getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        Command command;
        if (null != name) {
            command = commands.get(name);
        } else {
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return command;
    }
}