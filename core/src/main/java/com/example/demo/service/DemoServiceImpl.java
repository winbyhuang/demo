package com.example.demo.service;

import com.example.demo.domain.DemoDO;
import com.example.demo.dao.persistence.DemoDAO;
import com.example.demo.dao.persistence2.Demo2DAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import winby.model.BaseResultDTO;
import winby.model.BatchResultDTO;
import winby.model.ResultDTO;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */
//@Component
@Service
public class DemoServiceImpl implements DemoService {
    private static final Logger logger = LogManager.getLogger();
    private static final String SERVICE_NAME = "业务名称";
    @Autowired
    private DemoDAO demoDAO;
    @Autowired
    private Demo2DAO demo2DAO;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDTO<Integer> save(DemoDO entity) {
        ResultDTO<Integer> resultDTO = new ResultDTO<>(BaseResultDTO.SUCCESS);
        try {
            int code = demoDAO.insert(entity);
            if (code <= 0) {
                resultDTO.setSuccess(false);
                resultDTO.setErrorDetail("新建"+SERVICE_NAME+"失败");
//				logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + "新建"+serviceName+"失败");
                return resultDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultDTO.setSuccess(false);
            resultDTO.setErrorDetail("新建"+SERVICE_NAME+"异常");
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + "新建"+SERVICE_NAME+"异常");
        }
        return resultDTO;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDTO<Integer> remove(DemoDO entity) {
        ResultDTO<Integer> resultDTO = new ResultDTO<>(BaseResultDTO.SUCCESS);
        try {
            int code = demoDAO.delete(entity);
            if (code <= 0) {
                resultDTO.setSuccess(false);
                resultDTO.setErrorDetail("删除"+SERVICE_NAME+"失败");
//				logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + "删除"+serviceName+"失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultDTO.setSuccess(false);
            resultDTO.setErrorDetail("删除"+SERVICE_NAME+"异常");
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + "删除"+SERVICE_NAME+"异常");
        }
        return resultDTO;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDTO<Integer> modify(DemoDO entity) {
        ResultDTO<Integer> resultDTO = new ResultDTO<>(BaseResultDTO.SUCCESS);
        try {
            int code = demoDAO.update(entity);
            if (code <= 0) {
                resultDTO.setSuccess(false);
                resultDTO.setErrorDetail("修改"+SERVICE_NAME+"失败");
//				logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + "新建"+serviceName+"失败");
                return resultDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultDTO.setSuccess(false);
            resultDTO.setErrorDetail("修改"+SERVICE_NAME+"异常");
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + "修改"+SERVICE_NAME+"异常");
        }
        return resultDTO;
    }

    @Override
    public ResultDTO<DemoDO> getById(Long id) {
        ResultDTO<DemoDO> resultDTO = new ResultDTO<>(BaseResultDTO.SUCCESS);
        try {
            DemoDO entity = demoDAO.selectById(id);
            if (entity == null) {
                resultDTO.setSuccess(false);
                resultDTO.setErrorDetail("查询"+SERVICE_NAME+"为空");
                return resultDTO;
            }
            resultDTO.setModule(entity);
        } catch (Exception e) {
            e.printStackTrace();
            resultDTO.setSuccess(false);
            resultDTO.setErrorDetail("查询"+SERVICE_NAME+"异常");
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + "查询"+SERVICE_NAME+"异常");
        }
        return resultDTO;
    }

    @Override
    public BatchResultDTO<DemoDO> list(DemoDO entity) {
        BatchResultDTO<DemoDO> batchResultDTO = new BatchResultDTO<>(BaseResultDTO.SUCCESS);
        try {
            List<DemoDO> list= demoDAO.select(entity);
            list.add(demo2DAO.select(entity).get(0));
            if (CollectionUtils.isEmpty(list)) {
                batchResultDTO.setSuccess(false);
                batchResultDTO.setErrorDetail("查询"+SERVICE_NAME+"为空");
                return batchResultDTO;
            }
            batchResultDTO.setModule(list);
        } catch (Exception e) {
            e.printStackTrace();
            batchResultDTO.setSuccess(false);
            batchResultDTO.setErrorDetail("查询"+SERVICE_NAME+"异常");
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + "查询"+SERVICE_NAME+"异常");
        }
        return batchResultDTO;
    }

    @Override
    public ResultDTO<Integer> count(DemoDO entity) {
        ResultDTO<Integer> resultDTO = new ResultDTO<>(BaseResultDTO.SUCCESS);
        try {
            int count = demoDAO.count(entity);
            if (count <= 0) {
                resultDTO.setSuccess(false);
                resultDTO.setErrorDetail("查询"+SERVICE_NAME+"条数为空");
            }
            resultDTO.setModule(count);
        } catch (Exception e) {
            e.printStackTrace();
            resultDTO.setSuccess(false);
            resultDTO.setErrorDetail("查询"+SERVICE_NAME+"条数异常");
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() + ":" + "查询"+SERVICE_NAME+"条数异常");
        }
        return resultDTO;
    }
}
