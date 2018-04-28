package com.example.demo.controller;

import com.example.demo.common.constant.SysConstants;
import com.example.demo.common.util.GsonUtil;
import com.example.demo.common.webSocket.WebSocketUtil;
import com.example.demo.domain.DemoDO;
import com.example.demo.service.DemoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import winby.exception.GenericException;
import winby.model.BatchResultDTO;
import winby.model.ResultDTO;
import winby.model.ResultEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/13.
 */
@RequestMapping("/common/demoManager/")
@RestController
public class DemoController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private DemoService demoService;

    @PostMapping("save.do")
    ResultEntity save(HttpServletRequest request, DemoDO entity) throws Exception {
        ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
        entity.setCreator("1233");
        ResultDTO<Integer> resultDTO = demoService.save(entity);
        if (!resultDTO.isSuccess()) {
            throw new GenericException(resultDTO.getErrorDetail());
        }
        log(Thread.currentThread().getStackTrace()[1].getMethodName(), request.getAttribute(SysConstants.REQUEST_REMARK) + GsonUtil.toJson(result));
        return result;
    }

    @PostMapping("delete.do")
    ResultEntity delete(HttpServletRequest request, DemoDO entity) {
        ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
        entity.setModifier("1233");
        ResultDTO<Integer> resultDTO = demoService.remove(entity);
        if (!resultDTO.isSuccess()) {
            throw new GenericException(resultDTO.getErrorDetail());
        }
        log(Thread.currentThread().getStackTrace()[1].getMethodName(), request.getAttribute(SysConstants.REQUEST_REMARK) + GsonUtil.toJson(result));
        return result;
    }

    @PostMapping("modify.do")
    ResultEntity modify(HttpServletRequest request, DemoDO entity) {
        ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
        entity.setModifier("123");
        ResultDTO<Integer> resultDTO = demoService.modify(entity);
        if (!resultDTO.isSuccess()) {
            throw new GenericException(resultDTO.getErrorDetail());
        }
        log(Thread.currentThread().getStackTrace()[1].getMethodName(), request.getAttribute(SysConstants.REQUEST_REMARK) + GsonUtil.toJson(result));
        return result;
    }

    @PostMapping("getById.do")
    ResultEntity getById(HttpServletRequest request, Long id) {
        ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
        ResultDTO<DemoDO> resultDTO = demoService.getById(id);
        result.setResult(resultDTO.getModule());
        if (!resultDTO.isSuccess()) {
            throw new GenericException(resultDTO.getErrorDetail());
        }
        log(Thread.currentThread().getStackTrace()[1].getMethodName(), request.getAttribute(SysConstants.REQUEST_REMARK) + GsonUtil.toJson(result));
        return result;
    }

    @GetMapping("list.do")
    ResultEntity list(HttpServletRequest request, DemoDO entity) {
        ResultEntity result = new ResultEntity(ResultEntity.SUCCESS);
        BatchResultDTO<DemoDO> batchResultDTO = demoService.list(entity);
        result.setResult(batchResultDTO.getModule());
        log(Thread.currentThread().getStackTrace()[1].getMethodName(), request.getAttribute(SysConstants.REQUEST_REMARK) + GsonUtil.toJson(result));
        return result;
    }

    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public Map<String, Object> firstResp(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebSocketUtil.sendSome("ss", WebSocketUtil.userMap);
        session.setAttribute("aa", "dddd");
//        RedisUtil.set("ss","11");
//        System.out.println(RedisUtil.get("ss"));
        Map<String, Object> map = new HashMap<String, Object>();
        request.getSession().setAttribute("requestUrl", request.getRequestURL());
        map.put("sessionId", request.getSession().getId());
        map.put("requestUrl", request.getRequestURL());
        map.put("aa", session.getAttribute("aa"));
        return map;
    }

    @RequestMapping(value = "/b", method = RequestMethod.GET)
    public Object sessions(HttpSession session, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("requestUrl"));
        map.put("aa", session.getAttribute("aa"));
        return map;
    }

    final void log(String methodName, String result) {
        logger.info("{}:{}", methodName, result);
    }
}

