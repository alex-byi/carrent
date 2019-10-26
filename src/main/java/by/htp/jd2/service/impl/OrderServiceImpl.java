package by.htp.jd2.service.impl;

import java.util.List;

import by.htp.jd2.dao.DaoException;
import by.htp.jd2.dao.DaoProvider;
import by.htp.jd2.dao.impl.SQLOrderDao;
import by.htp.jd2.entity.Order;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.validation.OrderDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderServiceImpl implements OrderService {

    private static final OrderDataValidator validator = OrderDataValidator.getInstance();
    private static final Logger LOG = LogManager.getLogger(OrderServiceImpl.class.getName());

    @Override
    public List<Order> getAllOrders(int page) throws ServiceException {
        SQLOrderDao orderDao = DaoProvider.getInstance().getOrderDao();
        List<Order> list;
        try {
            list = orderDao.getAllOrders(page);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
        if (validator.checkOrdersList(list)) {
            throw new ServiceException("List<Order> no valid");
        }
        return list;
    }

    @Override
    public boolean addNewOrder(Order order) throws ServiceException {
        if (validator.checkOrderInfo(order)) {
            throw new ServiceException("Order data is no valid");
        }
        SQLOrderDao orderDao = DaoProvider.getInstance().getOrderDao();
        try {
            return orderDao.addNewOrder(order);
        } catch (DaoException e) {
            LOG.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> userOrders(int id) throws ServiceException {
        SQLOrderDao orderDao = DaoProvider.getInstance().getOrderDao();
        List<Order> list;
        try {
            list = orderDao.userOrders(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (validator.checkOrdersList(list)) {
            throw new ServiceException("List<Order> no valid");
        }
        return list;
    }

    @Override
    public boolean setAmount(int orderId, int amount) throws ServiceException {
        SQLOrderDao orderDao = DaoProvider.getInstance().getOrderDao();
        try {
            return orderDao.setAmount(orderId, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order getOrderById(int id) throws ServiceException {
        Order order;
        SQLOrderDao orderDao = DaoProvider.getInstance().getOrderDao();
        try {
            order = orderDao.getOrderById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (validator.checkOrderInfo(order)) {
            throw new ServiceException("Order data is no valid");
        }
        return order;
    }

    @Override
    public boolean setPayment(int orderId) throws ServiceException {
        SQLOrderDao orderDao = DaoProvider.getInstance().getOrderDao();
        try {
            return orderDao.setPayment(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean setComplete(int orderId) throws ServiceException {
        SQLOrderDao orderDao = DaoProvider.getInstance().getOrderDao();
        try {
            return orderDao.setComplete(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean setCanceled(int orderId) throws ServiceException {
        SQLOrderDao orderDao = DaoProvider.getInstance().getOrderDao();
        try {
            return orderDao.setCanceled(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean setRejectReason(String reason, int orderId) throws ServiceException {
        SQLOrderDao orderDao = DaoProvider.getInstance().getOrderDao();
        try {
            return orderDao.setRejectReason(reason, orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean setCrash(int orderId, int crashId) throws ServiceException {
        SQLOrderDao orderDao = DaoProvider.getInstance().getOrderDao();
        try {
            return orderDao.setCrash(orderId, crashId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int pageCol() throws ServiceException {
        SQLOrderDao orderDao = DaoProvider.getInstance().getOrderDao();
        int pageCol;
        try {
            pageCol = orderDao.pageCol();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return pageCol;
    }

}
