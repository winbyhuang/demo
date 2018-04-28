
import com.example.demo.common.constant.SysConstants
import com.example.demo.common.util.GsonUtil
import com.example.demo.common.webSocket.WebSocketUtil
import com.example.demo.domain.DemoDO
import com.example.demo.service.DemoService
import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import winby.exception.GenericException
import winby.model.ResultEntity
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 *
 * @author Winby
 * @date 2018/1/29  17:20
 * @since v1.0.0
 * @update
 */
@RequestMapping("kt")
@RestController
class DemoController {
    @Autowired
    private val demoService: DemoService? = null

    @PostMapping("save.do")
    @Throws(Exception::class)
    internal fun save(request: HttpServletRequest, entity: DemoDO): ResultEntity {
        val result = ResultEntity(ResultEntity.SUCCESS)
        entity.creator = "1233"
        val resultDTO = demoService!!.save(entity)
        if (!resultDTO.isSuccess) {
            throw GenericException(resultDTO.errorDetail)
        }
        log(Thread.currentThread().stackTrace[1].methodName, request.getAttribute(SysConstants.REQUEST_REMARK).toString() + GsonUtil.toJson(result))
        return result
    }

    @PostMapping("delete.do")
    @Throws(Exception::class)
    internal fun delete(request: HttpServletRequest, entity: DemoDO): ResultEntity {
        val result = ResultEntity(ResultEntity.SUCCESS)
        entity.modifier = "1233"
        val resultDTO = demoService!!.remove(entity)
        if (!resultDTO.isSuccess) {
            throw GenericException(resultDTO.errorDetail)
        }
        log(Thread.currentThread().stackTrace[1].methodName, request.getAttribute(SysConstants.REQUEST_REMARK).toString() + GsonUtil.toJson(result))
        return result
    }

    @PostMapping("modify.do")
    @Throws(Exception::class)
    internal fun modify(request: HttpServletRequest, entity: DemoDO): ResultEntity {
        val result = ResultEntity(ResultEntity.SUCCESS)
        entity.modifier = "123"
        val resultDTO = demoService!!.modify(entity)
        if (!resultDTO.isSuccess) {
            throw GenericException(resultDTO.errorDetail)
        }
        log(Thread.currentThread().stackTrace[1].methodName, request.getAttribute(SysConstants.REQUEST_REMARK).toString() + GsonUtil.toJson(result))
        return result
    }

    @PostMapping("getById.do")
    @Throws(Exception::class)
    internal fun getById(request: HttpServletRequest, id: Long?): ResultEntity {
        val result = ResultEntity(ResultEntity.SUCCESS)
        val resultDTO = demoService!!.getById(id)
        result.result = resultDTO.module
        if (!resultDTO.isSuccess) {
            throw GenericException(resultDTO.errorDetail)
        }
        log(Thread.currentThread().stackTrace[1].methodName, request.getAttribute(SysConstants.REQUEST_REMARK).toString() + GsonUtil.toJson(result))
        return result
    }

    @GetMapping("list.do")
    @Throws(Exception::class)
    internal fun list(request: HttpServletRequest, entity: DemoDO): ResultEntity {
        val result = ResultEntity(ResultEntity.SUCCESS)
        val batchResultDTO = demoService!!.list(entity)
        result.result = batchResultDTO.module
        log(Thread.currentThread().stackTrace[1].methodName, request.getAttribute(SysConstants.REQUEST_REMARK).toString() + GsonUtil.toJson(result))
        return result
    }

    @RequestMapping(value = "/a", method = arrayOf(RequestMethod.GET))
    @Throws(IOException::class)
    fun firstResp(session: HttpSession, request: HttpServletRequest, response: HttpServletResponse): Map<String, Any> {
        WebSocketUtil.sendSome("ss", WebSocketUtil.userMap)
        session.setAttribute("aa", "dddd")
        //        RedisUtil.set("ss","11");
        //        System.out.println(RedisUtil.get("ss"));
        val map = HashMap<String, Any>()
        request.session.setAttribute("requestUrl", request.requestURL)
        map.put("sessionId", request.session.id)
        map.put("requestUrl", request.requestURL)
        map.put("aa", session.getAttribute("aa"))
        return map
    }

    @RequestMapping(value = "/b", method = arrayOf(RequestMethod.GET))
    fun sessions(session: HttpSession, request: HttpServletRequest): Any {
        val map = HashMap<String, Any>()
        map.put("sessionId", request.session.id)
        map.put("message", request.session.getAttribute("requestUrl"))
        map.put("aa", session.getAttribute("aa"))
        return map
    }

    internal fun log(methodName: String, result: String) {
        logger.info("{}:{}", methodName, result)
    }

    companion object {
        private val logger = LogManager.getLogger()
    }
}